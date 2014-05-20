package gui;

import java.util.*;
import core.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Button {
	
	private List<ButtonListener> listeners;
	
	private boolean isPressed = false;
	
	private final int x;
	private final int y;
	private final int w;
	private final int h;
	
	private final int id;
	
	private int ix;
	private int iy;
	
	private boolean performClick = false;
	
	private BufferedImage img;
	private BufferedImage imgPressed;
	
	public Button(int id, int buttonImageIndex, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.w = 128; // A changer
		this.h = 24; // A changer
        this.ix = buttonImageIndex % 2; // A changer
        this.iy = buttonImageIndex / 2; // A changer
		
		img = GameView.loadImage("images/b1.png");
		imgPressed = GameView.loadImage("images/b1p.png");
	}
	
	public void update(MouseButtons mouseButtons) {

        int mx = mouseButtons.getX();
        int my = mouseButtons.getY();
        isPressed = false;
        if (mx >= x && my >= y && mx < (x + w) && my < (y + h)) {
            if (mouseButtons.isReleased(1)) {
                postClick();
            } else if (mouseButtons.isDown(1)) {
                isPressed = true;
            }
        }

        if (performClick) {
            if (listeners != null) {
                for (ButtonListener listener : listeners) {
                    listener.buttonPressed(this);
                }
            }
            performClick = false;
        }
    }
	
	public void postClick() {
        performClick = true;
    }
	
	public void draw(Graphics2D g) {

        if (isPressed) {
            g.drawImage(imgPressed, x, y, null);
        } else {
            g.drawImage(img, x, y, null);
        }
    }
	
	public boolean isPressed() {
        return isPressed;
    }
	
	public void addListener(ButtonListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<ButtonListener>();
        }
        listeners.add(listener);
    }
	
	public int getId() {
        return id;
    }
}