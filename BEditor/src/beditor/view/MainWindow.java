package beditor.view;

import beditor.model.*;
import beditor.controller.*;
import javax.swing.*;
import java.awt.*;

/**
* Window : class for the window which displays the editor
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MainWindow extends JFrame
{	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem quitFileMenu;
	private JMenuItem newFileMenu;
	private JMenuItem saveFileMenu;
	private MapPan mapPan;
	private NewMapPan newMapPan;

	/** Constructor to make a new window
	*/
	public MainWindow()
	{	
		this.newMapPan = new NewMapPan();
		this.setContentPane(this.newMapPan);
	
    	this.initWindow();
    	this.initMenu();
    	//this.setResizable(false);
    	
    	this.setVisible(true);
	}
	
	/** Initializes the window
	*/
	private void initWindow()
	{
   		this.setTitle("B Editor");
    	this.setSize(1024, 768);
   		this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** Initializes the menu
	*/
	private void initMenu()
	{
		this.menuBar = new JMenuBar();
    	
    	this.fileMenu = new JMenu("File");
    	this.menuBar.add(this.fileMenu);
    	
    	this.newFileMenu = new JMenuItem("New map");
    	this.fileMenu.add(this.newFileMenu);
    	
    	this.saveFileMenu = new JMenuItem("Save the map");
    	this.fileMenu.add(this.saveFileMenu);
    	
    	this.quitFileMenu = new JMenuItem("Quit");
    	this.fileMenu.add(this.quitFileMenu);
    	
    	this.setJMenuBar(menuBar); 
	}
	
	/** Gives the menu's "quit" button
	* @return the menu's "quit" button
	*/
	public JMenuItem getQuitFileMenu()
	{
		return this.quitFileMenu;
	}
	
	/** Gives the menu's "new map" button
	* @return the menu's "new map" button
	*/
	public JMenuItem getNewFileMenu()
	{
		return this.newFileMenu;
	}
	
	/** Gives the menu's "save map" button
	* @return the menu's "save map" button
	*/
	public JMenuItem getSaveFileMenu()
	{
		return this.saveFileMenu;
	}
	
	/** Gives the window's MapPan
	* @return the windows's MapPan
	*/
	public MapPan getMapPan()
	{
		return this.mapPan;
	}
	
	/** Gives the window's NewMapPan
	* @return the windows's NewMapPan
	*/
	public NewMapPan getNewMapPan()
	{
		return this.newMapPan;
	}
	
	/** Switches from the NewMapPan screen to the MapPan screen
	* @param mapPan the new MapPan screen
	*/
	public void switchMap(MapPan mapPan)
	{
		this.mapPan = mapPan;
		this.setContentPane(this.mapPan);
		this.revalidate();
	}
	
	/** Switches from the MapPan screen to the NewMapPan screen
	*/
	public void switchNewMap()
	{
		this.setContentPane(this.newMapPan);
		this.revalidate();
	}	
}
