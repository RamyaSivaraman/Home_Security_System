package model.sosafe.system.sensors;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import model.sosafe.system.Customer;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Thread class which runs once Simulate button is pressed.
 */
public class SensorMonitoringThread extends Thread{
	
	private Sensor snr;
	public SensorMonitoringThread (Sensor sr) {
		this.snr = sr;
	}
	public void run () {
		System.out.println("Inside run Snr "+snr.getSensorCode()+ " snr Type");
		
         	while (true) {
         		//No simulation when sensor is disabled
 				if (!snr.isEnabled()) {
 					System.out.println("breaking the simulation isEnabled False ");
 					break;
 				}
 				//Stop simulation when stop simulation is pressed
 				if (snr.isSimulationStop()) {
 					System.out.println("breaking the simulation simualation stop ");
 					break;
 				}
 				//If Breakin button is pressed.
 				if (snr.isEnabled() && snr.isBreakIn()) {
 				//	int currentHour = LocalDateTime.now().getHour();
 					String startTime = snr.getFrom_Time();
 					String endTime = snr.getTo_Time();
 					int startHour = 0;
 					//int tmp = 01;
 					int endHour = 0;
 					if (!startTime.equals("00:00")) {
 						String [] startArr = startTime.split(":");
 						startHour = Integer.valueOf(startArr[0]);
 					}
 					if (!endTime.equals("00:00")) {
 						String [] endArr = endTime.split(":");
 						endHour = Integer.valueOf(endArr[0]); 						
 					}
 					boolean isBetween = isCurrentHourBetween (startHour*100, endHour *100);
 					if (isBetween) {
	 					System.out.println("Inside Breakin "+snr.getSensorCode()+ " snr Type");
	 					snr.triggerEventResponse();
	 					snr.setUsedCount(snr.getUsedCount()+1);
	 					snr.setBreakIn(false);
	 					SoSafeUtil.updateEventsFile(snr);
	 					SoSafeUtil.updateEventGraphFile(snr);
	 					//break;
 					}
 					else {
 						System.out.println("Inside Breakin .. Time is not between from & to");
 						snr.setBreakIn(false);
 					}
 					
 				}
 				//if Fire is pressed during simulation.
                 if (snr.isEnabled() && snr.isFire()) {
                		String startTime = snr.getFrom_Time();
     					String endTime = snr.getTo_Time();
     					int startHour = 0;
     					//int tmp = 01;
     					int endHour = 0;
     					if (!startTime.equals("00:00")) {
     						String [] startArr = startTime.split(":");
     						startHour = Integer.valueOf(startArr[0]);
     					}
     					if (!endTime.equals("00:00")) {
     						String [] endArr = endTime.split(":");
     						endHour = Integer.valueOf(endArr[0]); 						
     					}
     					boolean isBetween = isCurrentHourBetween (startHour*100, endHour *100);
     					if (isBetween) {
     						System.out.println("Inside Fire "+snr.getSensorCode()+ " snr Type");
     	 					snr.triggerEventResponse();
     	 					snr.setUsedCount(snr.getUsedCount()+1);
    	 					snr.setFire(false);
     	 					SoSafeUtil.updateEventsFile(snr);
     	 					SoSafeUtil.updateEventGraphFile(snr);
     	 					//break;
     					}
     					else {
     						System.out.println("Inside Fire .. Time is not between from & to");
     						snr.setFire(false);
     					}
 					
 				}

 				try {
 					Thread.sleep(1000);
 				} catch (InterruptedException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
         }
	}
	
	/** Method which checks if current hour is between From and to.
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean isCurrentHourBetween (int from, int to) {
		//int from = 2300;
	    //int to = 800;
	    Date date = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
	    boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
	    
	    return isBetween;
	}
}
