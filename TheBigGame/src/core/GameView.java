package core;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import level.Level;

import gui.*;


public class GameView {
	
	private Level level;
	private Menu menu;
	public static Color color;
	
	public GameView(int screenWidth, int screenHeight) {
		level = null;
		menu = null;
		color = new Color(208,244,247);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public static int pixelsToTiles(float pixels, int tileSize) {
		return pixelsToTiles(Math.round(pixels), tileSize);
	}
	
	public static int pixelsToTiles(int pixels, int tileSize) {
		return (int)Math.floor((float)pixels / tileSize);
	}
	
	public static int tilesToPixels(int numTiles, int tileSize) {
		return numTiles * tileSize;
	}
	
	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		//g.setColor(new Color(208,244,247));
		g.setColor(color);
		
        g.fillRect(0, 0, screenWidth, screenHeight);
        
		// draw level
		if (level != null) {
			level.draw(g, screenWidth, screenHeight);
		}
		
		// draw menu
		if (menu != null) {
			menu.draw(g, screenWidth, screenHeight);
		}
	}
	
	public static BufferedImage loadImage(String filename) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(filename));
		} catch (IOException e) { System.out.println("Couldn't load file: " + filename); }
		return img;
	}
	
	public static void setColor(Color colorz) {
		color = colorz;
	}
}
