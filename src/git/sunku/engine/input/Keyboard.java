package git.sunku.engine.input;

import git.sunku.engine.graphics.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles everything related to Keyboard-centered input.
 */
public class Keyboard implements KeyListener {
    private static final int MAX_KEYS = 256;

    private static boolean[] pressed, cantPress, justPressed;

    public Keyboard(Window window) {
        pressed = new boolean[MAX_KEYS];
        cantPress = new boolean[MAX_KEYS];
        justPressed = new boolean[MAX_KEYS];

        window.addKeyListener(this);
    }

    /**
     * Updates all the keys so we can check for more specific key presses. (ie half-press)
     */
    public void update() {
        for(int i = 0; i < MAX_KEYS; i++) {
            if(cantPress[i] && !pressed[i]) {
                cantPress[i] = false;
            } else if(justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }

            if(!cantPress[i] && pressed[i]) {
                justPressed[i] = true;
            }
        }
    }

    public static boolean isPressed(int key) { return pressed[key]; }
    public static boolean justPressed(int key) { return justPressed[key]; }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > MAX_KEYS)
            return;

        pressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > MAX_KEYS)
            return;

        pressed[e.getKeyCode()] = false;
    }
}
