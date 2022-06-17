import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements ActionListener{
	//Properties
	//Player positions, bullet positions
	int intP1Y = 100;
	int intP1X = 100;
	int intP1DefX = 0;
	int intP1DefY = 0;
	
	String strP1 = null;
	String strP2 = null;
	String strP3 = null;
	String strP4 = null;
	String strP5 = null;
	
	int intPlayers = 0;
	
	int intP2Y = 500;
	int intP2X = 600;
	int intP2DefX = 0;
	int intP2DefY = 0;
	
	int intP3Y = 300;
	int intP3X = 400;
	int intP3DefX = 0;
	int intP3DefY = 0;
	
	int intP4Y = 200;
	int intP4X = 300;
	int intP4DefX = 0;
	int intP4DefY = 0;
	
	int mouseX;
	int mouseY;
	
	int cursorX;
	int cursorY;

	double bullet1X = intP1X + 25;
	double bullet1Y = intP1Y + 20;
	
	double bullet2X = intP2X + 25;
	double bullet2Y = intP2Y + 20;
	
	double bullet3X = intP3X + 25;
	double bullet3Y = intP3Y + 20;
	
	double bullet4X = intP4X + 25;
	double bullet4Y = intP4Y + 20;
	
	boolean fire = false;
	double bullet1Velocity = 0;
	double bullet2Velocity = 0;
	double bullet3Velocity = 0;
	double bullet4Velocity = 0;
	
	int wallX;
	int wallY;
	
	String strIdentity = "";
	
	//Player images
	BufferedImage P1img = null;
	BufferedImage P2img = null;
	BufferedImage P3img = null; 
	BufferedImage P4img = null;
	BufferedImage ground = null;
	BufferedImage wall = null;
	BufferedImage sand = null;
	BufferedImage lava = null;
	SuperSocketMaster ssm;
	String selectedMap;
	
	interface gameInterface{
		void paintComponent();
	}
	
	//Methods
	public void actionPerformed(ActionEvent evt){
			
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Map loading
		BufferedReader GrasslandMap = null;
		BufferedReader DesertMap = null;
		BufferedReader LavaMap = null;
		
		try{
			GrasslandMap = new BufferedReader(new FileReader("Grassland.csv"));
			DesertMap = new BufferedReader(new FileReader("Desert.csv"));
			LavaMap = new BufferedReader(new FileReader("Lava.csv"));
		}catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
			
		String strData[][];
		strData = new String [18][24];
			
		String strLine = "";
		String strRow[];
			
		int row;
		int col;
		//Drawing the respective maps
		if (selectedMap == "Grassland") {
			for(row = 0; row < 18; row++){
				try{
					strLine = GrasslandMap.readLine();
				}catch(IOException e){
					System.out.println("Error reading from file");
				}
				strRow = strLine.split(","); //Split based on commas
				//draws map
				for(col = 0; col < 24; col++){
					strData[row][col] = strRow[col];
					if(strData[row][col].equals("g")){
						g.drawImage(ground, col * 40, row * 40, null);
					}else if(strData[row][col].equals("w")){
						g.drawImage(wall, col * 40, row * 40, null);
						Rectangle wallRect = new Rectangle(col * 40, row * 40, 40, 40);
						Rectangle player1Rect = new Rectangle(intP1X, intP1Y, 55, 49);
						Rectangle bullet1Rect = new Rectangle((int)bullet1X, (int)bullet1Y, 10, 10);
							if(player1Rect.intersects(wallRect) && intP1DefX == -2){
								intP1DefX = 2;
								intP1X = intP1X + 4;
							}else if(player1Rect.intersects(wallRect) && intP1DefX == 2){
								intP1DefX = -2;
								intP1X = intP1X - 4;
							}else if(player1Rect.intersects(wallRect) && intP1DefY == -2){
								intP1DefY = 2;
								intP1Y = intP1Y + 4;
							}else if(player1Rect.intersects(wallRect) && intP1DefY == 2){
								intP1DefY = -2;
								intP1Y = intP1Y - 4;
							}if (bullet1Rect.intersects(wallRect)){
								double bullet1X = intP1X + 25;
								double bullet1Y = intP1Y + 20;
							}
					}
				}
			}
		} else if (selectedMap == "Lava") {
			for(row = 0; row < 18; row++){
				try{
					strLine = LavaMap.readLine();
				}catch(IOException e){
					System.out.println("Error reading from file");
				}
				strRow = strLine.split(","); //Split based on commas
				//draws map
				for(col = 0; col < 24; col++){
					strData[row][col] = strRow[col];
					if(strData[row][col].equals("w")){
						g.drawImage(wall, col * 40, row * 40, null);
					} else if(strData[row][col].equals("l")){
						g.drawImage(lava, col * 40, row * 40, null);
					}
				}
			}
		} else if (selectedMap == "Desert") {
			for(row = 0; row < 18; row++){
				try{
					strLine = DesertMap.readLine();
				}catch(IOException e){
					System.out.println("Error reading from file");
				}
				strRow = strLine.split(","); //Split based on commas
				//draws map
				for(col = 0; col < 24; col++){
					strData[row][col] = strRow[col];
					if(strData[row][col].equals("w")){
						g.drawImage(wall, col * 40, row * 40, null);
					} else if(strData[row][col].equals("s")){
						g.drawImage(sand, col * 40, row * 40, null);
					}
				}
			}
		}
		//Closing the map
		g.setColor(Color.BLACK);
		g.fillRect(960, 0, 320, 720);
		try{
			GrasslandMap.close();
			DesertMap.close();
			LavaMap.close();
		}catch(IOException e){
			System.out.println("Unable to close file");
		}	
		
		//Bullet physics
		//mouseX/Y = current x/y location of the mouse
		//originX/Y = x/y location of where the bullet is being shot from
		double angle1 =(Math.atan2(mouseX - intP1X, mouseY - intP1Y));
		double angle2 =(Math.atan2(mouseX - intP2X, mouseY - intP2Y));
		double angle3 =(Math.atan2(mouseX - intP3X, mouseY - intP3Y));
		double angle4 =(Math.atan2(mouseX - intP4X, mouseY - intP4Y));
		
		double x1Velocity = ((bullet1Velocity) * Math.cos(angle1));
		double y1Velocity = ((bullet1Velocity) * Math.sin(angle1));
		
		double x2Velocity = ((bullet2Velocity) * Math.cos(angle2));
		double y2Velocity = ((bullet2Velocity) * Math.sin(angle2));
		
		double x3Velocity = ((bullet3Velocity) * Math.cos(angle3));
		double y3Velocity = ((bullet3Velocity) * Math.sin(angle3));
		
		double x4Velocity = ((bullet4Velocity) * Math.cos(angle4));
		double y4Velocity = ((bullet4Velocity) * Math.sin(angle4));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)bullet1Y, (int)bullet1X, 10, 10);
		g.fillRect((int)bullet2Y, (int)bullet2X, 10, 10);
		g.fillRect((int)bullet3Y, (int)bullet3X, 10, 10);
		g.fillRect((int)bullet4Y, (int)bullet4X, 10, 10);
		
		bullet1X = bullet1X + x1Velocity;
		bullet1Y = bullet1Y + y1Velocity;
		
		bullet2X = bullet2X + x2Velocity;
		bullet2Y = bullet2Y + y2Velocity;
		
		bullet3X = bullet3X + x3Velocity;
		bullet3Y = bullet3Y + y3Velocity;
		
		bullet4X = bullet4X + x4Velocity;
		bullet4Y = bullet4Y + y4Velocity;
		
		if(bullet1Velocity == 0){
			bullet1X = intP1Y + 25;
			bullet1Y = intP1X + 20;
		}
		if(bullet2Velocity == 0){	
			bullet2X = intP2Y + 25;
			bullet2Y = intP2X + 20;
		}
		if(bullet3Velocity == 0){
			bullet3X = intP3Y + 25;
			bullet3Y = intP3X + 20;
		}
		if(bullet4Velocity == 0){
			bullet4X = intP4Y + 25;
			bullet4Y = intP4X + 20;
		}
		if(bullet1X > 720 || bullet1Y > 1000 || bullet1X < 0 || bullet1Y < 0){
			bullet1Velocity = 0;
			bullet1X = intP1Y;
			bullet1Y = intP1X;
		}
		if(bullet2X > 720 || bullet2Y > 1000 || bullet2X < 0 || bullet2Y < 0){
			bullet2Velocity = 0;
			bullet2X = intP2Y;
			bullet2Y = intP2X;
		}
		if(bullet3X > 720 || bullet3Y > 1000 || bullet3X < 0 || bullet3Y < 0){
			bullet3Velocity = 0;
			bullet3X = intP3Y;
			bullet3Y = intP3X;
		}
		if(bullet4X > 720 || bullet4Y > 1000 || bullet4X < 0 || bullet4Y < 0){
			bullet4Velocity = 0;
			bullet4X = intP4Y;
			bullet4Y = intP4X;
		}
		//Player Rotation
        float xDistance = cursorX - intP1X;
        float yDistance = cursorY - intP1Y;
        double rotationRequired = (Math.toDegrees(Math.atan2(yDistance, xDistance))/50);

        //Rotation information
        double locationX = P1img.getWidth() / 2;
        double locationY = P1img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        //Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(P1img, null), intP1X, intP1Y, null);
        
		//Drawing Players
		g.drawImage(P2img, intP2X, intP2Y, null);
		g.drawImage(P3img, intP3X, intP3Y, null);
		g.drawImage(P4img, intP4X, intP4Y, null);
		
		//Player movement
			intP1Y = intP1Y + intP1DefY;
			intP1X = intP1X + intP1DefX;
		
			intP2Y = intP2Y + intP2DefY;
			intP2X = intP2X + intP2DefX;
		
			intP3Y = intP3Y + intP3DefY;
			intP3X = intP3X + intP3DefX;
		
			intP4Y = intP4Y + intP4DefY;
			intP4X = intP4X + intP4DefX;
	}
	
	//Constructor
	public GamePanel(){
		super();
		try{
			P1img = ImageIO.read(this.getClass().getResourceAsStream("tank_blue.png"));
			P2img = ImageIO.read(this.getClass().getResourceAsStream("tank_red.png"));
			P3img = ImageIO.read(this.getClass().getResourceAsStream("tank_green.png"));
			P4img = ImageIO.read(this.getClass().getResourceAsStream("tank_orange.png"));
			wall = ImageIO.read(this.getClass().getResourceAsStream("building.jpg"));
			ground = ImageIO.read(this.getClass().getResourceAsStream("grass.jpg"));
			lava = ImageIO.read(this.getClass().getResourceAsStream("lava.png"));
			sand = ImageIO.read(this.getClass().getResourceAsStream("sand.jpg"));
		}catch (IOException e){
			System.out.println("Cant load images");
		}
	}



}
