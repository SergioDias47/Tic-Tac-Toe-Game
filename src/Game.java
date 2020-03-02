import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game implements ActionListener {
	private UI gui;
	private int cells[];
	public int playerTurn; //1 if player 1 or 2 if player 2
	public int winner;
	public int gamemode;
	public boolean reverseStart; // if true, the player 2 plays first
	public int player1Score;
	public int player2Score;
	
	public Game() {
		//UI
		gui = new UI("gui");
		gui.setMenuListener(this);
		initialize();
	}
	
	/*
	 * Fully initializes the game, setting up all the buttons and graphics.
	 */
	public void initialize() {
		gui.setGridListener(this);
		gui.setButtonListeners(this);
		cells = new int[10];
		playerTurn = 1;
		winner = 0;
		player1Score = 0;
		player2Score = 0;
		reverseStart = true; // will automatically be set to false when the user starts a game
	}
	
	/*
	 * Resets the grid, initializing a new match. The argument determines where score should be saved or discarded.
	 */
	public void initializeMatch(boolean saveScore) {
		gui.setGridListener(this);
		cells = new int[10];
		winner = 0;
		if(!saveScore) {
			reverseStart = false;
			player1Score = 0;
			player2Score = 0;
		} else {
			reverseStart = !reverseStart;
		}
		playerTurn = reverseStart? 2 : 1;
		if(gamemode == MyConstants.PLAYER_VS_BOT) {
			if(reverseStart) {
				int pos = findBestMove();
				updateState(pos);
				gui.fillBotCell(pos);
			}
		}
	}
	
	/*
	 * Returns true if the argument matches a player who won the game, and false otherwise.
	 */
	public boolean checkWinner(int player) {
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
		return false;
	}
	
	public int getScore() {
		if(checkWinner(MyConstants.PLAYER_1))
			return MyConstants.X_SCORE;
		if(checkWinner(MyConstants.PLAYER_2))
			return MyConstants.O_SCORE;
		else return MyConstants.TIE;
	}
	
	public void nextPlayer() {
		playerTurn = (playerTurn % 2) + 1;
	}
	
	public boolean updateState(int pos) { // returns true if succeed and false otherwise
		if(cells[pos] != MyConstants.EMPTY_CELL) // Cell is already filled, so the function does not succeed
			return false;
		int currentPlayer = playerTurn;
		System.out.println(currentPlayer);
		cells[pos] = currentPlayer; // Fills the cell
		if(checkWinner(MyConstants.PLAYER_1)) {
			winner = MyConstants.PLAYER_1;
			gui.showWinner(this);
		} else if(checkWinner(MyConstants.PLAYER_2)) {
			winner = MyConstants.PLAYER_2;
		} else if (isGridFull()) {
			winner = MyConstants.TIE;
		}
		
		nextPlayer();
		if(winner != 0) { // Means that the game is over
			gui.showWinner(this);
			gui.unsetGridListener(this);
			if(winner == MyConstants.PLAYER_1 || winner == MyConstants.TIE) {
				player1Score++;
				gui.updateScore(MyConstants.PLAYER_1, player1Score);
			} 
			if(winner == MyConstants.PLAYER_2 || winner == MyConstants.TIE){
				player2Score++;
				gui.updateScore(MyConstants.PLAYER_2, player2Score);
			}
			Timer timer = new Timer(3000, new ActionListener() {
				  @Override
				  public void actionPerformed(ActionEvent arg0) {
					  gui.resetGamePanel(false);
					  initializeMatch(true);
				  }
			});
			timer.setRepeats(false); 
			timer.start();
			return true;
		}
		return true;
	}
	
	public boolean isGridFull() {
		if(cells[1] != 0 && cells[2] != 0 && cells[3] != 0 && cells[4] != 0 &&
				cells[5] != 0 && cells[6] != 0 && cells[7] != 0 && cells[8] != 0 && cells[9] != 0) {
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		return isGridFull() || checkWinner(MyConstants.PLAYER_1) || checkWinner(MyConstants.PLAYER_2);
	}

	private int findBestMove() {
		int bestScore = -Integer.MIN_VALUE;
		int bestMove = 0; // position in the grid
		for(int i = 1; i <= 9; i++)
			if(cells[i] == MyConstants.EMPTY_CELL) {
				cells[i] = MyConstants.O_CELL;
				int score = minimax(false, 3);
				if(score > bestScore) {
					bestScore = score;
					bestMove = i;
				}
				cells[i] = MyConstants.EMPTY_CELL;
			}
		return bestMove;
	}

	private int minimax(boolean isMaximizing, int depth) {
		int currentScore = getScore();
		if(isGameOver() || depth == 0) {
			return currentScore;
		}
		
		if(isMaximizing) {
			int bestScore = Integer.MIN_VALUE;
			for(int i = 1; i <= 9; i++) {
				if(cells[i] == MyConstants.EMPTY_CELL) {
					cells[i] = MyConstants.O_CELL;
					int score = minimax(false, depth - 1);
					cells[i] = MyConstants.EMPTY_CELL;
					bestScore = Integer.max(bestScore, score);
				}
			}
			return bestScore;
		} else {
			int bestScore = Integer.MAX_VALUE;
			for(int i = 1; i <= 9; i++) {
				if(cells[i] == MyConstants.EMPTY_CELL) {
					cells[i] = MyConstants.X_CELL;
					int score = minimax(true, depth - 1);
					cells[i] = MyConstants.EMPTY_CELL;
					bestScore = Integer.min(bestScore, score);
				}
			}
			return bestScore;
		}
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass() == TTTButton.class) {
			int button = Integer.parseInt(((JComponent) e.getSource()).getName());
			boolean ret = updateState(button);
			if(!ret) return;
			switch((playerTurn % 2) + 1) {
				case 1:
					((TTTButton) e.getSource()).setX();
					break;
				case 2:
					((TTTButton) e.getSource()).setO();
					break;
			}
			if(gamemode == MyConstants.PLAYER_VS_BOT && !isGameOver()) {
				int pos = findBestMove();
				updateState(pos);
				gui.fillBotCell(pos);
			}		
		}
		else {
			if(((JComponent) e.getSource()).getName().equals("reset")) {
				gui.resetGamePanel(true);
				initializeMatch(false);
			}
			
			if(((JComponent) e.getSource()).getName().equals("goBack")) {
				gui.goToMenu();
			}
			
			if(((JComponent) e.getSource()).getName().equals("exit")) {
				gui.exit();
			}
		}
		
		
		JButton menuItem = (JButton) e.getSource();

        if (menuItem.getName().equals("PP")) {
        	gamemode = MyConstants.PLAYER_VS_PLAYER;
        	gui.resetGamePanel(true);
        	initializeMatch(false);
        	gui.startGame();
        }
        else if (menuItem.getName().equals("PB")) {
        	gamemode = MyConstants.PLAYER_VS_BOT;
        	gui.resetGamePanel(true);
        	initializeMatch(false);
        	gui.startGame();
        }
      
    }
	
}