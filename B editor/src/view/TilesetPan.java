package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
* Classe TilesetPan : permet de gérer l'affichage du tileset
*
* @author Hugo PIGEON
* @version 1.0
*/

public class TilesetPan extends JPanel
{
	private Sprite sprites[][];
	private MapPan mapPan;
	private int tilesetWidth;
	private int tilesetHeight;
	private Sprite selectedSprite;
	private BufferedImage tileset;

	/** Constructeur permettant de créer un nouveau TilesetPan en précisant le fichier de tileset
	* @param tilesetPath le chemin du tileset à charger
	* @param tileSize les dimensions d'un tile du tileset
	*/
	public TilesetPan(String tilesetPath, Dimension tileSize, MapPan mapPan)
	{
		if(tilesetPath != "")
		{
			try
			{
				this.mapPan = mapPan;
				File file = new File(tilesetPath);
				this.tileset = ImageIO.read(file);
				this.sprites = new Sprite[(int) tileSize.getWidth()][(int) tileSize.getHeight()];
				
				this.tilesetWidth = (int) (this.tileset.getWidth() / tileSize.getWidth());
				this.tilesetHeight = (int) (this.tileset.getHeight() / tileSize.getHeight());
				
				for(int y = 0 ; y < this.tilesetHeight ; y++)
				{
					for(int x = 0 ; x < this.tilesetWidth ; x++)
					{
						this.sprites[x][y] = new Sprite(this, this.tileset, new Coordinate((int) (x * tileSize.getWidth()), (int) (y * tileSize.getHeight())), tileSize, true, false);
						this.add(this.sprites[x][y].getImage());
					}
				}
				this.setSelectedSprite(sprites[0][0]);
				
				this.setLayout(new GridLayout(tilesetWidth + 1, tilesetHeight + 1, 0, 0));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Erreur : aucun tileset n'a été sélectionné");
		}
	}
	
	/** Permet de sélectionner un sprite
	* @param sprite le sprite sélectionné
	*/
	public void setSelectedSprite(Sprite sprite)
	{
		this.selectedSprite = sprite;
	}
	
	/** Permet d'obtenir le sprite sélectionné
	* @return le sprite sélectionné
	*/
	public Sprite getSelectedSprite()
	{
		return this.selectedSprite;
	}
	
	/** Permet d'obtenir les coordonnées du sprite sélectionné (en tiles)
	* @return les coordonnées du sprite sélectionné
	*/
	public Coordinate getSelectedSpriteIndex()
	{
		boolean trouve = false;
		Coordinate coord = new Coordinate(-1, -1);
	
		for(int x = 0 ; x < this.tilesetWidth ; x++)
		{
			for(int y = 0 ; y < this.tilesetHeight ; y++)
			{
				if(this.sprites[x][y] == this.selectedSprite)
				{
					trouve = true;
					coord.setX(x);
					coord.setY(y);
				}
			}
		}
		
		return coord;
	}
	
	/** Permet d'obtenir le sprite du tileset aux coordonnées et dimensions précisées
	* @param index l'index du sprite
	*/
	public BufferedImage getSpriteImage(int index)
	{
		BufferedImage ret = this.tileset.getSubimage(0, 0, this.sprites[0][0].getWidth(), this.sprites[0][0].getHeight());
		boolean found = false;
		
		for(int y = 0 ; y < this.tilesetHeight && !found ; y++)
		{
			for(int x = 0 ; x < this.tilesetWidth && !found ; x++)
			{
				if(x + this.tilesetWidth * y == index)
				{
					found = true;
					ret =  this.tileset.getSubimage(x * this.sprites[0][0].getWidth(), y * this.sprites[0][0].getHeight(), this.sprites[0][0].getWidth(), this.sprites[0][0].getHeight());
				}
			}
		}
		
		return ret;
	}	
	
	/** Permet d'obtenir la largeur du tileset (en tiles)
	* @return la largeur du tileset en tiles
	*/
	public int getTilesetWidth()
	{
		return this.tilesetWidth;
	}
	
	/** Permet d'obtenir le MapPan qui contient le TilesetPan
	* @return le MapPan qui contient le TilesetPan
	*/
	public MapPan getMapPan()
	{
		return this.mapPan;
	}
	
	/** Cette méthode est appelée pour rafrîchir le JPanel
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
