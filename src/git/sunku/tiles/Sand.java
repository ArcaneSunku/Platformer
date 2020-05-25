package git.sunku.tiles;

public class Sand extends Tile{

    public Sand() {
        super(getFromAtlas(3, 0));
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
