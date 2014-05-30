package beditor.view;

import beditor.controller.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;

/**
* Sprite : Displays a sprite from a picture
*
* @author Hugo PIGEON
* @version 1.1
*/

public class Sprite
{
	private JLabel img;
	private String file;
	private Point coord;
	private Dimension dim;
	private JPanel panel;

	/** Constructor which makes a new sprite in the given panel with the specified coordinates and dimensions. Information about the events is also needed
	* @param pan the panel which contains the sprite
	* @param coord the sprite's coordinates
	* @param dim the map's dimensions
	* @param checkClicks give true to allow mouse click events
	* @param checkMoves give true to allow mouse moves events
	*/
	public Sprite(JPanel pan, Point coord, Dimension dim, boolean checkClicks, boolean checkMoves)
	{
		this.panel = pan;
	
		this.file = file;
		this.coord = coord;
		this.dim = dim;
		
		this.img = new JLabel();
		this.img.setOpaque(true);
		
		if(this.panel instanceof MapPan)
		{
			MouseEventsManager mouseEvents = new MouseEventsManager(this, ((MapPan) this.panel).getMap(), checkClicks, checkMoves);
			this.img.addMouseListener(mouseEvents);
		}
		else if(this.panel instanceof TilesetPan)
		{
			MouseEventsManager mouseEventsLayer1 = new MouseEventsManager(this, ((TilesetPan) this.panel).getEditorPan().getLayer1().getMap(), checkClicks, checkMoves);
			MouseEventsManager mouseEventsLayer2 = new MouseEventsManager(this, ((TilesetPan) this.panel).getEditorPan().getLayer2().getMap(), checkClicks, checkMoves);
			this.img.addMouseListener(mouseEventsLayer1);
			this.img.addMouseListener(mouseEventsLayer2);
		}
	}
	
	/** Constructor which makes a new sprite in the given panel with the specified picture. The displayed picture is a part of the given one. Information about the events is also needed
	* @param pan the panel which contains the sprite
	* @param img the loaded picture to create the "subimage"
	* @param coord the "subimage"'s coordinates
	* @param dim the "subimage"'s dimensions
	* @param checkClicks give true to allow mouse click events
	* @param checkMoves give true to allow mouse moves events
	*/
	public Sprite(JPanel pan, BufferedImage img, Point coord, Dimension dim, boolean checkClicks, boolean checkMoves)
	{		
		this.panel = pan;
		this.coord = coord;
		this.dim = dim;
		
		this.img = new JLabel(new ImageIcon(img.getSubimage((int) coord.getX(), (int) coord.getY(), (int) dim.getWidth(), (int) dim.getHeight())));
		
		if(this.panel instanceof MapPan)
		{
			MouseEventsManager mouseEvents = new MouseEventsManager(this, ((MapPan) this.panel).getMap(), checkClicks, checkMoves);
			this.img.addMouseListener(mouseEvents);
		}
		else if(this.panel instanceof TilesetPan)
		{
			MouseEventsManager mouseEventsLayer1 = new MouseEventsManager(this, ((TilesetPan) this.panel).getEditorPan().getLayer1().getMap(), checkClicks, checkMoves);
			MouseEventsManager mouseEventsLayer2 = new MouseEventsManager(this, ((TilesetPan) this.panel).getEditorPan().getLayer2().getMap(), checkClicks, checkMoves);
			this.img.addMouseListener(mouseEventsLayer1);
			this.img.addMouseListener(mouseEventsLayer2);
		}
	}
	
	/** Changes the sprite's border
	* @param b the new border
	*/
	public void setBorder(Border b)
	{
		this.img.setBorder(b);
		this.img.revalidate();
	}
	
	/** Gives the sprite's width
	* @return the sprite's width
	*/
	public int getWidth()
	{
		return (int) this.dim.getWidth();
	}
	
	/** Gives the sprite's height
	* @return the sprite's height
	*/
	public int getHeight()
	{
		return (int) this.dim.getHeight();
	}
	
	/** Gives the sprite's position (in pixels)
	* @return the sprite's position (in pixels)
	*/
	public Point getPosition()
	{
		return this.coord;
	}
	
	/** Gives the sprite's image
	* @return the sprite's image
	*/
	public JLabel getImage()
	{
		return this.img;
	}
	
	/** Changes the sprite's image
	* @param img the new sprite's image
	*/
	public void setImage(ImageIcon img)
	{
		this.img.setIcon(img);
	}
	
	/** Gives the panel which contains the sprite
	* @return the panel which contains the sprite
	*/
	public JPanel getPanel()
	{
		return this.panel;
	}
}
