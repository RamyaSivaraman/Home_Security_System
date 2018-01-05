package model.sosafe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import model.sosafe.system.Customer;
import model.sosafe.system.Room;
import model.sosafe.system.ScheduleType;
import model.sosafe.system.sensors.MotionSensor;
import model.sosafe.system.sensors.Sensor;
import model.sosafe.system.sensors.TempSensor;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 */
public class SoSafeUtil {

	public SoSafeUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String getSensorCode() {
		String sensorCd = null;

		sensorCd = "S" + getNewSensorCode(SoSafeConstants.sequencesFile);

		return sensorCd;
	}

	/**
	 * Determines next Sensor code based on sequence file number, when new
	 * sensor is added.
	 * 
	 * @param fileName
	 * @return
	 */
	public static int getNewSensorCode(String fileName) {

		int nextSensorCode = 0;

		StringBuilder newsequenceStr = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = input.readLine()) != null && !(line.trim().isEmpty())) {

				if (line.contains(SoSafeConstants.sensorCodeSeqStr)) {
					String[] sensorCd = line.split(":");
					String sensorCode = (String) sensorCd[1];
					int sensorCodeValue = Integer.valueOf(sensorCode);
					nextSensorCode = sensorCodeValue + 1;
					newsequenceStr.append(SoSafeConstants.sensorCodeSeqStr + ":" + nextSensorCode);
					newsequenceStr.append("\r\n");
				} else {
					newsequenceStr.append(line);
					newsequenceStr.append("\r\n");
				}
			}
			input.close();
		} catch (Exception e) {

		} finally {

		}
		try {
			// Now newsequenceStrList will have updated content , which you can
			// override into file
			FileWriter fstreamWrite = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write(newsequenceStr.toString());
			out.close();
			// Close the input stream
		} catch (Exception e) {

		} finally {

		}
		return nextSensorCode;
	}

	/**
	 * This saves Sensor installation information to the file
	 * 
	 * @param room
	 * @param userId
	 */
	public static void saveToBuldingSensorFile(Room room, String userId) {

		try {
			FileWriter writer = new FileWriter(SoSafeConstants.sensorScheduleFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			// bufferedWriter.newLine();
			List<String> roomLineList = getStrListFromRoomObj(room, userId);
			for (String roomLine : roomLineList) {
				bufferedWriter.write(roomLine);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Get comma seperated list of strings representing Sensor installation
	 * information.
	 * 
	 * @param room
	 * @param userId
	 * @return
	 */
	public static List<String> getStrListFromRoomObj(Room room, String userId) {

		List<String> roomList = new ArrayList<String>();

		for (Sensor sr : room.getSensors()) {
			StringBuilder roomBuilder = new StringBuilder();
			roomBuilder.append(sr.getSensorCode() + ",");
			roomBuilder.append(userId + ",");
			String rName = room.getName();
			roomBuilder.append(rName + ",");

			if (sr instanceof TempSensor) {
				roomBuilder.append(SoSafeConstants.tempSensor + ",");
			} else if (sr instanceof MotionSensor) {
				roomBuilder.append(SoSafeConstants.motionSensor + ",");
			}

			roomBuilder.append(sr.isEnabled() + ",");
			roomBuilder.append(sr.getScheduleType() + ",");
			roomBuilder.append(sr.getFrom_Time() + ",");
			roomBuilder.append(sr.getTo_Time() + ",");
			roomBuilder.append(sr.getUsedCount());
			roomList.add(roomBuilder.toString());
		}
		return roomList;

	}

	/**
	 * Get Sensor schedule information from and represent it as Sensor obejct
	 * 
	 * @param schedSenLine
	 * @param customer
	 * @return
	 */
	public static Sensor getSensorObjFromSchedFileLine(String schedSenLine, Customer customer) {
		// 0 - SensorCode,
		// 1 - User,
		// 2 - Roomname,
		// 3- SensorType,
		// 4 - Enabled,
		// 5 - SchedType,
		// 6-FromTime
		// 7 - toTime
		// 8 - usedCount
		Sensor sr = null;

		String[] schedLineArr = schedSenLine.split(",");
		if (schedLineArr[3].equals(SoSafeConstants.tempSensor)) {
			sr = new TempSensor();
		}
		if (schedLineArr[3].equals(SoSafeConstants.motionSensor)) {
			sr = new MotionSensor();
		}
		sr.setSensorCode(schedLineArr[0]);
		sr.setUserName(schedLineArr[1]);
		sr.setLocation(schedLineArr[2]);
		// sr.set
		sr.setEnabled(Boolean.valueOf(schedLineArr[4]));
		sr.setScheduleType(ScheduleType.valueOf(schedLineArr[5]));
		sr.setFrom_Time(schedLineArr[6]);
		sr.setTo_Time(schedLineArr[7]);
		sr.setUsedCount(Integer.valueOf(schedLineArr[8]));
		sr.setPhNumbers(customer.getEmergencyPhno());
		sr.setPhNumbers2(customer.getEmergencyPhno2());
		// sr.setPhNumbers("9724001458");

		return sr;
	}

	/**
	 * Get Sensor Object from Selected sensorcombination
	 * 
	 * @param selectedSensorCmb
	 * @param customer
	 * @return
	 */
	public static Sensor getSensorObjFromStr(String selectedSensorCmb, Customer customer) {
		Sensor sr = null;
		StringBuilder newsequenceStr = new StringBuilder();
		String[] sensorComb = selectedSensorCmb.split("-");
		String selectedsensorCode = sensorComb[0];
		String selectedroomName = sensorComb[1];
		try {
			BufferedReader input = new BufferedReader(new FileReader(SoSafeConstants.sensorScheduleFile));
			String line = null;
			while (((line = input.readLine()) != null) && !(line.trim().isEmpty())) {

				if (line.contains(selectedsensorCode) && line.contains(selectedroomName)) {
					sr = getSensorObjFromSchedFileLine(line, customer);
					return sr;
				}
			}
			input.close();
		} catch (Exception e) {

		}

		return sr;

	}

	/**
	 * Update Sensor schedule to the file
	 * 
	 * @param sen
	 */
	public static void updateSensorSchedule(Sensor sen) {

		String sensorSchedStr = getSensorSchedLine(sen);
		String[] sensorComb = sensorSchedStr.split(",");
		String selectedsensorCode = sensorComb[0];
		String selectedroomName = sensorComb[1];
		StringBuilder newschedStr = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(SoSafeConstants.sensorScheduleFile));
			String line = null;
			while (((line = input.readLine()) != null) && !line.trim().isEmpty()) {

				if (line.contains(selectedsensorCode) && line.contains(selectedroomName)) {
					newschedStr.append(sensorSchedStr);
					newschedStr.append("\r\n");
				} else {
					newschedStr.append(line);
					newschedStr.append("\r\n");
				}
			}
			input.close();
		} catch (Exception e) {

		} finally {

		}
		try {
			// Now newsequenceStrList will have updated content , which you can
			// override into file
			FileWriter fstreamWrite = new FileWriter(SoSafeConstants.sensorScheduleFile);
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write(newschedStr.toString());
			out.close();
			// Close the input stream
		} catch (Exception e) {

		} finally {

		}

	}

	/**
	 * Sensor information represented as string line.
	 * 
	 * @param sr
	 * @return
	 */
	public static String getSensorSchedLine(Sensor sr) {

		StringBuilder sensorBuilder = new StringBuilder();
		sensorBuilder.append(sr.getSensorCode() + ",");
		sensorBuilder.append(sr.getUserName() + ",");

		sensorBuilder.append(sr.getLocation() + ",");

		if (sr instanceof TempSensor) {
			sensorBuilder.append(SoSafeConstants.tempSensor + ",");
		} else if (sr instanceof MotionSensor) {
			sensorBuilder.append(SoSafeConstants.motionSensor + ",");
		}

		sensorBuilder.append(sr.isEnabled() + ",");
		sensorBuilder.append(sr.getScheduleType() + ",");
		sensorBuilder.append(sr.getFrom_Time() + ",");
		sensorBuilder.append(sr.getTo_Time() + ",");
		sensorBuilder.append(sr.getUsedCount());
		// sensorBuilder.add(roomBuilder.toString());

		return sensorBuilder.toString();
	}

	/**
	 * Populate table model with sensor information
	 * 
	 * @param model
	 * @param customer
	 */
	public static void populateTableDataFromSensorSched(DefaultTableModel model, Customer customer) {

		StringBuilder newsequenceStr = new StringBuilder();
		// String [] sensorComb = selectedSensorCmb.split("-");
		// String selectedsensorCode = sensorComb[0];
		// String selectedroomName = sensorComb[1];
		try {
			BufferedReader input = new BufferedReader(new FileReader(SoSafeConstants.sensorScheduleFile));
			String line = null;
			int i = 0;
			while (((line = input.readLine()) != null) && !(line.trim().isEmpty())) {
				String[] sLine = line.split(",");
				if (customer.getName().equals(sLine[1])) {
					// Setting Alarm & Motion Sensor enabled on Building..
					String sensorType = (String) sLine[3];
					if (!customer.getBldg().isfAlarmSysSvceEnabled() && sensorType.equals(SoSafeConstants.tempSensor)) {
						customer.getBldg().setfAlarmSysSvceEnabled(true);
					}
					if (!customer.getBldg().isSecSysSvceEnabled() && sensorType.equals(SoSafeConstants.motionSensor)) {
						customer.getBldg().setSecSysSvceEnabled(true);
					}

					// Enabling Room Sensor.
					for (Room rm : customer.getBldg().getRooms()) {
						if (rm.getName().equals((String) sLine[2])) {
							Sensor snr = getSensorObjFromSchedFileLine(line, customer);

							if (rm.getmSensor() == null && snr instanceof MotionSensor) {
								rm.setmSensor((MotionSensor) snr);
							}
							if (rm.gettSensor() == null && snr instanceof TempSensor) {
								rm.settSensor((TempSensor) snr);
							}
						}
					}
					model.addRow(new Object[] { (Object) sLine[2], (Object) sLine[3], (Object) sLine[0] });
					// i++;
				}
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Populate table data from events file
	 * 
	 * @param model
	 * @param customer
	 */
	public static void populateTableDataFromEventsFile(DefaultTableModel model, Customer customer) {

		StringBuilder newsequenceStr = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader(new FileReader("res/events.txt"));
			String line = null;
			int i = 0;
			while (((line = input.readLine()) != null) && !(line.trim().isEmpty())) {
				String[] sLine = line.split(",");
				if (customer.getName().equals(sLine[0]))
					model.addRow(new Object[] { (Object) sLine[0], (Object) sLine[1], (Object) sLine[2],
							(Object) sLine[3], (Object) sLine[4] });
				// i++;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load sensor information from file based on logged in customer
	 * 
	 * @param filename
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public static String[] loadSensorInfoFromFile(String filename, Customer customer) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		List<String> strings = new ArrayList<String>();

		try {
			String line = null;
			while ((line = input.readLine()) != null) {
				String sensorComboItem = "";
				String[] sensorArr = line.split(",");
				if (sensorArr[1].equals(customer.getName())) {
					sensorComboItem = sensorArr[0] + "-" + sensorArr[2] + "-" + sensorArr[3];
					strings.add(sensorComboItem);
				}
			}
		}

		catch (FileNotFoundException e) {
			System.err.println("Error, file " + filename + " didn't exist.");
		} finally {
			input.close();
		}

		String[] lineArray = strings.toArray(new String[] {});
		return lineArray;

	}

	public static String[] loadRoomInfoFromFile(String filename) {
		String[] roomNames = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = null;
			if ((line = br.readLine()) != null) {
				// splitting line to tokens based on delimiters ":"
				roomNames = line.split(":");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomNames;
	}

	/**
	 * Determines number of events occured for a sensor on current month.
	 * 
	 * @param snr
	 * @return
	 */
	public static int getEventCountForMonth(Sensor snr) {
		int eventCount = 0;

		LocalDateTime date = java.time.LocalDateTime.now();
		String datetime = date.toString();
		int currentMonth = date.getMonthValue();
		// int month = cal.get(Calendar.MONTH);
		// Month.

		try {
			BufferedReader input = new BufferedReader(new FileReader("res/events.txt"));
			String line = null;
			int i = 0;
			while (((line = input.readLine()) != null) && !(line.trim().isEmpty())) {
				String[] sLine = line.split(",");
				System.out.println("Sline ARR count " + sLine.length);
				// Ramya,TEMP_SENSOR,2017-06-04T22:39:50.119,9724001458,S1,
				// 0 - Ramya
				// 1 - SensorType
				// 2 - Event Date
				// 3 - phnumber
				// 4 - Sensor code
				// 5 - empty

				if (sLine.length == 5 && sLine[4] != null && sLine[4].equals(snr.getSensorCode())) {
					String eventDate = sLine[2];
					int eventMonth = 0;
					String[] eventDateArr = sLine[2].split("-");
					String emergencyContactExists = sLine[3];
					if (emergencyContactExists != null && !emergencyContactExists.isEmpty()) {
						eventMonth = Integer.valueOf(eventDateArr[1]);
						if (eventMonth == currentMonth) {
							eventCount++;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventCount;

	}

	/**
	 * Synchronized method which is invoked my sensor threads to update event
	 * information to event file.
	 * 
	 * @param snr
	 */
	public synchronized static void updateEventsFile(Sensor snr) {
		try {
			FileWriter writer = new FileWriter(SoSafeConstants.eventFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			// bufferedWriter.newLine();
			String eventLine = "";
			// List<String> eventsList = getStrListFromRoomObj(room, userId);
			if (snr instanceof TempSensor) {
				// Date dateAndTime = Calendar.getInstance().getTime();
				// JLabel dateTime = new JLabel(dateAndTime.toString());
				LocalDateTime date = java.time.LocalDateTime.now();
				String datetime = date.toString();
				eventLine = eventLine + snr.getCustomer().getName() + "," + SoSafeConstants.tempSensor + "," + datetime
						+ "," + snr.getPhNumbers() + "&" + snr.getPhNumbers2() + "," + snr.getSensorCode() + ",";
			}
			if (snr instanceof MotionSensor) {
				LocalDateTime date = java.time.LocalDateTime.now();
				String datetime = date.toString();
				eventLine = eventLine + snr.getCustomer().getName() + "," + SoSafeConstants.motionSensor + ","
						+ datetime + "," + snr.getPhNumbers() + "&" + snr.getPhNumbers2() + "," + snr.getSensorCode()
						+ ",";
			}
			// String eventLine = snr.get
			// for (String roomLine : roomLineList) {
			bufferedWriter.write(eventLine);
			bufferedWriter.newLine();
			// }

			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/** Updates event graph information.
	 * @param snr
	 */
	public static void updateEventGraphFile(Sensor snr) {
		try {
			FileWriter writer = new FileWriter(SoSafeConstants.eventGraphFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			String eventLine = "";
			if (snr instanceof TempSensor) {
				eventLine = eventLine + snr.getCustomer().getName() + "," + SoSafeConstants.tempSensor + ","
						+ Calendar.getInstance().get(Calendar.MONTH) + ",";
			}
			if (snr instanceof MotionSensor) {
				eventLine = eventLine + snr.getCustomer().getName() + "," + SoSafeConstants.motionSensor + ","
						+ Calendar.getInstance().get(Calendar.MONTH) + ",";
			}
			bufferedWriter.write(eventLine);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
