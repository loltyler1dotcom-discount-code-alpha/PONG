package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JPanel {
	Ball ball = new Ball(this);
	Bar bar = new Bar(this);
	
	static final int FRAME_WIDTH = 600;
	static final int FRAME_HEIGHT = 600;
	int ball_posx;
	int ball_posy;
	int ball_velx;
	int ball_vely;
	int ball_width = ball.getBallWidth();
	int ball_height = ball.getBallHeight();
	
	int bar_left;
	int bar_right;
	int bar_height = FRAME_HEIGHT - 50 - ball_width;
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.paint(g2d);
		bar.paint(g2d);
	}
	
	public void updateBall() {
		ball.moveBall();
		ball_posx = ball.getPOSX();
		ball_posy = ball.getPOSY();
		ball_velx = ball.getVELX();
		ball_vely = ball.getVELY();
		
		if (ball_posx == FRAME_WIDTH - ball_width || ball_posx == 0) {
			ball.setVELX(-ball_velx);
		}
		
		if (ball_posy == 0) {
			ball.setVELY(-ball_vely);
		}
		
		if (ball_posy == bar_height && bar_left <= ball_posx - ball_width/2 && ball_posx <= bar_right + ball_width/2 ) {
			ball.setVELY(-ball_vely);
		}
	}
	
	public int getFrameWidth() {
		return FRAME_WIDTH;
	}
	
	public int getFrameHeight() {
		return FRAME_HEIGHT;
	}
	
	public void updateBar() {
		bar_left = bar.getPOSX();
		bar_right = bar.getPOSX() + bar.getBarWidth();
	}
	
	public void newGame() {
		ball.setPOSX(FRAME_WIDTH/2 - ball.getBallWidth()/2);
		ball.setPOSY(0);
		ball.setVELX(0);
		ball.setVELY(2);
		
		bar.setPOSX(FRAME_WIDTH/2 - bar.getBarWidth()/2);
		bar.setPOSY(FRAME_HEIGHT - 50);
	}
	
	public void deathScreen() {
		JFrame DeathFrame = new JFrame();
		JLabel ded = new JLabel("You are dead");
		DeathFrame.add(ded);
		DeathFrame.setSize(400, 200);
		DeathFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeathFrame.setVisible(true);
		JButton restart = new JButton("Restart");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong");
		Main main = new Main();
		frame.add(main);
		frame.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.newGame();
		
		while (true) {
			main.updateBall();
			main.updateBar();
			main.repaint();
			Thread.sleep(10);
		}
	}
}
