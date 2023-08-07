import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame{
	
	GamePanel panel;

	GameFrame(){
		panel = new GamePanel();
		
		
		this.setVisible(true);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
