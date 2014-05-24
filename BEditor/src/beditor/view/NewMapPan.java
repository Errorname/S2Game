package beditor.view;

import beditor.model.*;
import javax.swing.*;
import java.awt.*;

/**
* NewMapPan : a screen to create a new map
*
* @author Hugo PIGEON
* @version 1.0
*/

public class NewMapPan extends JPanel
{	
	private String tilesetPath;
	private JButton tilesetButton;
	private JButton confirmButton;
	private JLabel labelTilesetPath;
	private JSpinner spinnerMapWidth;
	private JSpinner spinnerMapHeight;
	private JSpinner spinnerTileWidth;
	private JSpinner spinnerTileHeight;
	private JRadioButton solidRadio;
	private JRadioButton rubberRadio;
	
	private final String LABEL_PATH_TILESET = "Path to the tileset : ";
	private final String LABEL_BROWSE_TILESET = "Browse...";
	private final String LABEL_MAP_WIDTH = "Map width (in tiles) :";
	private final String LABEL_MAP_HEIGHT = "Map height (in tiles) :";
	private final String LABEL_TILE_WIDTH = "Width of a tile (in pixels) :";
	private final String LABEL_TILE_HEIGHT = "Height of a tile (in pixels) :";
	private final String LABEL_COLLISION = "Collision type :";
	private final String LABEL_SOLID = "Solid";
	private final String LABEL_RUBBER = "Rubber";
	private final String LABEL_CREATE = "Create";


	/** Constructor that displays the buttons
	*/
	public NewMapPan()
	{		
		JLabel labelTileset = new JLabel(LABEL_PATH_TILESET);
		this.tilesetPath = "";
		this.labelTilesetPath = new JLabel(this.tilesetPath);
		this.tilesetButton = new JButton(LABEL_BROWSE_TILESET);
		
		JLabel labelMapWidth = new JLabel(LABEL_MAP_WIDTH);
		SpinnerModel mapWidthModel = new SpinnerNumberModel(32, 16, 512, 1);
		spinnerMapWidth = new JSpinner(mapWidthModel);
		
		JLabel labelMapHeight = new JLabel(LABEL_MAP_HEIGHT);
		SpinnerModel mapHeightModel = new SpinnerNumberModel(32, 16, 512, 1);
		spinnerMapHeight = new JSpinner(mapHeightModel);
		
		JLabel labelTileWidth = new JLabel(LABEL_TILE_WIDTH);
		SpinnerModel tilesWidthModel = new SpinnerNumberModel(32, 16, 256, 1);
		spinnerTileWidth = new JSpinner(tilesWidthModel);
		
		JLabel labelTileHeight = new JLabel(LABEL_TILE_HEIGHT);
		SpinnerModel tilesHeightModel = new SpinnerNumberModel(32, 16, 256, 1);
		spinnerTileHeight = new JSpinner(tilesHeightModel);
		
		JLabel labelCollision = new JLabel(LABEL_COLLISION);
		ButtonGroup collisionGroup = new ButtonGroup();
		solidRadio = new JRadioButton(LABEL_SOLID, true);
		rubberRadio = new JRadioButton(LABEL_RUBBER);
		collisionGroup.add(solidRadio);
		collisionGroup.add(rubberRadio);
		
		this.confirmButton = new JButton(LABEL_CREATE);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 3));
		JPanel p11 = new JPanel();
		p11.add(labelTileset);
		JPanel p12 = new JPanel();
		p12.add(this.labelTilesetPath);
		JPanel p13 = new JPanel();
		p13.add(this.tilesetButton);
		
		p1.add(p11);
		p1.add(p12);
		p1.add(p13);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 2));
		JPanel p21 = new JPanel();
		p21.add(labelMapWidth);
		JPanel p22 = new JPanel();
		p22.add(spinnerMapWidth);
		
		JPanel p23 = new JPanel();
		p23.add(labelMapHeight);
		JPanel p24 = new JPanel();
		p24.add(spinnerMapHeight);
		
		JPanel p25 = new JPanel();
		p25.add(labelTileWidth);
		JPanel p26 = new JPanel();
		p26.add(spinnerTileWidth);
		
		JPanel p27 = new JPanel();
		p27.add(labelTileHeight);
		JPanel p28 = new JPanel();
		p28.add(spinnerTileHeight);
		
		p2.add(p21);
		p2.add(p22);
		p2.add(p23);
		p2.add(p24);
		p2.add(p25);
		p2.add(p26);
		p2.add(p27);
		p2.add(p28);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1, 3));
		
		JPanel p31 = new JPanel();
		p31.add(labelCollision);
		
		JPanel p32 = new JPanel();
		p32.add(solidRadio);
		
		JPanel p33 = new JPanel();
		p33.add(rubberRadio);
		
		p3.add(p31);
		p3.add(p32);
		p3.add(p33);
		
		JPanel p4 = new JPanel();
		p4.add(confirmButton);
		
		this.setLayout(new GridLayout(4, 1));
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
	}
	
	/** Gives the "browse" button which allows to chose the tileset
	* @return the "browse" button
	*/
	public JButton getTilesetButton()
	{
		return this.tilesetButton;
	}
	
	/** Gives the "create" button
	* @return the "create" button
	*/
	public JButton getConfirmButton()
	{
		return this.confirmButton;
	}
	
	/** Allows to change the tileset's path
	* @param path the new tileset's path
	*/
	public void setTilesetPath(String path)
	{
		this.tilesetPath = path;
  		this.labelTilesetPath.setText(this.tilesetPath);
  		this.revalidate();
	}
	
	/** Gives the tileset's path
	* @return the tileset's path
	*/
	public String getTilesetPath()
	{
		return this.tilesetPath;
	}
	
	/** Gives the typped width for the map
	* @return the typped width for the map
	*/
	public int getMapWidth()
	{
		return ((Integer) this.spinnerMapWidth.getValue()).intValue();
	}
	
	/** Gives the typped height for the map
	* @return the typped height for the map
	*/
	public int getMapHeight()
	{
		return ((Integer) this.spinnerMapHeight.getValue()).intValue();
	}
	
	/** Gives the typped width for the tiles
	* @return the typped width for the tiles
	*/
	public int getTileWidth()
	{
		return ((Integer) this.spinnerTileWidth.getValue()).intValue();
	}
	
	/** Gives the typped height for the tiles
	* @return the typped height for the tiles
	*/
	public int getTileHeight()
	{
		return ((Integer) this.spinnerTileHeight.getValue()).intValue();
	}
	
	/** Gives the selected collision type
	* @return 0 for the solid collision, 1 for the rubber collision
	*/
	public int getSelectedCollision()
	{
		int ret = 0;
	
		if(this.rubberRadio.isSelected())
			ret = 1;
		
		return ret;
	}
}
