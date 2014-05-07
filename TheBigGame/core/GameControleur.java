package core;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

import level.Level;
import gui.*;
import gui.Menu;

public class GameControleur extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private int panelWidth;
	private int panelHeight;
	private Graphics2D g;
	private Image image;
	private GameView gv;
	private Level level;
	private Menu menu;
	
	public MouseButtons mouseButtons = new MouseButtons();
	private boolean mouseMoved = false;
	
	private boolean running = true;
	
	public GameControleur(int width, int height) {
		this.panelWidth = width;
		this.panelHeight = height;
		
		gv = new GameView(width, height);
		level = null;
		menu = new TitleMenu(width, height);
		gv.setMenu(menu);
		
		
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
        this.addMouseListener(this);
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
		if (level != null) {
			level.update();
		}
		if (menu != null) {
			menu.update(mouseButtons);
		}
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
	
	public void keyPressed(KeyEvent e) {
		level.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		level.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		
	} 
	
	public void mouseDragged(MouseEvent arg0) {
        mouseMoved = true;
    }

    public void mouseMoved(MouseEvent arg0) {
        mouseMoved = true;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseButtons.releaseAll();
    }

    public void mousePressed(MouseEvent e) {
        mouseButtons.setNextState(e.getButton(), true);
    }

    public void mouseReleased(MouseEvent e) {
        mouseButtons.setNextState(e.getButton(), false);
    }
}