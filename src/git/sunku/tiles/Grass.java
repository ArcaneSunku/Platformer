package git.sunku.tiles;

public class Grass extends Tile {
    public Grass() {
        super(getFromAtlas(1, 0));
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
