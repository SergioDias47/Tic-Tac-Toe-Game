import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel{
	private TTTGrid grid;
	private JLabel winLabel;
	private JButton rst;
	
	public GamePanel() {
		super();
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//Add Swing components to content pane
		rst = new JButton();
		rst.setText("Reset");
		rst.setName("reset");
		winLabel = new JLabel("", SwingConstants.CENTER);
		winLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		winLabel.setVisible(false);
		
		grid = new TTTGrid();
		
		add(grid, BorderLayout.NORTH);
		add(rst, BorderLayout.SOUTH);
		add(winLabel, BorderLayout.CENTER);	
	}
	
	public void reset() {
		remove(grid);
		winLabel.setVisible(false);
		grid = new TTTGrid();
		add(grid, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
	public void showWinner(Game game) {
		winLabel.setText("Player " + game.winner + " won the game!");
		winLabel.setVisible(true);
	}
	
	public void setButtonListeners(Game game) {
		rst.addActionListener(game);
		grid.setButtonListeners(game);
	}
	
	public void fillBotCell(int pos) {
		grid.fillBotCell(pos);
	}
}
