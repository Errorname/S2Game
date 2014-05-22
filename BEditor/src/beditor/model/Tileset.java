package beditor.model;

import java.awt.Dimension;
import java.awt.Point;

/**
* Tileset : a class to define the tileset
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Tileset
{
	private Tile tiles[][];
	private Item items[][];
	
	/** Constructor which makes a new tileset with the given dimensions (in tiles)
	* @param dim the tileset's dimensions in tiles
	*/
	public Tileset(Dimension dim)
	{
		this.tiles = new Tile[(int) dim.getWidth()][(int) dim.getHeight()];
		this.items = new Item[(int) dim.getWidth()][(int) dim.getHeight()];
		for(int x = 0 ; x < dim.getWidth() ; x++)
		{
			for(int y = 0 ; y < dim.getHeight() ; y++)
			{
				this.tiles[x][y] = new Tile(true, false);
				this.items[x][y] = new Item(ItemType.NOT_AN_ITEM);
			}
		}
	}
	
	/** Gives the tile at the specified position
	* @param pos the position of the tile to get
	* @return the tile at the specified position
	*/
	public Tile getTile(Point pos)
	{
		return this.tiles[(int) pos.getX()][(int) pos.getY()];
	}
	
	/** Gives the item at the specified position
	* @param pos the position of the item to get
	* @return the item at the specified position
	*/
	public Item getItem(Point pos)
	{
		return this.items[(int) pos.getX()][(int) pos.getY()];
	}
}
