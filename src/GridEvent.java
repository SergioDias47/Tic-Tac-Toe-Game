import java.util.EventObject;

public class GridEvent extends EventObject {
	private int winner;
	public GridEvent(Object source, int winner) {
		super(source);
		this.winner = winner;
	}
	public int getWinner() {
		return winner;
	}
}
