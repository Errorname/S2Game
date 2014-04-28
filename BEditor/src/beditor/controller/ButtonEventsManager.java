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
	
	private final String MAP_SAVED_MSG = "The map has been successfully saved";
	private final String MAP_SAVED_TITLE = "Map saved";
	private final String MAP_NOT_CREATED_MSG = "No map has been created, nothing saved";
	private final String MAP_NOT_CREATED_TITLE = "Error";
	private final String MAP_NOT_SAVED_MSG = "The map couldn't be saved";
	private final String MAP_NOT_SAVED_TITLE = "Error";
	private final String TILESET_NOT_SELECTED_MSG = "You must select a tileset";
	private final String TILESET_NOT_SELECTED_TITLE = "Error";
	
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
    				Map map = win.getEditorPan().getMapPan().getMap();
    				
    				File mapFile = new File("newMap.map");
					mapFile.createNewFile();
					FileWriter mapFileWriter = new FileWriter(mapFile);
					for(int y = 0 ; y < map.getHeight() ; y++)
					{
						for(int x = 0 ; x < map.getWidth() ; x++)
						{
							if(map.getTile(x, y) == -1)
								mapFileWriter.write("  ");
							else
								mapFileWriter.write(map.getTile(x, y) + " ");
						}
						mapFileWriter.write("\n");
					}
					
					JOptionPane savedMsg = new JOptionPane();
					savedMsg.showMessageDialog(win, MAP_SAVED_MSG, MAP_SAVED_TITLE, JOptionPane.INFORMATION_MESSAGE);      
					mapFileWriter.close();
    			}
    			catch(NullPointerException e)
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, MAP_NOT_CREATED_MSG, MAP_NOT_CREATED_TITLE, JOptionPane.ERROR_MESSAGE);
    			}
				catch(Exception e)
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, MAP_NOT_SAVED_MSG, MAP_NOT_SAVED_TITLE, JOptionPane.ERROR_MESSAGE);
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
    			int choice = chooser.showOpenDialog(win);
    			if(choice == JFileChooser.APPROVE_OPTION)
	    			win.getNewMapPan().setTilesetPath(chooser.getSelectedFile().getPath());
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
    				win.switchMap(new EditorPan(new Dimension(nmp.getMapWidth(), nmp.getMapHeight()), new Dimension(nmp.getTileWidth(), nmp.getTileHeight()), nmp.getTilesetPath()));
    			}
    			else
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, TILESET_NOT_SELECTED_MSG, TILESET_NOT_SELECTED_TITLE, JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	});
	}
}
