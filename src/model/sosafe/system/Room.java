package model.sosafe.system;

import java.util.List;

import model.sosafe.system.sensors.MotionSensor;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.system.sensors.TempSensor;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Model to represent room and its containments like sensors, mSensor, tSensor
 */
public class Room {
	//
	private String name;

	private List<Sensor> sensors;

	private TempSensor tSensor;

	private MotionSensor mSensor;

	public TempSensor gettSensor() {
		return tSensor;
	}

	public void settSensor(TempSensor tSensor) {
		this.tSensor = tSensor;
	}

	public MotionSensor getmSensor() {
		return mSensor;
	}

	public void setmSensor(MotionSensor mSensor) {
		this.mSensor = mSensor;
	}

	public Room() {

	}

	public Room(String rName) {
		this.name = rName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

}
