package item;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import level.*; 

/**
* this class define the item class 
*
*@author B Team  
*@version 1.0
*/
public abstract class  Item {
	
	private Tile itemTile ; 
	 
	
	protected BufferedImage img;
	
	/** constructor of the item class
	*@param x : coordinate X
	*@param y : coordinate Y 
	*@param index : the position of the tile in the tileset
	*@param img : the tilseset 
	*@param widthTile 
	*@param heightTile
	*@param solid : property of the tile 
	*@param breakable property of the tile 
	*/ 
	public Item (int x, int y, int index,BufferedImage img, int widthTile, int heightTile, boolean solid, boolean breakable)
	{
		itemTile = new Tile(x*widthTile, y*heightTile, img, index, widthTile, heightTile, solid, breakable); 
	}

	/**Method get the tile 
	*@return tile 
	*/
	public Tile getTile()
	{
		return this.itemTile; 
	}

	/**Method draw the item 
	*@param g
	*@param widthTile
	*@param heightTile
	*/
	public abstract void draw(Graphics g, int widthTile, int heightTile); 

	
	
}
