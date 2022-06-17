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
	
	JTextField LivesTextField = new JTextField("");
	
	JLabel IPLabel = new JLabel();
	JLabel PortLabel = new JLabel();
	JLabel JoinLabel = new JLabel();
	JLabel HostLabel = new JLabel();
	JLabel MapLabel = new JLabel();
	JLabel ChatLabel = new JLabel();
	JLabel LivesLabel = new JLabel();
	
	int intJoin = 0;
	int intW = 0;
	String strLineSplit[];
	int intHold;
	int intRotation;
	
	int intX = theGamePanel.intP1DefX;
	int intY = theGamePanel.intP1DefY;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thePanel.repaint();
			theGamePanel.repaint();
		}
		//Quit Game Button
		if(evt.getSource() == QuitGameButton){
			System.exit(0);
		}
		//Join Game Button
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
		//Host Game Button
		if(evt.getSource() == HostGameButton){
			thePanel.remove(JoinGameButton);
			thePanel.remove(HostGameButton);
			HostLabel.setForeground(Color.RED);
			HostLabel.setFont(new Font("Serif", Font.BOLD, 50));
			thePanel.add(HostLabel);
			thePanel.add(IPTextField);
			thePanel.add(PortTextField);
			thePanel.add(LivesTextField);
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
			thePanel.add(LivesLabel);
			MapLabel.setForeground(Color.RED);
			LivesLabel.setForeground(Color.RED);
			MapLabel.setFont(new Font("Serif", Font.BOLD, 30));
			LivesLabel.setFont(new Font("Serif", Font.BOLD, 30));
		}
		//Back button after pressing Host Game
		if(evt.getSource() == HostBackButton){
			thePanel.remove(HostLabel);
			thePanel.remove(IPTextField);
			thePanel.remove(PortTextField);
			thePanel.remove(LivesTextField);
			thePanel.remove(PortLabel);
			thePanel.remove(IPLabel);
			thePanel.remove(HostBackButton);
			thePanel.remove(HostButton);
			thePanel.remove(StartButton);
			thePanel.remove(MapSelectBox);
			thePanel.remove(MapLabel);
			thePanel.remove(LivesLabel);
			thePanel.add(JoinGameButton);
			thePanel.add(HostGameButton);
								
		}
		//Back button after pressing Join Game
		if(evt.getSource() == JoinBackButton){
			thePanel.remove(JoinLabel);
			thePanel.remove(IPTextField);
			thePanel.remove(PortTextField);
			thePanel.remove(LivesTextField);
			thePanel.remove(PortLabel);
			thePanel.remove(IPLabel);
			thePanel.remove(JoinBackButton);
			thePanel.remove(JoinButton);
			thePanel.add(HostGameButton);
			thePanel.add(JoinGameButton);
		
		} 
		if(evt.getSource() == HostButton){
			//Opens server and allows players to join
			HostButton.setEnabled(false);
			IPTextField.setEnabled(false);
			PortTextField.setEnabled(false);
			thePanel.add(StartButton);
			ssm = new SuperSocketMaster(Integer.parseInt(PortTextField.getText()), this);
			boolean tankConnect = ssm.connect();
			
		}
		if(evt.getSource() == JoinButton){
			//Joins the server
			JoinButton.setEnabled(false); 
			IPTextField.setEnabled(false);
			PortTextField.setEnabled(false);
			ssm = new SuperSocketMaster(IPTextField.getText(), Integer.parseInt(PortTextField.getText()), this);
			boolean tankConnect = ssm.connect();
			ssm.sendText("Client,Player1");
			
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
			//Map Select
			if(MapSelectBox.getSelectedItem() == "Grassland"){
				theGamePanel.selectedMap = "Grassland";
				ssm.sendText("Grassland");
			} else if (MapSelectBox.getSelectedItem() == "Desert"){
				theGamePanel.selectedMap = "Desert";
				ssm.sendText("Desert");
			} else if (MapSelectBox.getSelectedItem() == "Lava"){
				theGamePanel.selectedMap = "Lava";
				ssm.sendText("Lava");
			}
			theGamePanel.intP1Lives = Integer.parseInt(LivesTextField.getText());
			theGamePanel.intP2Lives = Integer.parseInt(LivesTextField.getText());
			theGamePanel.intP3Lives = Integer.parseInt(LivesTextField.getText());
			theGamePanel.intP4Lives = Integer.parseInt(LivesTextField.getText());
			
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
		if(evt.getSource() == ssm){
			//ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
			String strIn = ssm.readText();
			//Syncing map over the network
			String strInSplit[] = strIn.split(",");
			if(strInSplit[0].equals ("Client")){
				System.out.println("Client Connected");
				ssm.sendText("Map," + MapSelectBox.getSelectedItem());
			}
			//select map
			if(strInSplit[0].equals ("Map")){
				if(strInSplit[1].equals("Grassland")){
					theGamePanel.selectedMap = "Grassland";
				} else if(strInSplit[1].equals("Desert")){
					theGamePanel.selectedMap = "Desert";
				} else if(strInSplit[1].equals("Lava")){
					theGamePanel.selectedMap = "Lava";
				}
				theGamePanel.repaint();
				System.out.println("Received from server");
			}
			//filter chat
			if(!strInSplit[0].equals("Moving") && !strInSplit[0].equals("Shot") && !strInSplit[0].equals("Rotation") && !strInSplit[0].equals("Shot2") &&!strInSplit[0].equals("Shot3") && !strInSplit[0].equals("Shot4")){
				chatToReceive.append(ssm.readText() + "\n");
				chatToReceive.setCaretPosition(chatToReceive.getDocument().getLength());
			}
			//indicate action
			if(strInSplit[0].equals("Moving")){
				theGamePanel.intP1X = Integer.parseInt(strInSplit[1]);
				theGamePanel.intP1Y = Integer.parseInt(strInSplit[2]);
			}
			if(strInSplit[0].equals("Shot")){
				theGamePanel.bullet1X = Double.parseDouble(strInSplit[1]);
				theGamePanel.bullet1Y = Double.parseDouble(strInSplit[2]);
			}
			if(strInSplit[0].equals("Shot2")){
				theGamePanel.bullet2X = Double.parseDouble(strInSplit[1]);
				theGamePanel.bullet2Y = Double.parseDouble(strInSplit[2]);
			}
			if(strInSplit[0].equals("Shot3")){
				theGamePanel.bullet3X = Double.parseDouble(strInSplit[1]);
				theGamePanel.bullet3Y = Double.parseDouble(strInSplit[2]);
			}
			if(strInSplit[0].equals("Shot4")){
				theGamePanel.bullet4X = Double.parseDouble(strInSplit[1]);
				theGamePanel.bullet4Y = Double.parseDouble(strInSplit[2]);
			}
			if(strInSplit[0].equals("Rotation")){
				theGamePanel.cursorX = Integer.parseInt(strInSplit[1]);
				theGamePanel.cursorY = Integer.parseInt(strInSplit[2]);
			}
		}
		//Chat networking
		if(evt.getSource() == chatToSend){
			if(ssm != null){
				chatToReceive.append("You: " + chatToSend.getText() + "\n");
				ssm.sendText("Player: " + chatToSend.getText());
			}
			chatToSend.setText("");
			theFrame.requestFocus();
		}
		if(intHold == 1){
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
			ssm.sendText("Shot2,"+theGamePanel.bullet2X+","+theGamePanel.bullet2Y);
			ssm.sendText("Shot3,"+theGamePanel.bullet3X+","+theGamePanel.bullet3Y);
			ssm.sendText("Shot4,"+theGamePanel.bullet4X+","+theGamePanel.bullet4Y);
		}
	}
	
	public void keyReleased(KeyEvent evt){
		//Player Movement
		if(evt.getKeyChar() == 'w'){
			theGamePanel.intP1DefY = 0;
			theGamePanel.intP2DefY = 0;
			theGamePanel.intP3DefY = 0;
			theGamePanel.intP4DefY = 0;
			intY = 0;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 's'){
			theGamePanel.intP1DefY = 0;
			theGamePanel.intP2DefY = 0;
			theGamePanel.intP3DefY = 0;
			theGamePanel.intP4DefY = 0;
			intY = 0;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 'a'){
			theGamePanel.intP1DefX = 0;
			theGamePanel.intP2DefX = 0;
			theGamePanel.intP3DefX = 0;
			theGamePanel.intP4DefX = 0;
			intX = 0;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 'd'){
			theGamePanel.intP1DefX = 0;
			theGamePanel.intP2DefX = 0;
			theGamePanel.intP3DefX = 0;
			theGamePanel.intP4DefX = 0;
			intX = 0;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}

	}
	public void keyPressed(KeyEvent evt){
		//Player Movement
		if(evt.getKeyChar() == 'w'){
			theGamePanel.intP1DefY = -2;
			intY = -2;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 's'){
			theGamePanel.intP1DefY = +2;
			intY = +2;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 'a'){
			theGamePanel.intP1DefX = -2;
			intX = -2;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}else if(evt.getKeyChar() == 'd'){
			theGamePanel.intP1DefX = +2;
			intX = +2;
			ssm.sendText("Moving,"+theGamePanel.intP1X+","+theGamePanel.intP1Y);
			ssm.sendText("Shot,"+theGamePanel.bullet1X+","+theGamePanel.bullet1Y);
		}

	}
	public void keyTyped(KeyEvent evt){
		
	}
	public void itemStateChanged(ItemEvent e){
	
	}
	public void mouseMoved(MouseEvent evt){
		//Mouse aim
		theGamePanel.cursorX = evt.getX();
        theGamePanel.cursorY = evt.getY();
        if(ssm != null){
			ssm.sendText("Rotation,"+theGamePanel.cursorX+","+theGamePanel.cursorY);
		}
	}
	public void mousePressed(MouseEvent evt) {
		//Bullet/Mouse aim
		if(evt.getButton()==1 && theGamePanel.bullet1Velocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bullet1Velocity = 15;
		}
		if(evt.getButton()==1 && theGamePanel.bullet2Velocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bullet2Velocity = 15;
		}
		if(evt.getButton()==1 && theGamePanel.bullet3Velocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bullet3Velocity = 15;
		}
		if(evt.getButton()==1 && theGamePanel.bullet4Velocity == 0){
			theGamePanel.mouseX = evt.getX();
			theGamePanel.mouseY = evt.getY();
			theGamePanel.bullet4Velocity = 15;
		}
		intHold = 1;
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
		HostBackButton.setLocation(750, 475);
		HostBackButton.addActionListener(this);
		
		//Join Game Back Button
		JoinBackButton = new JButton("Back");
		JoinBackButton.setSize(100, 50);
		JoinBackButton.setLocation(750, 400);
		JoinBackButton.addActionListener(this);
		
		//Host Button
		HostButton = new JButton("Host");
		HostButton.setSize(100, 50);
		HostButton.setLocation(950, 475);
		HostButton.addActionListener(this);
		
		//Start Button (Host Side)
		StartButton  = new JButton("Start");
		StartButton.setSize(100, 50);
		StartButton.setLocation(1100, 475);
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
		PortTextField.setLocation(840, 250);
		
		//Lives Textfield
		LivesTextField.setSize(200, 50);
		LivesTextField.setLocation(840, 405);
		
		//Lives Label
		LivesLabel = new JLabel("Lives: ");
		LivesLabel.setSize(400, 50);
		LivesLabel.setLocation(750, 405);
		
		
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
