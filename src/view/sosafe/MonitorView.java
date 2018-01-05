package view.sosafe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.sosafe.system.Customer;
import model.sosafe.system.sensors.MotionSensor;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.system.sensors.TempSensor;
import model.sosafe.util.ImagePanel;
import model.sosafe.util.SoSafeConstants;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * Monitor View to simulate Breakin Fire on selected sensors.
 */
public class MonitorView extends JPanel {

	public JPanel panel, panel1, roompanel[], panel2, firepanel, panel3;
	public JLabel label, sprinklerlabel;
	public JCheckBox checkbox[];
	public String[] sensorComboArr;
	public Customer customer;
	public ImagePanel instrusionPanel[];
	public ImagePanel firePanel[];
	public JButton simulateButton, breakInButton, fireButton, stopSimulationButton, eventsButton, billButton, stop,
			disable;

	public MonitorView(Customer customer) {

		this.customer = customer;
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel.setPreferredSize(new Dimension(1500, 1000));

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		label = new JLabel("Monitoring Rooms & Simulation");
		label.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		label.setBackground(Color.BLUE);
		this.add(panel);
		setSize(1500, 1000);
		setVisible(true);

		breakInButton = new JButton("Break In");
		fireButton = new JButton("Fire");
		simulateButton = new JButton("Simulate");
		stopSimulationButton = new JButton("Stop Simulation");
		stop = new JButton("Stop");

		panel3 = new JPanel();
		panel3.setBackground(Color.cyan);
		panel3.setPreferredSize(new Dimension(1000, 100));
		ImagePanel sprinkler = new ImagePanel(new ImageIcon("res/firehead.jpg").getImage());
		panel3.add(sprinkler);
		sprinklerlabel = new JLabel("Sprinklers turned ON");
		sprinklerlabel.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		sprinklerlabel.setBackground(Color.BLACK);
		panel3.add(sprinklerlabel);

		panel.add(label);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel3.setVisible(false);
		firepanel = new JPanel();
		panel2.add(breakInButton);
		panel2.add(fireButton);
		panel2.add(simulateButton);
		panel2.add(stopSimulationButton);
		panel2.add(stop);

		List<Sensor> simulationSensorList = new ArrayList<Sensor>();

		try {

			sensorComboArr = SoSafeUtil.loadSensorInfoFromFile(SoSafeConstants.sensorScheduleFile, customer);
			checkbox = new JCheckBox[sensorComboArr.length];
			roompanel = new JPanel[sensorComboArr.length];
			instrusionPanel = new ImagePanel[sensorComboArr.length];
			firePanel = new ImagePanel[sensorComboArr.length];
			for (int i = 0; i < sensorComboArr.length; i++) {

				checkbox[i] = new JCheckBox(sensorComboArr[i]);
				checkbox[i].setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
				roompanel[i] = new JPanel();
				roompanel[i].setOpaque(true);
				roompanel[i].setPreferredSize(new Dimension(400, 200));
				roompanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				roompanel[i].add(checkbox[i]);
				panel1.add(roompanel[i]);
				instrusionPanel[i] = new ImagePanel(new ImageIcon("res/intrusion.jpg").getImage());
				firePanel[i] = new ImagePanel(new ImageIcon("res/fire.jpg").getImage());
			}
		}

		catch (Exception e) {
			System.out.println("File not Found");

		}

		simulateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkbox.length; i++) {
					if (checkbox[i].isSelected()) {
						System.out.println("Checkbox:" + i + " selected");
						String selectedSensr = checkbox[i].getText();
						Sensor selectedSensor = SoSafeUtil.getSensorObjFromStr(selectedSensr, customer);
						System.out.println(selectedSensor.getLocation() + ":" + selectedSensor.isSimulationStop());
						// if(selectedSensor.isSimulationStop()) {
						selectedSensor.setSimulationStop(false);
						selectedSensor.activateForSimulation();
						selectedSensor.setCustomer(customer);
						simulationSensorList.add(selectedSensor);
						// }
					}
				}
			}
		});

		stopSimulationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < checkbox.length; j++) {
					if (checkbox[j].isSelected()) {
						String[] srcoderoom = checkbox[j].getText().split("-");
						String selectedsensorCode = srcoderoom[0];
						String selectedroomName = srcoderoom[1];

						for (int i = 0; i < simulationSensorList.size(); i++) {
							if (simulationSensorList.get(i).getSensorCode().equals(selectedsensorCode)) {
								if (simulationSensorList.get(i).getLocation().equals(selectedroomName)) {
									Sensor selectedSensor = simulationSensorList.get(i);
									selectedSensor.setSimulationStop(true);
									if (selectedSensor instanceof MotionSensor) {
										instrusionPanel[j].setVisible(false);
										instrusionPanel[j].repaint();
										roompanel[j].repaint();
									}
									if (selectedSensor instanceof TempSensor) {
										firePanel[j].setVisible(false);
										firePanel[j].repaint();
										roompanel[j].repaint();
										panel3.setVisible(false);
									}
									simulationSensorList.remove(i);
								}
							}
						}
					}
				}
			}
		});

		breakInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int j = 0; j < checkbox.length; j++) {
					if (checkbox[j].isSelected()) {
						String[] srcoderoom = checkbox[j].getText().split("-");
						String selectedsensorCode = srcoderoom[0];
						String selectedroomName = srcoderoom[1];

						for (int i = 0; i < simulationSensorList.size(); i++) {
							if (simulationSensorList.get(i).getSensorCode().equals(selectedsensorCode)) {
								if (simulationSensorList.get(i).getLocation().equals(selectedroomName)) {
									Sensor selectedSensor = simulationSensorList.get(i);

									if (selectedSensor instanceof MotionSensor) {
										if (selectedSensor.isCurrentTimeBetween()) {
											roompanel[j].add(instrusionPanel[j]);
											roompanel[j].repaint();
											instrusionPanel[j].setVisible(true);

											if (selectedSensor.isEnabled() && !selectedSensor.isSimulationStop())
												selectedSensor.setBreakIn(true);
										}
									} else {
										JFrame parent = new JFrame();
										JOptionPane.showMessageDialog(parent, "Wrong Sensor type, no alarm");
									}

								}
							}
						}
					}
				}
			}
		});

		fireButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int j = 0; j < checkbox.length; j++) {
					if (checkbox[j].isSelected()) {
						String[] srcoderoom = checkbox[j].getText().split("-");
						String selectedsensorCode = srcoderoom[0];
						String selectedroomName = srcoderoom[1];

						for (int i = 0; i < simulationSensorList.size(); i++) {
							if (simulationSensorList.get(i).getSensorCode().equals(selectedsensorCode)) {
								if (simulationSensorList.get(i).getLocation().equals(selectedroomName)) {
									Sensor selectedSensor = simulationSensorList.get(i);

									if (selectedSensor instanceof TempSensor) {
										if (selectedSensor.isCurrentTimeBetween()) {
											roompanel[j].add(firePanel[j]);
											roompanel[j].repaint();
											firePanel[j].setVisible(true);
											if (selectedSensor.isEnabled() && !selectedSensor.isSimulationStop()) {
												panel3.setVisible(true);

												selectedSensor.setFire(true);
											}
										}
									} else {
										JFrame parent = new JFrame();
										JOptionPane.showMessageDialog(parent, "Wrong Sensor type, no alarm");
									}
								}
							}
						}
					}
				}
			}
		});

		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < checkbox.length; j++) {
					String[] srcoderoom = checkbox[j].getText().split("-");
					String selectedsensorCode = srcoderoom[0];
					String selectedroomName = srcoderoom[1];

					for (int i = 0; i < simulationSensorList.size(); i++) {
						if (simulationSensorList.get(i).getSensorCode().equals(selectedsensorCode)) {
							if (simulationSensorList.get(i).getLocation().equals(selectedroomName)) {
								Sensor selectedSensor = simulationSensorList.get(i);

								if (selectedSensor instanceof TempSensor) {
									firePanel[j].setVisible(false);
									firePanel[j].repaint();
									panel3.setVisible(false);
								}
								if (selectedSensor instanceof MotionSensor) {
									instrusionPanel[j].setVisible(false);
									instrusionPanel[j].repaint();
								}
								roompanel[j].repaint();
							}
						}
					}
				}
			}

		});

	}
}
