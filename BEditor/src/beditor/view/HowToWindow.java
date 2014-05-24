package beditor.view;

import beditor.model.*;
import beditor.controller.*;
import javax.swing.*;
import java.awt.*;

/**
* HowToWindow : class for the window which explains how to use BEditor
*
* @author Hugo PIGEON
* @version 1.0
*/

public class HowToWindow extends JFrame
{	
	private MainWindow win;
	private JLabel text;
	private JButton closeButton;

	private final String WINDOW_TITLE = "How to use BEditor";
	private final Dimension WINDOW_SIZE = new Dimension(400, 500);
	private final String CLOSE_LABEL = "Close";

	
	/** Constructor to make a new window
	* @param win the parent window
	*/
	public HowToWindow(MainWindow win)
	{
		this.win = win;
		this.initWindow();
		this.addContent();
    	
		this.setVisible(true);
	}
	
	/** Initializes the window
	*/
	private void initWindow()
	{
   		this.setTitle(WINDOW_TITLE);
		this.setSize(WINDOW_SIZE);
   		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/** Adds the content to the window
	*/
	private void addContent()
	{
		this.getContentPane().setLayout(new BorderLayout());
		
		this.text = new JLabel("<html><center><b>How to use BEditor</b><br/><br/>When you open BEditor, the program shows a window which allows you to create a new map.<br/>If you want to open an existing one, you can do it at any moment in the File > Load an existing map menu.<br/>Once you are in the editor, you can choose a part of the tileset on the right with a left click on it.<br/>You can then place it on the map with a left click on the left part.<br/>If you want to remove a tile from the map, you can right click on it.<br/>There are 4 layers on this editor :<ul><li>The layer 1 is mostly used for the background of the map</li><li>The layer 2 is for elements you want to superimpose on the layer 1</li><li>The items layer is for items the player can interact with</li><li>The last layer allows you to place the starting and finishing points. Left click is for the starting point and right click is for the finishing point</li></ul>You can switch between the diferent layers in the Map menu</center></html>");
		
		JPanel closePan = new JPanel();
		this.closeButton = new JButton(CLOSE_LABEL);
		closePan.add(this.closeButton);
		
		this.getContentPane().add(this.text, BorderLayout.CENTER);
		this.getContentPane().add(closePan, BorderLayout.SOUTH);
	}
	
	/** Gives the close button
	* @return the close button
	*/
	public JButton getCloseButton()
	{
		return this.closeButton;
	}
}
