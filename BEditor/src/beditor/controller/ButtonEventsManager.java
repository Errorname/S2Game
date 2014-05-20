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
	private final String NO_EDITED_MAP_MSG = "There is no edited map, you can't access to the properties";
	private final String NO_EDITED_MAP_TITLE = "Error";
	
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
    				Map mapLayer1 = win.getEditorPan().getLayer1().getMap();
    				Map mapLayer2 = win.getEditorPan().getLayer2().getMap();
    				Map mapStartFinish = win.getEditorPan().getStartFinishLayer().getMap();
    				
    				JFileChooser chooser = new JFileChooser();
					String filesAllowed[] = new String[1];
					filesAllowed[0] = "map";
					FileNameExtensionFilter filter = new FileNameExtensionFilter("map file", filesAllowed);
					chooser.setFileFilter(filter);
					int choice = chooser.showSaveDialog(win);
					if(choice == JFileChooser.APPROVE_OPTION)
					{
						File mapFile = new File(chooser.getSelectedFile().getPath());
						mapFile.createNewFile();
						FileWriter mapFileWriter = new FileWriter(mapFile);
				
						mapFileWriter.write("Tilemapping Version 1.0\n");
						mapFileWriter.write("#nbTilesLengthWorld\n");
						mapFileWriter.write(mapLayer1.getHeight() + "\n");
						mapFileWriter.write("#nbTilesWidthWorld\n");
						mapFileWriter.write(mapLayer1.getWidth() + "\n");
						mapFileWriter.write("#nbTilesX\n");
						mapFileWriter.write(win.getEditorPan().getTilesetPan().getTilesetWidth() + "\n");
						mapFileWriter.write("#nbTilesY\n");
						mapFileWriter.write(win.getEditorPan().getTilesetPan().getTilesetHeight() + "\n");
						mapFileWriter.write("#lengthTile\n");
						mapFileWriter.write((int) win.getEditorPan().getLayer1().getTilesDim().getHeight() + "\n");
						mapFileWriter.write("#widthTile\n");
						mapFileWriter.write((int) win.getEditorPan().getLayer1().getTilesDim().getWidth() + "\n");
						mapFileWriter.write("#scrollX\n");
						mapFileWriter.write(SCROLLX + "\n");
						mapFileWriter.write("#scrollY\n");
						mapFileWriter.write(SCROLLY + "\n");
						mapFileWriter.write("#nbItem\n");
						mapFileWriter.write("XXXXXXX  TO DO  XXXXXXX\n");
						mapFileWriter.write("#collisionType\n");
						if(mapLayer1.getCollisionType() == 0)
							mapFileWriter.write("solid\n");
						else if(mapLayer1.getCollisionType() == 1)
							mapFileWriter.write("rubber\n");
						int startX = -1, startY = -1, finishX = -1, finishY = -1;
						boolean foundStart = false, foundFinish = false;
						for(int x = 0 ; x < mapStartFinish.getWidth() && (!foundStart || !foundFinish) ; x++)
						{
							for(int y = 0 ; y < mapStartFinish.getHeight() && (!foundStart || !foundFinish) ; y++)
							{
								if(mapStartFinish.getTile(x, y) == 0)
								{
									startX = x;
									startY = y;
									foundStart = true;
								}
								else if(mapStartFinish.getTile(x, y) == 1)
								{
									finishX = x;
									finishY = y;
									foundFinish = true;
								}
							}
						}
						mapFileWriter.write("#coordinateStart\n");
						mapFileWriter.write(startX + " " + startY + "\n");
						mapFileWriter.write("#coordinateFinish\n");
						mapFileWriter.write(finishX + " " + finishY + "\n");
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
					
						mapFileWriter.write("#level_layer1\n");
						for(int y = 0 ; y < mapLayer1.getHeight() ; y++)
						{
							for(int x = 0 ; x < mapLayer1.getWidth() ; x++)
							{
								if(mapLayer1.getTile(x, y) == -1)
									mapFileWriter.write("  ");
								else
									mapFileWriter.write(mapLayer1.getTile(x, y) + " ");
							}
							mapFileWriter.write("\n");
						}
						mapFileWriter.write("#level_layer2\n");
						for(int y = 0 ; y < mapLayer2.getHeight() ; y++)
						{
							for(int x = 0 ; x < mapLayer2.getWidth() ; x++)
							{
								if(mapLayer2.getTile(x, y) == -1)
									mapFileWriter.write("  ");
								else
									mapFileWriter.write(mapLayer2.getTile(x, y) + " ");
							}
							mapFileWriter.write("\n");
						}
						mapFileWriter.close();
					
						JOptionPane savedMsg = new JOptionPane();
						savedMsg.showMessageDialog(win, MAP_SAVED_MSG, MAP_SAVED_TITLE, JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
    					JOptionPane errorMsg = new JOptionPane();
    					errorMsg.showMessageDialog(win, MAP_NOT_SAVED_MSG, MAP_NOT_SAVED_TITLE, JOptionPane.ERROR_MESSAGE);
					}
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
	
	public void addMapMenuListeners()
	{
		//Called when clicking on "layer 1"
		this.win.getMapLayer1Menu().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(win.getEditorPan() != null)
				{
					win.getEditorPan().editLayer1();
				}
				else
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, NO_EDITED_MAP_MSG, NO_EDITED_MAP_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Called when clicking on "layer 2"
		this.win.getMapLayer2Menu().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(win.getEditorPan() != null)
				{
					win.getEditorPan().editLayer2();
				}
				else
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, NO_EDITED_MAP_MSG, NO_EDITED_MAP_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Called when clicking on "starting and finishing points"
		this.win.getMapStartFinishMenu().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(win.getEditorPan() != null)
				{
					win.getEditorPan().editStartFinishLayer();
				}
				else
				{
    				JOptionPane errorMsg = new JOptionPane();
    				errorMsg.showMessageDialog(win, NO_EDITED_MAP_MSG, NO_EDITED_MAP_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Called when clicking on "properties"
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
    				errorMsg.showMessageDialog(win, NO_EDITED_MAP_MSG, NO_EDITED_MAP_TITLE, JOptionPane.ERROR_MESSAGE);
				}
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
    			win.getEditorPan().getLayer1().setMapDim(win.getMapPropertiesWindow().getMapWidth(), win.getMapPropertiesWindow().getMapHeight());
    			win.getEditorPan().getLayer2().setMapDim(win.getMapPropertiesWindow().getMapWidth(), win.getMapPropertiesWindow().getMapHeight());
    		}
    	});
    	
    	// Called when clicking on the "close" button
    	win.getMapPropertiesWindow().getCloseButton().addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
	    		win.getMapPropertiesWindow().dispose();
    			win.setMapPropertiesWindow(null);
    		}
    	});
	}
}
