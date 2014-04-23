package player;

import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Player implements KeyListener {
	protected float x;
	protected float y;
	
	private boolean KL;
	private boolean KR;
	private boolean KU;
	private boolean KD;
	
	private int vitesse = 1;
	
	protected BufferedImage img;
	
	public Player(int x, int y, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public float getX() {
        return x;
    }
    
    public void setX(float x) {
    	this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
    	this.y = y;
    }
    
    public void update() {
    	x += (KL?-1:0) * vitesse + (KR?1:0) * vitesse;
    	y += (KU?-1:0) * vitesse + (KD?1:0) * vitesse;
    }
	
	public void keyReleased(KeyEvent e) {
		
		// TODO
		// Si <- & -> => 0
		
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_LEFT) {
    		KL = false;
    	}
    	if (key == KeyEvent.VK_RIGHT) {
    		KR = false;
    	}
    	if (key == KeyEvent.VK_UP) {
    		KU = false;
    	}
    	if (key == KeyEvent.VK_DOWN) {
    		KD = false;
    	}
    }
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_LEFT) {
    		KL = true;
    	}
    	if (key == KeyEvent.VK_RIGHT) {
    		KR = true;
    	}
    	if (key == KeyEvent.VK_UP) {
    		KU = true;
    	}
    	if (key == KeyEvent.VK_DOWN) {
    		KD = true;
    	}
    } 
    public void keyTyped(KeyEvent e) {} 
}