package git.sunku.entities;

import git.sunku.Handler;
import git.sunku.engine.input.Keyboard;

import java.awt.event.KeyEvent;

public class Player extends Entity {

    private boolean m_Jumping, m_Falling;

    public Player() {
        super("Player", "slime", 2, 2);
        x = 0;
        y = 300;

        width = Handler.UNIT * 2;
        height = Handler.UNIT * 2;
        m_Jumping = false;
        m_Falling = false;
    }

    @Override
    public void update(double deltaTime) {
        if(Keyboard.isPressed(KeyEvent.VK_A)) {
            xVel -= 1.5f;
        } else if(Keyboard.isPressed(KeyEvent.VK_D)) {
            xVel += 1.5f;
        } else {
            if(xVel > 0f) {
                xVel-= 0.25f;
            } else if(xVel < 0f) {
                xVel+=0.25f;
            } else {
                xVel = 0;
            }
        }

        super.update(deltaTime);
    }

}
