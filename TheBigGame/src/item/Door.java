package item;


import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import level.*; 


/**
* this class define a door 
*
*@author B Team  
*@version 1.0
*/

public class Door extends Item{

	private boolean open;

/** Constructor of the door class
*@param x : coordinate x
*@param y : coordinate y 
*@param index : the position of the tile in the tileset
*@param img : the tileset 
*@param widthTile 
*@param heightTile
*/ 
	public Door(int x, int y, int index,BufferedImage img, int widthTile, int heightTile )
	{
		super(x,y,index,img, widthTile, heightTile, true, false); 
		this.open = false; 
		
	}

/** Method which open the door 
*/ 	
	public void openDoor()
	{
		this.open = true;
	}

/** Method which say if the door is open 
*@return boolean 
*/ 	
	public boolean isOpen()
	{
		return this.open; 
	}

/** Method which draw the door
*@param g
*@param pixelX
*@param pixelY
*/ 
	public void draw(Graphics g, int pixelX, int pixelY) {
		((Tile)super.getTile()).draw(g,pixelX,pixelY);
	}
	
}
