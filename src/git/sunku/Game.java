package git.sunku;

import git.sunku.engine.graphics.Renderer;
import git.sunku.engine.graphics.Window;
import git.sunku.engine.input.Input;
import git.sunku.engine.input.Keyboard;
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
    private Input m_Input;
    private Thread m_Thread;

    private Tile m_Grass;

    private String m_ShowFPS;

    private boolean m_DisplayFPS;

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

        int frames_per_second = m_Window.getRefreshRate();
        int frame_count = 0, frame_tick = 0;

        long current_time;
        long last_time = System.nanoTime();

        double total_time = 0.0;
        double frame_time = 0.0;

        boolean should_render = false;

        final double NANOSECONDS = 1e9;
        final double DELTA = 1 / (double) frames_per_second;

        while(mv_Running) {
            if(m_Window.hasClosed() || m_Window.isClosing())
                mv_Running = false;

            current_time = System.nanoTime();
            frame_time += current_time - last_time;
            total_time += (double) (current_time - last_time) / NANOSECONDS;
            last_time = current_time;

            while(frame_time >= DELTA * NANOSECONDS) {
                update(DELTA);
                frame_time -= DELTA * NANOSECONDS;
                should_render = true;
            }

            // Old Delta timer
//            while(delta >= 1) {
//                update(delta);
//                should_render = true;
//
//                updates++;
//                delta -= 1;
//            }

//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // we make sure we are rendering at least twice so there is no tearing

            if(should_render) {
                render();
                should_render = false;
            }

            render();

            if(m_DisplayFPS) {
                frame_count++;
                frame_tick += total_time;

                if(frame_tick >= 1) {

                    m_ShowFPS = String.format("FPS: %d, Avg Frame: %.2fms", frame_count, (total_time / frame_count * 1000));

//                    System.out.printf (m_ShowFPS);

                    frame_tick -= 1;
                    frame_count = 0;
                    total_time = 0;
                }
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
        m_Input = new Input(m_Window);
        m_Handler = new Handler(this);

        m_ShowFPS = String.format("FPS: %d, Avg Frame: %.2fms", 0, 0.00f);

        m_DisplayFPS = true;

        m_Grass = new Grass();

        m_Grass.x = 0;
        m_Grass.y = 0;

        m_Grass.width = 32;
        m_Grass.height = 32;
    }

    /**
     * Holds all of our logical updates for our game's components.
     * @param deltaTime the time we'll use to keep consistencies in input and rendering
     */
    private void update(double deltaTime) {
        m_Input.update();

        if(Keyboard.isPressed(KeyEvent.VK_D))
            m_Grass.x += m_Grass.width * deltaTime;

        if(Keyboard.justPressed(KeyEvent.VK_ESCAPE))
            mv_Running = false;
    }

    /**
     * Facilitates the graphical updates for all of our game's components.
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

        Renderer.drawTile(m_Grass);

        Renderer.setFont("vcr", 16f);
        final int fps_x = m_Window.getWidth() - Renderer.stringWidth(Renderer.getFont(), m_ShowFPS);
        Renderer.drawString(Color.blue, m_ShowFPS, fps_x, 20);

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
