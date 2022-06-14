import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.net.*;

public class GamePanel extends JPanel implements ActionListener{
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
	BufferedImage ground = null;
	BufferedImage wall = null;

	BufferedImage sand = null;
	BufferedImage lava = null;
	SuperSocketMaster ssm;
	//BufferedImage sand = null;
	//BufferedImage lava = null;
	
	interface gameInterface{
		void paintComponent();
	}


	//Methods
	public void actionPerformed(ActionEvent evt){
			
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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
		strData = new String [18][32];
			
		String strLine = "";
		String strRow[];
			
		int row;
		int col;
		for(row = 0; row < 18; row++){
			try{
				strLine = GrasslandMap.readLine();
			}catch(IOException e){
				System.out.println("Error reading from file");
			}
			strRow = strLine.split(","); //Split based on commas
			//draws map
			for(col = 0; col < 32; col++){
				strData[row][col] = strRow[col];
				if(strData[row][col].equals("g")){
					g.drawImage(ground, col * 40, row * 40, null);
				}else if(strData[row][col].equals("w")){
					g.drawImage(wall, col * 40, row * 40, null);
				} else if(strData[row][col].equals("l")){
					g.drawImage(lava, col * 40, row * 40, null);
				} else if(strData[row][col].equals("s")){
					g.drawImage(sand, col * 40, row * 40, null);
				}
			}
		}
		try{
			GrasslandMap.close();
			DesertMap.close();
			LavaMap.close();
		}catch(IOException e){
			System.out.println("Unable to close file");
		}
			
		//P1
		g.drawImage(P1img, intP1X, intP1Y, null);
		// movement
		intP1Y = intP1Y + intP1DefY;
		intP1X = intP1X + intP1DefX;
			
			
		//Bullet
			
		//mouseX/Y = current x/y location of the mouse
		//originX/Y = x/y location of where the bullet is being shot from
		double angle =(Math.atan2(mouseX - intP1X, mouseY - intP1Y));
		double xVelocity = ((bulletVelocity) * Math.cos(angle));
		double yVelocity = ((bulletVelocity) * Math.sin(angle));
		g.setColor(Color.BLACK);
		g.fillRect((int)bulletY, (int)bulletX, 10, 10);
		bulletX = bulletX + xVelocity;
		bulletY = bulletY + yVelocity;
		if(bulletVelocity == 0){
			bulletX = intP1Y;
			bulletY = intP1X;
		}

		if(bulletX > 720 || bulletY > 1280 || bulletX < 0 || bulletY < 0){
			//System.out.println(bulletX);
			bulletVelocity = 0;
			bulletX = intP1Y;
			bulletY = intP1X;
		}
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
			//lava = ImageIO.read(this.getClass().getResourceAsStream("lava.jpg"));
			//sand = ImageIO.read(this.getClass().getResourceAsStream("sand.jpg"));
		}catch (IOException e){
			System.out.println("Cant load images");
		}
	}



}
