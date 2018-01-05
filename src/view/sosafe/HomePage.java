package view.sosafe;

import java.awt.Container;
import javax.swing.JButton;
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
 *  Login page.
 * 
 */
@SuppressWarnings("serial")
public class HomePage extends JFrame // implements ActionListener
{
	public JTextField tfusername;
	public JPasswordField pfpassword;
	private JLabel lusername;
	private JLabel lpassword;
	public JButton newuser;
	public JButton login;

	public HomePage() {
		super("Home Page");

		// get content pane
		Container container = getContentPane();

		// creating buttons
		newuser = new JButton(new Controller("New User"));
		login = new JButton(new Controller("Login"));

		// set the labels
		lusername = new JLabel("User Name");
		lpassword = new JLabel("Password");

		// set the text fields
		tfusername = new JTextField(15);
		pfpassword = new JPasswordField(15);

		// set the layout
		setSize(400, 300);
		setLocation(500, 280);
		container.setLayout(null);

		// set the boundary
		tfusername.setBounds(90, 30, 150, 20);
		pfpassword.setBounds(90, 65, 150, 20);
		login.setBounds(110, 100, 90, 20);
		newuser.setBounds(110, 135, 90, 20);
		lusername.setBounds(20, 28, 80, 20);
		lpassword.setBounds(20, 63, 80, 20);

		// add it to the container
		container.add(newuser);
		container.add(login);
		container.add(lusername);
		container.add(lpassword);
		container.add(tfusername);
		container.add(pfpassword);
		setVisible(true);
	}
}
