import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuPanel extends JPanel{
	//Properties
	BufferedImage Titleimg = null;
	Color menuDecoration = new Color(31, 79, 21);
	Color menuDecoration2 = new Color(104, 1, 1);
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280, 720);
		
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
	
	}
	
	//Constructor
	public MenuPanel(){
		super();
		try{
			Titleimg = ImageIO.read(this.getClass().getResourceAsStream("title.png"));
		}catch (IOException e){
			System.out.println("Cant load images");
		}
	}



}
