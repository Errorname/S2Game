package beditor.model;

import java.awt.Dimension;

/**
* Map : a class to define the edited map
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Map
{
	private Dimension dim;
	private int tiles[][];
	private int collisionType;
	
	/** Constructor which makes a new map with the given dimensions
	* @param dim the map's dimensions
	* @param collisionType the collision type
	*/
	public Map(Dimension dim, int collisionType)
	{
		this.dim = dim;
		this.collisionType = collisionType;
		this.tiles = new int[(int) dim.getWidth()][(int) dim.getHeight()];
		for(int x = 0 ; x < dim.getWidth() ; x++)
		{
			for(int y = 0 ; y < dim.getHeight() ; y++)
			{
				this.tiles[x][y] = -1;
			}
		}
	}
	
	/** Gives the tile at the given coordinates
	* @param x the X coordinate of the tile
	* @param y the Y coordinate of the tile
	*/
	public int getTile(int x, int y)
	{
		return this.tiles[x][y];
	}
	
	/** Changes the tile at the given coordinates
	* @param x the X coordinate of the tile to change
	* @param y the Y coordinate of the tile to change
	* @param tile the new tile
	*/
	public void setTile(int x, int y, int tile)
	{
		this.tiles[x][y] = tile;
	}
	
	/** Gives the map's width
	* @return the map's width
	*/
	public int getWidth()
	{
		return (int) this.dim.getWidth();
	}
	
	/** Gives the map's height
	* @return the map's height
	*/
	public int getHeight()
	{
		return (int) this.dim.getHeight();
	}
	
	/** Sets the map's width
	* @param width the new map's width
	*/
	public void setWidth(int width)
	{
		int tmpTiles[][] = new int[width][(int) this.dim.getHeight()];
		
		for(int x = 0 ; x < width ; x++)
		{
			for(int y = 0 ; y < dim.getHeight() ; y++)
			{
				if(x < this.dim.getWidth())
					tmpTiles[x][y] = tiles[x][y];
				else
					tmpTiles[x][y] = -1;
			}
		}
		this.tiles = tmpTiles;
		this.dim.setSize(width, this.dim.getHeight());
	}
	
	/** Sets the map's height
	* @param height the new map's height
	*/
	public void setHeight(int height)
	{
		int tmpTiles[][] = new int[(int) this.dim.getWidth()][height];
		
		for(int x = 0 ; x < dim.getWidth() ; x++)
		{
			for(int y = 0 ; y < height ; y++)
			{
				if(y < this.dim.getHeight())
					tmpTiles[x][y] = tiles[x][y];
				else
					tmpTiles[x][y] = -1;
			}
		}
		this.tiles = tmpTiles;
		this.dim.setSize(this.dim.getWidth(), height);
	}
	
	/** Gives the map's collision type
	* @return the map's collision type
	*/
	public int getCollisionType()
	{
		return this.collisionType;
	}
}
