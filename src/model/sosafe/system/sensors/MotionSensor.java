package model.sosafe.system.sensors;

import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.sosafe.system.Customer;
import model.sosafe.util.ImagePanel;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Class to represent Motion Sensor.
 */
public class MotionSensor extends Sensor {

	private double installCost = 50;

	public MotionSensor() {
		// TODO Auto-generated constructor stub
	}

	public double getInstallCost() {
		return installCost;
	}

	public void setInstallCost(double installCost) {
		this.installCost = installCost;
	}

	@Override
	public void triggerEventResponse() {
		System.out.println("inside child motion class");

		JFrame parent = new JFrame();
		ImageIcon image2 = new ImageIcon("res/fire.jpg");
		String name = JOptionPane.showInputDialog(parent, "Alarm Ringing!! Sensor Alert " + getSensorCode()
				+ " Calling emergency number2 " + getPhNumbers() + " ,  " + getPhNumbers2() + " Response code?", null);

		while (true) {

			if (name.equals(getCustomer().getResponseCode())) {
				break;
			}

			else {
				name = JOptionPane.showInputDialog(parent,
						"Alarm Ringing!! Sensor Alert " + getSensorCode() + " Calling emergency number "
								+ getPhNumbers() + " ,  " + getPhNumbers2()
								+ " Wrong Response Code! Enter correct response code?",
						null);
			}
		}
	}

}
