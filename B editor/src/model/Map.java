package model;

/**
* Classe Carte : définit la carte à éditer
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Map
{
	private int w;
	private int h;
	private int tiles[][];
	
	/** Constructeur permettant de définir une carte en précisant sa largeur et sa hauteur
	* @param width la largeur de la carte
	* @param height la hauteur de la carte
	*/
	public Map(int width, int height)
	{
		this.w = width;
		this.h = height;
		this.tiles = new int[width][height];
		for(int i = 0 ; i < width ; i++)
		{
			for(int j = 0 ; j < height ; j++)
			{
				this.tiles[i][j] = -2;
			}
		}
	}
	
	/** Permet d'obtenir le tile aux coordonnées précisées ou -1 si les coordonnées ne sont pas valides
	* @param x la coordonnée en x du tile à récupérer
	* @param y la coordonnée en y du tile à récupérer
	*/
	public int getTile(int x, int y)
	{
		int ret = -1;
		
		if(x >= 0 && x < this.w && y >= 0 && y < this.h)
			ret = this.tiles[x][y];
			
		return ret;
	}
	
	/** Permet de modifier le tile aux coordonnées précisées si les coordonnées sont valides
	* @param x la coordonnée en x du tile à modifier
	* @param y la coordonnée en y du tile à modifier
	* @param tile le nouveau tile
	*/
	public void setTile(int x, int y, int tile)
	{
		if(x >= 0 && x < this.w && y >= 0 && y < this.h)
			this.tiles[x][y] = tile;
	}
	
	/** Permet d'obtenir la largeur de la carte
	* @return la largeur de la carte
	*/
	public int getWidth()
	{
		return this.w;
	}
	
	/** Permet d'obtenir la hauteur de la carte
	* @return la hauteur de la carte
	*/
	public int getHeight()
	{
		return this.h;
	}
}
