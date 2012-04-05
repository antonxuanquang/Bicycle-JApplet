// Assignment #: 12
//         Name: Jon Wendt
//    StudentID: [REMOVED]
//      Lecture: [REMOVED]
//  Description: The Assignment 12 class creates a control panel and
//               adds it as its Applet content and also sets its size.

import javax.swing.*;

public class Assignment12 extends JApplet
 {
  private static int WIDTH = 650;
  private static int HEIGHT = 340;

  public void init()
   {
       ControlPanel panel = new ControlPanel(WIDTH,HEIGHT);
       getContentPane().add(panel);
       setSize(WIDTH,HEIGHT);
   }
 }
