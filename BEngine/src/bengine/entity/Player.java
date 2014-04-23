package bengine.entity;

import bengine.Keys;

public class Player {
	
	public Keys keys;
    private int startX;
    private int startY;
	
	
	public Player(Keys keys, int x, int y) {
        this.keys = keys;

        startX = x;
        startY = y;

    }
	
	public void tick() {
		/*** tick tack ***/
		// Gravity
		
		// ex:
		// if (keys.jump.wasPressed()) ...
	}
	
	public void render() {
		/*** RENDER MEEEE ***/
	}

}
