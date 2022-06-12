//Top Down Tanks by Vincent, Elill, Brandon
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;

public class TankGame implements ActionListener, KeyListener, MouseMotionListener, MouseListener{
	//Properties
	JFrame theFrame = new JFrame("Top Down Tanks by Vincent, Elill, Brandon");
	MenuPanel thePanel = new MenuPanel();
	GamePanel theGamePanel = new GamePanel();
	Timer theTimer = new Timer(1000/60, this);
	public JLabel resultLabel;
	JButton QuitGameButton = new JButton();
	JButton JoinGameButton = new JButton();
	JButton HostGameButton = new JButton();
	JButton HostBackButton = new JButton();
	JButton JoinBackButton = new JButton();
	JButton HostButton = new JButton();
	JButton StartButton = new JButton();
	JButton JoinButton = new JButton();
	
	JTextField IPTextField = new JTextField();
	JTextField PortTextField = new JTextField();
	
	JLabel IPLabel = new JLabel();
	JLabel PortLabel = new JLabel();
	JLabel JoinLabel = new JLabel();
	JLabel HostLabel = new JLabel();
	
	
	//Methods
	public void mouseMoved(MouseEvent evt){

	}
	public void mousePressed(MouseEvent evt) {
		if(evt.getButton()==1 && theGamePanel.bulletVelocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bulletVelocity = 10;
		}
	}
	
