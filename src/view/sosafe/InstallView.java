package view.sosafe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.sosafe.system.Customer;
import model.sosafe.system.Room;
import model.sosafe.system.ScheduleType;
import model.sosafe.system.sensors.MotionSensor;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.system.sensors.TempSensor;
import model.sosafe.util.SoSafeConstants;
import model.sosafe.util.SoSafeUtil;
import view.sosafe.SheduleView;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Install view which allows customer to install sensors to selected rooms.
 */
public class InstallView extends JPanel {
	private static final long serialVersionUID = 1L;
	public JPanel panel1, panel2, panel3, panel4, panel5;
	public JCheckBox mCheckbox, tCheckbox;
	public JLabel label, label2, label3;
	public JTextField roomNameField;
	public JButton button;
	private JComboBox roomNameComboBox;
	public JTable table;
	public int count = 0;
	Customer customer;

	public InstallView(Customer customer) {
		this.customer = customer;
		this.setPreferredSize(new Dimension(1300, 800));
		// this.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(50, 50));
		// panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// panel2 = new JPanel();
		// panel2.setPreferredSize(new Dimension(20,20));
		// panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(300, 300));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		// panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel1.add(panel3);

		mCheckbox = new JCheckBox("Motion Sensor");
		tCheckbox = new JCheckBox("Temp Sensor");
		mCheckbox.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));
		tCheckbox.setFont(new java.awt.Font("Arial", Font.PLAIN, 20));

		label3 = new JLabel("Please Choose Rooms and Sensor Types for Installation");
		label3.setFont(new java.awt.Font("Arial", Font.ITALIC, 40));
		// label.setBackground(Color.BLUE);
		label3.setForeground(Color.BLACK);

		label = new JLabel("Select Sensor");
		label.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		// label.setBackground(Color.BLUE);

		label.setForeground(Color.BLUE);

		label2 = new JLabel("Room Name.");
		label2.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		// label2.setBackground(Color.BLUE);
		label2.setForeground(Color.BLUE);
		String[] roomNames = SoSafeUtil.loadRoomInfoFromFile("res/Rooms.txt");
		roomNameComboBox = new JComboBox(roomNames);
		// roomNameField = new JTextField(20);
		// Field.setSize(200,10);
		button = new JButton("Save");
		button.setPreferredSize(new Dimension(200, 50));
		// button3 = new JButton("Delete");
		// button.setPreferredSize(new Dimension(200, 50));

		// button2 = new JButton("Next");
		// button2.setPreferredSize(new Dimension(200, 50));
		panel4 = new JPanel();

		this.add(label3);
		panel3.add(label);

		panel3.add(mCheckbox);
		panel3.add(tCheckbox);

		panel4.add(label2);
		panel4.add(roomNameComboBox);
		// panel4.add(roomNameField);
		panel3.add(panel4);

		panel5 = new JPanel();
		// panel5.setPreferredSize(new Dimension(300,300));
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(button);
		// panel5.add(button3);
		// panel5.add(button2);

		panel3.add(panel5);

		String[] columns = { "Room", "Sensor Type", "Sensor Code" };
		Object[][] data = {};

		DefaultTableModel model = new DefaultTableModel(data, columns);
		SoSafeUtil.populateTableDataFromSensorSched(model, customer);
		// model.setColumnIdentifiers(columns);
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();

		/*
		 * DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		 * renderer.setPreferredSize(new Dimension(0, 0));
		 * table.getTableHeader().setDefaultRenderer(renderer);
		 */
		// table.setModel(model);
		// table.setBackground(Color.GRAY);
		// table.setForeground(Color.BLACK);
		Font font = new Font("", 20, 22);
		table.setFont(font);
		table.setRowHeight(30);

		// panel2.add(table);

		// panel1.setBackground(Color.GRAY);
		this.add(BorderLayout.NORTH, panel1);
		this.add(header, BorderLayout.NORTH);
		this.add(table);

		// this.add(button2);

		Object[] row = new Object[3];

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < customer.getBldg().getRooms().size(); i++) {
					Room nRoom = customer.getBldg().getRooms().get(i);
					if (nRoom.getName().equals(roomNameComboBox.getSelectedItem().toString())) {
						List<Sensor> snsrList = new ArrayList<Sensor>();
						if (mCheckbox.isSelected() && !tCheckbox.isSelected()) {
							if (nRoom.getmSensor() == null) {
								MotionSensor motionSr = new MotionSensor();
								motionSr.setSensorCode(SoSafeUtil.getSensorCode());
								motionSr.setEnabled(true);
								motionSr.setSimulationStop(true);
								motionSr.setScheduleType(ScheduleType.MANUAL);
								motionSr.setFrom_Time(SoSafeConstants.defaultTimeStr);
								motionSr.setTo_Time(SoSafeConstants.defaultTimeEndStr);
								motionSr.setUsedCount(0);
								motionSr.setUserName(customer.getName());
								motionSr.setInstallCost(50);
								motionSr.setInstallDate(Calendar.getInstance());
								motionSr.setLocation(nRoom.getName());
								// Enable secsysservice in building if not
								// already enabled
								if (!customer.getBldg().isSecSysSvceEnabled()) {
									customer.getBldg().setSecSysSvceEnabled(true);
									if (!customer.getBill().isEnabled())
										customer.getBill().setEnabled(true);
								}
								snsrList.add(motionSr);
								row[0] = roomNameComboBox.getSelectedItem().toString();
								row[1] = SoSafeConstants.motionSensor;
								row[2] = motionSr.getSensorCode();
								nRoom.setmSensor(motionSr);
								model.addRow(row);
							} else {
								JOptionPane.showMessageDialog(InstallView.this, "MSensor exists!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						} else if (!mCheckbox.isSelected() && tCheckbox.isSelected()) {
							if (nRoom.gettSensor() == null) {
								TempSensor tempSr = new TempSensor();
								tempSr.setSensorCode(SoSafeUtil.getSensorCode());
								tempSr.setEnabled(true);
								tempSr.setSimulationStop(true);
								tempSr.setScheduleType(ScheduleType.MANUAL);
								tempSr.setFrom_Time(SoSafeConstants.defaultTimeStr);
								tempSr.setTo_Time(SoSafeConstants.defaultTimeEndStr);
								tempSr.setUsedCount(0);
								tempSr.setUserName(customer.getName());
								tempSr.setInstallCost(100);
								tempSr.setInstallDate(Calendar.getInstance());
								tempSr.setLocation(nRoom.getName());
								// Enable falarmservice in building if not
								// already enabled
								if (!customer.getBldg().isfAlarmSysSvceEnabled()) {
									customer.getBldg().setfAlarmSysSvceEnabled(true);
									if (!customer.getBill().isEnabled())
										customer.getBill().setEnabled(true);
								}
								snsrList.add(tempSr);
								row[0] = roomNameComboBox.getSelectedItem().toString();
								row[1] = SoSafeConstants.tempSensor;
								row[2] = tempSr.getSensorCode();
								nRoom.settSensor(tempSr);
								// persistance(row);
								model.addRow(row);
							} else {
								JOptionPane.showMessageDialog(InstallView.this, "TSensor exists!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else if (mCheckbox.isSelected() && tCheckbox.isSelected()) {
							if (nRoom.getmSensor() == null) {
								MotionSensor motionSr = new MotionSensor();
								motionSr.setSensorCode(SoSafeUtil.getSensorCode());
								motionSr.setEnabled(true);
								motionSr.setSimulationStop(true);
								motionSr.setScheduleType(ScheduleType.MANUAL);
								motionSr.setFrom_Time(SoSafeConstants.defaultTimeStr);
								motionSr.setTo_Time(SoSafeConstants.defaultTimeEndStr);
								motionSr.setUsedCount(0);
								motionSr.setUserName(customer.getName());
								motionSr.setInstallCost(50);
								motionSr.setInstallDate(Calendar.getInstance());
								motionSr.setLocation(nRoom.getName());
								// Enable secsysservice in building if not
								// already enabled
								if (!customer.getBldg().isSecSysSvceEnabled()) {
									customer.getBldg().setSecSysSvceEnabled(true);
									if (!customer.getBill().isEnabled())
										customer.getBill().setEnabled(true);
								}
								snsrList.add(motionSr);
								row[0] = roomNameComboBox.getSelectedItem().toString();
								row[1] = SoSafeConstants.motionSensor;
								row[2] = motionSr.getSensorCode();
								nRoom.setmSensor(motionSr);
								model.addRow(row);
								// persistance(row);
							} else {
								JOptionPane.showMessageDialog(InstallView.this, "MSensor exists!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

							if (nRoom.gettSensor() == null) {
								TempSensor tempSr = new TempSensor();
								tempSr.setSensorCode(SoSafeUtil.getSensorCode());
								tempSr.setEnabled(true);
								tempSr.setSimulationStop(true);
								tempSr.setScheduleType(ScheduleType.MANUAL);
								tempSr.setFrom_Time(SoSafeConstants.defaultTimeStr);
								tempSr.setTo_Time(SoSafeConstants.defaultTimeEndStr);
								tempSr.setUsedCount(0);
								tempSr.setUserName(customer.getName());
								tempSr.setInstallCost(100);
								tempSr.setInstallDate(Calendar.getInstance());
								tempSr.setLocation(nRoom.getName());
								// Enable falarmservice in building if not
								// already enabled
								if (!customer.getBldg().isfAlarmSysSvceEnabled()) {
									customer.getBldg().setfAlarmSysSvceEnabled(true);
									if (!customer.getBill().isEnabled())
										customer.getBill().setEnabled(true);
								}
								snsrList.add(tempSr);
								row[0] = roomNameComboBox.getSelectedItem().toString();
								row[1] = SoSafeConstants.tempSensor;
								row[2] = tempSr.getSensorCode();
								nRoom.settSensor(tempSr);
								// persistance(row);
								model.addRow(row);
							} else {
								JOptionPane.showMessageDialog(InstallView.this, "TSensor exists!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}

						else {
							JOptionPane.showMessageDialog(InstallView.this, "Please enter a valid Sensor Type", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						nRoom.setSensors(snsrList);
						SoSafeUtil.saveToBuldingSensorFile(nRoom, customer.getName());
						tCheckbox.setSelected(false);
						mCheckbox.setSelected(false);
						break;
					}
				}
			}
		});
	}

	public void persistance(Object[] row) {

	}

}
