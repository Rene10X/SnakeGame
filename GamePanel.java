import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.TimerTask;
import java.util.random.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	boolean gameIsRunning = false;
	int screenWidth = 600;
	int screenHeight = 600;
	int[] bodyX = new int[144];
	int[] bodyY = new int[144];
	int unitSize = 50;
	int bodies = 3;
	int startingLocationX = screenWidth / 2;
	int startingLocationY = screenHeight / 2;
	char direction = 'R';
	int delay = 200; //ms
	int appleX;
	int appleY;
	int applesEaten = 0;
	
	Timer timer;
	TimerTask task;
	Random rand;
	
	GamePanel(){
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		timer = new Timer(delay, this);
		rand = new Random();
		
		
		for(int i = 0; i <= bodies; i++) {
			bodyX[i] = startingLocationX - (i*50);
			bodyY[i] = startingLocationY;
		}
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setLayout(null);
		
		newApple();
		gameIsRunning = true;
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
		
	public void draw(Graphics g) {
						
		if(gameIsRunning) {
			
			for(int i = 0; i <= 600; i += unitSize) {
				g.setColor(Color.black);
				g.drawLine(i, 0, i, 600);
				g.drawLine(0, i, 600, i);
			}
			
			g.setColor(Color.green);
			g.fillOval(appleX, appleY, unitSize, unitSize);
			
			g.setColor(Color.red);
			
			for(int i = 0; i <= bodies - 1; i++) {
				if(i == 0) {
					g.setColor(Color.red);
					g.fillOval(bodyX[i], bodyY[i], unitSize, unitSize);
				} else {
					g.setColor(new Color(150,0,0));
					g.fillOval(bodyX[i], bodyY[i], unitSize, unitSize);	
				}
				
			}
		} else {
			g.setFont(new Font("Ink Free", Font.BOLD, 75));
			g.drawString("MÄNG LÄBI!!!", screenWidth / 2 - 230, screenHeight / 2);
			String applesEatenConverted = Integer.toString(applesEaten);
			g.setFont(new Font("Ink Free", Font.BOLD, 25));
			g.drawString("sa sõid ära " + applesEatenConverted + " õuna", screenWidth / 2 - 230, screenHeight / 2 + 100);
		}
			
		
	}
	
	public void moveBody(){
		for(int i = bodies;i>0;i--) {
			bodyX[i] = bodyX[i-1];
			bodyY[i] = bodyY[i-1];
		}
		
		switch(direction) {
		case 'U':
			bodyY[0] = bodyY[0] - unitSize;
			break;
		case 'D':
			bodyY[0] = bodyY[0] + unitSize;
			break;
		case 'L':
			bodyX[0] = bodyX[0] - unitSize;
			break;
		case 'R':
			bodyX[0] = bodyX[0] + unitSize;
			break;
		}
		
	}
	
	public void checkCollision() {
		if(bodyX[0] >= screenWidth) {
			gameIsRunning = false;
		} else if(bodyX[0] < 0) {
			gameIsRunning = false;
		} else if (bodyY[0] < 0) {
			gameIsRunning = false;
		} else if (bodyY[0] >= screenHeight) {
			gameIsRunning = false;
		}
		for(int i = bodies; i > 0; i--) {
			if(bodyX[0] == bodyX[i] && bodyY[0] == bodyY[i]) {
				gameIsRunning = false;
			}
		}
	}
	
	public void newApple() {
		appleX = (rand.nextInt(screenWidth / 50) * 50);
		appleY = (rand.nextInt(screenHeight / 50) * 50);
		if(appleX == 0) {
			appleX = 50;
		}
		if(appleY == 0) {
			appleY = 50;
		}
		for(int i = 0; i <= bodies; i++) {
			if(appleX == bodyX[i] && appleY == bodyY[i]) {
				newApple();
			}
		}
		System.out.println(appleX);
		System.out.println(appleY);
	}
	
	public void checkForApple() {
		if (bodyX[0] == appleX && bodyY[0] == appleY) {
			bodies++;
			newApple();
			applesEaten++;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameIsRunning) {
			moveBody();
			checkCollision();
			checkForApple();
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'a':
			System.out.println("you pressed a");
			if (direction != 'R') {
				direction = 'L';
			}
			break;
		case 's':
			System.out.println("you pressed s");
			if (direction != 'U') {
				direction = 'D';
			}
			break;
		case 'd':
			System.out.println("you pressed d");
			if (direction != 'L') {
				direction = 'R';	
			}
			break;
		case 'w':
			System.out.println("you pressed w");
			if (direction != 'D') {
				direction = 'U';
			}
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
