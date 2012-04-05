// Assignment #: 12
//         Name: Jon Wendt
//    StudentID: [REMOVED]
//      Lecture: [REMOVED]
//  Description: The BicyclePanel class draws a bicycle in a panel
//               and moves it using javax.swing.Timer

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;

public class BicyclePanel extends JPanel
 {
  private int width;
  private int centerX, centerY, diameter; //parameter to draw a bicycle
  private Color bicycleColor;
  private int currentAngle;
  private int step; //how much currentAngle changes for each tick of Timer

  private Timer timer;
  private int delay;   //delay of Timer

  //The constructor initializes parameters for the bicycle, and start the timer
  public BicyclePanel(Color bicycleColor, Color backColor, int width)
   {
       this.bicycleColor = bicycleColor;
	   setBackground(backColor);
	   this.width = width;
	   delay = 20;
	   step = 3;
	   currentAngle = 0;
	   centerX = width/4;
	   centerY = width/4;
	   diameter = (width-10)/6;
	   timer = new Timer(delay, new MoveListener());
	   timer.start();
   }

  public void resume()
   {
	   //restarts the timer
	   timer.start();
   }

  public void suspend()
   {
	   //stops the timer
	   timer.stop();
   }

  public void reverse()
   {
	   //find the current direction of the bike and then change it to the opposite
	   if (step > 0)
		   step = -3;
	   else
		   step = 3;

	   //if the bike is stopped, start it again
	   if (timer.isRunning() != true)
		   timer.start();
   }

  public void setDelay(int delayValue)
   {
	   //sets the timer delay
	   timer.setDelay(delayValue);
   }

  //paintComponent method draws a bicycle by drawing two wheels and links between them
  //This method is given to you, and you do not need to modify it
  public void paintComponent(Graphics page)
   {
       super.paintComponent(page);

       //left wheel
       page.setColor(bicycleColor);
       page.fillArc(centerX, centerY, diameter, diameter, currentAngle, 90);
       page.fillArc(centerX, centerY, diameter, diameter, currentAngle+180, 90);

       page.setColor(Color.black);
       page.drawOval(centerX, centerY, diameter, diameter);


       //right wheel
       page.setColor(bicycleColor);
       page.fillArc(centerX+2*diameter, centerY, diameter, diameter, currentAngle, 90);
       page.fillArc(centerX+2*diameter, centerY, diameter, diameter, currentAngle+180, 90);

       page.setColor(Color.black);
       page.drawOval(centerX+2*diameter, centerY, diameter, diameter);


       //link two wheels
       page.setColor(bicycleColor);
       int seatLeftX = centerX+diameter-10;
       int seatLeftY = centerY-20;
       int seatRightX = centerX+2*diameter+10;
       int seatRightY = centerY-20;

       page.drawLine(seatLeftX, seatLeftY, seatRightX, seatRightY);

       page.drawLine(seatLeftX, seatLeftY, centerX+diameter/2, centerY+diameter/2);
       page.drawLine(seatRightX, seatRightY, centerX+(diameter*5)/2, centerY+diameter/2);

       page.drawLine(centerX+(diameter*3)/2, centerY+diameter/2, seatLeftX-10, seatLeftY-10);
       page.drawLine(centerX+(diameter*3)/2, centerY+diameter/2, seatRightX+10, seatRightY-10);

       page.drawLine(seatLeftX-20, seatLeftY-10, seatLeftX-10, seatLeftY-10);
       page.drawLine(seatRightX+20, seatRightY-10, seatRightX+10, seatRightY-10);

   }

  //MoveListener defined an action to be taken for each tick of the Timer.
  private class MoveListener implements ActionListener
   {
        public void actionPerformed(ActionEvent event)
          {
			//keeps the bike from moving outside of the screen by checking if
			//centerX is negative or over the width of the application, then
			//calls the reverse method. If it is within application, it moves
			//the bike forward and repaints it.
            currentAngle -= step;
			if ((centerX + step) > 0 && (centerX + step) < getSize().getWidth()-3*diameter)
				centerX += step;
			else
				reverse();
			repaint();
          }
   }
 }

