package view;

import controller.*;
import model.Coordinate;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;

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
	private Dimension dim;

	/** Constructeur qui permet de préciser le chemin de l'image, les coordonnées et la taille du sprite
	* @param file le fichier image chargé par le sprite
	* @param coord les coordonnées du sprite
	* @param dim les dimensions du sprite
	*/
	public Sprite(String file, Coordinate coord, Dimension dim)
	{
		this.file = file;
		this.coord = coord;
		this.dim = dim;
		
		this.img = new JLabel(new ImageIcon(file));
		
		MouseEventsManager mouseEvents = new MouseEventsManager(this.img);
		this.img.addMouseListener(mouseEvents);
		this.img.addMouseMotionListener(mouseEvents);
	}
	
	/** Constructeur qui permet de préciser l'image à charger ainsi que les coordonnées et la taille de la "sous-image"
	* @param img l'image chargée par le sprite
	* @param coord les coordonnées de la "sous-image"
	* @param dim les dimensions de la "sous-image"
	*/
	public Sprite(BufferedImage img, Coordinate coord, Dimension dim)
	{		
		this.img = new JLabel(new ImageIcon(img.getSubimage(coord.getX(), coord.getY(), (int) dim.getWidth(), (int) dim.getHeight())));
	}
	
	/** Permet de changer la bordure du sprite
	* @param b la nouvelle bordure
	*/
	public void setBorder(Border b)
	{
		this.img.setBorder(b);
		this.img.revalidate();
	}
	
	/** Permet d'obtenir la largeur du sprite
	* @return la largeur du sprite
	*/
	public int getWidth()
	{
		return (int) this.dim.getWidth();
	}
	
	/** Permet d'obtenir la hauteur du sprite
	* @return la largeur du sprite
	*/
	public int getHeight()
	{
		return (int) this.dim.getHeight();
	}
	
	/** Permet d'obtenir les coordonnées (en pixels) du tile
	* @return la position en pixels du tile
	*/
	public Coordinate getPosition()
	{
		return this.coord;
	}
	
	/** Permet d'obtenir l'image du sprite
	* @return l'image du sprite
	*/
	public JLabel getImage()
	{
		return this.img;
	}

	/** Affichage de l'image
	* @param g le conteneur dans lequel sera affichée l'image
	*/
  	/*public void paint(Graphics g)
  	{
		this.img.getIcon().paintIcon(null, g, this.coord.getX() + 1, this.coord.getY() + 1);
		this.img.getBorder().paintBorder(null, g, this.coord.getX(), this.coord.getY(), this.dim.getX() + 2, this.dim.getY() + 2);
	}*/
}