	public void mouseReleased(MouseEvent evt){
	}
	public void mouseDragged(MouseEvent evt){
	}
	public void mouseClicked(MouseEvent evt){	
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseExited(MouseEvent evt){
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thePanel.repaint();
			theGamePanel.repaint();
		}
		if(evt.getSource() == QuitGameButton){
			System.exit(0);
		}
		if(evt.getSource() == JoinGameButton){
			thePanel.remove(HostGameButton);
			thePanel.remove(JoinGameButton);
			JoinLabel.setForeground(Color.RED);
			JoinLabel.setFont(new Font("Serif", Font.BOLD, 50));
			thePanel.add(JoinLabel);
			thePanel.add(IPTextField);
			thePanel.add(PortTextField);
			PortLabel.setForeground(Color.RED);
			PortLabel.setFont(new Font("Serif", Font.BOLD, 30));
			thePanel.add(PortLabel);
			IPLabel.setForeground(Color.RED);
			IPLabel.setFont(new Font("Serif", Font.BOLD, 30));
			thePanel.add(IPLabel);
			thePanel.add(JoinBackButton);
			thePanel.add(JoinButton);
		}
		if(evt.getSource() == HostGameButton){
			thePanel.remove(JoinGameButton);
			thePanel.remove(HostGameButton);
			HostLabel.setForeground(Color.RED);
			HostLabel.setFont(new Font("Serif", Font.BOLD, 50));
			thePanel.add(HostLabel);
			thePanel.add(IPTextField);
			thePanel.add(PortTextField);
			PortLabel.setForeground(Color.RED);
			PortLabel.setFont(new Font("Serif", Font.BOLD, 30));
			thePanel.add(PortLabel);
			IPLabel.setForeground(Color.RED);
			IPLabel.setFont(new Font("Serif", Font.BOLD, 30));
			thePanel.add(IPLabel);
			thePanel.add(HostBackButton);
			thePanel.add(HostButton);
			thePanel.add(StartButton);
		}
		if(evt.getSource() == HostBackButton){
			thePanel.remove(HostLabel);
			thePanel.remove(IPTextField);
			thePanel.remove(PortTextField);
			thePanel.remove(PortLabel);
			thePanel.remove(IPLabel);
			thePanel.remove(HostBackButton);
			thePanel.remove(HostButton);
			thePanel.remove(StartButton);
			thePanel.add(JoinGameButton);
			thePanel.add(HostGameButton);
										
		}
		if(evt.getSource() == JoinBackButton){
			thePanel.remove(JoinLabel);
			thePanel.remove(IPTextField);
			thePanel.remove(PortTextField);
			thePanel.remove(PortLabel);
			thePanel.remove(IPLabel);
			thePanel.remove(JoinBackButton);
			thePanel.remove(JoinButton);
			thePanel.add(HostGameButton);
			thePanel.add(JoinGameButton);
			
		} 
		if(evt.getSource() == JoinButton){
			//Joins the server
			//When the player joins, print something just above the lines saying "Players in lobby: P1, P2 ...."
			
		}
		if(evt.getSource() == HostButton){
			//Opens server and allows players to join
			//When a player joins, print something just above the lines saying "Players: P1, P2 ...."
		}
		if(evt.getSource() == StartButton){
			//Clears screen then start the gameplay
			thePanel.removeAll();
			theFrame.setContentPane(theGamePanel);
			theFrame.pack();
			theFrame.requestFocus();	
		}
	}
	
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			theGamePanel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 's'){
			theGamePanel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 'a'){
			theGamePanel.intP1DefX = 0;
		}else if(evt.getKeyChar() == 'd'){
			theGamePanel.intP1DefX = 0;
		}
		if(evt.getKeyCode() == 32){
			
		}
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			theGamePanel.intP1DefY = -2;
		}else if(evt.getKeyChar() == 's'){
			theGamePanel.intP1DefY = +2;
		}else if(evt.getKeyChar() == 'a'){
			theGamePanel.intP1DefX = -2;
		}else if(evt.getKeyChar() == 'd'){
			theGamePanel.intP1DefX = +2;
		}
		
		if(evt.getKeyCode() == 32){
			
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}
	
	//Constructor
	public TankGame(){
		//Window Elements Essentials
		thePanel.setPreferredSize(new Dimension(1280, 720));
		theGamePanel.setPreferredSize(new Dimension(1280, 720));
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thePanel.addMouseMotionListener(this);
		theGamePanel.addMouseMotionListener(this);
		theGamePanel.addMouseListener(this);
		theFrame.addKeyListener(this);
		theGamePanel.addKeyListener(this);
		thePanel.setLayout(null);
		theFrame.setContentPane(thePanel);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		theTimer.start();
		
		//Quit Button
		QuitGameButton = new JButton("Quit Game");
		QuitGameButton.setSize(300, 50);
		QuitGameButton.setLocation(50, 440);
		QuitGameButton.addActionListener(this);
		thePanel.add(QuitGameButton);
		
		//Host Game Back Button
		HostBackButton = new JButton("Back");
		HostBackButton.setSize(100, 50);
		HostBackButton.setLocation(750, 400);
		HostBackButton.addActionListener(this);
		
		//Join Game Back Button
		JoinBackButton = new JButton("Back");
		JoinBackButton.setSize(100, 50);
		JoinBackButton.setLocation(750, 400);
		JoinBackButton.addActionListener(this);
		
		//Host Button
		HostButton = new JButton("Host");
		HostButton.setSize(100, 50);
		HostButton.setLocation(950, 400);
		HostButton.addActionListener(this);
		
		//Start Button (Host Side)
		StartButton  = new JButton("Start");
		StartButton.setSize(100, 50);
		StartButton.setLocation(1100, 400);
		StartButton.addActionListener(this);
		
		//Join Button
		JoinButton = new JButton("Join");
		JoinButton.setSize(100, 50);
		JoinButton.setLocation(950, 400);
		JoinButton.addActionListener(this);
		
		//Host Game Button
		HostGameButton = new JButton("Host Game");
		HostGameButton.setSize(300, 50);
		HostGameButton.setLocation(50, 240);
		HostGameButton.addActionListener(this);
		thePanel.add(HostGameButton);
		
		//Host Game Label
		HostLabel = new JLabel("Hosting Game: ");
		HostLabel.setSize(400, 70);
		HostLabel.setLocation(750, 50);
		
		//Join Game Button
		JoinGameButton = new JButton("Join Game");
		JoinGameButton.setSize(300, 50);
		JoinGameButton.setLocation(50, 340);
		JoinGameButton.addActionListener(this);
		thePanel.add(JoinGameButton);
		
		//Join Game Label
		JoinLabel = new JLabel("Joining Game: ");
		JoinLabel.setSize(400, 70);
		JoinLabel.setLocation(750, 50);
		
		//IP Textfield
		IPTextField.setSize(200, 50);
		IPTextField.setLocation(840, 180);
		//IP Label
		IPLabel = new JLabel("IP: ");
		IPLabel.setSize(400, 50);
		IPLabel.setLocation(750, 170);
		
		//Port Textfield
		PortTextField.setSize(200, 50);
		PortTextField.setLocation(840, 250);		
		//Port Label
		PortLabel = new JLabel("Port: ");
		PortLabel.setSize(400, 50);
		PortLabel.setLocation(750, 250);

	}

	//main method
	public static void main(String[] args){
		new TankGame();
	}

}
