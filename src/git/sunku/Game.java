package git.sunku;

import git.sunku.engine.graphics.Renderer;
import git.sunku.engine.graphics.Window;
import git.sunku.engine.input.Input;
import git.sunku.engine.input.Keyboard;
import git.sunku.engine.scenes.MainScene;
import git.sunku.engine.scenes.SceneManager;

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

    private SceneManager m_SceneManager;

    private String m_ShowFPS;

    private boolean m_DisplayFPS;

    private volatile boolean mv_Running;

    public Game(String title, int width, int height) {
        m_Window = new Window(title + " | F1: Display FPS", width, height);
    }

    /**
     * A thread safe way to start off our game.
     */
    public synchronized void start() {
        if (mv_Running) return;

        m_Thread = new Thread(this, "Game_Thread");
        mv_Running = true;
        m_Thread.start();
    }

    /**
     * A thread safe way to stop the game.
     */
    public synchronized void stop() {
        if (mv_Running) mv_Running = false;

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

        double frames_per_second = m_Window.getRefreshRate() == 0 ? 60 : m_Window.getRefreshRate();
        int frame_count = 0, frame_tick = 0;

        long last_time = System.nanoTime();

        double total_time = 0.0;
        double frame_time = 0.0;

        boolean should_render = false;

        final double NANOSECONDS = 1e9 / frames_per_second;

        while (mv_Running) {
            if (m_Window.hasClosed() || m_Window.isClosing())
                mv_Running = false;

            long current_time = System.nanoTime();
            frame_time += (current_time - last_time) / NANOSECONDS;
            total_time += frame_time;
            last_time = current_time;

            while (frame_time >= 1) {
                m_Input.update();
                update(frame_time);
                should_render = true;

                frame_time -= 1;
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

            if (should_render) {
                render();
                frame_count++;
                should_render = false;
            }

            render();
            frame_count++;

            frame_tick += total_time;

            if (frame_tick >= 1) {
                m_ShowFPS = String.format("%-1s %.2f, Avg Frame: %dms", "FPS:", (total_time / frame_count * 1000), frame_count);

                frame_tick -= 1;
                frame_count = 0;
                total_time = 0;
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
        m_SceneManager = new SceneManager();
        m_Handler = new Handler(this);

        m_Window.display();
        m_Input = new Input(m_Window);

        m_DisplayFPS = false;
        m_ShowFPS = String.format("%-1s %d, Avg Frame: %.2fms", "FPS:", 0, 0.00f);

        Handler.addScene(new MainScene());
        Handler.setScene("MainScene");
    }

    /**
     * Holds all of our logical updates for our game's components.
     *
     * @param deltaTime the time we'll use to keep consistencies in input and rendering
     */
    private void update(double deltaTime) {
        if(Keyboard.justPressed(KeyEvent.VK_F1))
            m_DisplayFPS = !m_DisplayFPS;

        m_SceneManager.update(deltaTime);
    }

    /**
     * Facilitates the graphical updates for all of our game's components.
     */
    private void render() {
        BufferStrategy strategy = m_Window.getBufferStrategy();

        if (strategy == null) {
            m_Window.createBufferStrategy(2);
            return;
        }

        Graphics graphics = strategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        m_Handler.setRenderingGraphics(graphics);
        m_SceneManager.render();

        if(m_DisplayFPS) { // FPS Renderer
            Renderer.setFont("vcr", Font.BOLD, 18f);
            final int fps_x = m_Window.getWidth() - Renderer.stringWidth(Renderer.getFont(), m_ShowFPS);
            Renderer.drawString(Color.white, m_ShowFPS, fps_x - 2, 20); // white shadow
            Renderer.drawString(Color.blue, m_ShowFPS, fps_x, 20); // blue font
        }

        graphics.dispose();
        strategy.show();
    }

    public SceneManager getSceneManager() {
        return m_SceneManager;
    }

    public int getWidth() {
        return m_Window.getWidth();
    }

    public int getHeight() {
        return m_Window.getHeight();
    }

}
