package level;
import core.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile {
	
	private int tileX;
	private int tileY;
	private int pixelX;
	private int pixelY;
	protected BufferedImage img;
	private int size;
	
	private boolean isSolid;
	private boolean isActivable;
	
	public Tile(int pixelX, int pixelY, BufferedImage img, int size) {
		tileX = GameView.pixelsToTiles(pixelX, size);
		tileY = GameView.pixelsToTiles(pixelY, size);
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.img = img;
		this.size = size;
	}
	
	public void draw(Graphics g, int pixelX, int pixelY) {
		g.drawImage(img, pixelX, pixelY, null);
	}
	
	public void draw(Graphics g, int pixelX, int pixelY, int offsetX, int offsetY) {
		draw(g, pixelX + offsetX, pixelY + offsetY);
	}
	
	public BufferedImage getImage() {
		return img;
	}
}