package beditor.view;

import javax.swing.*;
import java.awt.*;

/**
* PropertiesPan : a class to display and choose the properties of a tile
*
* @author Hugo PIGEON
* @version 1.0
*/

public class PropertiesPan extends JPanel
{
	private EditorPan editorPan;
	private JLabel selectedSprite;
	private JRadioButton emptyRadio;
	private JRadioButton solidRadio;
	private JRadioButton notBreakableRadio;
	private JRadioButton breakableRadio;

	private final String MESSAGE = "Selected tile :";
	private final String EMPTY_RADIO = "Empty";
	private final String SOLID_RADIO = "Solid";
	private final String NOT_BREAKABLE_RADIO = "Not breakable";
	private final String BREAKABLE_RADIO = "Breakable";

	/** Constructor which makes a new properties panel
	* @param editorPan the EditorPan which contains this PropertiesPan
	*/
	public PropertiesPan(EditorPan editorPan)
	{	
		this.setLayout(new GridLayout(4, 1));
		
		this.editorPan = editorPan;
		
		JLabel message = new JLabel(MESSAGE);
	
		this.selectedSprite = new JLabel(this.editorPan.getSelectedTile().getImage().getIcon());
	
		ButtonGroup solidGroup = new ButtonGroup();
		if(this.editorPan.getTilesetPan().getTileset().getTile(this.editorPan.getTilesetPan().getSelectedSpriteIndex()).getSolid())
		{
			this.emptyRadio = new JRadioButton(EMPTY_RADIO, false);
			this.solidRadio = new JRadioButton(SOLID_RADIO, true);
		}
		else
		{
			this.emptyRadio = new JRadioButton(EMPTY_RADIO, true);
			this.solidRadio = new JRadioButton(SOLID_RADIO, false);
		}
		solidGroup.add(emptyRadio);
		solidGroup.add(solidRadio);
		
		ButtonGroup breakableGroup = new ButtonGroup();
		if(this.editorPan.getTilesetPan().getTileset().getTile(this.editorPan.getTilesetPan().getSelectedSpriteIndex()).getBreakable())
		{
			this.notBreakableRadio = new JRadioButton(NOT_BREAKABLE_RADIO, false);
			this.breakableRadio = new JRadioButton(BREAKABLE_RADIO, true);
		}
		else
		{
			this.notBreakableRadio = new JRadioButton(NOT_BREAKABLE_RADIO, true);
			this.breakableRadio = new JRadioButton(BREAKABLE_RADIO, false);
		}
		breakableGroup.add(notBreakableRadio);
		breakableGroup.add(breakableRadio);
		
		JPanel messagePan = new JPanel();
		messagePan.add(message);
		JPanel solidPan = new JPanel();
		solidPan.add(this.emptyRadio);
		solidPan.add(this.solidRadio);
		JPanel breakablePan = new JPanel();
		breakablePan.add(this.notBreakableRadio);
		breakablePan.add(this.breakableRadio);
		
		this.add(messagePan);
		this.add(this.selectedSprite);
		this.add(solidPan);
		this.add(breakablePan);
	}
	
	/** Gives the "empty" radio button
	* @return the "empty" radio button
	*/
	public JRadioButton getEmptyRadio()
	{
		return this.emptyRadio;
	}
	
	/** Gives the "solid" radio button
	* @return the "solid" radio button
	*/
	public JRadioButton getSolidRadio()
	{
		return this.solidRadio;
	}
	
	/** Gives the "not breakable" radio button
	* @return the "not breakable" radio button
	*/
	public JRadioButton getNotBreakableRadio()
	{
		return this.notBreakableRadio;
	}
	
	/** Gives the "breakable" radio button
	* @return the "breakable" radio button
	*/
	public JRadioButton getBreakableRadio()
	{
		return this.breakableRadio;
	}
	
	/** Refreshes the display of this PropertiesPan
	*/
	public void revalidate()
	{
		if(this.editorPan != null && this.editorPan.getTilesetPan() != null)
		{
			this.selectedSprite.setIcon(this.editorPan.getSelectedTile().getImage().getIcon());
		
			if(this.editorPan.getTilesetPan().getTileset().getTile(this.editorPan.getTilesetPan().getSelectedSpriteIndex()).getSolid())
			{
				this.solidRadio.setSelected(true);
				this.emptyRadio.setSelected(false);
			}
			else
			{
				this.solidRadio.setSelected(false);
				this.emptyRadio.setSelected(true);
			}
				
			if(this.editorPan.getTilesetPan().getTileset().getTile(this.editorPan.getTilesetPan().getSelectedSpriteIndex()).getBreakable())
			{
				this.breakableRadio.setSelected(true);
				this.notBreakableRadio.setSelected(false);
			}
			else
			{
				this.breakableRadio.setSelected(false);
				this.notBreakableRadio.setSelected(true);
			}
		}
	
		super.revalidate();
	}
}
