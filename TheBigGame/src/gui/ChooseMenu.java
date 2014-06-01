package gui;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.util.zip.*;

import core.*;

public class ChooseMenu extends Menu {
    public static final int LEVEL_1_GAME_ID = 1;
    public static final int BACK_GAME_ID = 1014;
	
	private BufferedImage img;
	private static int selectedItem;
	private int nbItem = 6;

	private int nbFile;
	
	private GameControleur controleur;
	
	public ChooseMenu(int gameWidth, int gameHeight, GameControleur controleur) {
        this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.controleur = controleur;
		this.selectedItem = -1;

		try{
		nbFile = getListFile();	
		}catch(Exception e){
		}
		for (int i = 0; i < nbFile ; i++) {
			addButton(new Button(LEVEL_1_GAME_ID + i, 0, (gameWidth - 600) / 2 + (i % 6) * 100, (i/ 6) * 100 + 100, 76, 76));
		}

		addButton(new Button(BACK_GAME_ID, 1, (gameWidth + 430) / 2, 520));
		
		img = GameView.loadImage("images/CHOOSELEVELSCREEN1.png");
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
				System.out.println("je suis le niveau "+ i);
				try {
					System.out.println(getHashValue("/map/"+getNameFile(i)));
				} catch (Exception e) {}
				String name = JOptionPane.showInputDialog("Enter the password : ");
				System.out.println(name);
				try {
					if (getHashValue("/map/"+getNameFile(i)) == Long.valueOf(name).longValue()) {
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

    public String getNameFile(int index) throws Exception {
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
