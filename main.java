//Breakout
//by Zach Knox, Jack Fleisher, and Spencer Harding

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   public class main
   {
      public static JFrame frame;
      public static JMenuBar theMenu()
      {
         JMenuBar menuBar;
         JMenu menu, submenu;
         JMenuItem menuItem;
         JCheckBoxMenuItem cbMenuItem;
      	
         menuBar = new JMenuBar();
         menu = new JMenu("Game");
         menu.setMnemonic(KeyEvent.VK_G);
         menu.getAccessibleContext().setAccessibleDescription(
                "Options for the game");
         menuBar.add(menu);
         menuItem = new JMenuItem("Begin Game");
         menuItem.setMnemonic(KeyEvent.VK_B);
         menu.add(menuItem);
      
         menuItem = new JMenuItem("Reset Game");
         menuItem.setMnemonic(KeyEvent.VK_R);
         menu.add(menuItem);
      	
      	
         menu = new JMenu("Preferences");
         menu.setMnemonic(KeyEvent.VK_V);
         menu.getAccessibleContext().setAccessibleDescription(
                "Change different things about this application");
         menuBar.add(menu);
      	
      	
         return menuBar;
      }
      public static void main(String[] args)
      {
      	//Creates a frame
         frame = new JFrame("Breakout");
         frame.setSize(1024, 576);
      	//centers the frame
         frame.setLocationRelativeTo(null);
      	//disables resizing
         frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setJMenuBar(main.theMenu());
         frame.setContentPane(new panel1());
         frame.setVisible(true);
      }
   
   }