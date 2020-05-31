package git.sunku.engine.scenes;

import git.sunku.Handler;
import git.sunku.engine.graphics.Renderer;
import git.sunku.entities.Entity;
import git.sunku.entities.Player;
import git.sunku.levels.Level;
import git.sunku.levels.TestLevel;
import git.sunku.tiles.Dirt;
import git.sunku.tiles.Grass;
import git.sunku.tiles.Sand;
import git.sunku.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainScene extends Scene {

    private List<Tile> m_Tiles;

    private Entity m_Player;

    private Level m_Test;

    public MainScene() {
        super("MainScene");
    }

    @Override
    public void prepare() {
        System.out.println("PREPARING SCENE");

        System.out.println("PREPARING THE LEVEL");
//        m_Tiles = new ArrayList<Tile>();
//
//        for(int lvlWidth = 0; lvlWidth < 15; lvlWidth++) {
//            for(int lvlHeight = 0; lvlHeight < 15; lvlHeight++) {
//                Tile t = null;
//
//                switch(ThreadLocalRandom.current().nextInt(1, 4)) {
//                    case 1:
//                        t = new Grass();
//                        break;
//                    case 2:
//                        t = new Dirt();
//                        break;
//                    case 3:
//                        t = new Sand();
//                        break;
//                    default:
//                        throw new IllegalStateException("We shouldn't be here...");
//                }
//
//                t.x = lvlWidth * Handler.UNIT;
//                t.y = lvlHeight * Handler.UNIT;
//
//                t.width = Handler.UNIT;
//                t.height = Handler.UNIT;
//
//                m_Tiles.add(t);
//            }
//        }

        m_Test = new TestLevel();
        m_Test.initialize();

        System.out.println("INSERTING THE PLAYER");
        m_Player = new Player();

        System.out.println("FINISHED PREPARING");
    }

    @Override
    public void update(double deltaTime) {
        m_Player.update(deltaTime);
        m_Test.update(deltaTime);
    }

    @Override
    public void render() {
        m_Test.render();
        Renderer.drawEntity(m_Player);
    }
}
