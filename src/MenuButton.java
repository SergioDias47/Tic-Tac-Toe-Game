import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class MenuButton extends JButton {

	public MenuButton() {
		super();
		this.setBackground(new Color(255,255,255));
		this.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
		this.setBorderPainted(true);
	}
}
