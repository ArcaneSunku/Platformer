package git.sunku;

import git.sunku.engine.graphics.Rendering;
import git.sunku.engine.graphics.Window;
import git.sunku.engine.input.Keyboard;
import git.sunku.engine.input.Mouse;
import git.sunku.entities.Entity;
import git.sunku.tiles.Grass;
import git.sunku.tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * This class facilitates everything we need for a functioning game. </br>
 * We have the Main Thread started here and this also hosts the game loop.
 */

public class Game implements Runnable {
    private final Window m_Window;

    private Handler m_Handler;
    private Keyboard m_Keyboard;
    private Mouse m_Mouse;
    private Thread m_Thread;

    private Tile mGrass;

    private volatile boolean mv_Running;

    public Game(String title, int width, int height) {
        m_Window = new Window(title, width, height);
    }

    /**
     * A thread safe way to start off our game.
     */
    public synchronized void start() {
        if(mv_Running) return;

        m_Thread = new Thread(this, "Game_Thread");
        mv_Running = true;
        m_Thread.start();
    }

    /**
     * A thread safe way to stop the game.
     */
    public synchronized void stop() {
        if(mv_Running) mv_Running = false;

        try {
            m_Window.dispose();
            m_Thread.join(3);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Starts the Main Thread and also holds the Main Loop. </br>
     * Holds the most important stuff.
     */
    @Override
    public void run() {
        init();

        int updates = 0, frames = 0;
        long last_time = System.nanoTime();
        long timer = System.currentTimeMillis();

        double tick_limit = 60.0D;
        double nanoseconds_per_update = 1e9 / tick_limit;
        double delta = 0.0D;

        boolean should_render = false;

        while(mv_Running) {
            if(m_Window.hasClosed() || m_Window.isClosing())
                mv_Running = false;

            long now = System.nanoTime();
            delta += (now - last_time) / nanoseconds_per_update;
            last_time = now;

            while(delta >= 1) {
                update();
                should_render = true;

                updates++;
                delta -= 1;
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(should_render) {
                render();
                frames++;
                should_render = false;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                System.out.printf("%d updates, %d frames%n", updates, frames);

                timer+= 1000;
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    /**
     * Initializes everything we need to start the game. </br>
     * This saves on performance in some ways.
     */
    private void init() {
        Assets.init();

        m_Window.display();
        m_Keyboard = new Keyboard(m_Window);
        m_Mouse = new Mouse(m_Window);
        m_Handler = new Handler(this);

        mGrass = new Grass();

        mGrass.x = 0;
        mGrass.y = 0;

        mGrass.width = 32;
        mGrass.height = 32;
    }

    /**
     * Holds all of our logical updates for our game's components.
     */
    private void update() {
        m_Keyboard.update();
        m_Mouse.update();
    }

    /**
     * Facilitates the render updates for all of our game's components.
     */
    private void render() {
        BufferStrategy strategy = m_Window.getBufferStrategy();

        if(strategy == null) {
            m_Window.createBufferStrategy(2);
            return;
        }

        Graphics graphics = strategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        m_Handler.getRenderingGraphics(graphics);

        mGrass.draw();

        Rendering.setFont("vcr", 24f);
        Rendering.drawString(Color.blue, "Testing 123", 180, 180);

        graphics.dispose();
        strategy.show();
    }

    public int getWidth() {
        return m_Window.getWidth();
    }

    public int getHeight() {
        return m_Window.getHeight();
    }

}
