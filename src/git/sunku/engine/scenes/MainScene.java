package git.sunku.engine.scenes;

import git.sunku.engine.graphics.Renderer;
import git.sunku.tiles.Grass;
import git.sunku.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends Scene {

    private List<Tile> m_Tiles;

    public MainScene() {
        super("MainScene");
    }

    @Override
    public void prepare() {
        System.out.println("PREPARING MAIN");
        m_Tiles = new ArrayList<>();

        for(int x = 0; x < 300; x++) {
            for(int y = 0; y < 300; y++) {
                Tile tile = new Grass();

                tile.x = x * UNIT;
                tile.y = y * UNIT;
                tile.width = UNIT;
                tile.height = UNIT;

                m_Tiles.add(tile);
            }
        }
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render() {
        Renderer.drawTiles(m_Tiles);
    }
}
