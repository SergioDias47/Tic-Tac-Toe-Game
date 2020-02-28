import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game implements ActionListener {
	private UI gui;
	public int cells[];
	/*
	 0 = Nothing
	 1 = X
	 2 = O
	 */
	
	public int playerTurn; //1 if player 1 or 2 if player 2
	public boolean gameOver;
	public int winner;
	public int gamemode;
	
	public Game() {
		//UI
		gui = new UI("gui");
		initialize();
	}
	
	public void initialize() {
		gui.setButtonListeners(this);
		cells = new int[10];
		playerTurn = 1;
		gameOver = false;
		winner = 0;
	}
	
	public boolean checkWinner() {
		gameOver = true;
		winner = (playerTurn % 2) + 1;
		
		//Check rows
		if(cells[1] == cells[2] && cells[2] == cells[3] && cells[3] != 0)
			return true;
		if(cells[4] == cells[5] && cells[5] == cells[6] && cells[6] != 0)
			return true;
		if(cells[7] == cells[8] && cells[8] == cells[9] && cells[9] != 0)
			return true;

		//Check columns
		if(cells[1] == cells[4] && cells[4] == cells[7] && cells[7] != 0)
			return true;
		if(cells[2] == cells[5] && cells[5] == cells[8] && cells[8] != 0)
			return true;
		if(cells[3] == cells[6] && cells[6] == cells[9] && cells[9] != 0)
			return true;
		
		//Check diagonals
		if(cells[1] == cells[5] && cells[5] == cells[9] && cells[9] != 0)
			return true;
		if(cells[7] == cells[5] && cells[5] == cells[3] && cells[3] != 0)
			return true;
		
		winner = 0;
		gameOver = false;
		return false;
	}
	
	public boolean checkWinner(int player) {
		gameOver = true;
		winner = (playerTurn % 2) + 1;
		
		//Check rows
		if(cells[1] == cells[2] && cells[2] == cells[3] && cells[3] == player)
			return true;
		if(cells[4] == cells[5] && cells[5] == cells[6] && cells[6] == player)
			return true;
		if(cells[7] == cells[8] && cells[8] == cells[9] && cells[9] == player)
			return true;

		//Check columns
		if(cells[1] == cells[4] && cells[4] == cells[7] && cells[7] == player)
			return true;
		if(cells[2] == cells[5] && cells[5] == cells[8] && cells[8] == player)
			return true;
		if(cells[3] == cells[6] && cells[6] == cells[9] && cells[9] == player)
			return true;
		
		//Check diagonals
		if(cells[1] == cells[5] && cells[5] == cells[9] && cells[9] == player)
			return true;
		if(cells[7] == cells[5] && cells[5] == cells[3] && cells[3] == player)
			return true;
		
		winner = 0;
		gameOver = false;
		return false;
	}
	
	public int getWinner() {
		if(checkWinner(MyConstants.PLAYER_1))
			return MyConstants.PLAYER_1;
		if(checkWinner(MyConstants.PLAYER_2))
			return MyConstants.PLAYER_2;
		else return MyConstants.TIE;
	}
	
	public void nextPlayer() {
		playerTurn = (playerTurn % 2) + 1;
	}
	
	public int updateGameState(int pos) { // returns 0 if failure, 1 if player 1 played and same for 2
		if(cells[pos] != 0 || gameOver)
			return 0;
		int currentPlayer = playerTurn;
		cells[pos] = currentPlayer;
		nextPlayer();
		return currentPlayer;
	}
	
	public boolean isGameOver() {
		if(cells[1] != 0 && cells[2] != 0 && cells[3] != 0 && cells[4] != 0 &&
				cells[5] != 0 && cells[6] != 0 && cells[7] != 0 && cells[8] != 0 && cells[9] != 0) {
			return true;
		}
		return checkWinner();
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		System.out.println(((JComponent)e.getSource()).getName());
		if(e.getSource().getClass() == TTTButton.class) {
			int button = Integer.parseInt(((JComponent) e.getSource()).getName());
			int ret = updateGameState(button);
			switch(ret) {
				case 1:
					((TTTButton) e.getSource()).setX();
					break;
				case 2:
					((TTTButton) e.getSource()).setO();
					break;
			}
			if(checkWinner()) {
				gui.showWinner(this);
				return;
			}
			
			if(gamemode == MyConstants.PLAYER_VS_BOT) {
				int pos = findBestMove();
				updateGameState(pos);
				gui.fillBotCell(pos);
				if(checkWinner()) {
					gui.showWinner(this);
				}
			}		
		}
		else {
			if(((JComponent) e.getSource()).getName().equals("reset")) {
				gui.resetGamePanel();
				initialize();
			}
		}
		
		
		JButton menuItem = (JButton) e.getSource();

        if (menuItem.getName().equals("PP")) {
        	gamemode = MyConstants.PLAYER_VS_PLAYER;
        	gui.startGame();
        }
        else if (menuItem.getName().equals("PB")) {
        	gamemode = MyConstants.PLAYER_VS_BOT;
        	gui.startGame();
        }
      
    }

	private int findBestMove() {
		int bestScore = -Integer.MIN_VALUE;
		int move;
		for(int i = 1; i <= 9; i++)
			if(cells[i] == MyConstants.EMPTY_CELL) {
				cells[i] = MyConstants.O_CELL;
				int score = minimax(false);
			}
				
		return 0;
	}

	private int minimax(boolean isMaximizing) {
		if(isGameOver()) {
			return getWinner();
		}
		return 0;
	}		
	
}

