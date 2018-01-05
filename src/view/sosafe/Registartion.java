package view.sosafe;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.login.Controller;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * View to display registratin page.
 */
@SuppressWarnings("serial")
public class Registartion extends JFrame {
	private JLabel lname, lphonenumber, lbuildingname, lusername, lpfpassword, lrepassword, lemailid, lcountry;
	private JLabel lemergencycontactno, lemergencycontactno2, laddressline1, laddressline2, lcityortown,
			lstateorproorreg, lpostalorzip, lresponsecode, lcallingfacility;
	public JTextField name, phonenumber, buildingname, tfusername, emailid, emergencycontactno, emergencycontactno2;
	public JTextField addressline1, addressline2, cityortown, stateorproorreg, postalorzip, country, responsecode;
	public JPasswordField pfpassword, repassword;
	public JCheckBox callingyes, callingno;
	private JButton save;

	public Registartion() {
		super("New User Registration");
		// get content pane
		Container container = getContentPane();

		// creating buttons
		save = new JButton(new Controller("Save"));

		// set the labels
		lname = new JLabel("Name");
		lphonenumber = new JLabel("Phone No:");
		lbuildingname = new JLabel("Bulding Name");
		lusername = new JLabel("User Name");
		lpfpassword = new JLabel("<html>Password</html>");
		lrepassword = new JLabel("<html>Re-type Password</html>");
		lemailid = new JLabel("Email ID");
		lemergencycontactno = new JLabel("<html>Emergency Contact No</html>");
		lemergencycontactno2 = new JLabel("<html>Emergency Contact No</html>");
		laddressline1 = new JLabel("<html>Address Line1</html>");
		laddressline2 = new JLabel("<html>Address Line2</html>");
		lcityortown = new JLabel("City/Town");
		lstateorproorreg = new JLabel("<html>State/Province/Region</html>");
		lpostalorzip = new JLabel("Postal/Zip");
		lcountry = new JLabel("Country");
		lresponsecode = new JLabel("Response Code");
		lcallingfacility = new JLabel("Enable calling emergency contact");

		// set the text fields and password fields
		name = new JTextField(15);
		phonenumber = new JTextField(10);
		buildingname = new JTextField(15);
		tfusername = new JTextField(15);
		pfpassword = new JPasswordField(15);
		repassword = new JPasswordField(15);
		emailid = new JTextField(20);
		emergencycontactno = new JTextField(10);
		emergencycontactno2 = new JTextField(10);
		responsecode = new JTextField(10);
		addressline1 = new JTextField(20);
		addressline2 = new JTextField(20);
		cityortown = new JTextField(10);
		stateorproorreg = new JTextField(15);
		postalorzip = new JTextField(5);
		country = new JTextField(5);
		callingyes = new JCheckBox();
		callingno = new JCheckBox();

		// set the layout
		setSize(400, 300);
		setLocation(500, 280);
		container.setLayout(null);

		// set the boundary
		name.setBounds(160, 30, 150, 20);
		buildingname.setBounds(160, 65, 150, 20);
		tfusername.setBounds(160, 100, 150, 20);
		pfpassword.setBounds(160, 135, 150, 20);
		repassword.setBounds(160, 170, 150, 20);
		phonenumber.setBounds(160, 205, 150, 20);
		emailid.setBounds(160, 240, 150, 20);
		emergencycontactno.setBounds(160, 275, 150, 20);
		emergencycontactno2.setBounds(160, 310, 150, 20);
		addressline1.setBounds(160, 345, 150, 20);
		addressline2.setBounds(160, 380, 150, 20);
		cityortown.setBounds(160, 415, 150, 20);
		stateorproorreg.setBounds(160, 450, 150, 20);
		postalorzip.setBounds(160, 485, 150, 20);
		country.setBounds(160, 520, 150, 20);
		responsecode.setBounds(160, 555, 150, 20);
		// callingyes.setBounds(160,620,150,20);
		// callingno.setBounds(200,620,150,20);

		lname.setBounds(20, 28, 80, 20);
		lbuildingname.setBounds(20, 63, 100, 20);
		lusername.setBounds(20, 98, 80, 20);
		lpfpassword.setBounds(20, 133, 80, 20);
		lrepassword.setBounds(20, 170, 130, 20);
		lphonenumber.setBounds(20, 205, 80, 20);
		lemailid.setBounds(20, 240, 80, 20);
		lemergencycontactno.setBounds(20, 275, 130, 20);
		lemergencycontactno2.setBounds(20, 310, 130, 20);

		laddressline1.setBounds(20, 345, 100, 20);
		laddressline2.setBounds(20, 380, 100, 20);
		lcityortown.setBounds(20, 415, 80, 20);
		lstateorproorreg.setBounds(20, 450, 130, 20);
		lpostalorzip.setBounds(20, 485, 80, 20);
		lcountry.setBounds(20, 520, 80, 20);
		lresponsecode.setBounds(20, 555, 130, 20);
		// lcallingfacility.setBounds(20,580,130,20);
		save.setBounds(110, 600, 90, 20);

		// add it to the container
		container.add(name);
		container.add(buildingname);
		container.add(tfusername);
		container.add(pfpassword);
		container.add(repassword);
		container.add(phonenumber);
		container.add(emailid);
		container.add(emergencycontactno);
		container.add(emergencycontactno2);
		container.add(responsecode);
		container.add(addressline1);
		container.add(addressline2);
		container.add(cityortown);
		container.add(stateorproorreg);
		container.add(postalorzip);
		container.add(country);
		container.add(lname);
		container.add(lbuildingname);
		container.add(lusername);
		container.add(lpfpassword);
		container.add(lrepassword);
		container.add(lphonenumber);
		container.add(lemailid);
		container.add(lemergencycontactno);
		container.add(lemergencycontactno2);
		container.add(laddressline1);
		container.add(laddressline2);
		container.add(lcityortown);
		container.add(lstateorproorreg);
		container.add(lpostalorzip);
		container.add(lcountry);
		container.add(lresponsecode);

		container.add(save);

		setVisible(true);

	}
	// public static void main( String [] args ){
	// Registration application = new Registration();
	// application.setDefaultCloseOperation(
	// JFrame.EXIT_ON_CLOSE );
	// }
}
