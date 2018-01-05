package model.sosafe.system;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 */
public class BillView extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 *  Generates Bill view from customer & events triggered for this customer.
	 * @param customer
	 */
	public BillView(Customer customer) {
		System.out.println("Creating Bill Panel");
		double cost = 0;
		double secInstallCost = 0;
		double fireInstallCost = 0;
		double secSensInstallCost = 0;
		double fSensInstallCost = 0;
		double secSensUsage = 0;
		double fSensUsage = 0;

		Building bldg = customer.getBldg();
		if (bldg.isSecSysSvceEnabled()) {
			secInstallCost = bldg.getSecSysSvceInstallCost();
		}
		if (bldg.isfAlarmSysSvceEnabled()) {
			fireInstallCost = bldg.getfAlarmSysSvceInstallCost();
		}
		for (int i = 0; i < bldg.getRooms().size(); i++) {
			Room r = bldg.getRooms().get(i);
			if (r.getmSensor() != null) {
				if (r.getmSensor().isEnabled()) {
					secSensInstallCost += r.getmSensor().getInstallCost();
					secSensUsage += r.getmSensor().getUsedCount();
				}
			}
			if (r.gettSensor() != null) {
				if (r.gettSensor().isEnabled()) {
					fSensInstallCost += r.gettSensor().getInstallCost();
					fSensUsage += r.gettSensor().getUsedCount();
				}
			}
		}

		double monthservicecharge = 50;
		double secAlarmUsageCost = (secSensUsage * 20);
		double fAlarmUsageCost = (fSensUsage * 50);
		cost += secInstallCost + fireInstallCost + secSensInstallCost + fSensInstallCost + secAlarmUsageCost
				+ fAlarmUsageCost + monthservicecharge;

		Container container = getContentPane();

		JLabel lCustInfo = new JLabel("Customer Info");
		lCustInfo.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		JLabel lsvceContractId = new JLabel("Service ContractID: " + customer.getSvceContractId());
		JLabel lcustomerName = new JLabel("Cusomer Name: " + customer.getName());
		String addr = customer.getAddrLine1() + customer.getAddrLine2() + customer.getCity() + customer.getState()
				+ customer.getCountry() + " - " + customer.getZip();
		JLabel laddr = new JLabel("Address: " + addr);
		JLabel lemergPhno = new JLabel("Emergency Phno: " + customer.getEmergencyPhno());
		JLabel lcustPhno = new JLabel("Customer Phno: " + customer.getPhno());

		JLabel lSecSysInfo = new JLabel("Intrusion Detection Cost Info");
		lSecSysInfo.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		JLabel lSecSysInstallCharge = new JLabel("SecSys Initial Install Charge (One Time ): " + secInstallCost);
		JLabel lSecSensInstallCost = new JLabel("SecSys Sensor Install Cost (Per Sensor $50): " + secSensInstallCost);
		JLabel alarmRaisedByIntrusion = new JLabel();
		JLabel lSecSensUseCost = new JLabel("SecSys No. of Alarm calls to Service : " + secSensUsage
				+ " and Alarm Service Usage Cost (Per Alarm $20): " + (secSensUsage * 20));

		JLabel lfSysInfo = new JLabel("Fire Detection Cost Info");
		lfSysInfo.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
		JLabel lfSysInstallCharge = new JLabel("fSys Initial Install Charge: (One Time) : " + fireInstallCost);
		JLabel lfSensInstallCost = new JLabel("fSysSens Install Cost: " + fSensInstallCost);
		JLabel alarmRaisedByFire = new JLabel();
		JLabel lfSensUseCost = new JLabel("fSys No. of Alarm calls to Service : " + fSensUsage
				+ " and Alarm Service Usage Cost (Per Alarm $50): " + (fSensUsage * 50));

		JLabel lMonthCharge = new JLabel("Montly Service Charge: 50.0");
		JLabel lTotalCost = new JLabel("Total Cost: " + cost);
		lTotalCost.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));

		// set the layout
		setSize(400, 300);
		setLocation(500, 280);
		container.setLayout(null);

		lCustInfo.setBounds(20, 28, 1000, 20);
		lsvceContractId.setBounds(20, 63, 1000, 20);
		lcustomerName.setBounds(20, 98, 1000, 20);
		laddr.setBounds(20, 133, 400, 20);
		lemergPhno.setBounds(20, 170, 1000, 20);
		lcustPhno.setBounds(20, 205, 1000, 20);
		lSecSysInfo.setBounds(20, 275, 1000, 20);
		lSecSysInstallCharge.setBounds(20, 310, 1000, 20);
		lSecSensInstallCost.setBounds(20, 345, 1000, 20);
		lSecSensUseCost.setBounds(20, 380, 1000, 20);
		lfSysInfo.setBounds(20, 450, 200, 20);
		lfSysInstallCharge.setBounds(20, 485, 1000, 20);
		lfSensInstallCost.setBounds(20, 520, 1000, 20);
		lfSensUseCost.setBounds(20, 555, 1000, 20);
		lMonthCharge.setBounds(20, 625, 1000, 20);
		lTotalCost.setBounds(20, 660, 1000, 20);

		// add it to the container
		container.add(lCustInfo);
		container.add(lsvceContractId);
		container.add(lcustomerName);
		container.add(laddr);
		container.add(lemergPhno);
		container.add(lcustPhno);
		container.add(lSecSysInfo);
		container.add(lSecSysInstallCharge);
		container.add(lSecSensInstallCost);
		container.add(lSecSensUseCost);
		container.add(lfSysInfo);
		container.add(lfSysInstallCharge);
		container.add(lfSensInstallCost);
		container.add(lfSensUseCost);
		container.add(lMonthCharge);
		container.add(lTotalCost);

		setVisible(true);
	}
}
