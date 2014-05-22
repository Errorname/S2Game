package level;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import player.Player;

import core.GameView;
import item.*; 

public class Level  implements KeyListener {
	
	

	private Tile[][] tiles;
	private int mapWidth;
	private int mapHeight;
	
	private Image background;
	
	private int offsetX;
	private int offsetY;
	
	//private Item[] items;
	

	public int width, height;
    //attributs map
    private String [] file; 
    private int nbTilesLengthWorld, nbTilesWidthWorld; 
    private int nbTilesX, nbTilesY; 
    private int lengthTile, widthTile;
    private int scrollX, scrollY; 
    private int nbItem; 
    private String collisionType; 
    private Coordinate start;
    private Coordinate finish; 
    private String [][] props;
    private int [][] map1Int; 
    private Tile[][] map1Tile; 
    private int [][] map2Int; 
    private Tile [][] map2Tile; 
    private String [][] itemInfo; 
    private Item [][] mapItem; 
	private BufferedImage img; 


	
	private Player player;
		
	public Level() {
		fromFile("/level/1.map");
		init(); 
	}

		
	public void fromFile(String path) {
        /********* CHARGE LA MAP ICI ***********/

		mapWidth = 40*72;
		mapHeight = 14*72;

		file = new String[200];
		loadMap(path);
		parseFile();	
		
		
	
		player = new Player(400,300,GameView.loadImage("images/boobie.png"));
    }

