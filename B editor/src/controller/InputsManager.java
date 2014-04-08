package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.*;
import java.awt.*;
import javax.swing.*;

/**
* Classe InputsManager : traite les entrées
*
* @author Hugo PIGEON
* @version 1.0
*/

public class InputsManager
{
	private MainWindow win;
	
	/** Constructeur pour lequel il faut préciser la fenêtre
	* @param window la fenêtre du programme
	*/
	public InputsManager(MainWindow window)
	{
		this.win = window;
		
    	// permet de quitter lors du clic sur le bouton quitter
    	this.win.getQuitFileMenu().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			System.exit(0);
    		}
    	});
    		
    	// permet de sélectionner un fichier pour le choix du tileset
    	this.win.getNewMapPan().getTilesetButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			JFileChooser chooser = new JFileChooser();
    			String filesAllowed[] = new String[2];
    			filesAllowed[0] = "jpg";
    			filesAllowed[1] = "png";
    			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG images", filesAllowed);
    			chooser.setFileFilter(filter);
    			chooser.showOpenDialog(win);
    			win.getNewMapPan().setTilesetPath(chooser.getSelectedFile().getName());
    			
    			win.revalidate();
    		}
    	});
    	
    	// permet de valider la création de la nouvelle carte
    	this.win.getNewMapPan().getConfirmButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			if(win.getNewMapPan().getTilesetPath() != "")
    			{
    				win.switchNewMap(new MapPan(10, 10, 32, 32));
    				win.revalidate();
    			}
    			else
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, "Vous devez sélectionner un tileset", "Erreur", JOptionPane.ERROR_MESSAGE);
    			}
    			
    		}
    	});
	}
}
