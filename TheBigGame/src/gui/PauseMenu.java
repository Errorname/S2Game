package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;
import level.*;

public class PauseMenu extends Menu {
    public static final int RETRY_GAME_ID = 1015;
    public static final int CONTINUE_GAME_ID = 1016;
    public static final int EXIT_TO_TITLEMENU_GAME_ID = 1009;

	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 2;
	
	private GameControleur controleur;

	private Level level;
	
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
	
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);
	
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
		//System.out.println(button.getId());
		/*if (button.getId() == CHOOSEKEY_GAME_ID) {
			
			System.out.println("Start Button");
		}
		
		if (button.getId() == BACK_GAME_ID) {
			controleur.popMenu();
			setSelectedItem(-1);
		}*/

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
