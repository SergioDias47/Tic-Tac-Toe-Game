import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UI extends JFrame{
	private GamePanel gamePanel;
	private MenuFrame menuFrame;
	private CardLayout layout;
	
	public UI(String title) {
		super(title);
		
		setVisible(true);
		setSize(700,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game");
		
		//Set layout manager
		layout = new CardLayout();
		setLayout(layout);
		
		gamePanel = new GamePanel();
		menuFrame = new MenuFrame();
		
		//Add Swing components to content pane
		Container c = getContentPane();
		c.add(menuFrame);
		c.add(gamePanel);
		
	}

	public void setButtonListeners(Game game) {
		menuFrame.setButtonListeners(game);
		gamePanel.setButtonListeners(game);
	}
	
	public void startGame() {
		layout.next(getContentPane());
	}
	
	public void resetGamePanel() {
		gamePanel.reset();
	}

}
