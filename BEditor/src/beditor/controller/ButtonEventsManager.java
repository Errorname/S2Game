package beditor.controller;

import beditor.view.*;
import beditor.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
* ButtonEventsManager : a class which manages the events related to buttons
*
* @author Hugo PIGEON
* @version 1.0
*/

public class ButtonEventsManager
{
	private MainWindow win;
	
	/** Constructor which needs the window
	* @param window the program's window
	*/
	public ButtonEventsManager(MainWindow window)
	{
		this.win = window;
		
		// Called when clicking on "new map"
		this.win.getNewFileMenu().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				win.switchNewMap();
			
				win.revalidate();
			}
		});
		
    	// Called when clicking on "quit"
    	this.win.getQuitFileMenu().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			System.exit(0);
    		}
    	});
    	
    	// Called when clicking on "save the map"
    	this.win.getSaveFileMenu().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			try
    			{
    				Map map = win.getMapPan().getMap();
    				
    				File mapFile = new File("newMap.map");
					mapFile.createNewFile();
					FileWriter mapFileWriter = new FileWriter(mapFile);
					for(int y = 0 ; y < map.getHeight() ; y++)
					{
						for(int x = 0 ; x < map.getWidth() ; x++)
						{
							if(map.getTile(x, y) == 0)
								mapFileWriter.write("  ");
							else
								mapFileWriter.write(map.getTile(x, y) + " ");
						}
						mapFileWriter.write("\n");
					}
					
					JOptionPane savedMsg = new JOptionPane();
					savedMsg.showMessageDialog(win, "The map has been successfully saved", "Map saved", JOptionPane.INFORMATION_MESSAGE);      
					mapFileWriter.close();
    			}
    			catch(NullPointerException e)
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, "No map has been created, nothing saved", "Error", JOptionPane.ERROR_MESSAGE);
    			}
				catch(Exception e)
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, "The map couldn't be saved", "Error", JOptionPane.ERROR_MESSAGE);
				}
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
    			win.getNewMapPan().setTilesetPath(chooser.getSelectedFile().getPath());
    			
    			//////////////////////
    			// TRAITER LE CAS OU L'UTILISATEUR CLIQUE SUR ANNULER DANS LE CHOOSER
    			//////////////////////
    		}
    	});
    	
    	// permet de valider la création de la nouvelle carte
    	this.win.getNewMapPan().getConfirmButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			if(win.getNewMapPan().getTilesetPath() != "")
    			{
    				NewMapPan nmp = win.getNewMapPan();
    				win.switchMap(new MapPan(new Dimension(nmp.getMapWidth(), nmp.getMapHeight()), new Dimension(nmp.getTileWidth(), nmp.getTileHeight()), nmp.getTilesetPath()));
    			}
    			else
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, "You must select a tileset", "Error", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	});
	}
}
