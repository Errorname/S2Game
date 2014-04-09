package bengine.level;
import bengine.screen.*;
import bengine.math.*;

public class Level {
    public int width, height;

    public Tile[] tiles;

    public Level() {
        /*this.width = width;
        this.height = height;*/
    }

    public void fromFile(String path) {
        /********* CHARGE LA MAP ICI ***********/
    }

    public void init() {
        /********* INITIALISE DES TRUCS ICI ***********/
    }

    public void setTile(int x, int y, Tile tile) {
        
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return tiles[x + y * width];
    }

    public Tile getTile(Vec2 pos) {
        int x = (int) pos.x / Tile.WIDTH;
        int y = (int) pos.y / Tile.HEIGHT;
        return getTile(x, y);
    }

    public void tick() {
        /******** FAIT LES CALCULS ?? ***********/
    }

    public void render(Screen screen, int xScroll, int yScroll) {
        /******** AFFICHE LA MAP ICI **************/
    }
}
