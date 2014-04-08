package view;

import model.Coordinate;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

/**
* Classe Sprite : permet d'afficher un sprite à partir d'une image
*
* @author Hugo PIGEON repris de la classe écrite par Dylan GAUTIER
* @version 1.0
*/

public class Sprite
{
	private JLabel img;
	private String file;
	private Coordinate coord;
	private int width;
	private int height;

	/** Constructeur qui permet de préciser le chemin de l'image, les coordonnées et la taille du sprite
	* @param file le fichier image chargé par le sprite
	* @param coord les coordonnées du sprite
	* @param width la largeur du sprite
	* @param height la hauteur du sprite
	*/
	public Sprite(String file, Coordinate coord, int width, int height)
	{
		this.file = file;
		this.coord = coord;
		this.width = width;
		this.height = height;
		
		this.img = new JLabel(new ImageIcon(file));
	}
	
	/** Permet de changer la bordure du sprite
	* @param b la nouvelle bordure
	*/
	public void setBorder(Border b)
	{
		this.img.setBorder(b);
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}

	/** Affichage de l'image
	* @param g le conteneur dans lequel sera affichée l'image
	*/
  	public void paint(Graphics g)
  	{
		this.img.getIcon().paintIcon(null, g, this.coord.getX() + 1, this.coord.getY() + 1);
		this.img.getBorder().paintBorder(null, g, this.coord.getX(), this.coord.getY(), this.width + 2, this.height + 2);
	}
}
