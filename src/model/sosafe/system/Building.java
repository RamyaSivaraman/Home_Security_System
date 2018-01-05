package model.sosafe.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.sosafe.util.SoSafeConstants;
import model.sosafe.util.SoSafeUtil;
import model.sosafe.system.FireAlarmSystemsService;
import model.sosafe.system.Room;
import model.sosafe.system.SecSystemsService;
/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Class to represent Building model having rooms and selected service.
 */

public class Building {

	private String name;

	private String buildingId;

	private SecSystemsService sSystemSvce;

	private FireAlarmSystemsService fAlarmSysSvce;

	private List<Room> rooms;

	public Building(String name) {
		this.name = name;
		sSystemSvce = new SecSystemsService(false);
		fAlarmSysSvce = new FireAlarmSystemsService(false);
		rooms = new ArrayList<Room>();

		String[] roomNames = SoSafeUtil.loadRoomInfoFromFile("res/Rooms.txt");
		for (int i = 0; i < roomNames.length; i++) {
			addRoom(new Room(roomNames[i]));
		}
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void setSecSysSvceEnabled(boolean b) {
		if (b) {
			sSystemSvce.setEnabled(true);
			sSystemSvce.setInstallDate(Calendar.getInstance());
			sSystemSvce.setInstallCost(200);
			if (fAlarmSysSvce.isEnabled()) {
				fAlarmSysSvce.setInstallCost(240);
			}
		}
	}

	public void setfAlarmSysSvceEnabled(boolean b) {
		if (b) {
			fAlarmSysSvce.setEnabled(b);
			fAlarmSysSvce.setInstallDate(Calendar.getInstance());
			if (sSystemSvce.isEnabled())
				fAlarmSysSvce.setInstallCost(240);
			else
				fAlarmSysSvce.setInstallCost(300);
		}
	}

	public boolean isSecSysSvceEnabled() {
		return sSystemSvce.isEnabled();
	}

	public boolean isfAlarmSysSvceEnabled() {
		return fAlarmSysSvce.isEnabled();
	}

	public double getSecSysSvceInstallCost() {
		double cost = sSystemSvce.getInstallCost();
		sSystemSvce.setInstallCost(0);
		return cost;
	}

	public double getfAlarmSysSvceInstallCost() {
		double cost = fAlarmSysSvce.getInstallCost();
		fAlarmSysSvce.setInstallCost(0);
		return cost;
	}
}
