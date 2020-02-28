import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

public class TTTGrid extends JPanel {
	
	private static final long serialVersionUID = -8325765386826060356L;
	private EventListenerList listenerList = new EventListenerList();
	
	private TTTButton[] buttons;
	
	public TTTGrid() {
		Dimension size = getPreferredSize();
		size.height = 500;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("TTT Grid"));
		
		setLayout(new GridLayout(3,3));
		
		buttons = new TTTButton[9];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new TTTButton();
			buttons[i].setName(String.valueOf(i+1));
			buttons[i].setBackground(new Color(255,255,255));
			buttons[i].setFocusPainted(false);
			add(buttons[i]);
		}

		
	}
	
	public void setButtonListeners(Game game) {
		for(TTTButton b : buttons) {
			b.addActionListener(game);
		}
	}
	
	public void fillBotCell(int pos) {
		buttons[pos-1].setO();
	}
}
