package git.sunku.engine.input;

import git.sunku.engine.graphics.Window;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final int MAX_BUTTONS = 10;

    private static boolean[] clicked, cantClick, justClicked;

    public Mouse(Window window) {
        clicked = new boolean[MAX_BUTTONS];
        cantClick = new boolean[MAX_BUTTONS];
        justClicked = new boolean[MAX_BUTTONS];

        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.addMouseWheelListener(this);
    }

    public void update() {
        for(int i = 0; i < MAX_BUTTONS; i++) {
            if(cantClick[i] && !clicked[i]) {
                cantClick[i] = false;
            } else if(justClicked[i]) {
                cantClick[i] = true;
                justClicked[i] = false;
            }

            if(!cantClick[i] && clicked[i]) {
                justClicked[i] = true;
            }
        }
    }

    public static boolean isClicked(int key) { return clicked[key]; }
    public static boolean justClicked(int key) { return justClicked[key]; }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() < 0 || e.getButton() > MAX_BUTTONS)
            return;

        clicked[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() < 0 || e.getButton() > MAX_BUTTONS)
            return;

        clicked[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
