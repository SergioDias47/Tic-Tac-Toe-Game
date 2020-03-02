import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel{
	private TTTGrid grid;
	private JLabel winLabel, player1, player2, player1Score, player2Score;
	private JButton rst, goBack;
	
	public GamePanel() {
		super();
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//Add Swing components to content pane
		rst = new JButton();
		rst.setText("Reset");
		rst.setName("reset");
		winLabel = new JLabel("", SwingConstants.CENTER);
		winLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		winLabel.setVisible(false);
		
		player1 = new JLabel("Player 1", SwingConstants.CENTER);
		player2 = new JLabel("Player 2", SwingConstants.CENTER);
		player1Score = new JLabel("0", SwingConstants.CENTER);
		player2Score = new JLabel("0", SwingConstants.CENTER);
		player1.setFont(new Font("Arial", Font.PLAIN, 20));
		player2.setFont(new Font("Arial", Font.PLAIN, 20));
		player1Score.setFont(new Font("Arial", Font.PLAIN, 40));
		player2Score.setFont(new Font("Arial", Font.PLAIN, 40));
		
		goBack = new JButton();
		goBack.setText("Go back to menu");
		goBack.setName("goBack");
		
		grid = new TTTGrid();
		
		JPanel buttons = new JPanel();
		
		buttons.add(player1);
		buttons.add(Box.createRigidArea(new Dimension(70, 10)));
		buttons.add(player1Score);
		buttons.add(Box.createRigidArea(new Dimension(15, 10)));
		buttons.add(rst);
		buttons.add(goBack);
		buttons.add(Box.createRigidArea(new Dimension(15, 10)));
		buttons.add(player2Score);
		buttons.add(Box.createRigidArea(new Dimension(70, 10)));
		buttons.add(player2);
		
		add(grid, BorderLayout.NORTH);
		add(buttons, BorderLayout.SOUTH);
		add(winLabel, BorderLayout.CENTER);	
	}
	
	public void reset(boolean resetScores) {
		if(resetScores) {
			player1Score.setText("0");
			player2Score.setText("0");
		}
		remove(grid);
		winLabel.setVisible(false);
		grid = new TTTGrid();
		add(grid, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
	public void showWinner(Game game) {
		if(game.winner == MyConstants.TIE) {
			winLabel.setText("Draw! Starting next match in 3s");
		} else winLabel.setText("Player " + game.winner + " won the game! Starting next match in 3s");
		winLabel.setVisible(true);    
        
        Timer timer = new Timer();
        int begin = 1000;
        int timeInterval = 1000;
        timer.schedule(new TimerTask() {
          int time = 2;
           @Override
           public void run() {
        	   	String newText = winLabel.getText();
				 newText = newText.substring(0,newText.length() - 2) + time + "s"; 
				 time--;	  
				 winLabel.setText(newText);
				 if(time == 0)
					 this.cancel();
           }
        }, begin, timeInterval);
		
		
		
		
	}
	
	public void setButtonListeners(Game game) {
		rst.addActionListener(game);
		goBack.addActionListener(game);
	}
	
	public void setGridListener(Game game) {
		grid.setButtonListeners(game);
	}
	
	public void unsetGridListener(Game game) {
		grid.unsetButtonListeners(game);
	}
	
	public void fillBotCell(int pos) {
		grid.fillBotCell(pos);
	}
	
	public void updateScore(int player, int score) {
		if(player == 1) {
			player1Score.setText(score + "");
		} else {
			player2Score.setText(score + "");
		}
	}
}
