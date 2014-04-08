package view;

import model.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe Window : permet de gérer la fenêtre affichant l'éditeur de niveaux
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MainWindow extends JFrame
{	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem quitFileMenu;
	private MapPan mapPan;
	private NewMapPan newMapPan;

	/** Constructeur permettant de créer la fenêtre
	*/
	public MainWindow()
	{
		this.newMapPan = new NewMapPan();
		this.setContentPane(this.newMapPan);
	
    	this.initWindow();
    	this.initMenu();
    	
    	this.setVisible(true);
	}
	
	/** Initialise la fenêtre
	*/
	private void initWindow()
	{
   		this.setTitle("B Editor");
    	this.setSize(800, 600);
   		this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** Initialise le menu
	*/
	private void initMenu()
	{
		this.menuBar = new JMenuBar();
    	
    	this.fileMenu = new JMenu("Fichier");
    	this.menuBar.add(this.fileMenu);
    	
    	this.quitFileMenu = new JMenuItem("Quitter");
    	this.fileMenu.add(this.quitFileMenu);
    	
    	this.setJMenuBar(menuBar); 
	}
	
	/** Permet d'obtenir le bouton "quitter" du menu
	* @return le bouton "quitter" du menu
	*/
	public JMenuItem getQuitFileMenu()
	{
		return this.quitFileMenu;
	}
	
	/** Permet d'obtenir le bouton "parcourir" de la sélection de tileset
	* @return le bouton "parcourir" de la sélection de tileset
	*/
	public JButton getTilesetButton()
	{
		return this.newMapPan.getTilesetButton();
	}
	
	/** Permet d'obtenir le newMapPan
	* @return le newMapPan de la fenêtre
	*/
	public NewMapPan getNewMapPan()
	{
		return this.newMapPan;
	}
	
	/** Permet de passer de l'écran NewMapPan à l'écran MapPan
	* @param mapPan le nouvel écran MapPan
	*/
	public void switchNewMap(MapPan mapPan)
	{
		this.mapPan = mapPan;
		this.setContentPane(this.mapPan);
	}
}
