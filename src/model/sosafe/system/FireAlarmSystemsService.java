package model.sosafe.system;

import java.util.Calendar;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Model to represent Fire Alarm Service provided by system
 */
public class FireAlarmSystemsService extends HomeSecService {
	// 

	private boolean enabled;
	private double installCost;
	private Calendar installDate;

	public FireAlarmSystemsService(boolean b) {
		enabled = b;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public double getInstallCost() {
		return installCost;
	}

	public void setInstallCost(double installCost) {
		this.installCost = installCost;
	}

	public Calendar getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Calendar installDate) {
		this.installDate = installDate;
	}
}
