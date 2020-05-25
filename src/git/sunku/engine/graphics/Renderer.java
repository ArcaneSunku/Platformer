package git.sunku.engine.graphics;

import git.sunku.Assets;
import git.sunku.engine.graphics.textures.Texture;
import git.sunku.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Handles everything with the Main Rendering Thread's Graphics2D object. </br>
 * This way we can access a lot of the methods without the need of passing through </br>
 * the pesky Graphics every time.
 */

public class Renderer {

    private static Graphics2D m_Graphics2D;

    public Renderer() {
        m_Graphics2D = null;
    }

    public void getGraphics2D(Graphics2D g2d) {
        m_Graphics2D = g2d;
    }

    public static void drawTile(Tile tile) {
        tile.draw();
    }

    public static void drawTexture(Texture texture) {
        texture.draw();
    }

    public static void drawImage(BufferedImage image, float x, float y, int width, int height) {
        m_Graphics2D.drawImage(image, (int) x, (int) y, width, height, null);
    }

    public static Font getFont() { return m_Graphics2D.getFont(); }

    public static void setFont(String font, float size) {
        m_Graphics2D.setFont(Assets.getFont(font, size));
    }

    public static void setFont(String font, int style, float size) {
        m_Graphics2D.setFont(Assets.getFont(font, style, size));
    }

    public static void drawString(Color drawColor, String str, int x, int y) {
        m_Graphics2D.setColor(drawColor);
        drawString(str, x, y);
    }

    public static void drawString(String str, int x, int y) {
        m_Graphics2D.drawString(str, x, y);
    }

    public static int stringWidth(String str) {
        return stringWidth(m_Graphics2D.getFont(), str);
    }

    public static int stringWidth(Font font, String str) {
        return m_Graphics2D.getFontMetrics(font).stringWidth(str);
    }

    public static int fontHeight() {
        return fontHeight(m_Graphics2D.getFont());
    }

    public static int fontHeight(Font font) {
        return m_Graphics2D.getFontMetrics(font).getHeight();
    }

}