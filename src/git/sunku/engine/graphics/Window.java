package git.sunku.engine.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends Canvas {

    private JFrame m_Frame;
    private WindowListener m_Listener;

    private String m_Title;
    private int m_Width, m_Height;

    private boolean m_Closing, m_Closed;

    public Window(String title, int width, int height) {
        m_Title = title;

        m_Width = width;
        m_Height = height;

        initializeWindow();
    }

    private void initializeWindow() {
        m_Frame = new JFrame(m_Title);
        Dimension dimension = new Dimension(m_Width, m_Height);

        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);

        m_Listener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                m_Closing = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                m_Closed = true;
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };

        setFocusable(true);

        m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_Frame.setLayout(new BorderLayout());
        m_Frame.add(this, BorderLayout.CENTER);
        m_Frame.pack();
        m_Frame.setLocationRelativeTo(null);
        m_Frame.setResizable(false);
    }

    public void display() {
        m_Frame.setVisible(true);
        m_Frame.setFocusable(true);

        setFocusable(true);
        requestFocus();
    }

    public void setTitle(String title) {
        m_Frame.setTitle(title);
    }

    public void dispose() {
        m_Frame.dispose();
    }

    public boolean hasClosed() { return m_Closed; }
    public boolean isClosing() { return m_Closing; }

    public String title() { return m_Title; }

    public int getRefreshRate() {
        if(m_Frame != null) return 0;

        return getGraphicsConfiguration().getDevice().getDisplayMode().getRefreshRate();
    }

    public int width() { return m_Width; }
    public int height() { return m_Height; }

}
