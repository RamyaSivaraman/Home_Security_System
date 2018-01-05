package model.sosafe.system.sensors;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import model.sosafe.system.Customer;
import model.sosafe.system.ScheduleType;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Class to represent base class Sensor.
 */
public class Sensor {

	private String sensorCode;

	private boolean enabled;

	private int usedCount;

	private String userName;

	private Customer customer;

	private boolean breakIn = false;
	private boolean fire = false;

	private boolean simulationStop;

	public boolean isSimulationStop() {
		return simulationStop;
	}

	public void setSimulationStop(boolean simulationStop) {
		this.simulationStop = simulationStop;
	}

	private ScheduleType scheduleType;

	// private String auto;

	private String location;

	private double installCost;
	private Calendar installDate;

	private String phNumbers;

	private String phNumbers2;

	private String from_Time;

	private String to_Time;

	// private int latestSensorCode;

	public boolean isBreakIn() {
		return breakIn;
	}

	public void setBreakIn(boolean breakIn) {
		this.breakIn = breakIn;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFrom_Time() {
		return from_Time;
	}

	public void setFrom_Time(String from_Time) {
		this.from_Time = from_Time;
	}

	public String getTo_Time() {
		return to_Time;
	}

	public void setTo_Time(String to_Time) {
		this.to_Time = to_Time;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getUsedCount() {
		usedCount = 0;
		usedCount = SoSafeUtil.getEventCountForMonth(this);
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}

	public ScheduleType getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getInstallCost() {
		return installCost;
	}

	public void setInstallCost(double installCost) {
		this.installCost = installCost;
	}

	public String getPhNumbers() {
		return phNumbers;
	}

	public void setPhNumbers(String phNumbers) {
		this.phNumbers = phNumbers;
	}

	public String getPhNumbers2() {
		return phNumbers2;
	}

	public void setPhNumbers2(String phNumbers2) {
		this.phNumbers2 = phNumbers2;
	}

	public Sensor() {

	}

	public String getSensorCode() {
		return sensorCode;
	}

	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}

	public void activateForSimulation() {
		System.out.println("Activate sensor:" + getSensorCode() + " for simulation");
		if (isEnabled()) {
			SensorMonitoringThread th = new SensorMonitoringThread(this);
			th.start();
		}
	}

	public void triggerEventResponse() {
		System.out.println("Fire Response from super class sensor");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Calendar getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Calendar installDate) {
		this.installDate = installDate;
	}

	public boolean isCurrentTimeBetween() {
		String startTime = getFrom_Time();
		String endTime = getTo_Time();
		int startHour = 0;
		// int tmp = 01;
		int endHour = 0;
		if (!startTime.equals("00:00")) {
			String[] startArr = startTime.split(":");
			startHour = Integer.valueOf(startArr[0]);
		}
		if (!endTime.equals("00:00")) {
			String[] endArr = endTime.split(":");
			endHour = Integer.valueOf(endArr[0]);
		}
		return isCurrentHourBetween(startHour * 100, endHour * 100);
	}

	public boolean isCurrentHourBetween(int from, int to) {
		// int from = 2300;
		// int to = 800;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
		boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);

		return isBetween;
	}
}
