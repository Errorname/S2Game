package beditor.model;

import java.awt.Dimension;

/**
* Item : a class to define a tile
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Item
{
	private ItemType type;
	
	/** Constructor which defines a new item with its type
	* @param type the type of the item
	*/
	public Item(ItemType type)
	{
		this.type = type;
	}
	
	/** Gives the type of the item
	* @return the type of the item
	*/
	public ItemType getType()
	{
		return this.type;
	}
	
	/** Sets the type of the item
	* @param type the new type of the item
	*/
	public void setType(ItemType type)
	{
		this.type = type;
	}
}
