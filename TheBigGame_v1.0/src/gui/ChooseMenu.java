package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.util.zip.*;

import core.*;

/**
*This class define menu ChooseMenu
*
*@author B Team
*@version 1.0
*/
public class ChooseMenu extends Menu {
    public static final int LEVEL_1_GAME_ID = 1;
    public static final int BACK_GAME_ID = 1017;
	
	private BufferedImage img;
	private static int selectedItem = -1;

	private int nbFile;

	private int nbItem;	
	private GameControleur controleur;

	/**Constructor of class ChooseMenu
	* 
	*@param gameWidth representing the size of the width game
	*@param gameHeight representing the size of the height game
	*@param controleur representing the controleur
	*/
	public ChooseMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

		try{
		nbFile = getListFile();	
		nbItem = getListFile() +2;
		}catch(Exception e){
		}
		for (int i = 0; i < nbFile ; i++) {
			addButton(new Button(LEVEL_1_GAME_ID + i, 0, (gameWidth - 600) / 2 + (i % 6) * 100, (i/ 6) * 100 + 100, 76, 76));
		}

		addButton(new Button(BACK_GAME_ID, 1, (gameWidth + 430) / 2, 520));
		
		img = GameView.loadImage("images/CHOOSELEVELSCREEN1.png");
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

		for (int i=1; i<=nbFile; i++) {
			if (button.getId() == i && i == 1){
				try {
					controleur.setNameLevel(getNameFile(1));
					controleur.setNextLevel(getNameFile(2));
				}
				catch (Exception e) {}
				controleur.popMenu();
				isClosed = true;
			}
			else if (button.getId() == i){ 
				//System.out.println("je suis le niveau "+ i);
				try {
					//System.out.println(getHashValue("/map/"+getNameFile(i)));
				} catch (Exception e) {}
				String name = JOptionPane.showInputDialog("Enter the password : ");
				//System.out.println(name);
				try {
					if (getHashValue("/map/"+getNameFile(i)) == Long.valueOf(name).longValue()) {
						//
						System.out.println("Map loading");
						controleur.setNameLevel(getNameFile(i));
						if (i != nbFile) {
							controleur.setNextLevel(getNameFile(i+1));
						} else {
							controleur.setNextLevel(null);
						}
						controleur.popMenu();
						isClosed = true;
					}
				} catch (Exception e) {}
			}
		}

		if (button.getId() == BACK_GAME_ID) {
			controleur.popMenu();
			controleur.addMenu(new TitleMenu(gameWidth, gameHeight, controleur));
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

    /**Getter to get the number file
    * 
    *@return the number file in repertory
    */
    public static int getListFile() throws Exception {
    	File di   = new File("map");
		File fl[] = di.listFiles();
		int j;
		int count = 0;

		for (j=0; j < fl.length; j++)
		{
			FileInputStream fis = new FileInputStream(fl[j].getCanonicalFile());
			count ++;
		}
		return count;
		
    }

    /**Getter to get the name file
    * 
    *@param index representing the index 
    *@retrun the name of file corresponding of file
    */
    public static String getNameFile(int index) throws Exception {
    	File di   = new File("map");
		File fl[] = di.listFiles();
		int j;
		String str="";
		String totalinfo="";

    	for (j=0; j < index; j++)
		{
		FileInputStream fis = new FileInputStream(fl[j].getCanonicalFile());
		String filename = fl[j].getName().toString();
		totalinfo = filename;
		}
		//System.out.println("nom fichier : "+ totalinfo);
		return totalinfo;
    }
	
	/**Getter to get the hash code
	* 
	*@param map representing the name map
	*@return the hash code corresponding at the file map
	*/
	public static long getHashValue(String map) {
		long checksum = 0;
		try {
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+map);
			Adler32 adlerChecksum = new Adler32();
			File file = new File(map);
			long firstValue = adlerChecksum.getValue();
			CheckedInputStream cinStream = new CheckedInputStream(inputStream,adlerChecksum);
			long fileSize = file.length();
			byte[] b = new byte[128];
			while (cinStream.read(b) >= 0) {
			}
			checksum = cinStream.getChecksum().getValue();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return checksum;
	}
}
