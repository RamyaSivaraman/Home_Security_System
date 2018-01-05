package controller.login;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;

import model.sosafe.system.BillGenerate;
import model.sosafe.system.Building;
import model.sosafe.system.Customer;
import view.sosafe.HomePage;
import view.sosafe.HomeScreen;
import view.sosafe.Registartion;
import view.sosafe.SoSafeSystemViewLayout;

@SuppressWarnings("serial")
/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 15th 2017
 */
public class Controller extends AbstractAction {
	
	private static JFrame currentPage;

	public Controller(String label) {
		super(label);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Action Event listener for buttons in Login/Registration page.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
	
		// typecasting event source to JButton
		JButton b = (JButton) e.getSource();
		//If user clicks New User button Registration page will be opened
		if (b.getText().equals("New User")) {
			Registartion reg = new Registartion();
			currentPage = reg;
		} else if (b.getText().equals("Save")) { //Save button will save registration details
			// Creating UserDetails.txt and saving the new user details to the
			// text file.
			Component component = (Component) e.getSource();
			Registartion reg = (Registartion) SwingUtilities.getRoot(component);
			try {
				// Convert password to string to store in text file
				// Also check if password was retyped correctly
				String pwdStr = new String(reg.pfpassword.getPassword());
				String repwdStr = new String(reg.repassword.getPassword());
				if (!pwdStr.equals(repwdStr)) {
					JOptionPane.showConfirmDialog(null, "Password Mismatch!", "Registration InComplete",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				FileWriter writer = new FileWriter("res/UserDetails.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				// Write registration info into text file
				bufferedWriter.write(reg.name.getText() + "," + reg.buildingname.getText() + ","
						+ reg.tfusername.getText() + "," + pwdStr + "," + repwdStr + "," + reg.phonenumber.getText()
						+ "," + reg.emailid.getText() + "," + reg.emergencycontactno.getText() + ","
						+ reg.emergencycontactno2.getText() + "," + reg.addressline1.getText() + ","
						+ reg.addressline2.getText() + "," + reg.cityortown.getText() + ","
						+ reg.stateorproorreg.getText() + "," + reg.postalorzip.getText() + "," + reg.country.getText()
						+ "," + reg.responsecode.getText());

				bufferedWriter.newLine();
				bufferedWriter.close();

				Customer customer = new Customer();
				customer.setName(reg.name.getText());
				customer.setBldgName(reg.buildingname.getText());
				customer.setUsername(reg.tfusername.getText());
				customer.setPassword(pwdStr);
				customer.setPhno(reg.phonenumber.getText());
				customer.setEmail(reg.emailid.getText());
				customer.setEmergencyPhno(reg.emergencycontactno.getText());
				customer.setEmergencyPhno2(reg.emergencycontactno2.getText());
				customer.setAddrLine1(reg.addressline1.getText());
				customer.setAddrLine2(reg.addressline2.getText());
				customer.setCity(reg.cityortown.getText());
				customer.setState(reg.stateorproorreg.getText());
				customer.setZip(reg.postalorzip.getText());
				customer.setCountry(reg.country.getText());
				customer.setResponseCode(reg.responsecode.getText());
				Random rand = new Random();
				customer.setSvceContractId(Integer.toString(rand.nextInt(100)));
				customer.setBldg(new Building(reg.buildingname.getText()));
				customer.setBill(new BillGenerate(customer));

				SoSafeSystemViewLayout hs = new SoSafeSystemViewLayout(customer);

				hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				hs.setVisible(true);
				currentPage.setVisible(false);
				currentPage.dispose();

				// HomeScreen hs = new HomeScreen(customer);

				// hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// use if condition for Login button and use buffered reader to do the
		// actions
		// add a functionality on the homepage which closes the previous window
		// when a new window is opened
		else if (b.getText().equals("Login")) {
			Component component = (Component) e.getSource();
			// Registration reg = (Registration)
			// SwingUtilities.getRoot(component);
			HomePage home = (HomePage) SwingUtilities.getRoot(component);
			BufferedReader br;

			try {
				//Authentication using UserDetails.txt
				br = new BufferedReader(new FileReader("res/UserDetails.txt"));
				String username, password;
				String line = null;

				while ((line = br.readLine()) != null) {
					String[] values = line.split(":|,");
					username = values[2];
					password = values[3];

					if (username.equals(home.tfusername.getText())) {
						if (password.equals(new String(home.pfpassword.getPassword()))) {
							JOptionPane.showConfirmDialog(null, "Correct password!", "Login Complete",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

							Customer customer = new Customer();
							customer.setName(values[0]);
							customer.setBldgName(values[1]);
							customer.setUsername(username);
							customer.setPassword(password);
							customer.setPhno(values[5]);
							customer.setEmail(values[6]);
							customer.setEmergencyPhno(values[7]);
							customer.setEmergencyPhno2(values[8]);
							customer.setAddrLine1(values[9]);
							customer.setAddrLine2(values[10]);
							customer.setCity(values[11]);
							customer.setState(values[12]);
							customer.setZip(values[13]);
							customer.setCountry(values[14]);
							customer.setResponseCode(values[15]);
							Random rand = new Random();
							customer.setSvceContractId(Integer.toString(rand.nextInt(100)));
							customer.setBldg(new Building(values[1]));
							customer.setBill(new BillGenerate(customer));

							SoSafeSystemViewLayout hs = new SoSafeSystemViewLayout(customer);

							hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							hs.setVisible(true);
							currentPage.setVisible(false);
							currentPage.dispose();
							// currentPage.dispatchEvent(new
							// WindowEvent(currentPage,
							// WindowEvent.WINDOW_CLOSING));
						} else { // password does not match
							JOptionPane.showConfirmDialog(null, "Incorrect password!", "Login incomplete",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					}
				}
				br.close();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 *  Invokes Login Page (Home Page)
	 * @param args
	 */
	public static void main(String[] args) {
		HomePage application = new HomePage();
		currentPage = application;
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
