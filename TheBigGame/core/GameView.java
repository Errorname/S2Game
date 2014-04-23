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


public class GameView implements KeyListener {
	
	private Level level;
	
	public GameView() {
	}
	
	public void setLevel(Level level) {
		this.level = level;
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
		g.setColor(Color.WHITE);

        g.fillRect(0, 0, screenWidth, screenHeight);
        
		// draw level
		level.draw(g, screenWidth, screenHeight);
		
		// draw menu
	}
	
	public static BufferedImage loadImage(String filename) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(filename));
		} catch (IOException e) { System.out.println("THERE IS A FUCKING PROBLEME HERE"); }
		return img;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		level.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		level.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
}