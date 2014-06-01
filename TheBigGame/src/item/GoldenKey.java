package item;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import level.*; 

/**
* this class define a golden key 
*
*@author B Team  
*@version 1.0
*/

public class GoldenKey extends Item{
	
	/**Constructor of the golden key class 
	*@param x : coordinate X
	*@param y : coordinate Y 
	*@param index : the position of the tile in the tileset
	*@param img : the tileset 
	*@param widthTile
	*@param heightTile
	*/
	public GoldenKey(int x, int y, int index,BufferedImage img, int widthTile, int heightTile )
	{
		super(x,y,index,img, widthTile, heightTile, false, false); 
	}
	
	
	
	/**Method which draw the golden key 
	*@param g
	*@param pixelX
	*@param pixelY
	*/ 
	public void draw(Graphics g, int pixelX, int pixelY) {
		((Tile)super.getTile()).draw(g,pixelX,pixelY);
	}
}
