

/**
* Classe Vector : définit des vecteurs
*
* @author Mathieu GRONDIN
* @version 1.0
*/

package math;

public class Vector{
	private double x;
	private double y;
	
	
	/**Constructeur de la classe Vecteur
	*@param sonx abscisse du vecteur
	*@param sony ordonnee du vecteur
	*/
	public Vector(double sonx,double sony){
		this.x = sonx;
		this.y = sony;
	}
	
	/**Constructeur par recopie
	*@param vect le vecteur a copier
	*/
	public Vector(Vector vect){
		this.x = vect.getX();
		this.y = vect.getY();
	}
	
	/**methode pemertant de copier une coordonne dans une autre
	*@param coord la coordonne a copier
	*/
	public void copie(Vector vect){
		this.setX(vect.getX());
		this.setY(vect.getY());
	}
	
	/**Permet d'obtenir l'abcisse d'un vecteur
	*@return l'ordonnee du vecteur
	*/
	public double getX(){
		return this.x;
	}
	
	/**Permet d'obtenir l'ordonnee d'un vecteur
	*@return l'ordonnee du vecteur
	*/
	public double getY(){
		return this.y;
	}
	
	/**Permet de valuer l'abcisse d'un vecteur
	*@param newX la nouvelle abscisse
	*/
	public void setX(double newX){
		this.x = newX;
	}
	
	/**Permet de valuer l'ordonnee d'un vecteur
	*@param newY la nouvelle ordonnee
	*/
	public void setY(double newY){
		this.y = newY;
		}
}
