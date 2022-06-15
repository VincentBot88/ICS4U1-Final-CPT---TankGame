//Top Down Tanks by Vincent, Elill, Brandon
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.net.*;
import javax.swing.JComponent.*;


public class TankGame implements ActionListener, KeyListener, MouseMotionListener, MouseListener, ItemListener{
	//Properties
	SuperSocketMaster ssm;
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
	String[] strMapSelects = {"Grassland", "Desert", "Lava"};
	JComboBox MapSelectBox = new JComboBox();
	
	JTextArea chatToReceive = new JTextArea();
	JTextField chatToSend = new JTextField();
	JScrollPane theScrollBar = new JScrollPane(chatToReceive);
	
	JTextField IPTextField = new JTextField("localhost");
	JTextField PortTextField = new JTextField("6112");
	
	JLabel IPLabel = new JLabel();
	JLabel PortLabel = new JLabel();
	JLabel JoinLabel = new JLabel();
	JLabel HostLabel = new JLabel();
	JLabel MapLabel = new JLabel();
	JLabel ChatLabel = new JLabel();
	
	int intJoin = 0;
	int intW = 0;
	String strLineSplit[];
	
	//Methods
	public void itemStateChanged(ItemEvent e){
		if(MapSelectBox.getSelectedItem().equals("Grassland"))
			
		if(MapSelectBox.getSelectedItem().equals("Desert"))
			System.out.println("Hi");
		if(MapSelectBox.getSelectedItem().equals("Lava"))
			System.out.println("Hi");
	}
	public void mouseMoved(MouseEvent evt){

	}
	public void mousePressed(MouseEvent evt) {
		if(evt.getButton()==1 && theGamePanel.bulletVelocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bulletVelocity = 10;
		}
		ssm.sendText("P1: Shot");
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
		if(evt.getSource() == ssm){
			String strIn = ssm.readText();
			System.out.println("tank position "+strIn);
			chatToReceive.append(ssm.readText() + "\n");
			chatToReceive.setCaretPosition(chatToReceive.getDocument().getLength());
			String strLine = ssm.readText();
			strLineSplit = strLine.split(",");
		}
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
			thePanel.add(MapSelectBox);
			thePanel.add(MapLabel);
			MapLabel.setForeground(Color.RED);
			MapLabel.setFont(new Font("Serif", Font.BOLD, 30));
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
			thePanel.remove(MapSelectBox);
			thePanel.remove(MapLabel);
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
		if(evt.getSource() == HostButton){
			//Opens server and allows players to join
			//When a player joins, print something just above the lines saying "Players: P1, P2 ...."
			HostButton.setEnabled(false);
			IPTextField.setEnabled(false);
			PortTextField.setEnabled(false);
			thePanel.add(StartButton);
			ssm = new SuperSocketMaster(Integer.parseInt(PortTextField.getText()), this);
			boolean tankConnect = ssm.connect();
			
		}
		if(evt.getSource() == JoinButton){
			//Joins the server
			//When the player joins, print something just above the lines saying "Players in lobby: P1, P2 ...."
			JoinButton.setEnabled(false); 
			IPTextField.setEnabled(false);
			PortTextField.setEnabled(false);
			ssm = new SuperSocketMaster(IPTextField.getText(), Integer.parseInt(PortTextField.getText()), this);
			boolean tankConnect = ssm.connect();
			//if(tankConnect){
				thePanel.removeAll();
				theFrame.setContentPane(theGamePanel);
				theFrame.pack();
				theFrame.requestFocus();
				theGamePanel.add(theScrollBar);
				theGamePanel.add(chatToSend);
				theGamePanel.add(ChatLabel);
				ChatLabel.setForeground(Color.RED);
			}
		if(evt.getSource() == StartButton){
			//Clears screen then start the gameplay
			thePanel.removeAll();
			theFrame.setContentPane(theGamePanel);
			theFrame.pack();
			theFrame.requestFocus();	
			theGamePanel.add(theScrollBar);
			theGamePanel.add(chatToSend);
			theGamePanel.add(ChatLabel);
			ChatLabel.setForeground(Color.RED);
			
		}
		if(evt.getSource() == MapSelectBox){
			if(MapSelectBox.getSelectedItem() == "Grassland"){
				
			} else if (MapSelectBox.getSelectedItem() == "Desert"){
				
			} else if (MapSelectBox.getSelectedItem() == "Lava"){
				
			}
		}
		if(evt.getSource() == ssm){
			String strIn = ssm.readText();
			System.out.println("tank position "+strIn);
			chatToReceive.append(ssm.readText() + "\n");
			chatToReceive.setCaretPosition(chatToReceive.getDocument().getLength());
			//thePanel.removeAll();
			//theFrame.setContentPane(theGamePanel);
			//theFrame.pack();
			//theFrame.requestFocus();
			//theGamePanel.add(theScrollBar);
			//theGamePanel.add(chatToSend);
		}
		if(evt.getSource() == chatToSend){
			if(ssm != null){
				chatToReceive.append("You: " + chatToSend.getText() + "\n");
				ssm.sendText("Player: " + chatToSend.getText());
				
			}
			chatToSend.setText("");
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
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			theGamePanel.intP1DefY = -2;
			ssm.sendText("P1: Moving");
		}else if(evt.getKeyChar() == 's'){
			theGamePanel.intP1DefY = +2;
			ssm.sendText("P1: Moving");
		}else if(evt.getKeyChar() == 'a'){
			theGamePanel.intP1DefX = -2;
			ssm.sendText("P1: Moving");
		}else if(evt.getKeyChar() == 'd'){
			theGamePanel.intP1DefX = +2;
			ssm.sendText("P1: Moving");
		}

		System.out.println("sending message over network"+ssm);
	}
	public void keyTyped(KeyEvent evt){
		System.out.println("Key Typed");
		
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
		
		//Map Selection Drop Down Menu
		MapSelectBox = new JComboBox(strMapSelects);
		MapSelectBox.setSize(150, 50);
		MapSelectBox.setLocation(840, 330);
		MapSelectBox.addActionListener(this);
		MapSelectBox.addItemListener(this);
		
		//Map Label
		MapLabel = new JLabel("Map: ");
		MapLabel.setSize(400, 50);
		MapLabel.setLocation(750, 330);
		
		//Scroll Bar
		theScrollBar.setSize(240, 550);
		theScrollBar.setLocation(1000, 40);
		chatToReceive.setEditable(false);
		
		//Chat Textfield
		chatToSend.setSize(240, 30);
		chatToSend.setLocation(1000, 610);
		chatToSend.addActionListener(this);
		
		//Chat Label
		ChatLabel = new JLabel("Chat: ");
		ChatLabel.setSize(400, 70);
		ChatLabel.setLocation(1000, 0);
		
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
		
		
		theFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(ssm != null){
					ssm.disconnect();
				}
			}
		});

	}

	//main method
	public static void main(String[] args){
		new TankGame();
	}

}
