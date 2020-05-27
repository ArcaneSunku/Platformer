package git.sunku.entities;

import git.sunku.engine.graphics.Renderer;
import git.sunku.engine.graphics.textures.TextureAtlas;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Entity {

    protected TextureAtlas texture;

    protected String name;

    // Helps us explore the Atlas for animation esque behavior
    protected int dir, frame;

    // Positional and Velocity variables
    public float x, y;
    public float xVel, yVel;

    // Size
    public int width, height;

    public Entity(String name, String atlas, int rows, int columns) {
        texture = new TextureAtlas(atlas, rows, columns);
        dir = 0;
        frame = 0;
    }

    public void update(double deltaTime) {
        if(xVel > 5f)
            xVel = 3.5f;
        if(xVel < -5f)
            xVel = -3.5f;

        x += xVel * deltaTime;
        y += yVel * deltaTime;
    }

    public void draw() {
        if(texture != null) {
            if (frame < 0 || frame > texture.rowLength() || dir < 0 || dir > texture.columnLength()) return;

            Renderer.drawImage(texture.getImage(frame, dir), x, y, width, height);
        }
    }

    public Rectangle2D getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public String getName() { return name; }

}
