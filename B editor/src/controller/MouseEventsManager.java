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
	private Sprite sprite;
	private boolean checkClicks;
	private boolean checkMoves;
	
	/** Constructeur pour lequel il faut préciser le sprite observé
	* @param sprite le sprite observé
	* @param checkClicks mettre à true pour gérer les événements de clic
	* @param checkMoves mettre à true pour gérer les événements de mouvement
	*/
	public MouseEventsManager(Sprite sprite, boolean checkClicks, boolean checkMoves)
	{
		this.sprite = sprite;
		this.checkClicks = checkClicks;
		this.checkMoves = checkMoves;
	}

	/** Cette méthode est appelée lors d'un clic de la souris
	* @param e l'événement traité
	*/
	public void mouseClicked(MouseEvent e)
	{
		if(this.checkClicks)
		{
			if(this.sprite.getPanel() instanceof TilesetPan)
			{
				((TilesetPan) this.sprite.getPanel()).setSelectedSprite(this.sprite);
			}
			else if(this.sprite.getPanel() instanceof MapPan)
			{
				this.sprite.setImage(((ImageIcon) ((MapPan) this.sprite.getPanel()).getSelectedTile().getImage().getIcon()));
			}
			this.sprite.getPanel().revalidate();
		}
	}

	/** Cette méthode est appelée lorsque la souris entre sur le sprite
	* @param e l'événement traité
	*/
	public void mouseEntered(MouseEvent e)
	{
		if(this.checkMoves)
			this.sprite.getImage().setBorder(BorderFactory.createLineBorder(Color.white, 2));
	}
	
	/** Cette méthode est appelée lorsque la souris quitte le sprite
	* @param e l'événement traité
	*/
	public void mouseExited(MouseEvent e) 
	{
		if(this.checkMoves)
			this.sprite.getImage().setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
