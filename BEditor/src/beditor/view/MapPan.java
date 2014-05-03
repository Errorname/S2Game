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
	
	/** Constructor to make a new map
	* @param mapDim the map's dimensions
	* @param tileDim a tile's dimensions
	* @param collisionType the map's collision type
	* @param editorPan the EditorPan which contains this MapPan
	*/
	public MapPan(Dimension mapDim, Dimension tileDim, int collisionType, EditorPan editorPan)
	{
		this.tileDim = tileDim;
		this.editorPan = editorPan;
		
		this.setLayout(new GridLayout((int) mapDim.getHeight(), (int) mapDim.getWidth(), 0, 0));
		this.setPreferredSize(new Dimension((int) (mapDim.getWidth() * tileDim.getWidth()), (int) (mapDim.getHeight() * tileDim.getHeight())));
		
		this.map = new Map(mapDim, collisionType);
		
		this.tiles = new Sprite[(int) mapDim.getWidth()][(int) mapDim.getHeight()];
		
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
				int coordX = x * (int) tileDim.getWidth();
				int coordY = y * (int) tileDim.getHeight();
				
    			this.tiles[x][y] = new Sprite(this, new Point(coordX, coordY), tileDim, true, true);
    			this.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
    			this.add(this.tiles[x][y].getImage());
			}
		}
		
		this.reloadMap();
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
	
	/** Loads the map from the model
	*/
	public void reloadMap()
	{
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
				if(this.map.getTile(x, y) != -1)
					this.tiles[y][x].setImage(new ImageIcon(this.editorPan.getTilesetPan().getSpriteImage(this.map.getTile(x, y))));
				else
					this.tiles[y][x].setImage(null);
			}
		}
	}
}