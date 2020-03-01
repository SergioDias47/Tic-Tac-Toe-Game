import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class MenuFrame extends JMenuBar {
	private MenuButton startPPGameButton;
	private MenuButton startPBGameButton;
	private MenuButton exitButton;
	private Image backgroundImage;
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	  }
	
	public MenuFrame() {
		super();
		
		// Setting layout manager
		
		GridLayout layout = new GridLayout(7,3);
		setLayout(layout);
		this.setBackground(new Color(0,0,0));
		try {
			backgroundImage = ImageIO.read(new File("Pictures\\background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Swing components
		startPPGameButton = new MenuButton();
		startPPGameButton.setText("Start player vs player game");
		startPPGameButton.setName("PP");
		
		startPBGameButton = new MenuButton();
		startPBGameButton.setText("Start player vs bot game");
		startPBGameButton.setName("PB");
		startPPGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		startPBGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	
		exitButton = new MenuButton();
		exitButton.setText("Exit game");
		exitButton.setName("exit");
		
		// Add components to content pane
	
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(startPPGameButton, BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(startPBGameButton, BorderLayout.SOUTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(exitButton, BorderLayout.SOUTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
	}
	
	public void setButtonListeners(Game game) {
		startPPGameButton.addActionListener(game);
		startPBGameButton.addActionListener(game);
		exitButton.addActionListener(game);
	}

}
