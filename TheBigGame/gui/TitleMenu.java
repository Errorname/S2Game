package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.*;

public class TitleMenu extends Menu {
	public static final int START_GAME_ID = 1000;
    public static final int HOST_GAME_ID = 1002;
    public static final int JOIN_GAME_ID = 1003;
    public static final int EXIT_GAME_ID = 1001;

    public static final int CANCEL_JOIN_ID = 1004;
    public static final int PERFORM_JOIN_ID = 1005;
    public static final int RESTART_GAME_ID = 1006;
	
	private BufferedImage img;
	
	private int selectedItem = 0;
	private int nbItem = 2;
	
	public TitleMenu(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;

        addButton(new Button(START_GAME_ID, 0, (gameWidth - 128) / 2, 280));
        addButton(new Button(EXIT_GAME_ID, 1, (gameWidth - 128) / 2, 320));
		
		img = GameView.loadImage("images/TITLESCREEN.png");
    }
	
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		g.drawImage(img,0,0,null);
	
		super.draw(g,screenWidth,screenHeight);
	}
	
	public void update(MouseButtons mouseButtons) {
		super.update(mouseButtons);
		for (Button button : buttons) {
            if (button.isPressed()) {
				buttonPressed(button);
			}
        }
	}
	
	public void buttonPressed(Button button) {
		//System.out.println(button.getId());
		if (button.getId() == START_GAME_ID) {
			isClosed = true;
			System.out.println("Start Button");
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
}