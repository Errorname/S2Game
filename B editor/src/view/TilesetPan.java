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

	/** Constructeur permettant de créer un nouveau TilesetPan en précisant le fichier de tileset
	* @param tilesetPath le chemin du tileset à charger
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
				
				int tilesetWidth = (int) (image.getWidth() / tileSize.getWidth());
				int tilesetHeight = (int) (image.getHeight() / tileSize.getHeight());
				
				for(int x = 0 ; x < tilesetWidth ; x++)
				{
					for(int y = 0 ; y < tilesetHeight ; y++)
					{
						this.sprites[x][y] = new Sprite(image, new Coordinate((int) (x * tileSize.getWidth()), (int) (y * tileSize.getHeight())), tileSize);
						this.add(this.sprites[x][y].getImage());
					}
				}
				
				
				this.setLayout(new GridLayout(tilesetWidth, tilesetHeight, 0, 0));
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
}
