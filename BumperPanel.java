	//Breakout
	//Zach Knox, Jack Fleisher, Spencer Harding
	
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   public class BumperPanel extends JPanel
   {
      private static final int FRAME = 900;
      private static final Color BACKGROUND = new Color(204, 204, 204);
      private static final Color BALL_COLOR = Color.BLACK;
      private static final Color BUMPER_COLOR = Color.GREEN;
      private static final double BALL_DIAM = 50;
      private static final int BUMPER_X_WIDTH = 150;
      private static final int BUMPER_Y_WIDTH = 25;
   
      private BufferedImage myImage;
      private Graphics myBuffer;
      private Ball ball;
      //private Polkadot prize;
      private Bumper bumper, gg;
      private Bumper[] blocks;
      private int hits, lives, blocksDead;
      private Timer timer, keytime;    
      
      public BumperPanel()
      {
         myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         int hits = 0;
         int xPos = (int)(Math.random()*(FRAME-100) + 50);
         int yPos = (int)(Math.random()*(FRAME-100)+ 50);
         int xPosp = (int)(Math.random()*(FRAME-100) + 50);
         int yPosp = (int)(Math.random()*(FRAME-100)+ 50);
         int xPosb = (int)(Math.random()*(FRAME-100) + 50);
         int yPosb = (int)(Math.random()*(FRAME-100) + 50);
         ball = new Ball(400, 800, BALL_DIAM, BALL_COLOR);
         ball.setdx(3);
         ball.setdy(5);
         //prize = new Polkadot(xPosp, yPosp, PRIZE_DIAM, PRIZE_COLOR);
         bumper = new Bumper(375, 850, BUMPER_X_WIDTH, BUMPER_Y_WIDTH, BUMPER_COLOR);
         blocks = new Bumper[36];
			gg = new Bumper(0, 899, 900, 10, BACKGROUND);

         int blockCol = 4;
         int blockRow = 9;
         int x = 0;
         int Row = 0;
         int Col = 0;
         for(int i = 0; i < blockCol; i++)
         {
            for(int j = 0; j < blockRow; j++)
            {
               blocks[x] = new Bumper();
               blocks[x].setX(Row);
               blocks[x].setY(Col);
               blocks[x].setDiameter(100);
               blocks[x].setRadius(32);
            
               int rand = (int)(Math.random()*5+1);
               switch (rand) {
                  case 1:
                     blocks[x].setColor(Color.RED);
                     break;
                  case 2:
                     blocks[x].setColor(Color.BLUE);
                     break;
                  case 3:
                     blocks[x].setColor(Color.ORANGE);
                     break;
                  case 4:
                     blocks[x].setColor(Color.MAGENTA);
                     break;
                  case 5:
                     blocks[x].setColor(Color.BLUE);
                     break;
               }
            
               Row += 100;
               x++;
            }
            Col += 32;
            Row = 0;
         }
         hits = 0;
			blocksDead = 0;
			lives = 3;
         timer = new Timer(5, new Listener());
         timer.start();
         
         addKeyListener(new Key());
         setFocusable(true);
      }
      
      public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
          
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            // clear buffer and move ball
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0,0,FRAME,FRAME); 
            ball.move(FRAME, FRAME);
            
            // check for collisions
            BumperCollision.collide(bumper, ball); 	
         	
            // draw ball, bumper
            ball.draw(myBuffer);
            bumper.draw(myBuffer);

            for(int i = 0; i < 36; i++)
            {
               blocks[i].draw(myBuffer);
               while(blocks[i].inBumper(ball))
               {
					if(blocks[i].getColor() == Color.RED)
					{
					   BumperCollision.collide(blocks[i], ball);
						blocks[i].setColor(Color.WHITE);
                  hits+= 100;
					}
					else if(blocks[i].getColor() == Color.WHITE)
					{
					   BumperCollision.collide(blocks[i], ball);
						blocks[i].setColor(Color.BLUE);
                  hits+= 100;
					}
					else{
                  BumperCollision.collide(blocks[i], ball);
                  blocks[i].setX(10000);
                  blocks[i].setY(10000);
                  hits+= 100;
						blocksDead += 1;
						}
               }
            }
	            gg.draw(myBuffer);
               while(gg.inBumper(ball))
               {
                  BumperCollision.collide(gg, ball);
                  ball.setX(400);
						ball.setY(800);
						bumper.setX(350);
						bumper.setY(850);
                  lives -= 1;
               }
					
           if(blocksDead == 36)
			  	{
				myBuffer.setColor(Color.black);
            myBuffer.setFont(new Font("Segoe UI Semibold", Font.BOLD, 48));
				myBuffer.drawString("YOU WIN! Your score is: " + (hits+(lives*100)), 100, 350);
				myBuffer.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
				myBuffer.drawString("\"You...won...the...game\" ~George Washington Carver", 100, 450);
				timer.stop();
				}
				
           if(lives < 0)
			  	{
				myBuffer.setColor(Color.black);
            myBuffer.setFont(new Font("Segoe UI Semibold", Font.BOLD, 48));
				myBuffer.drawString("YOU LOSE! Your score is: " + (hits+(lives*100)), 100, 350);
				myBuffer.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
				myBuffer.drawString("\"You...are...bad...at...the...game\" ~George Washington Carver", 100, 450);
				timer.stop();
				}
         
            // update hits on buffer
            myBuffer.setColor(Color.black);
            myBuffer.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
            myBuffer.drawString("Score: " + hits, FRAME - 150, 25);
            myBuffer.drawString("Lives: " + lives, 0 + 150, 25);
            repaint();
         }
      } 

      private class Key extends KeyAdapter
      {
         public void keyPressed(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_A && bumper.getX() >= 0)
               bumper.setX(bumper.getX()-10);
            if(e.getKeyCode() == KeyEvent.VK_D && bumper.getX() <= FRAME- bumper.getXWidth())
               bumper.setX(bumper.getX()+10);
         }
      }
   }
   //"I...like...break...out" ~Abraham Lincoln