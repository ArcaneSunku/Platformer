package git.sunku.tiles;

import git.sunku.Assets;
import git.sunku.engine.graphics.textures.Texture;
import git.sunku.engine.graphics.textures.TextureAtlas;

public abstract class Tile {
    private static final TextureAtlas tileAtlas = new TextureAtlas("tiles", 6, 20);

    protected Texture texture;

    public float x, y;
    public int width, height;

    public Tile(Texture texture) {
        this.texture = texture;
    }

    public Tile(String texture) {
        this.texture = new Texture(texture);
    }

    public void draw() {
        if(texture.x != x)
            texture.x = x;

        if(texture.y != y)
            texture.y = y;

        if(texture.width != width)
            texture.width = width;

        if(texture.height != height)
            texture.height = height;

        texture.draw();
    }

    public abstract boolean isSolid();

    protected static Texture getFromAtlas(int row, int column) {
        return tileAtlas.getTexture(row, column);
    }

}
