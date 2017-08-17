import java.awt.Component;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle {
	Image pic;
	int dx =4;
	int dy =-3;
	Rectangle left,right;
	boolean destroyed=false;//used for destroying the blocks 
	boolean powerup =false;
	Block(int a,int b,int w,int h,String s )
	{
		x=a;
		y=b;
		width=w;
		height=h;
		pic=Toolkit.getDefaultToolkit().getImage(s);
		left= new Rectangle(a-1,b,1,h);//??
		right= new Rectangle(a+w+1,b,1,h);//??
	}
	public void draw(Graphics g, Component c)
	{
		if(!destroyed)
			g.drawImage(pic ,x,y,width, height,c);//boolean Graphics.drawImage(Image img,int x, int ImageObserver observer);
		
	}
	

	
	
}
/*
 SYNCHRONOUS

You are in a queue to get a movie ticket. You cannot get one until everybody in front of you gets one, and the same
 applies to the people queued behind you.

ASYNCHRONOUS

You are in a restaurant with many other people. 
You order your food. Other people can also order their food, they don't have to wait for your food to be cooked and served to 
you before they can order. In the kitchen restaurant workers are continuously cooking, serving, and taking orders. 
People will get their food served as soon as it is cooked.
 */
/*
Image objects aren't necessarily completely loaded. If Graphics.drawImage is invoked on an incomplete image 
it will draw as much of the image as it can, and then alert the ImageObserver (by calling imageUpdate) when more of the image is 
loaded.

The ImageObserver can be null, in which case you won't get any notification. This is common if the images are known to be loaded, 
or if there's already another mechanism doing repaints.

Note that Component implements ImageObserver, and its imageUpdate method will cause a repaint on the affected area.*/
 

