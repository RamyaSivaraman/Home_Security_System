package view.sosafe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.system.SystemLayoutController;
import model.sosafe.system.Customer;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 *  To navigate each view using card layout
 */
public class SoSafeSystemViewLayout extends JFrame {

	private JPanel jPanel1;
	private JButton navHomeButt;
	private JButton navNextButt;
	private JButton navPreviousButt;
	private JPanel panelContainer;
	private JButton eventButt;
	private JButton billButt;
	private JButton eventGraphButt;
	private Customer cust;
	Random random = new Random();

	public SoSafeSystemViewLayout(Customer customer) {
		this.cust = customer;
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		navPreviousButt = new JButton();
		navNextButt = new JButton();
		navHomeButt = new JButton();

		eventButt = new JButton();
		billButt = new JButton();
		eventGraphButt = new JButton();
		panelContainer = new JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(Color.WHITE);

		navPreviousButt.setText("Previous");
		navPreviousButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(navPreviousButt);

		navNextButt.setText("Next");
		navNextButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(navNextButt);

		navHomeButt.setText("Home");
		navHomeButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(navHomeButt);

		eventButt.setText("Event");
		navNextButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(eventButt);

		billButt.setText("Bill Details");
		billButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(billButt);

		eventGraphButt.setText("EvGraph");
		eventGraphButt.setPreferredSize(new Dimension(90, 23));
		jPanel1.add(eventGraphButt);

		panelContainer.setPreferredSize(new Dimension(800, 800));
		panelContainer.setLayout(new CardLayout());
		// setting the card layout

		getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);
		getContentPane().add(panelContainer, BorderLayout.CENTER);

		InstallView instPanel = new InstallView(cust);
		panelContainer.add(instPanel, "" + 0);

		SystemLayoutController controller = new SystemLayoutController(panelContainer, cust);

		navNextButt.addActionListener(controller);
		navHomeButt.addActionListener(controller);
		navPreviousButt.addActionListener(controller);
		billButt.addActionListener(controller);
		eventButt.addActionListener(controller);
		eventGraphButt.addActionListener(controller);

		pack();
	}

	public JPanel createSamplePanel(String panelTitle) {
		JPanel samplePanel = new JPanel();
		samplePanel.add(new JLabel(panelTitle));
		samplePanel.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		return samplePanel;

	}

}
