package beditor.view;

import beditor.model.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
* TilesetPan : a class to display the tileset
*
* @author Hugo PIGEON
* @version 1.1
*/

public class TilesetPan extends JPanel
{
	private Tileset tileset;
	private Sprite sprites[][];
	private EditorPan editorPan;
	private int tilesetWidth;
	private int tilesetHeight;
	private Sprite selectedSprite;
	private BufferedImage tilesetImg;
	private String tilesetPath;

	/** Constructor which makes a new tileset
	* @param tilesetPath the tileset's path
	* @param tileSize the dimensions of one tile in the tileset
	* @param editorPan the panel which contains the tileset
	*/
	public TilesetPan(String tilesetPath, Dimension tileSize, EditorPan editorPan)
	{
		if(tilesetPath != "")
		{
			this.tilesetPath = tilesetPath;
			this.editorPan = editorPan;
			File file = new File(tilesetPath);
			try
			{
				this.tilesetImg = ImageIO.read(file);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			this.tilesetWidth = (int) (this.tilesetImg.getWidth() / tileSize.getWidth());
			this.tilesetHeight = (int) (this.tilesetImg.getHeight() / tileSize.getHeight());
			
			this.tileset = new Tileset(new Dimension(tilesetWidth, tilesetHeight));
			
			this.sprites = new Sprite[this.tilesetWidth][this.tilesetHeight];
			
			for(int y = 0 ; y < this.tilesetHeight ; y++)
			{
				for(int x = 0 ; x < this.tilesetWidth ; x++)
				{
					this.sprites[x][y] = new Sprite(this, this.tilesetImg, new Point((int) (x * tileSize.getWidth()), (int) (y * tileSize.getHeight())), tileSize, true, false);
					this.add(this.sprites[x][y].getImage());
				}
			}
			this.setSelectedSprite(sprites[0][0]);
			
			this.setLayout(new GridLayout(tilesetHeight, tilesetWidth, 0, 0));
			this.revalidate();
		}
	}
	
	/** Selects a sprite in the tileset's tiles
	* @param sprite the selected sprite
	*/
	public void setSelectedSprite(Sprite sprite)
	{
		this.selectedSprite = sprite;
	}
	
	/** Gives the selected sprite
	* @return the selected sprite
	*/
	public Sprite getSelectedSprite()
	{
		return this.selectedSprite;
	}
	
	/** Gives the selected sprite's coordinates (in tiles)
	* @return the selected sprite's coordinates (in tiles)
	*/
	public Point getSelectedSpriteIndex()
	{
		boolean trouve = false;
		Point coord = new Point(-1, -1);
	
		for(int x = 0 ; x < this.tilesetWidth ; x++)
		{
			for(int y = 0 ; y < this.tilesetHeight ; y++)
			{
				if(this.sprites[x][y] == this.selectedSprite)
				{
					trouve = true;
					coord.setLocation(x, y);
				}
			}
		}
		
		return coord;
	}
	
	/** Gives the sprite of the tile at the specified index
	* @param index the tile's index
	*/
	public BufferedImage getSpriteImage(int index)
	{
		BufferedImage ret = this.tilesetImg.getSubimage(0, 0, this.sprites[0][0].getWidth(), this.sprites[0][0].getHeight());
		boolean found = false;
		
		for(int y = 0 ; y < this.tilesetHeight && !found ; y++)
		{
			for(int x = 0 ; x < this.tilesetWidth && !found ; x++)
			{
				if(x + this.tilesetWidth * y == index)
				{
					found = true;
					ret =  this.tilesetImg.getSubimage(x * this.sprites[0][0].getWidth(), y * this.sprites[0][0].getHeight(), this.sprites[0][0].getWidth(), this.sprites[0][0].getHeight());
				}
			}
		}
		
		return ret;
	}	
	
	/** Gives the tileset's width (in tiles)
	* @return the tileset's width (in tiles)
	*/
	public int getTilesetWidth()
	{
		return this.tilesetWidth;
	}
	
	/** Gives the tileset's height (in tiles)
	* @return the tileset's height (in tiles)
	*/
	public int getTilesetHeight()
	{
		return this.tilesetHeight;
	}
	
	/** Gives the panel which contains the tileset
	* @return the panel which contains the tileset
	*/
	public EditorPan getEditorPan()
	{
		return this.editorPan;
	}
	
	/** Gives the tileset (model)
	* @return the tileset
	*/
	public Tileset getTileset()
	{
		return this.tileset;
	}
	
	/** Gives the path of the tileset
	* @return the path of the tileset
	*/
	public String getTilesetPath()
	{
		return this.tilesetPath;
	}
	
	/** Called to refresh the tileset
	*/
	public void revalidate()
	{
		super.revalidate();
		for(int y = 0 ; y < this.tilesetHeight ; y++)
		{
			for(int x = 0 ; x < this.tilesetWidth ; x++)
			{
				this.sprites[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
			}
		}
		if(this.selectedSprite != null)
			this.selectedSprite.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
