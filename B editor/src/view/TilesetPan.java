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
	private int tilesetWidth;
	private int tilesetHeight;
	private Sprite selectedSprite;

	/** Constructeur permettant de créer un nouveau TilesetPan en précisant le fichier de tileset
	* @param tilesetPath le chemin du tileset à charger
	* @param tileSize les dimensions d'un tile du tileset
	*/
	public TilesetPan(String tilesetPath, Dimension tileSize)
	{
		if(tilesetPath != "")
		{
			try
			{
				File file = new File(tilesetPath);
				BufferedImage image = ImageIO.read(file);
				this.sprites = new Sprite[(int) tileSize.getWidth()][(int) tileSize.getHeight()];
				
				this.tilesetWidth = (int) (image.getWidth() / tileSize.getWidth());
				this.tilesetHeight = (int) (image.getHeight() / tileSize.getHeight());
				
				for(int y = 0 ; y < this.tilesetHeight ; y++)
				{
					for(int x = 0 ; x < this.tilesetWidth ; x++)
					{
						this.sprites[x][y] = new Sprite(this, image, new Coordinate((int) (x * tileSize.getWidth()), (int) (y * tileSize.getHeight())), tileSize, true, false);
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
