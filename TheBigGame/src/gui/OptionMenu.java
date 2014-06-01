package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;

public class OptionMenu extends Menu {
    public static final int CHOOSEKEY_GAME_ID = 1007;
    public static final int OPTION_GAME_ID = 1008;
    public static final int HIGHSCORE_GAME_ID = 1009;
    public static final int CREDIT_GAME_ID = 1010;
    public static final int BACK_GAME_ID = 1014;

    //public static final int CANCEL_JOIN_ID = 1004;
    public static final int PERFORM_JOIN_ID = 1012;
    public static final int RESTART_GAME_ID = 1013;
	
	private BufferedImage img;
	
	private static int selectedItem = -1;
	private int nbItem = 2;
	
	private GameControleur controleur;
	
	public OptionMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

       // addButton(new Button(CHOOSEKEY_GAME_ID, 0, (gameWidth - 128) / 2, 280));
        //addButton(new Button(OPTION_GAME_ID, 2, (gameWidth - 128) / 2, 320));
        addButton(new Button(BACK_GAME_ID, 1, (gameWidth - 128) / 2, 440));
		
		img = GameView.loadImage("images/TITLESCREEN.png");
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
		if (button.getId() == CHOOSEKEY_GAME_ID) {
			
			System.out.println("Start Button");
		}
		
		if (button.getId() == BACK_GAME_ID) {
			controleur.popMenu();
			setSelectedItem(-1);
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
