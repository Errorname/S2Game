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
	private JButton applyButton;
	private JSpinner spinnerMapWidth, spinnerMapHeight, spinnerStartX, spinnerStartY;

	private final String WINDOW_TITLE = "Map properties";
	private final Dimension WINDOW_SIZE = new Dimension(400, 200);
	private final String APPLY_LABEL = "Apply";
	private final String LABEL_MAP_WIDTH = "Map's width (in tiles) :";
	private final String LABEL_MAP_HEIGHT = "Map's height (in tiles) :";
	private final String LABEL_STARTING_COORDINATES = "Starting coordinates :";
	private final String LABEL_STARTING_X = "X :";
	private final String LABEL_STARTING_Y = "Y :";

	
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
		this.getContentPane().setLayout(new GridLayout(4, 1));
		
		JPanel p1 = new JPanel();
		JLabel labelMapWidth = new JLabel(LABEL_MAP_WIDTH);
	    SpinnerModel mapWidthModel = new SpinnerNumberModel(this.win.getEditorPan().getMapPan().getMap().getWidth(), 16, 512, 1);
    	this.spinnerMapWidth = new JSpinner(mapWidthModel);
		p1.add(labelMapWidth);
		p1.add(this.spinnerMapWidth);
                
		JPanel p2 = new JPanel();
	    JLabel labelMapHeight = new JLabel(LABEL_MAP_HEIGHT);
	    SpinnerModel mapHeightModel = new SpinnerNumberModel(this.win.getEditorPan().getMapPan().getMap().getHeight(), 16, 512, 1);
    	this.spinnerMapHeight = new JSpinner(mapHeightModel);
		p2.add(labelMapHeight);
		p2.add(this.spinnerMapHeight);
		
		JPanel p3 = new JPanel();
		JLabel labelStartingCoordinates = new JLabel(LABEL_STARTING_COORDINATES);
		JLabel labelStartX = new JLabel(LABEL_STARTING_X);
	    SpinnerModel startXModel = new SpinnerNumberModel(0, 0, 512, 1);
		this.spinnerStartX = new JSpinner(startXModel);
		JLabel labelStartY = new JLabel(LABEL_STARTING_Y);
	    SpinnerModel startYModel = new SpinnerNumberModel(0, 0, 512, 1);
		this.spinnerStartY = new JSpinner(startYModel);
		p3.add(labelStartingCoordinates);
		p3.add(labelStartX);
		p3.add(this.spinnerStartX);
		p3.add(labelStartY);
		p3.add(this.spinnerStartY);
		
		JPanel p4 = new JPanel();
		this.applyButton = new JButton(APPLY_LABEL);
		p4.add(this.applyButton);

		this.getContentPane().add(p1);
		this.getContentPane().add(p2);
		this.getContentPane().add(p3);
		this.getContentPane().add(p4);
	}
	
	/** Gives the apply button
	* @return the apply button
	*/
	public JButton getApplyButton()
	{
		return this.applyButton;
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
