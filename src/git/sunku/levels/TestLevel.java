package git.sunku.levels;

import git.sunku.Handler;
import git.sunku.engine.graphics.Renderer;
import git.sunku.tiles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestLevel extends Level {

    public TestLevel() {
        super(10, 4, 4);

        levelX = 20;
        levelY = 400;
    }

    @Override
    public void initialize() {
        List<Tile> tileLayer1 = new ArrayList<>();

        for(int x = 0; x < levelWidth; x++) {
            for(int y = 0; y < levelHeight; y++) {
                Tile t;

                switch (ThreadLocalRandom.current().nextInt(1, 4)) {
                    case 1:
                        t = new Grass();
                        break;
                    case 2:
                        t = new Dirt();
                        break;
                    case 3:
                        t = new Sand();
                        break;
                    default:
                        throw new IllegalStateException("Random Tiles didn't work!");
                }

                t.x = x * Handler.UNIT + levelX;
                t.y = y * Handler.UNIT + levelY;
                t.width = Handler.UNIT;
                t.height = Handler.UNIT;

                tileLayer1.add(t);
            }
        }

        tiles.put(0, tileLayer1);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render() {
        for(int i = 0; i < tiles.size(); i++) {
            Renderer.drawTiles(tiles.get(i));
        }
    }
}
