
/**
* classe qui définit un tile
*
* @author Thomas PICARD 
* @version 1.0
*/

public class Tile
{
	private int id; 
	private boolean breakable;
	private boolean solid;  


	public Tile(int theId)
	{
		switch (theId) 
		{
			case 1: 
				this.id = theId; 
				this.sprite = new Sprite("tile/1.png"); 
				this.breakable = false;
				this.solid = true; 
			break; 
			default : 
				this.id = 0; 
				this.breakable = true;
				this.solid = false; 	
		}
		/* ... */ 
	}
	
	public boolean isBreakable()
	{
		return breakable; 
	} 
	
	public boolean isSolid()
	{
		return solid; 
	}

	public int getId()
	{
		return this.id; 
	}

		public void afficher(Coordinate coord) 
	{
		sprite.afficher();
	}

}
