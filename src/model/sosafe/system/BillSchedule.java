package model.sosafe.system;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 */

public class BillSchedule {
	
	private final Runnable billRun;
	private Timer timer = new Timer();

	/**
	 *  Timer task that invokes bill view every 10 minutes which generates bill
	 *
	 */
	private class billTimerTask extends TimerTask {
		public void run() {
			try {
				billRun.run();
			} finally {
				billschedule();
			}
			System.out.println("Schedule Set");
		}
	}

	public BillSchedule(Runnable runnable) {
		billRun = runnable;
		billschedule();
	}

	private void billschedule() {
		cancelTimer();
		timer = new Timer();
		timer.schedule(new billTimerTask(), nextBillDate());
	}

	/** Bill Schedule to run every 10 minutes 
	 * @return
	 */
	private Date nextBillDate() {
		Calendar billDate = Calendar.getInstance();
		billDate.add(Calendar.MINUTE, 10);
		System.out.println("Next Bill: " + billDate.getTime().toString());
		return billDate.getTime();
	}

	public void cancelTimer() {
		timer.cancel();
		timer.purge();
	}
}
