package beditor.view;

import beditor.model.*;
import beditor.controller.*;
import javax.swing.*;
import java.awt.*;

/**
* MapPropertiesWindow : class for the window which displays the current edited map's properties
*
* @author Hugo PIGEON
* @version 1.0
*/

public class MapPropertiesWindow extends JFrame
{	
	private MainWindow win;
	private JButton applyButton, closeButton;
	private JSpinner spinnerMapWidth, spinnerMapHeight, spinnerStartX, spinnerStartY, spinnerEndX, spinnerEndY;

	private final String WINDOW_TITLE = "Map properties";
	private final Dimension WINDOW_SIZE = new Dimension(400, 150);
	private final String APPLY_LABEL = "Apply";
	private final String CLOSE_LABEL = "Close";
	private final String LABEL_MAP_WIDTH = "Map's width (in tiles) :";
	private final String LABEL_MAP_HEIGHT = "Map's height (in tiles) :";

	
	/** Constructor to make a new window
	* @param win the parent window
	*/
	public MapPropertiesWindow(MainWindow win)
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
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		JPanel p1 = new JPanel();
		JLabel labelMapWidth = new JLabel(LABEL_MAP_WIDTH);
	    SpinnerModel mapWidthModel = new SpinnerNumberModel(this.win.getEditorPan().getLayer1().getMap().getWidth(), 16, 512, 1);
    	this.spinnerMapWidth = new JSpinner(mapWidthModel);
		p1.add(labelMapWidth);
		p1.add(this.spinnerMapWidth);
                
		JPanel p2 = new JPanel();
	    JLabel labelMapHeight = new JLabel(LABEL_MAP_HEIGHT);
	    SpinnerModel mapHeightModel = new SpinnerNumberModel(this.win.getEditorPan().getLayer1().getMap().getHeight(), 16, 512, 1);
    	this.spinnerMapHeight = new JSpinner(mapHeightModel);
		p2.add(labelMapHeight);
		p2.add(this.spinnerMapHeight);
		
		JPanel p3 = new JPanel();
		this.applyButton = new JButton(APPLY_LABEL);
		this.closeButton = new JButton(CLOSE_LABEL);
		p3.add(this.applyButton);
		p3.add(this.closeButton);

		this.getContentPane().add(p1);
		this.getContentPane().add(p2);
		this.getContentPane().add(p3);
	}
	
	/** Gives the apply button
	* @return the apply button
	*/
	public JButton getApplyButton()
	{
		return this.applyButton;
	}
	
	/** Gives the close button
	* @return the close button
	*/
	public JButton getCloseButton()
	{
		return this.closeButton;
	}
	
	/** Gives the typped width for the map
	* @return the typped width for the map
	*/
	public int getMapWidth()
	{
		return ((Integer) this.spinnerMapWidth.getValue()).intValue();
	}
	
	/** Gives the typped height for the map
	* @return the typped height for the map
	*/
	public int getMapHeight()
	{
		return ((Integer) this.spinnerMapHeight.getValue()).intValue();
	}
}
