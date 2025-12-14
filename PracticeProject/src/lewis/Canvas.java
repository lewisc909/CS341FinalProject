package lewis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The main drawing canvas and game loop controller.
 */
public class Canvas extends JComponent implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private Timer gameLoopTimer;
    private List<GameObject> gameObjectList;
    private int highlighted = -1; // index of highlighted (selected) object

    public Canvas() {
        gameObjectList = new LinkedList<>();

        frame = new JFrame("Animation Canvas");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        // Game loop at ~40 FPS (25ms)
        gameLoopTimer = new Timer(25, this);
        gameLoopTimer.start();

        setFocusTraversalKeysEnabled(false); // allow TAB to be captured
        addKeyListener(this);

        frame.setVisible(true);
    }

    /**
     * Adds GameObjects to the List, which are later added to the Canvas
     */
    public synchronized void addGameObject(GameObject sprite) {
        gameObjectList.add(sprite);
    }

    /**
     * Request focus for keyboard control
     */
    public void requestCanvasFocus() {
        setFocusable(true);
        requestFocusInWindow();
        // If not focused yet, request focus on frame
        frame.requestFocus();
        requestFocus();
    }

    /**
     * Set the highlighted selection index externally (Main)
     */
    public void setHighlighted(int idx) {
        if (idx < 0 || idx >= gameObjectList.size()) return;
        // clear previous
        if (highlighted >= 0 && highlighted < gameObjectList.size()) {
            gameObjectList.get(highlighted).setSelected(false);
        }
        highlighted = idx;
        gameObjectList.get(highlighted).setSelected(true);
        requestCanvasFocus();
    }

    /**
     * Paint component: draw all GameObjects. Use paintComponent for Swing properness.
     */
    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject s : gameObjectList) {
            s.draw(this, g);
        }
    }

    // ********************************************************
    // ActionListener (game loop)
    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        for (GameObject gameObject : gameObjectList) {
            gameObject.move(this);
            gameObject.setImage();
        }
        repaint();
    }

    // ********************************************************
    // KeyListener: forward arrow keys to currently selected GameObject (if it is controllable)
    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Forward to selected object if it implements KeyListener (TypeD does)
        if (highlighted >= 0 && highlighted < gameObjectList.size()) {
            GameObject selected = gameObjectList.get(highlighted);
            if (selected instanceof KeyListener) {
                ((KeyListener) selected).keyPressed(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle TAB cycling selection on keyReleased to avoid multiple increments
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            // deselect previous
            if (highlighted >= 0 && highlighted < gameObjectList.size()) {
                gameObjectList.get(highlighted).setSelected(false);
            }
            highlighted++;
            if (highlighted >= gameObjectList.size()) highlighted = 0;
            // select new
            gameObjectList.get(highlighted).setSelected(true);
            return;
        }

        // Forward to selected object if it's a KeyListener
        if (highlighted >= 0 && highlighted < gameObjectList.size()) {
            GameObject selected = gameObjectList.get(highlighted);
            if (selected instanceof KeyListener) {
                ((KeyListener) selected).keyReleased(e);
            }
        }
    }

    /**
     * Provide access to the list for potential use by strategies or objects (read-only semantics)
     */
    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }
}
