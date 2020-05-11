package git.sunku;

import git.sunku.engine.graphics.Rendering;

import java.awt.*;

public class Handler {

    private static Game m_Game;
    private final Rendering m_Rendering;

    public Handler(Game game) {
        m_Game = game;
        m_Rendering = new Rendering();
    }

    public void getRenderingGraphics(Graphics g) {
        m_Rendering.getGraphics2D((Graphics2D) g);
    }

    public static int gameWidth() { return m_Game.getWidth(); }
    public static int gameHeight() { return m_Game.getHeight(); }
}
