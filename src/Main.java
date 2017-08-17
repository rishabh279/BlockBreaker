import java.awt.Color;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String args[])
	{
		JFrame f = new JFrame("Block Breaker");
		f.getContentPane().setBackground(Color.white);
		BlockBreakerPanel panel = new BlockBreakerPanel();
		f.getContentPane().add(panel);
		f.setVisible(true);
		f.setSize(490,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		
	}
		
}
/*
In this simple project, we have first created a JFrame .Then we have set the properties of frame like setSize(490,600);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setResizable(false);
All the work that we are doing is in a panel the reason is that we can organize the components in  a proper way.Then we created  a 
blockbreaker class which extends JPanel.First we created an arraylist in which we add blocks using the the add() method.We created a block
class to draw the blocks.Then we override the paintComponent(Graphics g) method in order to dispaly the block on the frame.After 
that we create an animate class for moving  paddle in motion.For that we created a paddle then add it in the arrraylist of paddle.
Then to draw in the frame we use super.paintcompomnent(g) method which ensures that as we move the paddle left or right only that 
part of the frame is painted with brown color that lies within the paddle and its boundary.now we assosiate event handling using 
KeyListener.As the position of paddle is changed we need to use repaint() method which is to be used whenever something is changed
in the  frame.we  use repaint method by creating an animate class and the calling update method in an infinite loop so as whenever
position of paddel is changed  loop calls update method which further execute repaint method.
*/