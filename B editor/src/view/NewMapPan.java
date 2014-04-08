package view;

import model.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe MapPan : permet de chosir les options pour créer une nouvelle carte
*
* @author Hugo PIGEON
* @version 1.0
*/

public class NewMapPan extends JPanel
{	
	private String tilesetPath;
	private JButton tilesetButton;
	private JButton confirmButton;
	private JLabel labelTilesetPath;

	/** Constructeur permettant d'afficher les boutons
	*/
	public NewMapPan()
	{		
		JLabel labelTileset = new JLabel("Chemin vers le tileset : ");
		this.tilesetPath = "";
		this.labelTilesetPath = new JLabel(this.tilesetPath);
		this.tilesetButton = new JButton("Parcourir...");
		
		JLabel labelMapWidth = new JLabel("Largeur de la carte (en tiles) :");
		SpinnerModel mapWidthModel = new SpinnerNumberModel(64, 1, 512, 1);
		JSpinner spinnerMapWidth = new JSpinner(mapWidthModel);
		
		JLabel labelMapHeight = new JLabel("Hauteur de la carte (en tiles) :");
		SpinnerModel mapHeightModel = new SpinnerNumberModel(64, 1, 512, 1);
		JSpinner spinnerMapHeight = new JSpinner(mapHeightModel);
		
		JLabel labelTileWidth = new JLabel("Largeur des tiles (en pixels) :");
		SpinnerModel tilesWidthModel = new SpinnerNumberModel(32, 1, 256, 1);
		JSpinner spinnerTileWidth = new JSpinner(tilesWidthModel);
		
		JLabel labelTileHeight = new JLabel("Hauteur des tiles (en pixels) :");
		SpinnerModel tilesHeightModel = new SpinnerNumberModel(32, 1, 256, 1);
		JSpinner spinnerTileHeight = new JSpinner(tilesHeightModel);
		
		this.confirmButton = new JButton("Valider");
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 3));
		JPanel p11 = new JPanel();
		p11.add(labelTileset);
		JPanel p12 = new JPanel();
		p12.add(this.labelTilesetPath);
		JPanel p13 = new JPanel();
		p13.add(this.tilesetButton);
		
		p1.add(p11);
		p1.add(p12);
		p1.add(p13);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 2));
		JPanel p21 = new JPanel();
		p21.add(labelMapWidth);
		JPanel p22 = new JPanel();
		p22.add(spinnerMapWidth);
		
		JPanel p23 = new JPanel();
		p23.add(labelMapHeight);
		JPanel p24 = new JPanel();
		p24.add(spinnerMapHeight);
		
		JPanel p25 = new JPanel();
		p25.add(labelTileWidth);
		JPanel p26 = new JPanel();
		p26.add(spinnerTileWidth);
		
		JPanel p27 = new JPanel();
		p27.add(labelTileHeight);
		JPanel p28 = new JPanel();
		p28.add(spinnerTileHeight);
		
		p2.add(p21);
		p2.add(p22);
		p2.add(p23);
		p2.add(p24);
		p2.add(p25);
		p2.add(p26);
		p2.add(p27);
		p2.add(p28);
		
		JPanel p3 = new JPanel();
		p3.add(confirmButton);
		
		this.setLayout(new GridLayout(3, 1));
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
	}
	
	/** Permet d'obtenir le bouton "parcourir" de la sélection de tileset
	* @return le bouton "parcourir" de la sélection de tileset
	*/
	public JButton getTilesetButton()
	{
		return this.tilesetButton;
	}
	
	/** Permet d'obtenir le bouton "valider" de la sélection de tileset
	* @return le bouton "valider" de la sélection de tileset
	*/
	public JButton getConfirmButton()
	{
		return this.confirmButton;
	}
	
	/** Permet de modifier le chemin du tileset
	* @param path le chemin du tileset
	*/
	public void setTilesetPath(String path)
	{
		this.tilesetPath = path;
  		this.labelTilesetPath.setText(this.tilesetPath);
	}
	
	/** Permet d'obtenir le chemin du tileset
	* @return le chemin du tileset
	*/
	public String getTilesetPath()
	{
		return this.tilesetPath;
	}
}
