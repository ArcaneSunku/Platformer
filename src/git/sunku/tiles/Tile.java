package git.sunku.tiles;

import git.sunku.engine.graphics.textures.Texture;

public abstract class Tile {

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
        texture.x = x;
        texture.y = y;

        texture.draw();
    }

}
