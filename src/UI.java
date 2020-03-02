import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
		setTitle("Tic-Tac-Toe Game");
		
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
		//menuFrame.setButtonListeners(game);
		gamePanel.setButtonListeners(game);
	}
	
	public void setGridListener(Game game) {
		gamePanel.setGridListener(game);
	}
	
	public void unsetGridListener(Game game) {
		gamePanel.unsetGridListener(game);
	}
	
	public void setMenuListener(Game game) {
		menuFrame.setButtonListeners(game);
	}
	
	
	public void fillBotCell(int pos) {
		gamePanel.fillBotCell(pos);
	}
	
	public void startGame() {
		layout.next(getContentPane());
	}
	
	public void resetGamePanel(boolean resetScores) {
		gamePanel.reset(resetScores);
	}
	
	public void showWinner(Game game) {
		gamePanel.showWinner(game);
	}
	
	public void goToMenu() {
		layout.next(getContentPane());
	}
	
	public void exit() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public void updateScore(int player, int score) {
		gamePanel.updateScore(player, score);
	}

}
