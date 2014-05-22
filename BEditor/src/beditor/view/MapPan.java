package beditor.view;

import beditor.model.*;
import beditor.controller.*;
import javax.swing.*;
import java.awt.*;

/**
* MapPan : a class to dispay the map
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MapPan extends JPanel
{
	private Sprite tiles[][];
	private Map map;
	private Dimension tileDim;
	private EditorPan editorPan;
	private MapPanType type;
	
	private final Point DEFAULT_STARTING_POINT = new Point(0, 0);
	private final Point DEFAULT_ENDING_POINT = new Point(0, 1);
	
	/** Constructor to make a new map
	* @param mapDim the map's dimensions
	* @param tileDim a tile's dimensions
	* @param collisionType the map's collision type
	* @param type set 0 for a regular MapPan, 1 to edit the starting and finishing points, 2 to edit the items
	* @param editorPan the EditorPan which contains this MapPan
	*/
	public MapPan(Dimension mapDim, Dimension tileDim, int collisionType, MapPanType type, EditorPan editorPan)
	{
		this.type = type;
		this.tileDim = tileDim;
		this.editorPan = editorPan;
		
		this.setLayout(new GridLayout((int) mapDim.getHeight(), (int) mapDim.getWidth(), 0, 0));
		this.setPreferredSize(new Dimension((int) (mapDim.getWidth() * tileDim.getWidth()), (int) (mapDim.getHeight() * tileDim.getHeight())));
		
		this.map = new Map(mapDim, collisionType);
		if(type == MapPanType.START_FINISH)
		{
			this.map.setTile(0, 0, 0);
			this.map.setTile(0, 1, 1);
		}
		
		this.tiles = new Sprite[(int) mapDim.getWidth()][(int) mapDim.getHeight()];
		
		for(int y = 0 ; y < this.map.getHeight() ; y++)
		{
			for(int x = 0 ; x < this.map.getWidth() ; x++)
			{
				int coordX = x * (int) tileDim.getWidth();
				int coordY = y * (int) tileDim.getHeight();
				
    			this.tiles[x][y] = new Sprite(this, new Point(coordX, coordY), tileDim, true, true);
    			this.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
    			this.add(this.tiles[x][y].getImage());
			}
		}
	}
	
	/** Allows to change the map's dimension
	* @param width the new width
	* @param height the new height
	*/
	public void setMapDim(int width, int height)
	{
		Sprite tmpTiles[][] = new Sprite[width][height];
		
		this.removeAll();
		
		for(int y = 0 ; y < height ; y++)
		{
			for(int x = 0 ; x < width ; x++)
			{
				int coordX = x * (int) this.tileDim.getWidth();
				int coordY = y * (int) this.tileDim.getHeight();
				
				if(x < this.map.getWidth() && y < this.map.getHeight())
				{
					if(this.type == MapPanType.START_FINISH)
					{
						if(x == 0 && y == 0)
							this.map.setTile(x, y, 0);
						else if(x == 0 && y == 1)
							this.map.setTile(x, y, 1);
						else
							this.map.setTile(x, y, -1);
    					tmpTiles[x][y] = new Sprite(this, new Point(coordX, coordY), this.tileDim, true, true);
    				}
					else
    					tmpTiles[x][y] = this.tiles[x][y];
    			}
    			else
    				tmpTiles[x][y] = new Sprite(this, new Point(coordX, coordY), this.tileDim, true, true);
    			tmpTiles[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
    			this.add(tmpTiles[x][y].getImage());
			}
		}
		this.tiles = tmpTiles;
		
		this.map.setWidth(width);
		this.map.setHeight(height);
		this.setLayout(new GridLayout(height, width, 0, 0));
		this.setPreferredSize(new Dimension((int) (width * this.tileDim.getWidth()), (int) (height * this.tileDim.getHeight())));
		
		this.reloadMap();
		this.revalidate();
	}
	
	/** Gives the map
	* @return the EditorPan's map
	*/
	public Map getMap()
	{
		return this.map;
	}
	
	/** Gives the tile at the specified coordinates (in tiles)
	* @param p the tile's coordinates
	* @return the tile as a Sprite
	*/
	public Sprite getTile(Point p)
	{
		return this.tiles[(int) p.getX()][(int) p.getY()];
	}
	
	/** Gives the EditorPan related to this MapPan
	* @return the EditorPan related to this MapPan
	*/
	public EditorPan getEditorPan()
	{
		return this.editorPan;
	}
	
	/** Gives the dimensions of a tile
	* @return the dimensions of a tile
	*/
	public Dimension getTilesDim()
	{
		return this.tileDim;
	}
	
	/** Gives the type of the MapPan
	* @return the type of the MapPan - see the constructor for more details
	*/
	public MapPanType getType()
	{
		return this.type;
	}
	
	/** Loads the map from the model
	*/
	public void reloadMap()
	{
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
				if(this.type != MapPanType.START_FINISH)
				{
					if(this.map.getTile(x, y) != -1)
						this.tiles[x][y].setImage(new ImageIcon(this.editorPan.getTilesetPan().getSpriteImage(this.map.getTile(x, y))));
					else
						this.tiles[x][y].setImage(null);
				}
				else
				{
					if(this.map.getTile(x, y) == 0)
						this.tiles[x][y].getImage().setBackground(Color.GREEN);
					else if(this.map.getTile(x, y) == 1)
						this.tiles[x][y].getImage().setBackground(Color.RED);
					else
						this.tiles[x][y].getImage().setBackground(null);
				}
			}
		}
	}
}
