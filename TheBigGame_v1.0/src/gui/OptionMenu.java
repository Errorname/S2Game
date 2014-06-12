package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;
import player.*;

/**
*This class define menu OptionMenu
*
*@author B Team
*@version 1.0
*/
public class OptionMenu extends Menu {
    public static final int CHOOSEKEY_GAME_ID = 1007;
    public static final int BACK_GAME_ID = 1014;

	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 2;
	
	private GameControleur controleur;
	
	/**Constructor of class OptionMenu
	* 
	*@param gameWidth representing the size of the width game
	*@param gameHeight representing the size of the height game
	*@param controleur representing the controleur
	*/
	public OptionMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

		/*if (Player.getIsArrow() == true) {
			
		}*/

       // addButton(new Button(CHOOSEKEY_GAME_ID, 0, (gameWidth - 128) / 2, 280));
        addButton(new Button(BACK_GAME_ID, 1, (gameWidth - 128) / 2, 440));
		
		img = GameView.loadImage("images/TITLESCREEN.png");
    }
	
	/**Method to draw picture
	* 
	*@param g representing Graphics2D
	*@param screenWidth representing the size of width screen
	*@param screenHeight representing the size of height screen
	*/
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);
	
		super.draw(g,screenWidth,screenHeight);
	}
	
	/**Method to update mouse
	* 
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
		//System.out.println(button.getId());
		if (button.getId() == CHOOSEKEY_GAME_ID) {
			
			System.out.println("Start Button");
		}
		
		if (button.getId() == BACK_GAME_ID) {
			controleur.popMenu();
			setSelectedItem(-1);
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
