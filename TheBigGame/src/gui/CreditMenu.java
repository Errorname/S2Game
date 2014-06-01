package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;

public class CreditMenu extends Menu {

    public static final int BACK_GAME_ID = 1014;
	
	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 2;
	
	private GameControleur controleur;

	private Graphics2D g;
	
	public CreditMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

		
        addButton(new Button(BACK_GAME_ID, 1, (gameWidth + 430) / 2, 520));
		
		img = GameView.loadImage("images/CREDITSCREEN.png");
    }
	
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);

		//Font.draw(g, "Responsible for the map editor Hugo Pigeon Responsible for the view Dylan Gauthier Responsible models Thomas Picard", 2,2);

	
		super.draw(g,screenWidth,screenHeight);
	}
	
	public void update(MouseButtons mouseButtons) {
		super.update(mouseButtons);
		for (Button button : buttons) {
            if (button.isClicked()) {
				buttonPressed(button);
			}
        }
	}
	
	public void buttonPressed(Button button) {

		if (button.getId() == BACK_GAME_ID) {
			controleur.popMenu();
			setSelectedItem(-1);
		}
    }
	
	public void keyPressed(KeyEvent e) {

    }
	
	public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public static int getSelectedItem(){
    	return selectedItem;
    }

    public void setSelectedItem(int selectedItem){
    	this.selectedItem = selectedItem;
    }
}
