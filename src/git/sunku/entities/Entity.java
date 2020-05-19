package git.sunku.entities;

import git.sunku.engine.graphics.Rendering;
import git.sunku.engine.graphics.textures.TextureAtlas;

public class Entity {

    protected TextureAtlas texture;

    public float x, y;
    public int width, height;

    public Entity(String atlas, int rows, int columns) {
        texture = new TextureAtlas(atlas, rows, columns);
    }

    public void update() {

    }

    public void draw() {
        if(texture != null) {
            Rendering.drawImage(texture.getImage(2, 0), x, y, width, height);
        }
    }

}
