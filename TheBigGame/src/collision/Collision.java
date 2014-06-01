package collision;

/**class Collision : superclass for collision
*@author Mathieu GRONDIN
*@version 1.0
*/
public abstract class Collision{

	protected boolean rubber;
	protected boolean ordinary;
	
	public abstract void tick();
}
