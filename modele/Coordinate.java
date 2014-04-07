
/**
* Classe Coordonnee : définit des coordonnées
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Coordinate
{
	private int x;
	private int y;

	/** Constructeur permettant de spécifier les coordonnées
	* @param coordX la coordonnée en X
	* @param coordY la coordonnée en Y
	*/
	public Coordinate(int coordX, int coordY)
	{
		this.x = coordX;
		this.y = coordY;
	}
	
	/** Permet d'obtenir la coordonnée en X
	* @return la coordonnée en X
	*/
	public int getX()
	{
		return this.x;
	}
	
	/** Permet d'obtenir la coordonnée en Y
	* @return la coordonnée en Y
	*/
	public int getY()
	{
		return this.y;
	}
	
	/** Permet de changer la coordonnée en X
	* @param coordX la coordonnée en X
	*/
	public void setX(int coordX)
	{
		this.x = coordX;
	}
	
	/** Permet de changer la coordonnée en Y
	* @param coordY la coordonnée en Y
	*/
	public void setY(int coordY)
	{
		this.y = coordY;
	}
}
