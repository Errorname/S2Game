package beditor.view;

import beditor.model.*;
import beditor.controller.*;
import javax.swing.*;
import java.awt.*;

/**
* EditorPan : a class to dispay the editor
*
* @author Hugo PIGEON
* @version 1.0
*/

public class EditorPan extends JPanel
{
	private MapPan mapPan;
	private TilesetPan tilesetPan;
	private PropertiesPan propertiesPan;
	
	private final Dimension MAP_SCROLL_SIZE = new Dimension(512, 700);
	private final Dimension TILESET_SCROLL_SIZE = new Dimension(512, 500);
	private final Dimension PROPERTIES_SCROLL_SIZE = new Dimension(512, 200);
	private final int SPLIT_EDITOR_LOCATION = (int) MAP_SCROLL_SIZE.getWidth();
	private final int SPLIT_PROPERTIES_LOCATION = (int) TILESET_SCROLL_SIZE.getHeight();
	
	/** Constructor to make a new map
	* @param mapDim the map's dimensions
	* @param tileDim a tile's dimensions
	* @param tilesetPath the path to the tileset file
	*/
	public EditorPan(Dimension mapDim, Dimension tileDim, String tilesetPath)
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		this.mapPan = new MapPan(mapDim, tileDim, this);		
		this.tilesetPan = new TilesetPan(tilesetPath, tileDim, this);
		this.propertiesPan = new PropertiesPan(this);
		
		JScrollPane mapScroll = new JScrollPane(this.mapPan);
		mapScroll.setPreferredSize(MAP_SCROLL_SIZE);
		JScrollPane tilesetScroll = new JScrollPane(this.tilesetPan);
		tilesetScroll.setPreferredSize(TILESET_SCROLL_SIZE);
		JScrollPane propertiesScroll = new JScrollPane(this.propertiesPan);
		propertiesScroll.setPreferredSize(PROPERTIES_SCROLL_SIZE);
		
		JSplitPane propertiesSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tilesetScroll, propertiesScroll);
		propertiesSplit.setDividerLocation(SPLIT_PROPERTIES_LOCATION);
		JSplitPane editorSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapScroll, propertiesSplit);
		editorSplit.setDividerLocation(SPLIT_EDITOR_LOCATION);
		this.add(editorSplit, BorderLayout.CENTER);
	}
	
	/** Gives the selected tile in the tileset
	* @return the selected tile in the tileset
	*/
	public Sprite getSelectedTile()
	{
		return this.tilesetPan.getSelectedSprite();
	}
	
	/** Gives the MapPan related to this EditorPan
	* @return the MapPan related to this EditorPan
	*/
	public MapPan getMapPan()
	{
		return this.mapPan;
	}
	
	/** Gives the TilesetPan related to this EditorPan
	* @return the TilesetPan related to this EditorPan
	*/
	public TilesetPan getTilesetPan()
	{
		return this.tilesetPan;
	}
	
	/** Gives the PropertiesPan related to this EditorPan
	* @return the PropertiesPan related to this EditorPan
	*/
	public PropertiesPan getPropertiesPan()
	{
		return this.propertiesPan;
	}
}
