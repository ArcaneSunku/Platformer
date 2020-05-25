package git.sunku.engine.graphics.textures;

import git.sunku.Assets;

import java.awt.image.BufferedImage;

public class TextureAtlas {

    private final BufferedImage[][] m_Images;

    private final int m_Rows;
    private final int m_Columns;

    public TextureAtlas(String atlasName, BufferedImage texture, int rows, int columns) {
        if(!Assets.inCache(texture))
            Assets.putImage(atlasName, texture);

        m_Rows = rows;
        m_Columns = columns;
        m_Images = new BufferedImage[rows][columns];

        for(int y = 0; y < columns; y++) {
            for(int x = 0; x < rows; x++) {
                int w = texture.getWidth() / rows, h = texture.getHeight() / columns;
                m_Images[x][y] = texture.getSubimage(x * w, y * h, w, h);
            }
        }
    }

    public TextureAtlas(String texture, int rows, int columns) {
        m_Rows = rows;
        m_Columns = columns;
        m_Images = new BufferedImage[rows][columns];

        BufferedImage image = Assets.getImage(texture);

        for(int y = 0; y < columns; y++) {
            for(int x = 0; x < rows; x++) {
                int w = image.getWidth() / rows, h = image.getHeight() / columns;
                m_Images[x][y] = image.getSubimage(x * w, y * h, w, h);
            }
        }
    }

    public Texture getTexture(int row, int column) {
        if(row < 0 || row > m_Rows || column < 0 || column > m_Columns)
            return null;

        return new Texture(m_Images[row][column]);
    }

    public BufferedImage getImage(int row, int column) {
        if(row < 0 || row > m_Rows || column < 0 || column > m_Columns)
            return null;

        return m_Images[row][column];
    }

    public int rowLength() { return m_Rows; }
    public int columnLength() { return m_Columns; }

}
