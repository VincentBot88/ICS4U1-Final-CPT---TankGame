import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class TESTTankPanel extends JPanel{
	//Properties
	int intP1Y = 100;
	int intP1X = 100;
	int intP1DefX = 0;
	int intP1DefY = 0;
	int mouseX;
	int mouseY;
	double bulletX = intP1X;
	double bulletY = intP1Y;
	boolean fire = false;
	double bulletVelocity = 0; //however fast you want your bullet to travel
	BufferedImage P1img = null;
	//BufferedImage P2img = null;
	//BufferedImage P3img = null; 
	//BufferedImage P4img = null;
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1280, 720);
		//P1
		g.setColor(Color.BLACK);
		//g.fillRect(intP1X, intP1Y, 30, 30);
		g.drawImage(P1img, intP1X, intP1Y, null);
		// movement
		intP1Y = intP1Y + intP1DefY;
		intP1X = intP1X + intP1DefX;
		
		//bullet
		
        //mouseX/Y = current x/y location of the mouse
        //originX/Y = x/y location of where the bullet is being shot from
        double angle =(Math.atan2(mouseX - intP1X, mouseY - intP1Y));
        double xVelocity = ((bulletVelocity) * Math.cos(angle));
        double yVelocity = ((bulletVelocity) * Math.sin(angle));
		g.fillRect((int)bulletY, (int)bulletX, 10, 10);
		bulletX = bulletX + xVelocity;
		bulletY = bulletY + yVelocity;
		if(bulletVelocity == 0){
			bulletX = intP1Y;
			bulletY = intP1X;
		}
		if(bulletX > 720 || bulletY > 1280){
			System.out.println(bulletX);
			bulletVelocity = 0;
			bulletX = intP1Y;
			bulletY = intP1X;
		}
	}
	
	//Constructor
	public TESTTankPanel(){
		super();
		try{
			P1img = ImageIO.read(this.getClass().getResourceAsStream("tank_blue.png"));
			//P2img = ImageIO.read(this.getClass().getResourceAsStream("P2.png"));
			//P3img = ImageIO.read(this.getClass().getResourceAsStream("P3.png"));
			//P4img = ImageIO.read(this.getClass().getResourceAsStream("P4.png"));
		}catch (IOException e){
			System.out.println("Cant load images");
		}
	}



}
