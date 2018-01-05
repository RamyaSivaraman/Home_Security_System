package view.sosafe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.sosafe.system.Customer;
import model.sosafe.system.ScheduleType;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.util.SoSafeConstants;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 *  View to update sensor schedules and disarm system
 */
public class SheduleView extends JPanel {

	public JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7;
	public JLabel label1, label2, label3;
	public JComboBox sensorComboBox, fromTimeCmb, toTimeCmb;
	// public JCheckBox autoCheckbox,manualCheckbox;
	public JRadioButton on, off, autoSchedule, manualSchedule;

	public JButton savebutton, enablesystem, disarmsystem;
	private java.util.List<String> sensorslistfromfile;
	Customer customer;

	public SheduleView(Customer customer) {

		this.customer = customer;

		// Container container = getContentPane();
		// container.setLayout( new FlowLayout() );

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1400, 900));
		// this.setBorder(BorderFactory.createLineBorder(Color.RED));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// container.add(panel);
		this.add(panel);

		try {
			String[] sensorComboArr = SoSafeUtil.loadSensorInfoFromFile(SoSafeConstants.sensorScheduleFile, customer);
			sensorComboBox = new JComboBox(sensorComboArr);
		} catch (Exception e) {
			System.out.println("File not Found");

		}

		fromTimeCmb = new JComboBox(loadTimeCombo());
		toTimeCmb = new JComboBox(loadTimeCombo());

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

		label2 = new JLabel("FROM TIME");
		label3 = new JLabel("TO TIME");

		panel4.add(label2);
		panel4.add(fromTimeCmb);

		panel5.add(label3);
		panel5.add(toTimeCmb);

		// autoCheckbox = new JCheckBox("Automatic Schedule");
		// manualCheckbox = new JCheckBox("Manual Schedule");

		ButtonGroup enableGroup = new ButtonGroup();
		on = new JRadioButton("ON");
		off = new JRadioButton("OFF");
		enableGroup.add(on);
		enableGroup.add(off);

		ButtonGroup scheduleGroup = new ButtonGroup();

		autoSchedule = new JRadioButton("Auto Schedule");
		manualSchedule = new JRadioButton("Manul Schedule");
		scheduleGroup.add(autoSchedule);
		scheduleGroup.add(manualSchedule);

		on.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
		off.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));

		panel6.add(on);
		panel6.add(off);
		autoSchedule.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
		manualSchedule.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));

		// autoCheckbox.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
		// manualCheckbox.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
		panel2.add(autoSchedule);
		panel2.add(manualSchedule);

		panel3.add(panel4);
		panel3.add(panel5);
		label1 = new JLabel("Choose Schedule for Sensors");
		label1.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		// label.setBackground(Color.BLUE);
		label1.setForeground(Color.BLACK);

		savebutton = new JButton("save");
		disarmsystem = new JButton("Disarm System");

		// nextbutton = new JButton("monitor");

		// breakInButton = new JButton ("Break In");
		// simulateButton = new JButton ("Simulate");
		// stopSimulationButton = new JButton ("Stop Simulation");
		panel7.add(savebutton);
		panel7.add(disarmsystem);
		// panel7.add(nextbutton);
		// panel7.add(breakInButton);
		// panel7.add(simulateButton);
		// panel7.add(stopSimulationButton);

		panel.add(label1);
		panel.add(panel1);
		panel.add(panel6);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel7);

		panel1.add(sensorComboBox);

		String selectedSensorStr = (String) sensorComboBox.getSelectedItem();

		loadSensorInfoFromSenstr(selectedSensorStr);
		disarmsystem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame parent = new JFrame();
				String name = JOptionPane.showInputDialog(parent, "Give password", null);
				while (true) {
					if (name.equals(customer.getPassword())) {

						for (int i = 0; i < sensorComboBox.getItemCount(); i++) {
							String selectedSensr = (String) sensorComboBox.getItemAt(i);
							Sensor selectedSensor = SoSafeUtil.getSensorObjFromStr(selectedSensr, customer);
							selectedSensor.setEnabled(false);
							SoSafeUtil.updateSensorSchedule(selectedSensor);
						}
						sensorComboBox.setSelectedIndex(0);
						break;
					}

					else {
						name = JOptionPane.showInputDialog(parent, " Wrong Password! Enter correct password", null);
					}
				}
			}
		});

		setSize(1500, 1000);
		setVisible(true);

		sensorComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedSensorStr = (String) sensorComboBox.getSelectedItem();

				loadSensorInfoFromSenstr(selectedSensorStr);
			}
		});

		savebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame parent = new JFrame();
				String name = JOptionPane.showInputDialog(parent, "Give password", null);

				while (true) {

					if (name.equals(customer.getPassword())) {
						String selectedSensr = (String) sensorComboBox.getSelectedItem();
						Sensor selectedSensor = SoSafeUtil.getSensorObjFromStr(selectedSensr, customer);
						if (off.isSelected()) {
							selectedSensor.setEnabled(false);
						}

						if (on.isSelected()) {
							selectedSensor.setEnabled(true);
						}

						if (autoSchedule.isSelected()) {
							selectedSensor.setScheduleType(ScheduleType.AUTO);
							if (!(((String) fromTimeCmb.getSelectedItem()).equals("00"))) {
								selectedSensor.setFrom_Time(fromTimeCmb.getSelectedItem() + "");
							}

							if (!(((String) toTimeCmb.getSelectedItem()).equals("00"))) {
								selectedSensor.setTo_Time(toTimeCmb.getSelectedItem() + "");
							}

						}

						if (manualSchedule.isSelected()) {
							selectedSensor.setScheduleType(ScheduleType.MANUAL);
							selectedSensor.setFrom_Time(SoSafeConstants.defaultTimeStr);
							selectedSensor.setTo_Time(SoSafeConstants.defaultTimeEndStr);
						}

						SoSafeUtil.updateSensorSchedule(selectedSensor);
						break;
					}

					else {
						name = JOptionPane.showInputDialog(parent, " Wrong Password! Enter correct password", null);
					}
				}
			}
		});

	}

	public void loadSensorInfoFromSenstr(String selectedSensorStr) {

		Sensor selectedSensor = SoSafeUtil.getSensorObjFromStr(selectedSensorStr, customer);

		if (selectedSensor != null) {

			if (selectedSensor.isEnabled()) {
				on.setSelected(true);
			} else {
				off.setSelected(true);
			}

			if (selectedSensor.getScheduleType().equals(ScheduleType.AUTO)) {
				autoSchedule.setSelected(true);
			} else {
				manualSchedule.setSelected(true);
			}

			if (selectedSensor.getFrom_Time() != null) {
				fromTimeCmb.setSelectedItem(selectedSensor.getFrom_Time());
			}
			if (selectedSensor.getTo_Time() != null) {
				toTimeCmb.setSelectedItem(selectedSensor.getTo_Time());
			}

		}

	}

	public String[] loadTimeCombo() {
		List<String> timeStrings = new ArrayList<String>();
		for (int i = 0; i <= 23; i++) {

			if (i < 10) {

				timeStrings.add("0" + i + ":00");
			} else {
				timeStrings.add(i + "" + ":00");
			}
		}

		String[] timeStrArr = timeStrings.toArray((new String[] {}));
		return timeStrArr;
	}

}
