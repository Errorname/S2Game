package core;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import level.Level;

public class GameControleur extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private Graphics2D g;
	private Image image;
	private GameView gv;
	private Level level;
	
	private boolean running = true;
	
	public GameControleur(int width, int height) {
		this.panelWidth = width;
		this.panelHeight = height;
		
		gv = new GameView();
		level = new Level();
		gv.setLevel(level);
		
		
		this.setFocusable(true);
		this.addKeyListener(gv);
	}
	
	public void gameAction() {
		gameUpdate(); // Update game state.
		gameRender(); // Draw to the double buffer.
		paintScreen(); // Draw double buffer to screen.
	}
	
	public void addNotify() {
		super.addNotify(); // creates the peer
		startGame(); // start the thread
	}
	
	public void startGame() {
        running = true;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

	@Override
	public void run() {
		while(running) {
			gameAction();
		}
	}
	
	private void gameUpdate() {
		// Do some stuff here, like moving the player or looking at the colliding things
		level.update();
	}
	
	private void gameRender() {
		if(image == null) {
			image = createImage(this.panelWidth, this.panelHeight);
			return;
		}
	    g = (Graphics2D) image.getGraphics();  
		gv.draw(g, panelWidth, panelHeight);
	}
	
	private void paintScreen() {	
		Graphics2D g;
		try {
			g = (Graphics2D) this.getGraphics();
			if ((g != null) && (image != null))  {
				g.drawImage(image, 0, 0, null);
				g.dispose();
			} 
		} catch (Exception e) { System.out.println("Graphics context error: " + e); }
	}
}