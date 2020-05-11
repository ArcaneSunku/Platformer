package git.sunku;

import git.sunku.engine.graphics.Rendering;
import git.sunku.engine.graphics.Window;
import git.sunku.entities.Entity;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private final Window m_Window;

    private Handler m_Handler;
    private Thread m_Thread;

    private Entity ent;

    private volatile boolean mv_Running;

    public Game(String title, int width, int height) {
        m_Window = new Window(title, width, height);
    }

    public synchronized void start() {
        if(mv_Running) return;

        m_Thread = new Thread(this, "Game_Thread");
        mv_Running = true;
        m_Thread.start();
    }

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

    private void init() {
        Assets.init();

        m_Window.display();
        m_Handler = new Handler(this);

        ent = new Entity("tiles");

        ent.x = 0;
        ent.y = 0;

        ent.width = 64;
        ent.height = 64;
    }

    private void update() {
        ent.update();
    }

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

        ent.draw();

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
