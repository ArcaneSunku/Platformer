package git.sunku.levels;

import git.sunku.tiles.Tile;

import java.util.HashMap;
import java.util.List;

public abstract class Level {
    protected static final int MAX_LAYERS = 5;

    // A Tile system, trying to visualize Layers
    protected HashMap<Integer, List<Tile>> tiles;

    protected int m_Layer;

    protected float levelX, levelY;
    protected int levelWidth, levelHeight;

    public Level(int width, int height, int tileLayers) {
        levelX = 0;
        levelY = 0;

        levelWidth = width;
        levelHeight = height;

        // Limit how many layers of Tiles we have
        tiles = new HashMap<>(Math.min(tileLayers, MAX_LAYERS));
    }

    public abstract void initialize();
    public abstract void update(double dt);
    public abstract void render();

    public void loadLevel(String levelName) {
        // TODO: Implement a way to load Editor created Levels
    }

}
