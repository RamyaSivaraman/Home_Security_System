package model.sosafe.system;

import javax.swing.JFrame;

import model.sosafe.system.Customer;
/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 */
public class BillGenerate {
	private Customer customer;
	private boolean enabled;
	private String serviceContractId;
	private String customerName;
	private String addr;
	private String emergencyPhno;
	private String customerPhno;
	private final double monthlyCost = 50;

	private Runnable generateBill = new Runnable() {
		public void run() {
			generateBillDetails();
		}
	};

	
	/** Constructor accepting customer object which is used generate bill
	 * @param customer
	 */
	public BillGenerate(Customer customer) {
		this.customer = customer;
		serviceContractId = customer.getSvceContractId();
		customerName = customer.getName();
		addr = customer.getAddrLine1() + customer.getAddrLine2() + customer.getCity() + customer.getState()
				+ customer.getCountry() + " - " + customer.getZip();
		emergencyPhno = customer.getEmergencyPhno();
		customerPhno = customer.getPhno();
		enabled = true;
		System.out.println("Creating BillSchedule");
		BillSchedule b = new BillSchedule(generateBill);
	}
	
	/**
	 *  Method to generate Bill View.
	 */
	public void generateBillDetails() {
		System.out.println("Generating bill: " + enabled);
		if (enabled) {
			System.out.println("Generating BillView");
			BillView bill = new BillView(customer);
			bill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}


	public String getServiceContractId() {
		return serviceContractId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
