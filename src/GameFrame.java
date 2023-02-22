import javax.swing.JFrame;

public class GameFrame extends JFrame {

 
	GameFrame(){
		
		//setting up the game window using swing.JFrame
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closing window
		this.setResizable(false); //user ability to resize.  
		this.pack();
		this.setVisible(true); // makes the window appear 
		this.setLocationRelativeTo(null); //centers the window
			
	}

	}