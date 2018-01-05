package view.sosafe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.sosafe.system.Customer;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 *  View class to display table view of generated events.
 */
public class EventsView extends JFrame {

	public JPanel panel;
	public JLabel label;
	public JTable table;
	private Customer customer;

	public EventsView(Customer customer) {
		table = new JTable();
		this.customer = customer;
		Object[] row = new Object[5];
		String[] columns = { "Customer Name", "Event Type", "Date", "Called Emergency Number ", "Sensor Code" };
		Object[][] data = {};

		DefaultTableModel model1 = new DefaultTableModel(data, columns);
		SoSafeUtil.populateTableDataFromEventsFile(model1, customer);
		model1.setColumnIdentifiers(columns);

		table.setModel(model1);
		// table.setBackground(Color.GRAY);
		// table.setForeground(Color.BLACK);
		Font font = new Font("", 20, 22);
		table.setFont(font);
		table.setRowHeight(100);

		JTableHeader header = table.getTableHeader();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1500, 1000));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		label = new JLabel("List of Events");
		label.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
		panel.add(label);
		// panel.add(BorderLayout.NORTH);
		panel.add(header, BorderLayout.NORTH);
		panel.add(table);

		setSize(1500, 1000);
		setVisible(true);
		this.add(panel);

	}
}
