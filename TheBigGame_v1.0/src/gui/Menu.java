package gui;

import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.util.*;
import core.*;

/**
*This class define Menu
*
*@author B Team
*@version 1.0
*/
public abstract class Menu implements ButtonListener, KeyListener {
	
	protected List<Button> buttons = new ArrayList<Button>();
    protected int gameWidth;
	protected int gameHeight;
	
	protected boolean isClosed = false;
	
    /**Method which add Button
    *@param button representing the button
    *@return button
    */
	protected Button addButton(Button button) {
		buttons.add(button);
		button.addListener(this);
		return button;
	}
	
    /**Method which draw the button
    *@param g
    *@param screenWidth
    *@param screenHeight
    */
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		for (Button button : buttons) {
            button.draw(g);
        }
	}

    /**Method which update the mouse buttons
    *@param mouseButtons representing the mouse buttons
    */
	public void update(MouseButtons mouseButtons) {
		for (Button button : buttons) {
            button.update(mouseButtons);
			
        }
	}
	
	/**Getter to get is closed
    *@return isCloded
    */
	public boolean isClosed() {
		return isClosed;
	}
}