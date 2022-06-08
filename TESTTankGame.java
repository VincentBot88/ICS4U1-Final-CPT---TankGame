//Top Down Tanks by Vincent, Ellil, Brandon
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TESTTankGame implements ActionListener, KeyListener, MouseMotionListener{
	//Properties
	JFrame theFrame = new JFrame("Top Down Tanks");
	TESTTankPanel thePanel = new TESTTankPanel();
	Timer theTimer = new Timer(1000/60, this);
	public JLabel resultLabel;
	
	//Methods
	public void mouseMoved(MouseEvent evt){
		thePanel.mouseX = evt.getX();
		thePanel.mouseY = evt.getY();
	}
	public void mouseDragged(MouseEvent evt){
		
	}
	
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thePanel.repaint();
		}	
	}
	
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			thePanel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 's'){
			thePanel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 'a'){
			thePanel.intP1DefX = 0;
		}else if(evt.getKeyChar() == 'd'){
			thePanel.intP1DefX = 0;
		}
		if(evt.getKeyCode() == 32){
			
		}
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			thePanel.intP1DefY = -2;
		}else if(evt.getKeyChar() == 's'){
			thePanel.intP1DefY = +2;
		}else if(evt.getKeyChar() == 'a'){
			thePanel.intP1DefX = -2;
		}else if(evt.getKeyChar() == 'd'){
			thePanel.intP1DefX = +2;
		}
		
		if(evt.getKeyCode() == 32){
			thePanel.bulletVelocity = 4;
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}
	
	//Constructor
	public TESTTankGame(){
		thePanel.setPreferredSize(new Dimension(1280, 720));
		thePanel.addMouseMotionListener(this);
		theFrame.setContentPane(thePanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setVisible(true);
		theFrame.setResizable(false);
		theFrame.addKeyListener(this);
		theTimer.start();
	}

	//main method
	public static void main(String[] args){
		new TESTTankGame();
	}

}
