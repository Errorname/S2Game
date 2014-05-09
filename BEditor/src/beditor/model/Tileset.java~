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
	
	/** Constructor which makes a new tileset with the given dimensions (in tiles)
	* @param dim the tileset's dimensions in tiles
	*/
	public Tileset(Dimension dim)
	{
		this.tiles = new Tile[(int) dim.getWidth()][(int) dim.getHeight()];
		for(int x = 0 ; x < dim.getWidth() ; x++)
		{
			for(int y = 0 ; y < dim.getHeight() ; y++)
			{
				this.tiles[x][y] = new Tile(false, false);
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
}
