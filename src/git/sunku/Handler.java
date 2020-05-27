package git.sunku;

import git.sunku.engine.graphics.Renderer;
import git.sunku.engine.scenes.Scene;
import git.sunku.engine.scenes.SceneManager;

import java.awt.*;

public class Handler {

    public static int UNIT = 32;

    private static Game m_Game;
    private static Rectangle m_GameBounds;
    private final Renderer m_Renderer;

    public Handler(Game game) {
        m_Game = game;
        m_Renderer = new Renderer();

        m_GameBounds = new Rectangle(0, 0, game.getWidth(), game.getHeight());
    }

    private static SceneManager sceneManager() { return m_Game.getSceneManager(); }

    public static void addScene(Scene newScene) {
        sceneManager().addScene(newScene);
    }
    public static void setScene(String newScene) {
        sceneManager().setScene(newScene);
    }

    public void setRenderingGraphics(Graphics g) {
        m_Renderer.getGraphics2D((Graphics2D) g);
    }

    public static Rectangle gameBounds() { return m_GameBounds; }

    public static int gameWidth() { return m_Game.getWidth(); }
    public static int getHeight() { return m_Game.getHeight(); }
}
