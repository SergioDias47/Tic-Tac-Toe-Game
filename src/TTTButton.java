import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TTTButton extends JButton{
	ImageIcon X, O;
	
	public TTTButton() {
		super();
		X=new ImageIcon("Pictures\\X.png");
		O=new ImageIcon("Pictures\\O.png");
	}
	
	public void setO() {
		setIcon(O);
	}
	
	public void setX() {
		setIcon(X);
	}
	
}
