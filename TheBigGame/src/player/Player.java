package player;

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

public class Player implements KeyListener {

	private Coordinate startCoord;
	private Coordinate playerCoord;
	private Coordinate endCoord;
	
	private boolean KL;	//key left
	private boolean KR;	//key right
	private boolean KU;	//key up
	private boolean KD;	//key down
	private boolean KE;	//key enter
	private boolean lastKU = false;

	private Vector speed;
	private double VxMax = 0.5;
	private double VyMax = 1;
	private Vector acceleration;
	private double accelerationX = 0.045;
	private double accelerationY = 2;
	protected BufferedImage img;
	
	private final double GRAVITY = 0.005;
	private boolean onGround = false;
	private boolean offScreen = false;
	private boolean isWinner;

	private Box box;				//box du joueur pour les colisions
	private Collision collision; 	//type de coll
	
	public boolean hasGoldenKey;
	public boolean hasLollipop;
	
	private long lollipopLast = 7000;
	private long lollipopLeft = lollipopLast;
	
	private long flashLast = 500;
	private long flashLeft = flashLast;
	private boolean flash = true;
	
	private BufferedImage imgJump;   // 1
	private BufferedImage imgJumpL;  // 1
	private BufferedImage imgStand;  // 2
	private BufferedImage imgStandL; // 2
	private BufferedImage imgWalk1;  // 3
	private BufferedImage imgWalk1L; // 3
	private BufferedImage imgWalk2;  // 4
	private BufferedImage imgWalk2L; // 4
	
	private BufferedImage AimgJump;   // 1
	private BufferedImage AimgJumpL;  // 1
	private BufferedImage AimgStand;  // 2
	private BufferedImage AimgStandL; // 2
	private BufferedImage AimgWalk1;  // 3
	private BufferedImage AimgWalk1L; // 3
	private BufferedImage AimgWalk2;  // 4
	private BufferedImage AimgWalk2L; // 4
	
	private boolean right = true;
	
	private int pos = 0;
	private long walkLast = 100;
	private long toNewWalk = walkLast;
	private int currentWalk = 3;
	
	private int standCount = 0;
	private int standOk = 3;
	
	public Player(Coordinate startCoord, Coordinate endCoord, BufferedImage img, int collisionType){
		this.startCoord = new Coordinate(startCoord);
		this.playerCoord = new Coordinate(startCoord);
		this.endCoord = new Coordinate(endCoord);
		this.img = img;
		
		imgJump = GameView.loadImage("images/alienGreen_jump.png");
		imgJumpL = GameView.loadImage("images/alienGreen_jumpL.png");
		imgStand = GameView.loadImage("images/alienGreen_stand.png");
		imgStandL = GameView.loadImage("images/alienGreen_standL.png");
		imgWalk1 = GameView.loadImage("images/alienGreen_walk1.png");
		imgWalk1L = GameView.loadImage("images/alienGreen_walk1L.png");
		imgWalk2 = GameView.loadImage("images/alienGreen_walk2.png");
		imgWalk2L = GameView.loadImage("images/alienGreen_walk2L.png");
		
		AimgJump = GameView.loadImage("images/alienYellow_jump.png");
		AimgJumpL = GameView.loadImage("images/alienYellow_jumpL.png");
		AimgStand = GameView.loadImage("images/alienYellow_stand.png");
		AimgStandL = GameView.loadImage("images/alienYellow_standL.png");
		AimgWalk1 = GameView.loadImage("images/alienYellow_walk1.png");
		AimgWalk1L = GameView.loadImage("images/alienYellow_walk1L.png");
		AimgWalk2 = GameView.loadImage("images/alienYellow_walk2.png");
		AimgWalk2L = GameView.loadImage("images/alienYellow_walk2L.png");
		
		isWinner = false;
		
		hasGoldenKey = false;
		hasLollipop = false;
		lollipopLeft = lollipopLast;
		flashLeft = flashLast;
		
		speed = new Vector(0,0);
		acceleration = new Vector (1.0,0.0);
		
		//verifier si les coordonnes de la box sont bien placees
		box = new Box ((int)this.startCoord.getX(),(int)this.startCoord.getY(),50,68);
		
		switch(collisionType){
			case 1:
				collision = new OrdinaryCollision();
			break;
			case 2:
				collision = new RubberCollision();
			break;
		}
		
		GameView.setColor(new Color(208,244,247));
	}
	
