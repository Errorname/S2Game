package model;

/**
* Classe Vector : définit des vecteurs
*
* @author Mathieu GRONDIN
* @version 1.0
*/
public class Vector{
	private int x;
	private int y;
	
	
	/**Constructeur de la classe Vecteur
	*@param sonx abscisse du vecteur
	*@param sony ordonnee du vecteur
	*/
	public Vector(int sonx,int sony){
		this.x = sonx;
		this.y = sony;
	}
	
	/**Permet d'obtenir l'abcisse d'un vecteur
	*@return l'ordonnee du vecteur
	*/
	public int getX(){
		return this.x;
	}
	
	/**Permet d'obtenir l'ordonnee d'un vecteur
	*@return l'ordonnee du vecteur
	*/
	public int getY(){
		return this.y;
	}
	
	/**Permet de valuer l'abcisse d'un vecteur
	*@param newX la nouvelle abscisse
	*/
	public void setX(int newX){
		this.x = newX;
	}
	
	/**Permet de valuer l'ordonnee d'un vecteur
	*@param newY la nouvelle ordonnee
	*/
	public void setY(int newY){
		this.y = newY;
	}
}
