import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
	
	public void fireGridEvent(GridEvent event) {
		Object[] listeners = listenerList.getListenerList();
		for(int i = 0; i < listeners.length; i+=1) {
			if(listeners[i] == GridListener.class) {
				((GridListener) listeners[i+1]).gridEventOcurred(event);
			}
		}
	}
	
	public void addGridListener(GridListener listener) {
		listenerList.add(GridListener.class, listener);
	}
	
	public void removeGridListener(GridListener listener) {
		listenerList.remove(GridListener.class, listener);
	}
	
	public void setButtonListeners(Game game) {
		for(TTTButton b : buttons) {
			b.addActionListener(game);
		}
	}
}
