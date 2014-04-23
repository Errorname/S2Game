package level;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import player.Player;

import core.GameView;

public class Level  implements KeyListener {
	
	private int tileSize = 32;

	private Tile[][] tiles;
	private int mapWidth;
	private int mapHeight;
	
	private Image background;
	
	private int offsetX;
	private int offsetY;
	
	//private Item[] items;
		
	private Player player;
		
	public Level() {
		fromFile("lool");
	}
		
	public void fromFile(String path) {
        /********* CHARGE LA MAP ICI ***********/
		tiles = new Tile[100][100];
		mapWidth = 100*tileSize;
		mapHeight = 100*tileSize;
		
		BufferedImage bomb = GameView.loadImage("images/bomb.png");
		for(int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				tiles[i][j] = new Tile(i*32,j*32,bomb,32);
			}
		}
		
		player = new Player(500,500,null);
    }

    public void init() {
        /********* INITIALISE DES TRUCS ICI ***********/
    }
    
    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
    	
		int mapPixelWidth = GameView.tilesToPixels(getWidth(), tileSize);
		int mapPixelHeight = GameView.tilesToPixels(getHeight(), tileSize);
		
		// get the scrolling position of the map based on the player
		
		offsetX = Math.round(player.getX()) - screenWidth/2 - tileSize;
		//int offsetX = 0;
		offsetX = Math.max(offsetX, 0);
		offsetX = Math.min(offsetX, mapWidth - screenWidth - 25); // THERE IS A PROBLEM HERE!!
		
		offsetY = Math.round(player.getY()) - screenHeight/2 - tileSize;
		//int offsetY = 0;
		offsetY = Math.max(offsetY, 0);
		offsetY = Math.min(offsetY, mapHeight - screenHeight - 25); // Just a fix for an error
		
		// draw parallax background image
		if (background != null) {
			int x = offsetX * (screenWidth - background.getWidth(null)) / (screenWidth - mapWidth);
			int y = offsetY * (screenHeight - background.getHeight(null)) / (screenHeight - mapHeight);
			g.drawImage(background, x, y, null);
		}
		
		// Define the view
		System.out.println(offsetX + " " + offsetY);
		
		int firstTileX = GameView.pixelsToTiles(offsetX, tileSize);
		int lastTileX = firstTileX + GameView.pixelsToTiles(screenWidth, tileSize) + 1;
		int firstTileY = GameView.pixelsToTiles(offsetY, tileSize);
		int lastTileY = firstTileY + GameView.pixelsToTiles(screenHeight, tileSize) + 1;
		
		for (int y = firstTileY; y <= lastTileY; y++) {
			for (int x = firstTileX; x <= lastTileX; x++) {
				Tile tile = tiles[x][y];
				if (tile != null) {
					tile.draw(g,GameView.tilesToPixels(x, tileSize)-offsetX,  GameView.tilesToPixels(y, tileSize)-offsetY);
					// http://javamarioplatformer.codeplex.com/SourceControl/latest#src/devforrest/mario/core/GameRenderer.java
				}
			}
		}
    }
    
    public int getOffsetX() {
    	return offsetX;
    }
    
    public int getOffsetY() {
    	return offsetY;
    }
    
    public void update() {
    	player.update();
    }
    
    public Tile[][] getTiles() {
		return tiles;
	}
    
    public int getWidth() {
		return tiles.length;
	}
    
    public int getHeight() {
		return tiles[0].length;
	}
    
    public Tile getTile(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			return null;
		} else {
			if(tiles[x][y] != null) {
				return tiles[x][y];
			} else {
				return null;
			}
		}
	}
    
    public BufferedImage getImage(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			return null;
		} else {
			if(tiles[x][y] != null) {
			     return tiles[x][y].getImage();
			} else {
				return null;
			}
		}
	}
    
    public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}
    
    public void setTile(int x, int y, BufferedImage img) {
		tiles[x][y] = new Tile(x, y, img, tileSize);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    /*public Player getPlayer() {
		return player;
	}
    
    public void setPlayer(Player player) {
		this.player = player;
	}*/
}
