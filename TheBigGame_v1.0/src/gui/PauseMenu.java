package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;
import level.*;

/**
*This class define menu PauseMenu
*
*@author B Team
*@version 1.0
*/
public class PauseMenu extends Menu {
    public static final int RETRY_GAME_ID = 1015;
    public static final int CONTINUE_GAME_ID = 1016;
    public static final int EXIT_TO_TITLEMENU_GAME_ID = 1009;

	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 3;
	
	private GameControleur controleur;

	private Level level;
	
	/**Constructor of class PauseMenu
	*@param gameWidth representing the size of the width game
	*@param gameHeight representing the size of the height game
	*@param controleur representing the controleur
	*/
	public PauseMenu(int gameWidth, int gameHeight, GameControleur controleur, Level level) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.level = level;
		level = null;
		this.selectedItem = -1;

       	addButton(new Button(CONTINUE_GAME_ID, 0, (gameWidth - 128) / 2, 280));
        addButton(new Button(RETRY_GAME_ID, 2, (gameWidth - 128) / 2, 320));
        addButton(new Button(EXIT_TO_TITLEMENU_GAME_ID, 1, (gameWidth - 128) / 2, 360));
		
		img = GameView.loadImage("images/PAUSESCREEN.png");
    }
	
	/**Method to draw picture
	*@param g representing Graphics2D
	*@param screenWidth representing the size of width screen
	*@param screenHeight representing the size of height screen
	*/
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);
	
		super.draw(g,screenWidth,screenHeight);
	}
	
	/** Method to update mouse
	*@param mouseButtons representing the mouse buttons
	*/
	public void update(MouseButtons mouseButtons) {
		super.update(mouseButtons);
		for (Button button : buttons) {
            if (button.isClicked()) {
				buttonPressed(button);
			}
        }
	}
	
	/**Method to know if button is pressed
	* 
	*@param button representing the button
	*/ 
	public void buttonPressed(Button button) {

		if (button.getId() == CONTINUE_GAME_ID) {
			Level.pause = false;
			isClosed = true;
		}

		if (button.getId() == RETRY_GAME_ID) {
			level.retry();
			isClosed = true;
		}

		if (button.getId() == EXIT_TO_TITLEMENU_GAME_ID) {
			controleur.popMenu();
			controleur.setLevel(null);
			isClosed = true;
			TitleMenu titleMenu = new TitleMenu(gameWidth, gameHeight, controleur);
			controleur.addMenu(titleMenu);
		}
    }
	
	/**Method to know if key is pressed
	* 
	*@param e representing the keyEvent
	*/
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            selectedItem--;
            if (selectedItem < 0) {
                selectedItem = nbItem-1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            selectedItem++;
            if (selectedItem > nbItem-1) {
                selectedItem = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            buttons.get(selectedItem).postClick();
        }
    }
	
	/**Method to know if key released
	* 
	*@param arg0 representing the keyEvent
	*/
	public void keyReleased(KeyEvent arg0) {
    }

    /**Method to know the key type
    *
    *@param arg0 representing the KeyEvent
    */
    public void keyTyped(KeyEvent arg0) {
    }


    /**Getter to get selectedItem
    * 
    *@return the selectedItem
    */
    public static int getSelectedItem(){
    	return selectedItem;
    }

    /**Setter to set selectedItem
    * 
    *@param selectedItem representing the item selected
    */
    public void setSelectedItem(int selectedItem){
    	this.selectedItem = selectedItem;
    }
}
