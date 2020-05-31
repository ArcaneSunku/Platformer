package git.sunku.entities;

import git.sunku.engine.graphics.Renderer;
import git.sunku.engine.graphics.textures.TextureAtlas;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Entity {

    protected TextureAtlas texture;
    protected String name;

    protected int health;
    protected int dir, frame;

    protected boolean walking, dead;

    // Positional and Velocity variables
    public float x, y;
    public float xVel, yVel;

    // Size
    public int width, height;

    public Entity(String name, String atlas, int rows, int columns) {
        texture = new TextureAtlas(atlas, rows, columns);

        health = 25;
        dir = 0;
        frame = 0;

        walking = false;
    }

    public void update(double deltaTime) {
        if(height <= 0)
            dead = true;

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

    public boolean isAlive() { return !dead; }

}
