package view;

import model.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe MapPan : permet de gérer l'affichage de la carte
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MapPan extends JPanel
{
	private Sprite tiles[][];
	private Map map;
	
	/** Constructeur permettant d'afficher une nouvelle carte
	* @param mapWidth la largeur de la carte
	* @param mapHeight la hauteur de la carte
	* @param tileWidth la largeur des tiles
	* @param tileHeight la hauteur des tiles
	*/
	public MapPan(int mapWidth, int mapHeight, int tileWidth, int tileHeight)
	{
	
		this.setBackground(Color.white);
		this.map = new Map(mapWidth, mapHeight);
		this.tiles = new Sprite[mapWidth][mapHeight];
		
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
    			this.tiles[x][y] = new Sprite("empty.jpg", new Coordinate(x * tileWidth, y * tileHeight), tileWidth, tileHeight);
    			this.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}
	}
	
	/** Redéfinition de paint pour afficher la carte
	* @param g le conteneur dans lequel sera affichée l'image
	*/
	public void paint(Graphics g)
	{
		super.paint(g);
		
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
				this.tiles[x][y].paint(g);
			}
		}
	}
}
