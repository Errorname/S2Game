package collision;

import math.*;
import collision.*;
import level.Tile;
import core.GameView;
import item.*;
import player.Player;

/**class Collision : superclass for collision
*@author Mathieu GRONDIN
*@version 1.0
*/
public abstract class Collision{

	protected boolean rubber;
	protected boolean ordinary;
	
	public abstract void update(Player player,Coordinate coordJoueur,double dT,Tile tile[][],Item item[][],int lengthTile,int widthTile,int mapHeight);
}
