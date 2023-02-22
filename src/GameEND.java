import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class GameEND extends JButton implements ActionListener {
	
	JButton resetbutton; 
	GameEND(){
		//Experimenting with a reset button 
		 
		this.setBackground(Color.black);

		//resetbutton = new JButton();
		
		//resetbutton.setText("Reset");
		//resetbutton.setSize(800,800);
		//resetbutton.setLocation(250,250);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//this.setSize(600,500);
		//this.setLayout(null);
		//this.add(resetbutton);
		
		resetbutton = new JButton();
		resetbutton.setText("Reset");
		resetbutton.setSize(100,50);
		resetbutton.setLocation(400,400);
		resetbutton.addActionListener(this);
		
		
		this.setVisible(true);
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource( )== resetbutton) {
			

			
		}
		
	}
	

}
