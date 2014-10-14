// Assignment #: 12
//         Name: Jon Wendt
//    StudentID: [REMOVED]
//      Lecture: [REMOVED]
//  Description: Creates the JComponents and panels necessary to control the
//				 bike's, adding listeners to them so they can use the
//				 appropriate BicyclePanel methods.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
	// 1 for the red bicycle control, 2 for the blue bicycle control
	private BicyclePanel bicycle1, bicycle2,bicycle3, bicycle4;

	// the panels and components needed to organize the controls neatly
	private JPanel leftPanel, rightPanel, leftTopPanel, leftBottomPanel;
	private JPanel topLPanel, topRPanel, topMidPanel, bottomMidPanel, bottomLPanel, bottomRPanel;
	private JButton startRed, stopRed, reverseRed, startBlue, stopBlue,
			reverseBlue, addRed, addBlue;
	private JLabel redLabel, blueLabel, xLabel, yLabel;
	private JSlider redDelay, blueDelay, xSpeed, ySpeed;
	private int width, height;

	// The constructor creates 6 buttons, 2 sliders, and 2 bicycle panels
	// and organize them using layouts.
	public ControlPanel(int width, int height) {
		// initializes the width and height
		this.width = width;
		this.height = height;

		// create 2 bicycle panels and arrange them using GridLayout
		bicycle1 = new BicyclePanel(Color.red, Color.cyan, width / 2);
		bicycle2 = new BicyclePanel(Color.blue, Color.yellow, width / 2);

		// creates the right panel and fills it with the two bicycle panels
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2, 1));
		rightPanel.add(bicycle1);
		rightPanel.add(bicycle2);

		// initializes the buttons, adding listeners to each one
		startRed = new JButton("Start Red");
		startRed.addActionListener(new ButtonListener());
		stopRed = new JButton("Stop Red");
		stopRed.addActionListener(new ButtonListener());
		reverseRed = new JButton("Reverse Red");
		reverseRed.addActionListener(new ButtonListener());

		
		startBlue = new JButton("Start Blue");
		startBlue.addActionListener(new ButtonListener());
		stopBlue = new JButton("Stop Blue");
		stopBlue.addActionListener(new ButtonListener());
		reverseBlue = new JButton("Reverse Blue");
		reverseBlue.addActionListener(new ButtonListener());
		
		
		// initializes the sliders and their labels //new
		redLabel = new JLabel("Red Delay", JLabel.CENTER);
		redDelay = new JSlider(JSlider.VERTICAL);
		redDelay.setMaximum(50);
		redDelay.setPaintLabels(true);
		redDelay.setPaintTicks(true);
		redDelay.setMajorTickSpacing(10);
		redDelay.setMinorTickSpacing(1);
		redDelay.setValue(20);
		redDelay.addChangeListener(new SliderListener());
		
		xLabel = new JLabel("Horizontal Speed", JLabel.CENTER);
		xSpeed = new JSlider(JSlider.VERTICAL);
		xSpeed.setMaximum(30);
		xSpeed.setPaintLabels(true);
		xSpeed.setMajorTickSpacing(5);
		xSpeed.setMinorTickSpacing(1);
		xSpeed.setValue(5);
		xSpeed.addChangeListener(new SliderListener());
		
		blueLabel = new JLabel("Blue Delay", JLabel.CENTER);
		blueDelay = new JSlider(JSlider.VERTICAL);
		blueDelay.setMaximum(50);
		blueDelay.setPaintLabels(true);
		blueDelay.setPaintTicks(true);
		blueDelay.setMajorTickSpacing(10);
		blueDelay.setMinorTickSpacing(1);
		blueDelay.setValue(20);
		blueDelay.addChangeListener(new SliderListener());
		
		yLabel = new JLabel ("Vertical Speed", JLabel.CENTER);
		ySpeed = new JSlider(JSlider.VERTICAL);
		ySpeed.setMaximum(30);
		ySpeed.setPaintLabels(true);
		ySpeed.setMajorTickSpacing(5);
		ySpeed.setMinorTickSpacing(1);
		ySpeed.setValue(5);
		ySpeed.addChangeListener(new SliderListener());

		// sets up the layout for the nested panels
		topLPanel = new JPanel();
		topLPanel.setLayout(new GridLayout(3, 1));
		topRPanel = new JPanel();
		topRPanel.setLayout(new BorderLayout());
		topMidPanel = new JPanel (new BorderLayout());
		bottomLPanel = new JPanel();
		bottomLPanel.setLayout(new GridLayout(3, 1));
		bottomMidPanel = new JPanel (new BorderLayout());
		bottomRPanel = new JPanel();
		bottomRPanel.setLayout(new BorderLayout());

		// organizes the components neatly
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2, 1));
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(new GridLayout(1, 3));
		
		topLPanel.add(startRed);
		topLPanel.add(stopRed);
		topLPanel.add(reverseRed);
		
		topMidPanel.add(xLabel, BorderLayout.NORTH);
		topMidPanel.add(xSpeed, BorderLayout.CENTER);
		
		topRPanel.add(redLabel, BorderLayout.NORTH);
		topRPanel.add(redDelay, BorderLayout.CENTER);
		
		leftTopPanel.add(topLPanel);
		leftTopPanel.add(topMidPanel);
		leftTopPanel.add(topRPanel);
		
		leftBottomPanel = new JPanel();
		leftBottomPanel.setLayout(new GridLayout(1, 3));
		bottomLPanel.add(startBlue);
		bottomLPanel.add(stopBlue);
		bottomLPanel.add(reverseBlue);
		
		bottomMidPanel.add(yLabel, BorderLayout.NORTH);
		bottomMidPanel.add(ySpeed, BorderLayout.CENTER);
				
		bottomRPanel.add(blueLabel, BorderLayout.NORTH);
		bottomRPanel.add(blueDelay, BorderLayout.CENTER);
		
		leftBottomPanel.add(bottomLPanel);
		leftBottomPanel.add(bottomMidPanel);
		leftBottomPanel.add(bottomRPanel);
		
		leftPanel.add(leftTopPanel);
		leftPanel.add(leftBottomPanel);

		// organize the left panel and right panel using SplitPane
		setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(120, height)); // don't
																// understand
																// this line's
																// function
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel,
				rightPanel); // new
		add(sp);

		setPreferredSize(new Dimension(width, height)); // don't understand this
														// line's function
	}

	private class ButtonListener implements ActionListener // from this till the
															// end --> see
															// BicyclePanel
	{
		public void actionPerformed(ActionEvent event) {
			// checks which button was pressed and calls the appropriate method
			Object action = event.getSource();
			if (action.equals(startRed))
				bicycle1.resume(); // resume() method in BicyclePanel.java
			else if (action.equals(stopRed))
				bicycle1.suspend(); // suspend() method in BicyclePanel.java
			else if (action.equals(reverseRed))
				bicycle1.reverse(); // reverse() method in BicyclePanel.java
			else if (action.equals(startBlue))
				bicycle2.resume();
			else if (action.equals(stopBlue))
				bicycle2.suspend();
			else if (action.equals(reverseBlue))
				bicycle2.reverse();
			
		}
	} // end of ButtonListener

	private class SliderListener implements ChangeListener {
		
		public void stateChanged(ChangeEvent event) {
			// checks which slider was moved and sets that respective timer's
			// delay
			Object action = event.getSource();
			if (action.equals(redDelay))
				bicycle1.setDelay(redDelay.getValue()); // setDelay(int) method
														// in BicyclePanel.java
			else if (action.equals(blueDelay))
				bicycle2.setDelay(blueDelay.getValue());
			
			else if (action.equals(xSpeed))
			{
				bicycle1.stepX = xSpeed.getValue();
				bicycle2.stepX = xSpeed.getValue();
			}	
			else if (action.equals(ySpeed))
				bicycle1.stepY = ySpeed.getValue();
				bicycle2.stepY = ySpeed.getValue();
		}

	} // end of SliderListener

} // end of ControlPanel
