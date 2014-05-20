package gui;

import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.util.*;
import core.*;

public abstract class Menu implements ButtonListener, KeyListener {
	
	protected List<Button> buttons = new ArrayList<Button>();
    protected int gameWidth;
	protected int gameHeight;
	
	protected boolean isClosed = false;
	
	protected Button addButton(Button button) {
		buttons.add(button);
		button.addListener(this);
		return button;
	}
	
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		for (Button button : buttons) {
            button.draw(g);
        }
	}
	
	public void update(MouseButtons mouseButtons) {
		for (Button button : buttons) {
            button.update(mouseButtons);
			
        }
	}
	
	public boolean isClosed() {
		return isClosed;
	}
}