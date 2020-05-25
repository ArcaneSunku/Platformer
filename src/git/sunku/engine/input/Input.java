package git.sunku.engine.input;

import git.sunku.engine.graphics.Window;

/**
 * Holds and handles both of our Input related classes, as to reduce clutter
 * within the code.
 */
public class Input {

    private final Keyboard m_Keys;
    private final Mouse m_Buttons;

    public Input(Window window) {
        m_Keys = new Keyboard(window);
        m_Buttons = new Mouse(window);

        window.setFocusable(true);
        window.requestFocus();
    }

    public void update() {
        m_Keys.update();
        m_Buttons.update();
    }

}
