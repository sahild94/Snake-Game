import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

	//fixed window size 
	static final int Screen_Width = 1024; 
	static final int Screen_Height = 1000; 
	//fixed object size 
	static final int Unit_Size = 25; 
	//Calculation to Determine how many objects can be on the screen
	static final int Game_Units = (Screen_Width * Screen_Height)/Unit_Size; 
	//Timer -- for speed of the game 
	static final int Delay = 75; 
	
	//Arrays to hold the body parts of the snake in the x and y coordinates
	//the size of snake parts is decided by Game_Units 
	final int x[] = new int [Game_Units]; 
	final int y[] = new int [Game_Units]; 

	//size of the snake 
	int snakeBody = 6;
	//blocks consumed counter
	int itemsEaten = 0;
	//Block Coordinates 
	int itemX; 
	int itemY;
	
	//initial direction of the snake 
	char direction = 'R';
	
	boolean running = false; 
	Timer timer; 
	Random random; 
	
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startgame();
		
		 
	}
	
	public void startgame() {
		newItem();
		running = true; 
		timer = new Timer(Delay,this); 
		timer.start();
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
			
	}
	
	public void draw(Graphics g) {
		
		if(running) {
		/*	//Grid System for Planning/Optimizing Object sizes in the game
			for(int i=0; i<Screen_Height/Unit_Size; i++) {
				// x-axis
				g.drawLine(i*Unit_Size, 0, i*Unit_Size, Screen_Height);
				//y-axis
				g.drawLine(0, i*Unit_Size, Screen_Width, i*Unit_Size);
			}
			*/
			g.setColor(Color.orange);
			g.fillOval(itemX, itemY, Unit_Size, Unit_Size);
		
		
			//Drawing the snake and its body 
			for(int j=0; j< snakeBody; j++) {
				if(j == 0) {
					g.setColor(Color.green);
					g.fillRect(x[j], y[j], Unit_Size, Unit_Size); //rectangle method. needs coord and width/height
				}
				else {
					//g.setColor(new Color(45,180,0));
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); //random color snake 
					//random color became too much 
					g.fillRect(x[j], y[j], Unit_Size, Unit_Size); //rectangle method. needs coord and width/height
				}
			}
			//Game Score
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD,40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score:"+ itemsEaten, (Screen_Width - metrics.stringWidth("Score:"+ itemsEaten))/2, g.getFont().getSize());
		}
		else gameOver(g);
	}
	
	public void move() {
		for(int i = snakeBody; i>0; i--) {
			//moving the snake body 
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':// up on y-axis 
			//move the head by one unit size 
			y[0] = y[0] - Unit_Size;
			break; 
		case 'D': //down on y-axis
			//move the head by one unit size 
			y[0] = y[0] + Unit_Size;
			break; 
		case 'L': // left on x-axis
			//move the head by one unit size 
			x[0] = x[0] - Unit_Size;
			break; 
		case 'R': //right on x-axis
			//move the head by one unit size 
			x[0] = x[0] + Unit_Size;
			break; 
		}
	}
	
	public void newItem() {
		
		//Generating item at random location
		// "*Unit_Size" is required for item to land within grid spaces 
		itemX = random.nextInt((int)(Screen_Width/Unit_Size))*Unit_Size; 
		itemY = random.nextInt((int)(Screen_Height/Unit_Size))*Unit_Size; 
	}
	

	public void checkitem() {
		
		if((x[0]== itemX) && (y[0] == itemY)) {
			snakeBody++;
			itemsEaten++; 
			newItem(); 
		}
	}
	
	public void checkCollisions() {
		for( int i = snakeBody; i>0; i--) {
			//check to see if the head of the snake hit it's body 
			//if so, game is over
			if((x[0]== x[i])&&(y[0]== y[i])) {
				running = false;
			}
		}
		//Check if Head touches left border
		if(x[0]<0) {
			running = false;
		}
		//Check if Head touches right border 
		if(x[0]>Screen_Width) {
			running = false;
		}
		//Check if Head touches top border
		if(y[0]<0) {
		running = false;
		}
		//Check if Head touches Bottom border
	    if(y[0]> Screen_Height) {
	    running = false;
		}
	    
	    if(!running) {
	    	timer.stop(); 
	    }
	    
	    
	}
	public void gameOver(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.setFont(new Font("Ink Free", Font.BOLD,75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		//Writing the Score and Centering Location
		g.drawString("Game Over", (Screen_Width - metrics.stringWidth("Game Over"))/2, Screen_Height/2);
		
		//Game Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		//Writing the Score and Centering Location
		g.drawString("Score:"+ itemsEaten, (Screen_Width - metrics2.stringWidth("Score:"+ itemsEaten))/2, g.getFont().getSize());
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			checkitem();
			checkCollisions();
			
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			//VK_(arrow key direction) to grab the key
			case KeyEvent.VK_LEFT:
				//make it so you can only turn in 90 degree increments. 
				if(direction != 'R') {
					direction = 'L';					
				}
				break; 
			case KeyEvent.VK_RIGHT:
				//make it so you can only turn in 90 degree increments. 
				if(direction != 'L') {
					direction = 'R';					
				}
				break; 
			case KeyEvent.VK_UP:
				//make it so you can only turn in 90 degree increments. 
				if(direction != 'D') {
					direction = 'U';					
				}
				break; 
			case KeyEvent.VK_DOWN:
				//make it so you can only turn in 90 degree increments. 
				if(direction != 'U') {
					direction = 'D';					
				}
				break; 
			}
		}
	}
	

}
