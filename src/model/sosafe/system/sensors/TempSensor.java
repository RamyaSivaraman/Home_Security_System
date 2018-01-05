package model.sosafe.system.sensors;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.sosafe.system.Customer;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Class to represent Temp Sensor.
 */
public class TempSensor extends Sensor {

	private double installCost = 100;

	public TempSensor() {
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
		System.out.println("inside child fire class");

		JFrame parent = new JFrame();
		ImageIcon phone = new ImageIcon("res/phone.png");
		String name = JOptionPane.showInputDialog(parent, "Alarm Ringing!! Sensor Alert " + getSensorCode()
				+ " Calling emergency numbers " + getPhNumbers() + " ,  " + getPhNumbers2() + " Response code?", null);

		while (true) {

			if (name.equals(getCustomer().getResponseCode())) {
				break;
			}

			else {
				name = JOptionPane.showInputDialog(parent,
						"Alarm Ringing!! Sensor Alert " + getSensorCode() + " Calling emergency numbers "
								+ getPhNumbers() + " ,  " + getPhNumbers2()
								+ " Wrong Response Code!Enter correct response code?",
						null);
			}
		}

	}
}