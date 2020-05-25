package git.sunku;

import git.sunku.engine.graphics.Renderer;

import java.awt.*;

public class Handler {

    private static Game m_Game;
    private final Renderer m_Renderer;

    public Handler(Game game) {
        m_Game = game;
        m_Renderer = new Renderer();
    }

    public void getRenderingGraphics(Graphics g) {
        m_Renderer.getGraphics2D((Graphics2D) g);
    }

    public static int gameWidth() { return m_Game.getWidth(); }
    public static int gameHeight() { return m_Game.getHeight(); }
}
