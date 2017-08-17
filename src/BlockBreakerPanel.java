import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class BlockBreakerPanel extends JPanel implements KeyListener {
/*JPanel is the simplest container class. A panel provides space in which an application can attach any other component, 
 * including other panels.
The default layout manager for a panel is the FlowLayout layout manager.*/
/*
 There are top level containers such as JFrame. These can serve as the main window in which a GUI is built.

Then there are intermediate level containers. These must be placed in other containers, they cannot exist by themselves.
 They either help you organize components or they add functionality. A JPanel is a very simple container that helps you to 
 organize other components. While a JSplitPane adds the functionality of having two panes that are variable sized.

When you have a complex GUI you may want to use JPanels to organize various areas of your GUI and then add each of the 
panels to your JFrame.

In Java the Swing API makes use of the Composite Design Pattern. This means that you can compose very complex objects from 
other objects and still treat the composite objects the same way as the simple objects. So you can put a JPanel into a JPanel 
and it still behaves like a JPanel.

Think of it like a tackle box (or sewing kit). It is made of a big container. But rather than put many small objects 
into this big container and make it difficult to manage later you can place some smaller compartments inside the big box. 
Then hooks and sinkers etc go in the compartments. Its easier to manage. The big box is the JFrame and the compartments are the 
JPanels.
/*
A panel is a container.
It allows you to group components together
It allows you to devise complex interfaces, as each panel can have a different layout, allowing you to leverage the power of 
different layout managers.
It allows you to build reusable components and isolate responsibility
But most of all, it gives you the bases for deciding how the panel should be deployed. With a panel you can add it to a frame 
or applet or another component as needed...
A panel also makes a good surface onto which to perform custom painting. For all the benefits mentioned above - its isolated and 
reusable...*/

	Thread thread;
	Animate animate;
	int size =20;
	ArrayList<Block> b = new ArrayList<Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();
	
	Block paddle;
	
		BlockBreakerPanel()
		{
			paddle = new Block(150,480,160,30,"image-5.png");
			paddle.add(new Block(150,480,160,30,"image-5.png"));//here add method adds the specified component in the array list
			for(int i=0;i<12;i+=2)
			{
				b.add(new Block((i*40),0,80,30,"image-1.png"));
				
			}
			for(int i=0;i<12;i+=2)
			{
				b.add(new Block((i*40),30,80,30,"image-2.png"));
				
			}
			for(int i=0;i<12;i+=2)
			{
				b.add(new Block((i*40),60,80,30,"image-3.png"));
				
			}
			for(int i=0;i<12;i+=2)
			{
				b.add(new Block((i*40),90,80,30,"image-4.png"));
				
			}
			Random random = new Random();
			b.get(random.nextInt(24)).powerup=true;//http://stackoverflow.com/questions/10259599/how-to-use-arraylists-get-method
			b.get(random.nextInt(24)).powerup=true;//will get the random block
			b.get(random.nextInt(24)).powerup=true;
			b.get(random.nextInt(24)).powerup=true;
			b.get(random.nextInt(24)).powerup=true;
			b.get(random.nextInt(24)).powerup=true;
			ball.add(new Block(220,437,20,20,"image-6.png"));
			
			addKeyListener(this);
			setFocusable(true);
		}
		
		
		
		public void paintComponent(Graphics g)//paintComponent  method is automatically created when we create the panel
		{
			/*
			 Calling object.paintComponent(g) is an error.
			Instead this method is called automatically when the panel is created. The paintComponent() method can also be called explicitly 
			by the repaint() method defined in Component class.
			The effect of calling repaint() is that Swing automatically clears the graphic on the panel and executes the paintComponent 
			method to redraw the graphics on this panel.
			http://stackoverflow.com/questions/15544549/how-does-paintcomponent-work
			*/
			
			
			super.paintComponent(g);//http://www.dreamincode.net/forums/topic/80369-what-does-the-superpaintcomponentg-do/
			//http://stackoverflow.com/questions/28724609/what-does-super-paintcomponentg-do
			//g.setColor(Color.white);
			setBackground(Color.white);
			
			for(Block b1 : b)
			{
				b1.draw(g, this);//here this is refering to Jpanel which act as imageobserver to ensure image is fully loaded
			}
			for(Block b1 : ball)
			{
				b1.draw(g, this);//here this is refering to Jpanel which act as imageobserver to ensure image is fully loaded
			}
			for(Block p : powerup)
			{
				
				p.draw(g, this);//here this is refering to Jpanel which act as imageobserver to ensure image is fully loaded
			}
			paddle.draw(g, this);
		}
		int count=0;
		public void update()
		{
			for(Block p : powerup)
			{
				p.y+=1;
				if(p.intersects(paddle)&&!p.destroyed)
				{
					p.destroyed=true;
					ball.add(new Block(paddle.dx+120,450,20,20,"image-6.png"));
				}
			}
			for(Block b2 : ball)
			{
				b2.x+=b2.dx;
				if(b2.x>(getWidth()-size) && b2.dx>0 || b2.x<0 )//this is used so that ball doesnt go outside the JFrame
					b2.dx*=-1;//-1 for opposite direction
				if(b2.y<0 || b2.intersects(paddle))//this is used so that ball go in opposite direction when collide with frame
					b2.dy*=-1;//-1 for opposite direction
				b2.y+=b2.dy;
				for(Block b1 : b)
				{ 
					if((b1.left.intersects(b2)||b1.right.intersects(b2))&&!b1.destroyed)//here (!destroyed) means false and (destroyed) means true
					{
						b1.destroyed=true;
						count++;
						b2.dx*=-1;//-1 for opposite direction so that ball also bounce when collides with the blocks
						if(b1.powerup)
							powerup.add(new Block(b1.x,b1.y,7,7,"image-7.png"));
					}
					else if(!b1.destroyed&&b2.intersects(b1))
						{
							b1.destroyed=true;
							count++;
							b2.dy*=-1;
							if(b1.powerup)
								powerup.add(new Block(b1.x,b1.y,7,7,"image-7.png"));
						}
				}
			}

		repaint();//http://stackoverflow.com/questions/10768619/paint-and-repaint-in-java
		/* You need to call repaint() when you want to re-draw your GUI because you have changed something inside.
			If you want to delete a button, you need to remove it (or make it invisible) then you need to call validate() or
 			repaint() to re-calculate (re-draw) the GUI.
		 */			
		if(count==b.size()){
				JOptionPane.showMessageDialog(null, "You sucessfully completed the game");
				thread.stop();
				return;
			}
		}
		
		public void keyPressed(KeyEvent e) {//http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.htmls 
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				animate = new Animate(this);
				//there is a cycle which is happening again and again
				/*
				 the cycle is as follows
				 1)first repaint is called so as to clear the screen
				 2)second when key is presssed ie. left or right then super.paintcomponent make sure that background 
				 color behind componenets remains same in this case it is white 
				 */
				thread = new Thread(animate);
				thread.start();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0)
			{
				paddle.x-=20;
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width))
			{
				paddle.x+=20;
			}
		
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}

//Conclusion
/*
 * Constructor BlockBreakerPanel is used to add the copmonents in arraylist that we want on the screen.
 * paintComponent(Graphics g) is called automatically, and when there is change in the screen.By change in the screen mean
 * that when ball hits the block it has to disappear,this is done by setting destroyed=true.As block will only remain on the 
 * screen till destroyed value is false.So when destroyed value set to true in update method then the paintComponent(Graphics g)
 * is called again to disappear the block.
 * After that all the motion is performed by thread in Animate class which continously(intervals of .001sec) calls update method 
 * in which all code of movement for paddle, ball ,powerup is defined.
 *     
 */

