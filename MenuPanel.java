import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuPanel extends JPanel{
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
	double bulletVelocity = 0; //However fast you want your bullet to travel
	BufferedImage P1img = null;
	BufferedImage P2img = null;
	BufferedImage P3img = null; 
	BufferedImage P4img = null;
	BufferedImage Titleimg = null;
	Color menuDecoration = new Color(31, 79, 21);
	Color menuDecoration2 = new Color(104, 1, 1);
	
	
		
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280, 720);
		
		//P1
		//g.drawImage(P1img, intP1X, intP1Y, null);**********************************************************************************************Uncomment it later on when u code the mechanics, vincent
		// movement
		intP1Y = intP1Y + intP1DefY;
		intP1X = intP1X + intP1DefX;
		
		//Title card
		g.drawImage(Titleimg, 100, 10, null);
		
		//Main Menu Decoration
		g.setColor(menuDecoration);
		g.fillRect(400, 0, 40, 720);
		g.setColor(Color.WHITE);
		g.fillRect(460, 0, 40, 720);
		g.setColor(menuDecoration);
		g.fillRect(520, 0, 40, 720);
		g.setColor(menuDecoration2);
		g.fillRect(0, 540, 400, 40);
		g.setColor(Color.WHITE);
		g.fillRect(0, 600, 400, 40);
		g.setColor(menuDecoration2);
		g.fillRect(0, 660, 400, 40);
		g.fillRect(560, 540, 720, 40);
		g.setColor(Color.WHITE);
		g.fillRect(560, 600, 720, 40);
		g.setColor(menuDecoration2);
		g.fillRect(560, 660, 720, 40);
	
		
		
		//Bullet
		
        //mouseX/Y = current x/y location of the mouse
        //originX/Y = x/y location of where the bullet is being shot from
        double angle =(Math.atan2(mouseX - intP1X, mouseY - intP1Y));
        double xVelocity = ((bulletVelocity) * Math.cos(angle));
        double yVelocity = ((bulletVelocity) * Math.sin(angle));
        g.setColor(Color.WHITE);
		//g.fillRect((int)bulletY, (int)bulletX, 10, 10);**********************************************************************************************Uncomment it later on when u code the mechanics, vincent
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
	public MenuPanel(){
		super();
		try{
			P1img = ImageIO.read(this.getClass().getResourceAsStream("tank_blue.png"));
			P2img = ImageIO.read(this.getClass().getResourceAsStream("tank_red.png"));
			P3img = ImageIO.read(this.getClass().getResourceAsStream("tank_green.png"));
			P4img = ImageIO.read(this.getClass().getResourceAsStream("tank_orange.png"));
			Titleimg = ImageIO.read(this.getClass().getResourceAsStream("title.png"));
		}catch (IOException e){
			System.out.println("Cant load images");
		}
	}



}
