package git.sunku.entities;

import git.sunku.engine.graphics.Rendering;
import git.sunku.engine.graphics.textures.TextureAtlas;

public abstract class Entity {

    protected TextureAtlas texture;

    public float x, y;
    public int width, height;

    public Entity(String atlas) {
        this(atlas, 6, 20);
    }

    public Entity(String atlas, int rows, int columns) {
        texture = new TextureAtlas(atlas, rows, columns);
    }

    public abstract void update();

    public void draw() {
        if(texture != null) {
            Rendering.drawImage(texture.getImage(1, 0), x, y, width, height);
        }
    }

}
