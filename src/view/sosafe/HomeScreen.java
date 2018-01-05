package view.sosafe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.sosafe.system.Customer;
import model.sosafe.system.Room;
import model.sosafe.system.sensors.MotionSensor;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 */
public class HomeScreen extends JFrame {
	public JPanel panel;
	Customer customer;

	public HomeScreen(Customer customer) {
		super("Home");

		this.customer = customer;

		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		panel = new JPanel();

		InstallView iv = new InstallView(customer);

		// SheduleView s = new SheduleView();

		// Controller c = new Controller();
		panel.setPreferredSize(new Dimension(1400, 900));
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		container.add(panel);
		// panel.add(s);
		panel.add(iv);

		setSize(1500, 1000);
		setVisible(true);

	}
}