	public Coordinate getCoord(){
		return this.playerCoord;
	}

	public void setCoordX(int x){
		x = (x < 0) ? 0 : x;
		this.playerCoord.setX(x);
	}
	
	public void setCoordY(int y){
		y = (y < 0) ? 0 : y;
		this.playerCoord.setY(y);
	}
	
	public Box getBox(){
		return this.box;
	}

	public void setBoxY(int y){
		this.box.setY(y);
	}

	public void draw(Graphics g, int offsetX, int offsetY) {
		if (hasLollipop) {
			if (pos == 1) {
				if (right) {
					g.drawImage(AimgJump,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(AimgJumpL,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 2) {
				if (right) {
					g.drawImage(AimgStand,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(AimgStandL,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 3) {
				if (right) {
					g.drawImage(AimgWalk1,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(AimgWalk1L,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 4) {
				if (right) {
					g.drawImage(AimgWalk2,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(AimgWalk2L,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			}
		} else {
			if (pos == 1) {
				if (right) {
					g.drawImage(imgJump,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(imgJumpL,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 2) {
				if (right) {
					g.drawImage(imgStand,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(imgStandL,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 3) {
				if (right) {
					g.drawImage(imgWalk1,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(imgWalk1L,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			} else if (pos == 4) {
				if (right) {
					g.drawImage(imgWalk2,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				} else {
					g.drawImage(imgWalk2L,(int)this.playerCoord.getX() - offsetX,(int)this.playerCoord.getY() - offsetY,null);
				}
			}
		}
	}
		
	public Vector getSpeed(){
		return this.speed;
	}
	public Vector getAcceleration(){
		return this.acceleration;
	}

	public void update(Coordinate coordJoueur,double dT,Tile tile[][],Item item[][],int lengthTile,int widthTile,int mapHeight) {
		
		Vector newSpeed = new Vector(speed);
		
		Coordinate newCoordJoueur = new Coordinate(coordJoueur);
		
		if (KL && !KR) {												// A GAUCHE //
			newSpeed.setX(newSpeed.getX() - accelerationX * dT);
			if(newSpeed.getX() < -VxMax){
				newSpeed.setX(-VxMax); 
			}
			right = false;
		} 
		else if (KR && !KL) {											// A DROITE //
			newSpeed.setX(newSpeed.getX() + accelerationX * dT);
			if(newSpeed.getX() > VxMax){
				newSpeed.setX(VxMax);
			}
			right = true;
		}
		else {															// MILIEU //
			if (newSpeed.getX() > 0) {
				newSpeed.setX(newSpeed.getX() - accelerationX * dT);
				if (newSpeed.getX() < 0) {
					newSpeed.setX(0);
				}
			} else if (newSpeed.getX() < 0) {
				newSpeed.setX(newSpeed.getX() + accelerationX * dT);
				if (newSpeed.getX() > 0) {
					newSpeed.setX(0);
				}
			}
		}
		
		if (newSpeed.getY() == 0) {
			onGround = true;
		} else {
			onGround = false;
		}
		
		newSpeed.setY(newSpeed.getY() + GRAVITY * dT);					// GRAVITE //
		
		if (KU && onGround && !lastKU) {								// SAUT    //
			newSpeed.setY(newSpeed.getY() - accelerationY);
		} 
		
		if(newSpeed.getY() < -VyMax){									// VITESSE MAX EN Y //
			newSpeed.setY(-VyMax);
		}
		
																		//////// COLLISION HORIZONTALE ////////
		Box testBox = new Box((int)(coordJoueur.getX() + newSpeed.getX() * dT),
							  (int)(coordJoueur.getY()),
							  this.getBox().getW(),
							  this.getBox().getH() );
		
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
							if (tile[i][j].getIsBreakable() && hasLollipop) {
								tile[i][j] = null;
							} else {
								possible = false;
							}
						}
					}
				}else {
					if (item[i][j] instanceof Door && (testBox.getX()+testBox.getW())%lengthTile != 0  ) {
						if (KE && hasGoldenKey) {
							hasGoldenKey = false;
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
			
			if (KL || KR) {
				pos = currentWalk;
				toNewWalk = toNewWalk-(int)dT;
				if(toNewWalk <= 0)
				{
					toNewWalk = walkLast;
					currentWalk = (currentWalk == 3) ? 4 : 3;
				}
			} else {
				standCount++;
				if (standCount == standOk) {
					pos = 2;
					standCount = 0;
				}
			}
		} else {
			newCoordJoueur.setX(coordJoueur.getX());
			newSpeed.setX(0);
			if (KL || KR) {
				pos = currentWalk;
				toNewWalk-=dT;
				if(toNewWalk <= 0);
				{
					toNewWalk = walkLast;
					currentWalk = (currentWalk == 3) ? 4 : 3;
				}
			} else {
				standCount++;
				if (standCount == standOk) {
					pos = 2;
					standCount = 0;
				}
			}
		}
		
																		//////// COLLISION VERTICALE ////////
		testBox = new Box((int)(coordJoueur.getX()),
						  (int)(coordJoueur.getY() + newSpeed.getY() * dT),
						  this.getBox().getW(),
						  this.getBox().getH() );
		
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
							if (tile[i][j].getIsBreakable() && hasLollipop) {
								tile[i][j] = null;
							} else {
								possible = false;
							}
						}
					} 
				} else {
					if (item[i][j] instanceof Door && (testBox.getX()+testBox.getW())%lengthTile != 0  ) {
						if (KE && hasGoldenKey) {
							hasGoldenKey = false;
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
			pos = 1;
		} else {
			newCoordJoueur.setY(coordJoueur.getY());
			newSpeed.setY(0);
		}
		
		speed.setX(newSpeed.getX());
		speed.setY(newSpeed.getY());
		coordJoueur.setX(newCoordJoueur.getX());
		coordJoueur.setY(newCoordJoueur.getY());
		
		if(newCoordJoueur.getY() > mapHeight - 73){						// TEST OFFSCREEN //
			coordJoueur.setX(startCoord.getX());
			coordJoueur.setY(startCoord.getY());
			//System.out.println("T'ES MORT MOUAHAHAHAHAHAHAHAHAH");
			//newCoordJoueur.setY(mapHeight - 73);
		}
		
		
										/////////// TEST GOLDEN KEY //////////
		
		
		testBox = new Box((int)(coordJoueur.getX()),
						  (int)(coordJoueur.getY()),
						  this.getBox().getW(),
						  this.getBox().getH() );
		
		
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
					if (item[i][j] instanceof GoldenKey && !hasGoldenKey) {
						hasGoldenKey = true;
						item[i][j] = null;
					}else if (item[i][j] instanceof Lollipop && !hasLollipop) {
						hasLollipop = true;
						item[i][j] = null;
						GameView.setColor(new Color(255,159,51));
					}
				}
			}
		}
		//System.out.println("END:      " + endCoord.getX() + " " + endCoord.getY());
		//System.out.println("PLAYER: " + GameView.pixelsToTiles((int)coordJoueur.getX()+box.getW()/2,lengthTile) + " " + GameView.pixelsToTiles((int)coordJoueur.getY()+box.getH()/2,widthTile));
		if (GameView.pixelsToTiles((int)coordJoueur.getX()+box.getW()/2,lengthTile) == GameView.pixelsToTiles((int)endCoord.getX(),lengthTile) &&
			GameView.pixelsToTiles((int)coordJoueur.getY()+box.getH()/2,widthTile) == GameView.pixelsToTiles((int)endCoord.getY(),widthTile)) {
			isWinner = true;
		}
		
		if (hasLollipop && lollipopLeft > 0) {
			lollipopLeft -= dT;
		} else {
			hasLollipop = false;
			GameView.setColor(new Color(208,244,247));
		}
		
		if (hasLollipop) {
			flashLeft -= dT;
			if (flashLeft < 0) {
				flashLeft = flashLast;
				flash = !flash;
				if (flash) {
					GameView.setColor(new Color(255,159,51));
				} else {
					GameView.setColor(new Color(208,244,247));
				}
			}
		}
		
		lastKU = KU;
	}
	
	public boolean isWinner() {
		return isWinner;
	}

	public void keyReleased(KeyEvent e) {

	// TODO
	// Si <- & -> => 0

		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			KL = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			KR = false;
		}
		if (key == KeyEvent.VK_UP) {
			KU = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			KD = false;
		}
		if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_NUMPAD0 ) {
			KE = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			KL = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			KR = true;
		}
		if (key == KeyEvent.VK_UP) {
			KU = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			KD = true;
		}
		if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_NUMPAD0 ) {
			KE = true;
		}
	}
	
	public void keyTyped(KeyEvent e) {}
}
