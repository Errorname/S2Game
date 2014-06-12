package collision;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;

import math.*;
import collision.*;
import level.Tile;
import core.GameView;
import item.*;
import player.Player;

/**class OrdinaryCollision : class for rubber collision
*@author Mathieu GRONDIN
*@version 1.0
*/
public class RubberCollision extends Collision{
	
	/**RubberCollision class constructor
	*/
	public RubberCollision(){
		this.rubber = true;
		this.ordinary = false;
	}
	
	/**
	*/
	public void update(Player player,Coordinate coordJoueur,double dT,Tile tile[][],Item item[][],int lengthTile,int widthTile,int mapHeight){
/*

		Vector newSpeed = new Vector(player.getSpeed());
		Coordinate newCoordJoueur = new Coordinate(coordJoueur);
		

		if (player.getKL() && !player.getKR()) {												// A GAUCHE //
			newSpeed.setX(newSpeed.getX() - player.getAccelerationX() * dT);
			if(newSpeed.getX() < -player.getVxMax()){
				newSpeed.setX(-player.getVxMax()); 
			}
			player.setRight(false);
		} 
		else if (player.getKR() && !player.getKL()) {											// A DROITE //
			newSpeed.setX(newSpeed.getX() + player.getAccelerationX() * dT);
			if(newSpeed.getX() > player.getVxMax()){
				newSpeed.setX(player.getVxMax());
			}
			player.setRight(true);
		}
		else {															// MILIEU //
			if (newSpeed.getX() > 0) {
				newSpeed.setX(newSpeed.getX() - player.getAccelerationX() * dT);
				if (newSpeed.getX() < 0) {
					newSpeed.setX(0);
				}
			} else if (newSpeed.getX() < 0) {
				newSpeed.setX(newSpeed.getX() + player.getAccelerationX() * dT);
				if (newSpeed.getX() > 0) {
					newSpeed.setX(0);
				}
			}
		}
		
		if (newSpeed.getY() == 0) {
			player.setOnGround(true);
		} else {
			player.setOnGround(false);
		}
		
		newSpeed.setY(newSpeed.getY() + player.getGRAVITY() * dT);					// GRAVITE //
		
		if (player.getKU() && player.getOnGround() && !player.getLastKU()) {								// SAUT    //
			newSpeed.setY(newSpeed.getY() - player.getAccelerationY());
		} 
		
		if(newSpeed.getY() < -player.getVyMax()){									// VITESSE MAX EN Y //
			newSpeed.setY(-player.getVyMax());
		}
		
																		//////// COLLISION HORIZONTALE ////////
		Box testBox = new Box((int)(coordJoueur.getX() + newSpeed.getX() * dT),
							  (int)(coordJoueur.getY()),
							  player.getBox().getW(),
							  player.getBox().getH() );
		
		int xa = GameView.pixelsToTiles(testBox.getX(),lengthTile);						// A GAUCHE
		int xb = GameView.pixelsToTiles(testBox.getX() + testBox.getW(),lengthTile);	// A DROITE
		xa = (xa < 0) ? 0 : 1;
		int ya = GameView.pixelsToTiles(testBox.getY(),widthTile);						// EN HAUT
		int yb = GameView.pixelsToTiles(testBox.getY() + testBox.getH(),widthTile);		// EN BAS
		
		boolean possible = true;
		
		for (int i = xa; i <= xb; i++) {							// DE GAUCHE A DROITE //
			for (int j = ya;j <= yb; j++) {							// DE HAUT EN BAS //
				if ( tile[i][j] != null ) {
					if (tile[i][j].getIsSolid()) {
						if ((tile[i][j].getBox()).collision(testBox)) {
							if (tile[i][j].getIsBreakable() && player.getHasLollipop()) {
								tile[i][j] = null;
							} else {
								possible = false;
							}
						}
					}
				}else {
					if (item[i][j] instanceof Door && (testBox.getX()+testBox.getW())%lengthTile != 0  ) {
						if (player.getKE() && player.getHasGoldenKey()) {
							player.setHasGoldenKey(false);
							item[i][j] = null;
						} else {
							possible = false;
						}
					}
				}
			}
		}
		
		if (possible) {
			newCoordJoueur.setX(coordJoueur.getX() + newSpeed.getX() * dT); // CALCUL NOUVELLES COORDONNEES //
			
			if (player.getKL() || player.getKR()) {
				player.setPos(player.getCurrentWalk());
				player.setToNewWalk(player.getToNewWalk()-(int)dT);
				if(player.getToNewWalk() <= 0)
				{
					player.setToNewWalk(player.getWalkLast());
					int newCurrentWalk = (player.getCurrentWalk == 3) ? 4 : 3;
					player.setCurrentWalk(newCurrentWalk);
				}
			} else {
				player.setStandCount(player.getStandCount() + 1);
				if (player.getStandCount() == player.getStandOk()) {
					player.setPos(2);
					player.setStandCount(0);
				}
			}
		} else {
			newCoordJoueur.setX(coordJoueur.getX());
			newSpeed.setX(0);
			if (player.getKL() || player.getKR()) {
				player.setPos(player.getCurrentWalk());
				player.setToNewWalk(player.getToNewWalk() - (long)dT);
				if(player.getToNewWalk() <= 0);
				{
					player.setToNewWalk(player.getWalkLast());
					int newCurrentWalk = (player.getCurrentWalk() == 3) ? 4 : 3;
					player.setCurrentWalk(newCurrentWalk);
				}
			} else {
				player.setStandCount(player.getStandCount() + 1);
				if (player.getStandCount() == player.getStandOk()) {
					player.setPos(2);
					player.setStandCount(0);
				}
			}
		}
		
																		//////// COLLISION VERTICALE ////////
		testBox = new Box((int)(coordJoueur.getX()),
						  (int)(coordJoueur.getY() + newSpeed.getY() * dT),
						  player.getBox().getW(),
						  player.getBox().getH() );
		
		xa = GameView.pixelsToTiles(testBox.getX(),lengthTile);						// A GAUCHE
		xb = GameView.pixelsToTiles(testBox.getX() + testBox.getW(),lengthTile);	// A DROITE
		
		ya = GameView.pixelsToTiles(testBox.getY(),widthTile);						// EN HAUT
		yb = GameView.pixelsToTiles(testBox.getY() + testBox.getH(),widthTile);		// EN BAS
		
		yb = (yb >= mapHeight/widthTile) ? mapHeight/widthTile-1 : yb;
		//System.out.println(mapHeight/widthTile);
		possible = true;
		
		//System.out.println("DAFUQ? " + dT);
		
		for (int i = xa; i <= xb; i++) {							// DE GAUCHE A DROITE //
			for (int j = ya;j <= yb; j++) {							// DE HAUT EN BAS //
				//System.out.println(i + " " + j);
				if ( tile[i][j] != null ) {
					if (tile[i][j].getIsSolid()) {
						if ((tile[i][j].getBox()).collision(testBox)) {
							if (tile[i][j].getIsBreakable() && player.getHasLollipop()) {
								tile[i][j] = null;
							} else {
								possible = false;
							}
						}
					} 
				} else {
					if (item[i][j] instanceof Door && (testBox.getX()+testBox.getW())%lengthTile != 0  ) {
						if (player.getKE() && player.getHasGoldenKey()) {
							player.setHasGoldenKey(false);
							item[i][j] = null;
						} else {
							possible = false;
						}
					}
				}
			}
		}
		
		if (possible) {
			newCoordJoueur.setY(coordJoueur.getY() + newSpeed.getY() * dT);
			player.setPos(1);
		} else {
			newCoordJoueur.setY(coordJoueur.getY());
			newSpeed.setY(0);
		}
		

		player.setSpeedX(newSpeed.getX());
		player.setSpeedY(newSpeed.getY());
//		speed.setX(newSpeed.getX());
//		speed.setY(newSpeed.getY());
		player.setCoordJoueurX(newCoordJoueur.getX());
		player.setCoordJoueurY(newCoordJoueur.getY());
//		coordJoueur.setX(newCoordJoueur.getX());
//		coordJoueur.setY(newCoordJoueur.getY());
		
		if(newCoordJoueur.getY() > mapHeight - 73){						// TEST OFFSCREEN //
			player.setCoordJoueurX(player.getStartCoord().getX());
			player.setCoordJoueurY(player.getStartCoord().getY());
		//	coordJoueur.setX(startCoord.getX());
		//	coordJoueur.setY(startCoord.getY());
			//System.out.println("T'ES MORT MOUAHAHAHAHAHAHAHAHAH");
			//newCoordJoueur.setY(mapHeight - 73);
		}
		
		
										/////////// TEST GOLDEN KEY //////////
		
		
		testBox = new Box((int)(coordJoueur.getX()),
						  (int)(coordJoueur.getY()),
						  player.getBox().getW(),
						  player.getBox().getH() );
		
		
		xa = GameView.pixelsToTiles(testBox.getX() + 5,lengthTile);						// A GAUCHE
		xb = GameView.pixelsToTiles(testBox.getX() + testBox.getW() - 5,lengthTile);	// A DROITE
		
		ya = GameView.pixelsToTiles(testBox.getY() + 10,widthTile);						// EN HAUT
		yb = GameView.pixelsToTiles(testBox.getY() + testBox.getH() - 10,widthTile);		// EN BAS
		
		possible = true;
		
		//System.out.println("DAFUQ? " + dT);
		
		//System.out.println(xa + " " + xb + " / " + ya + " " + yb);
		
		xa = (xa <0) ? 0 : xa;
		
		for (int i = xa; i <= xb; i++) {							// DE GAUCHE A DROITE //
			for (int j = ya;j <= yb; j++) {							// DE HAUT EN BAS //
				//System.out.println(i + " " + j);
				if ( item[i][j] != null ) {
					//System.out.println(tile[i][j].isKey);
					if (item[i][j] instanceof GoldenKey && !player.getHasGoldenKey()) {
						player.setHasGoldenKey(true);
						item[i][j] = null;
					}else if (item[i][j] instanceof Lollipop && !player.getHasLollipop()) {
						player.setHasLollipop(true);
						item[i][j] = null;
						GameView.setColor(new Color(255,159,51));
					}
				}
			}
		}
		//System.out.println("END:      " + endCoord.getX() + " " + endCoord.getY());
		//System.out.println("PLAYER: " + GameView.pixelsToTiles((int)coordJoueur.getX()+box.getW()/2,lengthTile) + " " + GameView.pixelsToTiles((int)coordJoueur.getY()+box.getH()/2,widthTile));
		if (GameView.pixelsToTiles((int)coordJoueur.getX()+player.getBox().getW()/2,lengthTile) == GameView.pixelsToTiles((int)player.getEndCoord.getX(),lengthTile) &&
			GameView.pixelsToTiles((int)coordJoueur.getY()+player.getBox().getH()/2,widthTile) == GameView.pixelsToTiles((int)player.getEndCoord.getY(),widthTile)) {
			player.setIsWinner(true);
		}
		
		if (player.getHasLollipop() && player.getLollipopLeft() > 0) {
			player.setLollipopLeft(player.getLollipopLeft() - (long)dT);
		} else {
			player.setHasLollipop(false);
			GameView.setColor(new Color(208,244,247));
		}
		
		if (player.getHasLollipop()) {
			player.setFlashLeft(player.getFlashLeft() - (long)dT);
			if (player.getFlashLeft() < 0) {
				player.setFlashLeft(player.getFlashLast());
				player.setFlash(!player.getFlash());
				if (player.getFlash()) {
					GameView.setColor(new Color(255,159,51));
				} else {
					GameView.setColor(new Color(208,244,247));
				}
			}
		}

		player.setLastKU(player.getKU());*/
	}
}