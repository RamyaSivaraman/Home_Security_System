package view.sosafe;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JPanel;

import model.sosafe.util.SoSafeConstants;
import model.sosafe.util.SoSafeUtil;

/**
 * @author Haritha
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Additional feature Mid term makeup
 */
public class EventBarGraphView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] fillRectX = { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
	private int[] fillRectY = { 480, 440, 400, 360, 320, 280, 240, 200, 160, 120, 80, 40 };
	// private Color[] fillRectColor = {Color.black, Color.red, Color.green,
	// Color.blue,
	// Color.CYAN, Color.darkGray, Color.magenta, Color.gray, Color.orange,
	// Color.pink, Color.yellow,
	// Color.lightGray};
	private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec" };
	private int[] monthMotionEvents = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int[] monthFireEvents = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	// draw Account balance as a bar graph
	public void paintComponent(Graphics g) {
		// ensure proper painting sequence
		super.paintComponent(g);

		// draw vertical and horizontal axes
		g.setColor(Color.black);
		// X-Axis
		g.drawLine(50, 500, 450, 500);
		// Y-Axis
		g.drawLine(50, 5, 50, 500);

		// draw graph labels
		g.setFont(new Font("SansSerif", Font.PLAIN, 10));
		// g.drawString( "-$5,000", 5, 10 );
		g.drawString("0", 50, 510);
		g.drawString("1", 85, 510);
		g.drawString("2", 135, 510);
		g.drawString("3", 185, 510);
		g.drawString("4", 235, 510);
		g.drawString("5", 285, 510);
		g.drawString("6", 335, 510);
		g.drawString("7", 385, 510);
		g.drawString("8", 435, 510);

		String[] eventLineTokens = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("res/eventGraph.txt"));
			String line = null;
			while ((line = br.readLine()) != null) {
				// splitting line to tokens based on delimiters ":"
				eventLineTokens = line.split(",");
				if (eventLineTokens[1].equals(SoSafeConstants.motionSensor)) {
					System.out.println("Log msens");
					monthMotionEvents[Integer.parseInt(eventLineTokens[2])]++;
				}
				if (eventLineTokens[1].equals(SoSafeConstants.tempSensor)) {
					System.out.println("Log tsens");
					monthFireEvents[Integer.parseInt(eventLineTokens[2])]++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < months.length; i++) {
			System.out.println(
					"Motion[+" + months[i] + "]: Motion:" + monthMotionEvents[i] + ", Fire:" + monthFireEvents[i]);
			g.setColor(Color.black);
			g.drawString(months[i], 5, fillRectY[i] + 15);
			g.setColor(Color.BLUE);
			g.fillRect(fillRectX[i], fillRectY[i], monthMotionEvents[i] * 40, 10);
			g.setColor(Color.RED);
			g.fillRect(fillRectX[i], fillRectY[i] + 10, monthFireEvents[i] * 40, 10);
			if (monthMotionEvents[i] != 0) {
				g.setFont(new Font("SansSerif", Font.PLAIN, 10));
				g.setColor(Color.BLUE);
				g.drawString("Motion", fillRectX[i] + monthMotionEvents[i] * 40, fillRectY[i] + 10);
			}
			if (monthFireEvents[i] != 0) {
				g.setFont(new Font("SansSerif", Font.PLAIN, 10));
				g.setColor(Color.RED);
				g.drawString("Fire", fillRectX[i] + monthFireEvents[i] * 40, fillRectY[i] + 20);
			}
		}

		// drawLegend(g);
	} // end method paintComponent

	// repaint graph when display is updated
	public void updateDisplay() {
		repaint();
	}

	// get AccountBarGraphView's preferred size
	public Dimension getPreferredSize() {
		return new Dimension(470, 530);
	}

	// get AccountBarGraphView's minimum size
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	// get AccountBarGraphView's maximum size
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	// draw pie chart legend on given Graphics context
	private void drawLegend(Graphics g) {
		// draw Account color swatch at next offset
		g.setColor(Color.BLUE);
		g.fillRect(100, 10, 95, 12);
		// draw graph labels
		g.setFont(new Font("SansSerif", Font.PLAIN, 10));
		// g.drawString( "-$5,000", 5, 10 );
		g.drawString("Motion", 500, 10);
	} // end method drawLegend
}
