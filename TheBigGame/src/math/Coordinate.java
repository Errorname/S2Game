/**
* Classe Coordonnee : définit des coordonnées
*
* @author Hugo PIGEON
* @version 1.0
*/

package math;

public class Coordinate{
	private double x;
	private double y;

	/** Constructeur permettant de spécifier les coordonnées
	* @param coordX la coordonnée en X
	* @param coordY la coordonnée en Y
	*/
	public Coordinate(double coordX, double coordY){
		this.x = coordX;
		this.y = coordY;
	}
	
	/**Constructeur par recopie
	*@param coord les corrdonnees a copier
	*/
	public Coordinate(Coordinate coord){
		this.x = coord.getX();
		this.y = coord.getY();
	}
	
	/**methode pemertant de copier une coordonne dans une autre
	*@param coord la coordonne a copier
	*/
	public void copie(Coordinate coord){
		this.setX(coord.getX());
		this.setY(coord.getY());
	}
	
	/** Permet d'obtenir la coordonnée en X
	* @return la coordonnée en X
	*/
	public double getX()
	{
		return this.x;
	}
	
	/** Permet d'obtenir la coordonnée en Y
	* @return la coordonnée en Y
	*/
	public double getY()
	{
		return this.y;
	}
	
	/** Permet de changer la coordonnée en X
	* @param coordX la coordonnée en X
	*/
	public void setX(double coordX)
	{
		this.x = coordX;
	}
	
	/** Permet de changer la coordonnée en Y
	* @param coordY la coordonnée en Y
	*/
	public void setY(double coordY)
	{
		this.y = coordY;
	}
}
