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
	private JMenu mapMenu;
	private JMenuItem mapPropertiesMenu;
	private EditorPan editorPan;
	private NewMapPan newMapPan;
	private ButtonEventsManager buttons;
	
	private final String WINDOW_TITLE = "BEditor";
	private final Dimension WINDOW_SIZE = new Dimension(/*1024, 768*/800, 500);
	private final String FILE_MENU = "File";
	private final String NEW_MAP_MENU = "New map";
	private final String SAVE_MAP_MENU = "Save the map";
	private final String QUIT_MENU = "Quit";
	private final String MAP_MENU = "Map";
	private final String MAP_PROPERTIES_MENU = "Properties";
	
	/** Constructor to make a new window
	*/
	public MainWindow()
	{	
		this.newMapPan = new NewMapPan();
		this.setContentPane(this.newMapPan);
	
    	this.initWindow();
    	this.initMenu();
    	
		this.buttons = new ButtonEventsManager(this);
    	
    	this.setVisible(true);
	}
	
	/** Initializes the window
	*/
	private void initWindow()
	{
   		this.setTitle(WINDOW_TITLE);
    	this.setSize(WINDOW_SIZE);
   		this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** Initializes the menu
	*/
	private void initMenu()
	{
		this.menuBar = new JMenuBar();
    	
    	this.fileMenu = new JMenu(FILE_MENU);
    	this.menuBar.add(this.fileMenu);
    	
    	this.newFileMenu = new JMenuItem(NEW_MAP_MENU);
    	this.fileMenu.add(this.newFileMenu);
    	
    	this.saveFileMenu = new JMenuItem(SAVE_MAP_MENU);
    	this.fileMenu.add(this.saveFileMenu);
    	
    	this.quitFileMenu = new JMenuItem(QUIT_MENU);
    	this.fileMenu.add(this.quitFileMenu);
    	
    	this.mapMenu = new JMenu(MAP_MENU);
    	this.menuBar.add(this.mapMenu);
    	
    	this.mapPropertiesMenu = new JMenuItem(MAP_PROPERTIES_MENU);
    	this.mapMenu.add(this.mapPropertiesMenu);
    	
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

	/** Gives the map menu's "properties" button
	* @return the map menu's "properties" button
	*/
	public JMenuItem getMapPropertiesMenu()
	{
		return this.mapPropertiesMenu;
	}
	
	/** Gives the window's EditorPan
	* @return the windows's EditorPan
	*/
	public EditorPan getEditorPan()
	{
		return this.editorPan;
	}
	
	/** Gives the window's NewMapPan
	* @return the windows's NewMapPan
	*/
	public NewMapPan getNewMapPan()
	{
		return this.newMapPan;
	}
	
	/** Switches from the NewMapPan screen to the EditorPan screen
	* @param editorPan the new EditorPan screen
	*/
	public void switchMap(EditorPan editorPan)
	{
		this.editorPan = editorPan;
		this.buttons.addPropertiesPanListeners();
		this.setContentPane(this.editorPan);
		this.revalidate();
	}
	
	/** Switches from the EditorPan screen to the NewMapPan screen
	*/
	public void switchNewMap()
	{
		this.setContentPane(this.newMapPan);
		this.revalidate();
	}
	
	/** Gives this window's ButtonEventsManager
	* @return this window's ButtonEventsManager
	*/
	public ButtonEventsManager getButtonEventsManager()
	{
		return this.buttons;
	}
}
