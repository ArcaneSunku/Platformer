package git.sunku.engine.graphics;

import git.sunku.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rendering {

    private static Graphics2D m_Graphics2D;

    public Rendering() {
        m_Graphics2D = null;
    }

    public void getGraphics2D(Graphics2D g2d) {
        m_Graphics2D = g2d;
    }

    public static void drawImage(BufferedImage image, float x, float y, int width, int height) {
        m_Graphics2D.drawImage(image, (int) x, (int) y, width, height, null);
    }

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
