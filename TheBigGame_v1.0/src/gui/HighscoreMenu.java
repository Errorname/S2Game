package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;


import core.*;

/**
*This class define menu HighScoreMenu
*
*@author B Team
*@version 1.0
*/
public class HighscoreMenu extends Menu {
    public static final int BACK_GAME_ID = 1014;

	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 2;
	
	private GameControleur controleur;

	private String sCurrentLine;
	private String[] splits;
	
	/**Constructor of class HighScoreMenu
	* 
	*@param gameWidth representing the size of the width game
	*@param gameHeight representing the size of the height game
	*@param controleur representing the controleur
	*/
	public HighscoreMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

        addButton(new Button(BACK_GAME_ID, 1, (gameWidth - 128) / 2, 440));
		
		//img = GameView.loadImage("images/TITLESCREEN.png");
    }

	/**Method to draw picture
	* 
	*@param g representing Graphics2D
	*@param screenWidth representing the size of width screen
	*@param screenHeight representing the size of height screen
	*/
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);
		int x = 128;

		try (BufferedReader br = new BufferedReader(new FileReader("dialogue.dat")))
		{
 			while ((sCurrentLine = br.readLine()) != null) {
				x = x + 32;
				Font.draw(g, sCurrentLine, 160, x);
			}
 
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
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
