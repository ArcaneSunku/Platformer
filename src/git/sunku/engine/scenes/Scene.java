package git.sunku.engine.scenes;

public abstract class Scene {

    protected final String SCENE_NAME;

    public Scene(String name) {
        SCENE_NAME = name;
    }

    public abstract void prepare();
    public abstract void update(double deltaTime);
    public abstract void render();

    public String getName() { return SCENE_NAME; }

}
