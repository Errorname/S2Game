package controller;

import model.Coordinate;
import view.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Classe MouseEventsManager : traite les événements générés par la souris
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MouseEventsManager extends MouseAdapter
{
	private JLabel sprite;
	
	/** Constructeur pour lequel il faut préciser le sprite observé
	* @param sprite le sprite observé
	*/
	public MouseEventsManager(JLabel sprite)
	{
		this.sprite = sprite;
	}

	/** Cette méthode est appelée lors d'un mouvement de la souris
	* @param e l'événement traité
	*/
	public void mouseEntered(MouseEvent e)
	{
		this.sprite.setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
	public void mouseExited(MouseEvent e) 
	{
		this.sprite.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
