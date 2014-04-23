package beditor.model;

/**
* Coordinate : a class to define coordinates
*
* @author Hugo PIGEON
* @version 1.0
*/

public class Coordinate
{
	private int x;
	private int y;

	/** Constructor which makes new coordinates
	* @param coordX the X coordinate
	* @param coordY the Y coordinate
	*/
	public Coordinate(int coordX, int coordY)
	{
		this.x = coordX;
		this.y = coordY;
	}
	
	/** Gives the X coordinate
	* @return the X coordinate
	*/
	public int getX()
	{
		return this.x;
	}
	
	/** Gives the Y coordinate
	* @return the Y coordinate
	*/
	public int getY()
	{
		return this.y;
	}
	
	/** Changes the X coordinate
	* @param coordX the new X coordinate
	*/
	public void setX(int coordX)
	{
		this.x = coordX;
	}
	
	/** Changes the Y coordinate
	* @param coordY the new Y coordinate
	*/
	public void setY(int coordY)
	{
		this.y = coordY;
	}
}
