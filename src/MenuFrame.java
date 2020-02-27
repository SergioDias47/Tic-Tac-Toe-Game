import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuFrame extends JPanel {
	private JButton startPPGameButton;
	private JButton startPBGameButton;
	public MenuFrame() {
		super();
		
		// Setting layout manager
		setLayout(new BorderLayout());
		
		// Swing components
		startPPGameButton = new JButton();
		startPPGameButton.setText("Start player vs player game");
		startPPGameButton.setName("PP");
		
		startPBGameButton = new JButton();
		startPBGameButton.setText("Start player vs bot game");
		startPBGameButton.setName("PB");
		
		// Add components to content pane
		add(startPPGameButton, BorderLayout.NORTH);
		add(startPBGameButton, BorderLayout.SOUTH);
		
		
	}
	
	public void setButtonListeners(Game game) {
		startPPGameButton.addActionListener(game);
		startPBGameButton.addActionListener(game);
	}

}
