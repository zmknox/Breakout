//Breakout
//by Zach Knox, Jack Fleisher, and Spencer Harding

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;


   public class panel1 extends JPanel
	{
	public static int Row = 0;
	public static int Col = 0;
		public void paintComponent(Graphics g)
		{

			int[] blockRow = new int[8];
			int[] blockCol = new int[6];
			for(int i = 0; i < blockCol.length; i++)
			{
				for(int j = 0; j < blockRow.length; j++)
				{
				int rand = (int)(Math.random()*5+1);
				switch (rand) {
				case 1:
				g.setColor(Color.RED);
				break;
				case 2:
				g.setColor(Color.BLUE);
				break;
				case 3:
				g.setColor(Color.MAGENTA);
				break;
				case 4:
				g.setColor(Color.YELLOW);
				break;
				case 5:
				g.setColor(Color.GREEN);
				break;
				}
				g.fillRect(Row, Col, 128, 32);
				Row += 128;
				}
				Col += 32;
				Row = 0;
			}
						//Draws Title Screen
			g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 240));
			g.setColor(Color.BLACK);
			g.drawString("Breakout", 0, 185);
			g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
			g.drawString("by Zach Knox, Jack Fleisher, and Spencer Harding", 19, 220);
  			g.drawString("\"I...love...break...out\" ~Abraham Lincoln", 19, 246);	
		}

      public panel1()
      {
			setLayout(new BorderLayout());
			
         JButton button = new JButton("Begin");
         button.setPreferredSize( new Dimension(100, 50));
         button.addActionListener(new Listener());
         add(button, BorderLayout.SOUTH);
			JLabel pleft = new JLabel("                                                                         ");
			add(pleft, BorderLayout.WEST);
			JLabel pright = new JLabel("                                                                         ");
			add(pright, BorderLayout.EAST);
			JLabel pup = new JLabel("");
			add(pup, BorderLayout.NORTH);
	   }
      private class Listener extends main implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         frame.dispose();
			      	//Creates a frame
         JFrame lvls = new JFrame("Breakout");
         lvls.setSize(900, 900);
      	//centers the frame
         lvls.setLocationRelativeTo(null);
      	//disables resizing
         lvls.setResizable(false);
         lvls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         lvls.setJMenuBar(main.theMenu());
         lvls.setContentPane(new BumperPanel());
         lvls.setVisible(true);
 
         }
      }


		
   }