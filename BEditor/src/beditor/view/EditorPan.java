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
	private MainWindow mainWindow;
	private MapPan layer1, layer2, currentlyEdited;
	private TilesetPan tilesetPan;
	private PropertiesPan propertiesPan;
	private JScrollPane mapScroll, tilesetScroll, propertiesScroll;
	private JSplitPane propertiesSplit, editorSplit;
	
	private final Dimension MAP_SCROLL_SIZE = new Dimension(512, 700);
	private final Dimension TILESET_SCROLL_SIZE = new Dimension(512, 500);
	private final Dimension PROPERTIES_SCROLL_SIZE = new Dimension(512, 200);
	private final int SPLIT_EDITOR_LOCATION = (int) MAP_SCROLL_SIZE.getWidth();
	private final int SPLIT_PROPERTIES_LOCATION = (int) TILESET_SCROLL_SIZE.getHeight();
	
	/** Constructor to make a new map
	* @param mapDim the map's dimensions
	* @param tileDim a tile's dimensions
	* @param tilesetPath the path to the tileset file
	* @param collisionType the map's collision type
	* @param mainWindow the MainWindow containing the EditorPan
	*/
	public EditorPan(Dimension mapDim, Dimension tileDim, String tilesetPath, int collisionType, MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		this.layer1 = new MapPan(mapDim, tileDim, collisionType, this);
		this.layer2 = new MapPan(mapDim, tileDim, collisionType, this);
		this.currentlyEdited = this.layer1;
		
		this.tilesetPan = new TilesetPan(tilesetPath, tileDim, this);
		this.propertiesPan = new PropertiesPan(this);
		
		this.mapScroll = new JScrollPane(this.currentlyEdited);
		this.mapScroll.setPreferredSize(MAP_SCROLL_SIZE);
		this.tilesetScroll = new JScrollPane(this.tilesetPan);
		this.tilesetScroll.setPreferredSize(TILESET_SCROLL_SIZE);
		this.propertiesScroll = new JScrollPane(this.propertiesPan);
		this.propertiesScroll.setPreferredSize(PROPERTIES_SCROLL_SIZE);
		
		this.propertiesSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.tilesetScroll, this.propertiesScroll);
		this.propertiesSplit.setDividerLocation(SPLIT_PROPERTIES_LOCATION);
		this.propertiesSplit.setContinuousLayout(true);
		this.editorSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.mapScroll, this.propertiesSplit);
		this.editorSplit.setDividerLocation(SPLIT_EDITOR_LOCATION);
		this.editorSplit.setContinuousLayout(true);
		this.add(this.editorSplit, BorderLayout.CENTER);
	}
	
	/** Allows to edit the layer 1
	*/
	public void editLayer1()
	{
		this.currentlyEdited = this.layer1;
		this.mapScroll.setViewportView(this.currentlyEdited);
	}
	
	/** Allows to edit the layer 2
	*/
	public void editLayer2()
	{
		this.currentlyEdited = this.layer2;
		this.mapScroll.setViewportView(this.currentlyEdited);
	}
	
	/** Gives the selected tile in the tileset
	* @return the selected tile in the tileset
	*/
	public Sprite getSelectedTile()
	{
		return this.tilesetPan.getSelectedSprite();
	}
	
	/** Gives the currently edited MapPan related to this EditorPan
	* @return the currently edited MapPan related to this EditorPan
	*/
	public MapPan getCurrentlyEdited()
	{
		if(this.currentlyEdited == this.layer1)
			System.out.println("1");
		else if(this.currentlyEdited == this.layer2)
			System.out.println("2");
		else
			System.out.println("WTF");
		return this.currentlyEdited;
	}
	
	/** Gives the layer 1
	* @return the layer 1
	*/
	public MapPan getLayer1()
	{
		return this.layer1;
	}
	
	/** Gives the layer 2
	* @return the layer 2
	*/
	public MapPan getLayer2()
	{
		return this.layer2;
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
	
	/** Gives the MainWindow containing this EditorPan
	* @return the MainWindow containing this EditorPan
	*/
	public MainWindow getMainWindow()
	{
		return this.mainWindow;
	}
}
