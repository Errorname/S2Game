package collision;

import math.*;
import level.Tile;

/**class box : defined a box for collision
*@author Mathieu GRONDIN
*@version 1.0
*/
public class Box{
	private int x;
	private int y;
	private int w;
	private int h;
	
	/**Class Box constructor
	*@param itsX the abscissa of the upper left corner of the box
	*@param itsY the ordinate of the upper left corner of the box
	*@param itsW the width of the box
	*@param itsH the height of the
	*/
	public Box(int itsX,int itsY,int itsW,int itsH){
		this.x = itsX;
		this.y = itsY;
		this.w = itsW;
		this.h = itsH;
	}
	
	/**Returns the abscissa of the upper left corner of a box
	*@return the abscissa of the upper left corner of the box
	*/
	public int getX(){
		return this.x;
	}
	
	/**Returns the ordinate of the upper left corner of a box
	*@return the ordinate of the upper left corner of the box
	*/
	public int getY(){
		return this.y;
	}
	
	/**Returns the width of a box
	*@return the width of the box
	*/
	public int getW(){
		return this.w;
	}
	
	/**Returns the height of a bos
	*@return the height of the box
	*/
	public int getH(){
		return this.h;
	}
	
	/**Replaces the value of the abscissa of the upper left corner of the box
	*@param newX the new abscissa
	*/
	public void setX(int newX){
		this.x = newX;
	}
	
	/**Replaces the value of the ordinate of the upper left corner of the box
	*@param newY the new ordinate
	*/
	public void setY(int newY){
		this.y = newY;
	}
	
	/**Replace the value of the box widht
	*@param newW the new widht of the box
	*/
	public void setW(int newW){
		this.w = newW;
	}
	
	/**Replaces the value of the box height
	*@param newH the new height of the box
	*/
	public void setH(int newH){
		this.h = newH;
	}
	
	/**Returns whether two box in are in touch  or not 
	*@param box the box with which testing collision
	*@return true if the two box are touching, false otherwise
	*/
	public boolean collision(Box box){
		Box box1 = box;
		Box box2 = this;
		if ((box2.x >= box1.x + box1.w)      // trop à droite
		||  (box2.x + box2.w <= box1.x) // trop à gauche
		||  (box2.y >= box1.y + box1.h) // trop en bas
		||  (box2.y + box2.h <= box1.y))  // trop en haut
		{
		    return false; 
	    } else {
          	return true;
        }
	}
	
	/**Returns whether a box is on an other box or not
	*@param box the box with which testing collision
	*@return true if the two box are touching, false otherwise
	*/
	public boolean collisionB(Box box){
		boolean touch = false;
		touch = box.getY() <= (this.getY() + this.getH()) && !((box.getX() >= this.getX() + this.getW()) || (box.getX() + box.getW() <= this.getX()) || (box.getY() + box.getH() <= this.getY()));
		return touch;
	}

	/**Specifies whether a box passes through the ground
	*@param a the ground level
	*@return true if the the box is passing through the ground, false otherwise
	*/
	public boolean collision(int a){
		boolean touch;
		touch = (this.getY() + this.getH() >= a);
		return touch;
	}
	
	/**Specifies whether a box collides with a wall or not
	*@param coord the tile coordinate
	*@return true if the box is colliding, false otherwise
	*/
	/*
	public boolean colision(Coordinate coord,int TILE_WIDHT,int TILE_HEIGHT){
		int i,j;
//		final int TILE_WIDHT = 16;
//		final int TILE_HEIGHT = 16;
		int i1 = this.getX() / TILE_WIDHT;
		int j1 = this.getY() / TILE_HEIGHT;
		int i2 = (this.getX() + this.getW() - 1) / TILE_WIDHT;
		int j2 = (this.getY() + this.getH() - 1) / TILE_HEIGHT;
		for(i=i1;i<=i2;i++){
			for(j=j1;j<=j2;j++){
				if(tile[i][j].isSolid()){
					return true;
				}
			}
		}
		return false;
	}
	*/

	public String toString(){
		String s = "";
		s += "x : " + getX() + " y : " + getY();
		return s;
	}
}
