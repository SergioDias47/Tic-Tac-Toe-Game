import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Game implements ActionListener {
	private UI gui;
	public int state[][];
	/*
	 0 = Nothing
	 1 = X
	 2 = O
	 */
	
	public int playerTurn; //1 if player 1 or 2 if player 2
	public boolean gameOver;
	public int winner;
	
	public Game() {
		//UI
		gui = new UI("gui");
		
		gui.setButtonListeners(this);
		
		state = new int[3][3];
		playerTurn = 1;
		gameOver = false;
		winner = 0;
	}
	
	public boolean checkWinner() {
		gameOver = true;
		winner = (playerTurn % 2) + 1;
		//Check rows
		for(int[] row : state) {
			if(checkRow(row))
				return true;
		}

		//Check columns
		if(state[0][0] == state[1][0] && state[1][0] == state[2][0] && state[0][0] != 0)
			return true;
		if(state[0][1] == state[1][1] && state[1][1] == state[2][1] && state[0][1] != 0)
			return true;
		if(state[0][2] == state[1][2] && state[1][2] == state[2][2] && state[2][2] != 0)
			return true;
		
		//Check diagonals
		if(state[0][0] == state[1][1] && state[2][2] == state[1][1] && state[0][0] != 0)
			return true;
		if(state[0][2] == state[1][1] && state[2][0] == state[1][1] && state[1][1] != 0)
			return true;
		
		winner = 0;
		gameOver = false;
		return false;
	}
	
	static boolean checkRow(int[] row) {
		int value = row[0];
		for(int i = 1; i < row.length; i++) {
			if(row[i] != value)
				return false;
		}
		return (value != 0);
	}
	
	public void nextPlayer() {
		playerTurn = (playerTurn % 2) + 1;
	}
	
	public int updateGameState(int xpos, int ypos) { // returns 0 if failure, 1 if player 1 played and same for 2
		if(state[ypos][xpos] != 0 || gameOver)
			return 0;
		int currentPlayer = playerTurn;
		state[ypos][xpos] = currentPlayer;
		nextPlayer();
		return currentPlayer;
	}
	
	public boolean isGameOver() {
		if(state[0][0] != 0 && state[0][1] != 0 && state[0][2] != 0 && state[1][0] != 0 &&
				state[1][1] != 0 && state[1][2] != 0 && state[2][0] != 0 && state[2][1] != 0 && state[2][2] != 0) {
			return true;
		}
		return checkWinner();
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass() == TTTButton.class) {
			((TTTButton) e.getSource()).setX();
		}
		
		
		JButton menuItem = (JButton) e.getSource();

        if (menuItem.getName().equals("PP")) {
        	gui.startGame();
        }
        else if (menuItem.getName().equals("PB")) {
        	
        }
        else if (menuItem.getName().equals("1")) {
        	
        }
        
        
    }
}

//https://github.com/OSSpk/Minesweeper-Desktop-Game/tree/master/Code/src/minesweeper
