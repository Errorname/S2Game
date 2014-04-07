package vue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
* Classe Fenetre : permet de gérer la fenêtre affichant l'éditeur de niveaux
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Fenetre extends JFrame
{	
	private JMenuBar barreMenu;
	private JMenu menuFichier;
	private JMenuItem menuFichierQuitter;

	/** Constructeur permettant de créer la fenêtre
	*/
	public Fenetre()
	{
   		this.setTitle("B editor");
    	this.setSize(800, 600);
   		this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    	
    	this.barreMenu = new JMenuBar();
    	
    	this.menuFichier = new JMenu("Fichier");
    	this.barreMenu.add(this.menuFichier);
    	this.menuFichierQuitter = new JMenuItem("Quitter");
    	this.menuFichier.add(this.menuFichierQuitter);
    	
    	this.setJMenuBar(barreMenu);   
    	  
    	this.setVisible(true);
	}
}
