package level;
import core.*;
import collision.Box;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
* this class define tile 
*
*@author B Team  
*@version 1.0
*/

public class Tile {
	
	private int tileX;
	private int tileY;
	private int pixelX;
	private int pixelY;
	protected BufferedImage img;
	protected BufferedImage imgTile; 
	private int widthTile;
	private int heightTile;  
	private Box box;

	private boolean isSolid;
	private boolean isBreakable;

/** constructor of tile 
*@param pixelX : the position in X in pixel 
*@param pixelY : the position in Y in pixel 
*@param img : the tileset 
*@param idTile : the position of the tile in the tileset
*@param widthTile  
*@param heightTile 
*@param s : boolean if the tile is solid
*@param b : boolean if the tile is breakable 
*/ 
	public Tile(int pixelX, int pixelY, BufferedImage img,int idTile, int widthTile, int heightTile, boolean s, boolean b)
	{
		tileX = GameView.pixelsToTiles(pixelX, widthTile);
		tileY = GameView.pixelsToTiles(pixelY, heightTile);
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.img = img;
		this.widthTile = widthTile;
		this.heightTile = heightTile; 
		this.isSolid = s;
		this.isBreakable = b;
		
		if(isSolid){
			box = new Box(pixelX,pixelY,widthTile,heightTile);
		}
		
		this.imgTile = this.getSpriteImage(idTile); 
	}

	/** Method which separate the tileset in tile et choose the tile request 
*@param index : the position of the tile 
*@return image of the tile 
*/ 
	private BufferedImage getSpriteImage(int index)
	{
		BufferedImage ret = this.img.getSubimage(0, 0, this.widthTile, this.heightTile);
		boolean found = false;	
	
		//System.out.println(" " + index);

		int imgWidth = (img.getWidth()/widthTile) * (widthTile);
		int imgHeight =  (img.getHeight()/heightTile) * (heightTile);
		
		
		for(int y = 0 ; y < imgHeight && !found ; y= widthTile+y)
		{
			for(int x = 0 ; x < imgWidth && !found ; x = heightTile + x)
			{

				if((x/widthTile) + ((imgWidth/widthTile)*(y/heightTile)) == index) 
				{
					//System.out.println((x/this.widthTile) + " " + (y/this.widthTile));
				
					found = true;
					ret =  this.img.getSubimage(x,y, this.widthTile, this.heightTile);
				}
			}
		}
		
		return ret;
	}
	
	/** Method which draw the tile 
*@param g 
*@param pixelX 
*@param pixelY
*/
	public void draw(Graphics g, int pixelX, int pixelY) 
	{
		g.drawImage(imgTile, pixelX, pixelY, null);
	}
	
/** Method draw the tile 
*@param g 
*@param pixelX
*@param pixelY
*@param offsetX
*@param offsetY
*/
	public void draw(Graphics g, int pixelX, int pixelY, int offsetX, int offsetY) 
	{
		draw(g, pixelX + offsetX, pixelY + offsetY);
	}
	
	public Box getBox(){
		return this.box;
	}
	
/** Method which give the image tile 
*@return the image 
*/
	public BufferedImage getImage() 
	{
		return this.imgTile;
	}

	public boolean getIsSolid(){
		return this.isSolid;
	}
	
	public boolean getIsBreakable(){
		return this.isBreakable;
	}

	public int getPxX(){
		return this.pixelX;
	}

	public int getPxY(){
		return this.pixelY;
	}
}
