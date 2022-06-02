//TOP DOWN TANKS, MADE BY ELLIL, VINCENT, BRANDON
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class tankGame implements ActionListener{
	JFrame theFrame = new JFrame("Top Down Tanks");
	tankPanel thePanel = new tankPanel();
	Timer theTimer = new Timer(1000/60, this);
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			
		}
	}
	
	
	//Constructor
	public tankGame(){
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thePanel.setPreferredSize(new Dimension(1280, 720));
		theFrame.setContentPane(thePanel);
		theFrame.pack();
		theFrame.setVisible(true);
		theFrame.setResizable(false);
	}

	//main method
	public static void main (String[] args){
		new tankGame();
	}
}


