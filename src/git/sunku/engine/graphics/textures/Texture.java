package git.sunku.engine.graphics.textures;

import git.sunku.Assets;
import git.sunku.Handler;
import git.sunku.engine.graphics.Rendering;

import java.awt.image.BufferedImage;

public class Texture {

    private BufferedImage m_Image;

    public float x, y;
    public int width, height;

    public Texture(BufferedImage image) {
        m_Image = image;

        width = m_Image.getWidth();
        height = m_Image.getHeight();
    }

    public Texture(String texture) {
        m_Image = Assets.getImage(texture);

        width = m_Image.getWidth();
        height = m_Image.getHeight();
    }

    public void draw() {
        Rendering.drawImage(m_Image, (int) x, (int) y, width, height);
    }

}
