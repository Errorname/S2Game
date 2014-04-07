
/**
* classe qui définit un Item
*
* @author Thomas PICARD 
* @version 1.0
*/

public class Item
{
	private int id; 
	private Sprite sprite; 

	public Tile(int theId)
	{
		switch (theId) 
		{
			case 1: 
				this.id = theId; 
				sprite = new Sprite("item/1.png");
				break; 
			default : 
				this.id = 0;	
		}
		/* ... */ 
	}

	public int getId()
	{
		return this.id; 
	}

	public void afficher(Coordinate coord) 
	{
		sprite.afficher(coord);
	}

}
