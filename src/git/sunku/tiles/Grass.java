package git.sunku.tiles;

import git.sunku.engine.graphics.textures.Texture;

public class Grass extends Tile {
    public Grass() {
        super(getFromAtlas(1, 0));
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
