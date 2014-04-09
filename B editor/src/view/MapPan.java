package view;

import model.*;
import controller.*;
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
	private TilesetPan tilesetPan;
	
	/** Constructeur permettant d'afficher une nouvelle carte
	* @param mapDim les dimensions de la carte
	* @param tileDim les dimensions d'un tile
	* @param tilesetPath le chemin du tileset
	*/
	public MapPan(Dimension mapDim, Dimension tileDim, String tilesetPath)
	{
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(0, 2));
		
		JPanel tilesPan = new JPanel();
		tilesPan.setLayout(new GridLayout((int) mapDim.getHeight(), (int) mapDim.getWidth(), 0, 0));
		tilesPan.setPreferredSize(new Dimension((int) (mapDim.getWidth() * tileDim.getWidth()), (int) (mapDim.getHeight() * tileDim.getHeight())));
		
		this.map = new Map(mapDim);
		
		this.tiles = new Sprite[(int) mapDim.getWidth()][(int) mapDim.getHeight()];
		
		for(int x = 0 ; x < this.map.getWidth() ; x++)
		{
			for(int y = 0 ; y < this.map.getHeight() ; y++)
			{
				int coordX = x * (int) tileDim.getWidth();
				int coordY = y * (int) tileDim.getHeight();
				
    			this.tiles[x][y] = new Sprite(this, "empty.jpg", new Coordinate(coordX, coordY), tileDim, true, true);
    			this.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
    			tilesPan.add(this.tiles[x][y].getImage());
			}
		}
		
		this.tilesetPan = new TilesetPan(tilesetPath, tileDim);
		
		JScrollPane tilesScroll = new JScrollPane(tilesPan);
		tilesScroll.setPreferredSize(new Dimension(512, 700));
		JScrollPane tilesetScroll = new JScrollPane(this.tilesetPan);
		tilesetScroll.setPreferredSize(new Dimension(512, 700));
		this.add(tilesScroll);
		this.add(tilesetScroll);
	}
	
	/** Permet d'obtenir le tile dont les coordonnées sont précisées (coordonnées en tiles)
	* @param c les coordonnées du tile à récupérer
	* @return le Sprite correspondant au tile
	*/
	public Sprite getTile(Coordinate c)
	{
		return this.tiles[c.getX()][c.getY()];
	}
	
	/** Permet d'obtenir le nombre de tiles en X
	* @return le nombre de tiles en X
	*/
	public int getTilesNumberX()
	{
		return this.tiles.length;
	}
	
	/** Permet d'obtenir le nombre de tiles en Y
	* @return le nombre de tiles en Y
	*/
	public int getTilesNumberY()
	{
		int ret = -1;
		
		if(this.getTilesNumberX() >= 0)
			ret = this.tiles[0].length;
		
		return ret;
	}
	
	/** Permet d'obtenir le tile sélectionné dans le tileset
	* @return le tile sélectionné dans le tileset
	*/
	public Sprite getSelectedTile()
	{
		return this.tilesetPan.getSelectedSprite();
	}
}
