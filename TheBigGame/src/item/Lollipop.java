package item;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import level.*; 

/**
* this class define the lollipop 
*
*@author B Team  
*@version 1.0
*/
public class Lollipop extends Item {

	private boolean haveLollipop; 
	/** Constructor of the lollipop class 
	*@param x : coordinate X 
	*@param y : coordinate Y 
	*@param index : the position of the tile in the tileset
	*@param img : the tileset
	*@param widthTile
	*@param heightTile
	*/
	public Lollipop (int x, int y, int index,BufferedImage img, int widthTile, int heightTile )
	{
		super(x,y,index,img, widthTile, heightTile, false, false);
		haveLollipop = false; 
	}
	
	/**Method which if the player have the lollipop 
	*@return boolean 
	*/
	public boolean ownLollipop() 
	{
		return this.haveLollipop; 
	}
	
	/**Method which take the lollipop 
	*/
	public void takeLollipop()
	{
		this.haveLollipop = true; 
	}
	
	/**Method which draw the lollipop 
	*@param g
	*@param pixelX
	*@param pixelY
	*/ 
	public void draw(Graphics g, int pixelX, int pixelY) {
		((Tile)super.getTile()).draw(g,pixelX,pixelY);
	}
}
