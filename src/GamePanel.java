import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel{
	private TTTGrid grid;
	private JLabel winLabel;
	
	public GamePanel() {
		super();
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//Add Swing components to content pane
		JButton rst = new JButton();
		rst.setText("Reset");
		winLabel = new JLabel("", SwingConstants.CENTER);
		winLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		winLabel.setVisible(false);
		
		grid = new TTTGrid();
		setUpHandler();
		
		add(grid, BorderLayout.NORTH);
		add(rst, BorderLayout.SOUTH);
		add(winLabel, BorderLayout.CENTER);
		
		rst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(grid);
				winLabel.setVisible(false);
				reset();
				add(grid, BorderLayout.NORTH);
				revalidate();
				repaint();
			}
		});
		
	}
	
	private void setUpHandler() {
		grid.addGridListener(new GridListener() {
			public void gridEventOcurred(GridEvent event) {
				winLabel.setText("Player " + event.getWinner() + " won the game!");
				winLabel.setVisible(true);
			}
		});
	}
	
	private void reset() {
		grid = new TTTGrid();
		setUpHandler();
	}
	
	public void setButtonListeners(Game game) {
		grid.setButtonListeners(game);
	}
}
