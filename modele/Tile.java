
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


	public Tile(int theId, boolean solid, boolean breakable)
	{
		this.id = theId; 
		//this.sprite = new Sprite("tile/1.png"); 
		this.breakable = breakable;
		this.solid = solid; 
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

		/*public void afficher(Coordinate coord) 
	{
		sprite.afficher();
	}
*/
}
