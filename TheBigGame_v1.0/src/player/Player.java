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
//	private boolean offScreen = false;
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

	public void setCoordX(int x){
		x = (x < 0) ? 0 : x;
		this.playerCoord.setX(x);
	}
	
	public void setCoordY(int y){
		y = (y < 0) ? 0 : y;
		this.playerCoord.setY(y);
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

	public void update(Coordinate coordJoueur,double dT,Tile tile[][],Item item[][],int lengthTile,int widthTile,int mapHeight) {
	collision.update(this,coordJoueur,dT,tile,item,lengthTile,widthTile,mapHeight);
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
	
	public boolean getKU(){
		return this.KU;
	}

	public boolean getKR(){
		return this.KR;
	}

	public boolean getKL(){
		return this.KL;
	}

	public boolean getKE(){
		return this.KE;
	}

	public Vector getSpeed(){
		return this.speed;
	}

	public Coordinate getCoord(){
		return this.playerCoord;
	}

	public Vector getAcceleration(){
		return this.acceleration;
	}

	public double getAccelerationX(){
		return this.accelerationX;
	}

	public double getAccelerationY(){
		return this.accelerationY;
	}

	public double getVxMax(){
		return this.VxMax;
	}

	public double getVyMax(){
		return this.VyMax;
	}

	public boolean getOnGround(){
		return this.onGround;
	}

	public double getGRAVITY(){
		return this.GRAVITY;
	}

	public boolean getLastKU(){
		return this.lastKU;
	}

	public Box getBox(){
		return this.box;
	}

	public boolean getHasLollipop(){
		return this.hasLollipop;
	}

	public boolean getHasGoldenKey(){
		return this.hasGoldenKey;
	}

	public int getCurrentWalk(){
		return this.currentWalk;
	}

	public long getToNewWalk(){
		return this.toNewWalk;
	}

	public long getWalkLast(){
		return this.walkLast;
	}

	public int getStandCount(){
		return this.standCount;
	}

	public int getStandOk(){
		return this.standOk;
	}

	public int getPos(){
		return this.pos;
	}

	public long getLollipopLast(){
		return this.lollipopLast;
	}

	public long getLollipopLeft(){
		return this.lollipopLeft;
	}

	public long getFlashLeft(){
		return this.flashLeft;
	}

	public long getFlashLast(){
		return this.flashLast;
	}

	public boolean getFlash(){
		return this.flash;
	}

	public boolean getRight(){
		return this.right;
	}

	public Coordinate getStartCoord(){
		return this.startCoord;
	}

	public Coordinate getEndCoord(){
		return this.endCoord;
	}

	public void setRight(boolean right){
		this.right = right;
	}

	public void setOnGround(boolean onGround){
		this.onGround = onGround;
	}

	public void setLastKU(boolean lastKU){
		this.lastKU = lastKU;
	}

	public void setHasLollipop(boolean hasLollipop){
		this.hasLollipop = hasLollipop;
	}

	public void setHasGoldenKey(boolean hasGoldenKey){
		this.hasGoldenKey = hasGoldenKey;
	}

	public void setToNewWalk(long toNewWalk){
		this.toNewWalk = toNewWalk;
	}

	public void setCurrentWalk(int currentWalk){
		this.currentWalk = currentWalk;
	}

	public void setPos(int pos){
		this.pos = pos;
	}

	public void setStandCount(int standCount){
		this.standCount = standCount;
	}

	public void setIsWinner(boolean isWinner){
		this.isWinner = isWinner;
	}

	public void setSpeedX(double x){
		this.speed.setX(x);
	}

	public void setSpeedY(double y){
		this.speed.setY(y);
	}

	public void setFlash(boolean flash){
		this.flash = flash;
	}

	public void setFlashLeft(long flashLeft){
		this.flashLeft = flashLeft;
	}

	public void setLollipopLeft(long lollipopLeft){
		this.lollipopLeft = lollipopLeft;
	}
	
	public void keyTyped(KeyEvent e) {}
}