	private void loadMap(String path)
    {
		int i = 0;  
		
		
		//lecture du fichier texte	
		try{
	         
	        //
	        FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") + path);
	         
	         
	        // Get the object of DataInputStream
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String strLine;
	         
	         
	        //Read File Line By Line
	        while ((strLine = br.readLine()) != null)   {
	            // Print the content on the console
	        	file[i] = strLine; 
				i++;
	        }
	         
	        //Close the input stream
	        in.close();
	         
        } catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
	
	private void parseFile() 
    {
		nbTilesLengthWorld = Integer.parseInt(file[2]); 
		nbTilesWidthWorld = Integer.parseInt(file[4]);
		nbTilesY = Integer.parseInt(file[6]);
		nbTilesX = Integer.parseInt(file[8]);
		lengthTile = Integer.parseInt(file[10]);
		widthTile = Integer.parseInt(file[12]);   
		scrollX = Integer.parseInt(file[14]);
		scrollY = Integer.parseInt(file[16]);
		nbItem = Integer.parseInt(file[18]);
		collisionType = file[20];
		img = GameView.loadImage("images/"+ file[26]);
		
		String [] coordinate = file[22].split(" "); 
		start = new Coordinate (Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
		coordinate = file[24].split(" "); 
		finish = new Coordinate (Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
		
		props = new String [nbTilesY*nbTilesX][4];
		
		for(int i=28; i<(nbTilesY*nbTilesX)+28; i++)
		{
			String [] prop = file[i].split("[\\s\\s\\s]");
			for(int j=0; j<4; j++)
			{
				props[i-28][j] = prop[j]; 
			}
		}
		
		map1Int = new int [nbTilesLengthWorld][nbTilesWidthWorld];
		map2Int = new int [nbTilesLengthWorld][nbTilesWidthWorld];
		
		for(int i=29+(nbTilesY*nbTilesX); i<29+(nbTilesY*nbTilesX)+nbTilesWidthWorld; i++) 
		{


			String [] tile = file[i].split("\\s"); 

			

			for (int q=0; q<tile.length; q++)
			{	
					if(tile[q].equals(""))
					{
						map1Int[q][i-(29+(nbTilesY*nbTilesX))] = -1;
					}
					else 
					{
					//map1Int[q][i-(27+(nbTilesY*nbTilesX))] = 0;
					map1Int[q][i-(29+(nbTilesY*nbTilesX))] = Integer.parseInt(tile[q]); 
					}
			}

			if(tile.length < nbTilesLengthWorld) 
			{
				for(int x=tile.length; x<nbTilesLengthWorld; x++) 
				{
					map1Int[x][i-(29+(nbTilesY*nbTilesX))] = -1; 
				}
			}
		}
		
		for(int i=30+((nbTilesY*nbTilesX)+nbTilesWidthWorld); i<30+((nbTilesY*nbTilesX)+nbTilesWidthWorld*2); i++) 
		{
			String [] tile = file[i].split("\\s"); 

			for (int q=0; q<tile.length; q++)
			{	
					if(tile[q].equals(""))
					{
						map2Int[q][i-(30+(nbTilesY*nbTilesX)+nbTilesWidthWorld)] = -1;
					}
					else 
					{
					//map1Int[q][i-(27+(nbTilesY*nbTilesX))] = 0;
					map2Int[q][i-(30+(nbTilesY*nbTilesX)+nbTilesWidthWorld)] = Integer.parseInt(tile[q]); 
					}
			}

			if(tile.length < nbTilesLengthWorld) 
			{
				for(int x=tile.length; x<nbTilesLengthWorld; x++) 
				{
					map2Int[x][i-(30+(nbTilesY*nbTilesX)+nbTilesWidthWorld)] = -1; 
				}
			}

		}
		
		itemInfo = new String[nbItem][3]; 
		
		for(int counter=31+((nbTilesY*nbTilesX)+(nbTilesWidthWorld*2)); counter < 29+((nbTilesY*nbTilesX)+(nbTilesWidthWorld*2) + nbItem); counter++)
		{
			String [] info = file[counter].split("[\\s\\s]"); 
			for(int o=0; o < 3; o++)
			{
				itemInfo[counter-(31+((nbTilesY*nbTilesX)+(nbTilesWidthWorld*2)))][o] = info[o]; 
			}
		}
		
    }
    
    public void init() {
        /********* INITIALISE DES TRUCS ICI ***********/
        System.out.println(nbTilesLengthWorld + " " + nbTilesWidthWorld);
    	map1Tile = new Tile[nbTilesLengthWorld][nbTilesWidthWorld]; 
    	map2Tile = new Tile[nbTilesLengthWorld][nbTilesWidthWorld];

		for(int i=0; i<nbTilesLengthWorld; i++)
		{
			for(int j=0; j<nbTilesWidthWorld; j++) 
			{
				boolean breakable = false; 
				boolean solid = false; 
				if(map1Int[i][j] >= 0)
				{
					if(props[map1Int[i][j]][2] =="solid")
					{
						solid = true; 
					}

					if(props[map1Int[i][j]][3] =="breakable")
					{
						breakable = true; 
					}				
					if(map1Int[i][j] >= 0) 
					{				
					map1Tile[i][j] = new Tile (i*lengthTile, j*widthTile, img,map1Int[i][j], lengthTile,widthTile,solid,breakable); 
					} 
				}
			} 
			
			
		}
		for(int i=0; i<nbTilesLengthWorld; i++)
		{
			for(int j=0; j<nbTilesWidthWorld; j++) 
			{
				boolean breakable = false; 
				boolean solid = false; 
				if(map2Int[i][j] >= 0 )
				{
					if(props[map2Int[i][j]][2] =="solid")
					{
						solid = true; 
					}

					if(props[map2Int[i][j]][3] =="breakable")
					{
						breakable = true; 
					}
				}
			} 
				
			
			
		}
		
		mapItem = new Item [nbTilesLengthWorld][nbTilesWidthWorld];
    }

	public void draw(Graphics2D g, int screenWidth, int screenHeight) {
		
		int mapPixelWidth = GameView.tilesToPixels(getWidth(), lengthTile);
		int mapPixelHeight = GameView.tilesToPixels(getHeight(), widthTile);

		// get the scrolling position of the map based on the player

		offsetX = (int)player.getX() - screenWidth/2;
		offsetX = (offsetX < 0) ? 0 : offsetX;
		offsetX = (offsetX > mapWidth - screenWidth -1) ? mapWidth - screenWidth -1 : offsetX;

		offsetY = (int)player.getY() - screenHeight/2;
		offsetY = (offsetY < 0) ? 0 : offsetY;
		offsetY = (offsetY > mapHeight - screenHeight - 1) ? mapHeight - screenHeight -1 : offsetY;
		
		//System.out.print("Player: " + player.getX() + " : " + player.getY());
		//System.out.println("   Offset: " + offsetX + " : " + offsetY);

		// draw parallax background image
		if (background != null) {
			int x = offsetX * (screenWidth - background.getWidth(null)) / (screenWidth - mapWidth);
			int y = offsetY * (screenHeight - background.getHeight(null)) / (screenHeight - mapHeight);
			g.drawImage(background, x, y, null);
		}


		int firstTileX = GameView.pixelsToTiles(offsetX, lengthTile);
		int lastTileX = GameView.pixelsToTiles(offsetX + screenWidth, lengthTile);
		int firstTileY = GameView.pixelsToTiles(offsetY, widthTile);
		int lastTileY = GameView.pixelsToTiles(offsetY + screenHeight, widthTile);
		
		//System.out.println("FirstTile: " + firstTileX + " : " + firstTileY + " LastTile: " + lastTileX + " : " + lastTileY);
		

		for (int y = firstTileY; y <= lastTileY; y++) {
			for (int x = firstTileX; x <= lastTileX; x++) {
				Tile tile = map1Tile[x][y];
				if (tile != null) {
					tile.draw(g,GameView.tilesToPixels(x, lengthTile)-offsetX,  GameView.tilesToPixels(y, widthTile)-offsetY);
					// http://javamarioplatformer.codeplex.com/SourceControl/latest#src/devforrest/mario/core/GameRenderer.java
				}
			}
		}

		player.draw(g,offsetX,offsetY);
	}
    
    public int getOffsetX() {
    	return offsetX;
    }
    
    public int getOffsetY() {
    	return offsetY;
    }
    
    public void update() {
    	player.update();
    	if (player.getX() > mapWidth - 73) {
    		player.setX(mapWidth-73);
    	}
    	if (player.getY() > mapHeight - 73) {
    		player.setY(mapHeight-73);
    	}
    }
    
    public Tile[][] getTiles() {
		return tiles;
	}
    
    public int getWidth() {
		return widthTile;
	}
    
    public int getHeight() {
		return lengthTile;
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
    
    /*public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}
    
    public void setTile(int x, int y, BufferedImage img) {
		//tiles[x][y] = new Tile(x, y, img, lengthTile, widthTile);
	}*/

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
    
    public Player getPlayer() {
		return player;
	}
    
    public void setPlayer(Player player) {
		this.player = player;
	}
}
