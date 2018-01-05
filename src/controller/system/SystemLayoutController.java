package controller.system;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.sosafe.system.BillGenerate;
import model.sosafe.system.BillView;
import model.sosafe.system.Customer;
import view.sosafe.EventBarGraphView;
import view.sosafe.EventsView;
import view.sosafe.InstallView;
import view.sosafe.MonitorView;
import view.sosafe.SheduleView;
import view.sosafe.SoSafeSystemViewLayout;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 */
public class SystemLayoutController implements ActionListener {
	private JPanel jPanel1;
	private JButton navHomeButt;
	private JButton navNextButt;
	private JButton navPreviousButt;
	private JPanel panelContainer;
	private int pageIndex = 0;
	Random random = new Random();
	private Customer cust;

	public SystemLayoutController(JPanel panelContain, Customer custr) {
		this.panelContainer = panelContain;
		this.cust = custr;

	}

	/*
	 * (non-Javadoc) Navigation Controller for Next, Previous, Home, Event
	 * Details, Bill Generate, Event Graph
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Next")) {
			if (pageIndex == 0) {
				SheduleView schedPanel = new SheduleView(cust);
				panelContainer.add(schedPanel, "" + 1);
				pageIndex++;
				CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
				cardLayout.next(panelContainer);

			} else if (pageIndex == 1) {
				MonitorView monitorView = new MonitorView(cust);
				panelContainer.add(monitorView, "" + 2);
				pageIndex++;
				CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
				cardLayout.next(panelContainer);
			} else if (pageIndex == 2) {

			}

		} else if (b.getText().equals("Previous")) {
			if (pageIndex == 0) {

			} else if (pageIndex == 1) {
				InstallView instPanel = new InstallView(cust);
				panelContainer.add(instPanel, "" + 0);
				pageIndex = 0;
				CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
				cardLayout.previous(panelContainer);
			} else if (pageIndex == 2) {
				MonitorView monitorView = new MonitorView(cust);
				panelContainer.add(monitorView, "" + 2);
				pageIndex = 1;
				CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
				cardLayout.previous(panelContainer);
			}

		} else if (b.getText().equals("Home")) {
			pageIndex = 0;
			CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
			cardLayout.first(panelContainer);
			// pageIndex = 0;
		} else if (b.getText().equals("Event")) {
			EventsView ev = new EventsView(cust);
			ev.setVisible(true);
		}

		else if (b.getText().equals("Bill Details")) {
			BillView bv = new BillView(cust);
			bv.setVisible(true);

		}

		else if (b.getText().equals("EvGraph")) {
			JFrame evGraphFrame = new JFrame("Event Graph");
			EventBarGraphView evBarGraph = new EventBarGraphView();
			evGraphFrame.getContentPane().add(evBarGraph);
			evGraphFrame.setSize(700, 700);
			evGraphFrame.setVisible(true);
		}
	}
}
