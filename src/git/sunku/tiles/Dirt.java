package git.sunku.tiles;

import git.sunku.engine.graphics.textures.Texture;

public class Dirt extends Tile {

    public Dirt() {
        super(getFromAtlas(2, 0));
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
