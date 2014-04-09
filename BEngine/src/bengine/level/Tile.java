package bengine.level;

//import java.util.List;

import bengine.entity.Entity;
import bengine.screen.*;

public class Tile {
    public static final int HEIGHT = 32;
    public static final int WIDTH = 32;

    public Level level;
    public int x, y;
    public int img;
    public int minimapColor;

    public void init(Level level, int x, int y, int img) {
        this.level = level;
        this.x = x;
        this.y = y;
        //minimapColor = Art.floorTileColors[img & 7][img / 8];
    }

    public boolean canPass(Entity e) {
        return true;
    }

    public void render(Screen screen) {
        //screen.blit(Art.floorTiles[img & 7][img / 8], x * Tile.WIDTH, y * Tile.HEIGHT);
    }

    public void handleCollision(Entity entity, double xa, double ya) {
    }

    public boolean isBuildable() {
        return false;
    }

    public void neighbourChanged(Tile tile) {
    }

    public int getCost() {
        return 0;
    }

    public boolean castShadow() {
        return false;
    }

    public void renderTop(Screen screen) {
    }
}