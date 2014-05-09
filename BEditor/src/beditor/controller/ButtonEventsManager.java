package beditor.controller;

import beditor.view.*;
import beditor.model.*;
import java.awt.event.*;
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
	
	private final int SCROLLX = 10;
	private final int SCROLLY = 10;
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
				
					mapFileWriter.write("Tilemapping Version 1.0\n");
					mapFileWriter.write("#nbTilesLengthWorld\n");
					mapFileWriter.write(map.getHeight() + "\n");
					mapFileWriter.write("#nbTilesWidthWorld\n");
					mapFileWriter.write(map.getWidth() + "\n");
					mapFileWriter.write("#nbTilesX\n");
					mapFileWriter.write(win.getEditorPan().getTilesetPan().getTilesetWidth() + "\n");
					mapFileWriter.write("#nbTilesY\n");
					mapFileWriter.write(win.getEditorPan().getTilesetPan().getTilesetHeight() + "\n");
					mapFileWriter.write("#lengthTile\n");
					mapFileWriter.write((int) win.getEditorPan().getMapPan().getTilesDim().getHeight() + "\n");
					mapFileWriter.write("#widthTile\n");
					mapFileWriter.write((int) win.getEditorPan().getMapPan().getTilesDim().getWidth() + "\n");
					mapFileWriter.write("#scrollX\n");
					mapFileWriter.write(SCROLLX + "\n");
					mapFileWriter.write("#scrollY\n");
					mapFileWriter.write(SCROLLY + "\n");
					mapFileWriter.write("#nbItem\n");
					mapFileWriter.write("XXXXXXX  TO DO  XXXXXXX\n");
					mapFileWriter.write("#collisionType\n");
					if(map.getCollisionType() == 0)
						mapFileWriter.write("solid\n");
					else if(map.getCollisionType() == 1)
						mapFileWriter.write("rubber\n");
					mapFileWriter.write("#coordinateStart\n");
					mapFileWriter.write("XXXXXXX  TO DO  XXXXXXX\n");
					mapFileWriter.write("#coordinateFinish\n");
					mapFileWriter.write("XXXXXXX  TO DO  XXXXXXX\n");
					mapFileWriter.write("#tileset\n");
					for(int y = 0 ; y < win.getEditorPan().getTilesetPan().getTilesetHeight() ; y++)
					{
						for(int x = 0 ; x < win.getEditorPan().getTilesetPan().getTilesetWidth() ; x++)
						{
							mapFileWriter.write("tile " + (x + y * win.getEditorPan().getTilesetPan().getTilesetWidth()));
							if(win.getEditorPan().getTilesetPan().getTileset().getTile(new Point(x, y)).getSolid())
								mapFileWriter.write(" solid ");
							else
								mapFileWriter.write(" empty ");
							if(win.getEditorPan().getTilesetPan().getTileset().getTile(new Point(x, y)).getBreakable())
								mapFileWriter.write("breakable\n");
							else
								mapFileWriter.write("not breakable\n");
						}
					}
					
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
					mapFileWriter.close();
					
					JOptionPane savedMsg = new JOptionPane();
					savedMsg.showMessageDialog(win, "The map has been successfully saved", "Map saved", JOptionPane.INFORMATION_MESSAGE); 
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

		//Called when clicking on "properties" in the "map" menu
		this.win.getMapPropertiesMenu().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(win.getEditorPan() != null)
				{
					win.setMapPropertiesWindow(new MapPropertiesWindow(win));
				}
				else
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, "There is no edited map, you can't access to the properties", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
    		
    	// Called when clicking on "browse" for the tileset
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
    	
    	// Called when clicking on "create" for the new map
    	this.win.getNewMapPan().getConfirmButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			if(win.getNewMapPan().getTilesetPath() != "")
    			{
    				NewMapPan nmp = win.getNewMapPan();
    				win.switchMap(new EditorPan(new Dimension(nmp.getMapWidth(), nmp.getMapHeight()), new Dimension(nmp.getTileWidth(), nmp.getTileHeight()), nmp.getTilesetPath(), nmp.getSelectedCollision(), win));
    			}
    			else
    			{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, TILESET_NOT_SELECTED_MSG, TILESET_NOT_SELECTED_TITLE, JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	});
	}
	
	public void addPropertiesPanListeners()
	{	
    	// Called when clicking on the "empty" radio button in the properties
    	this.win.getEditorPan().getPropertiesPan().getEmptyRadio().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
	    		win.getEditorPan().getTilesetPan().getTileset().getTile(win.getEditorPan().getTilesetPan().getSelectedSpriteIndex()).setSolid(false);
    		}
    	});
    	
    	// Called when clicking on the "solid" radio button in the properties
    	this.win.getEditorPan().getPropertiesPan().getSolidRadio().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
	    		win.getEditorPan().getTilesetPan().getTileset().getTile(win.getEditorPan().getTilesetPan().getSelectedSpriteIndex()).setSolid(true);
    		}
    	});
    	
    	// Called when clicking on the "not breakable" radio button in the properties
    	this.win.getEditorPan().getPropertiesPan().getNotBreakableRadio().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
	    		win.getEditorPan().getTilesetPan().getTileset().getTile(win.getEditorPan().getTilesetPan().getSelectedSpriteIndex()).setBreakable(false);
    		}
    	});
    	
    	// Called when clicking on the "breakable" radio button in the properties
    	this.win.getEditorPan().getPropertiesPan().getBreakableRadio().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
	    		win.getEditorPan().getTilesetPan().getTileset().getTile(win.getEditorPan().getTilesetPan().getSelectedSpriteIndex()).setBreakable(true);
    		}
    	});
	}
	
	public void addMapPropertiesWindowListeners()
	{
		// Called when clicking on the "apply" button
    	win.getMapPropertiesWindow().getApplyButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			win.getEditorPan().getMapPan().setMapDim(win.getMapPropertiesWindow().getMapWidth(), win.getMapPropertiesWindow().getMapHeight());
    			
	    		win.getMapPropertiesWindow().dispose();
    			win.setMapPropertiesWindow(null);
    		}
    	});
	}
}
