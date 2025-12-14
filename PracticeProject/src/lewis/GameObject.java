package lewis;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.Icon;

/**
 * Abstract base class for game objects.
 * Extended with selection capability and a currentImage index.
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int velocity;
    private int direction;

    protected List<Icon> imageList;
    protected int currentImage;

    // selection flag for highlighting
    private boolean selected = false;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        velocity = 0;
        currentImage = 0;
        direction = Direction.NONE;
    }

    /**
     * Draw the current image. If selected, draw a red rectangle around it.
     */
    public void draw(Component c, Graphics g) {
        Icon icon = imageList.get(currentImage);
        icon.paintIcon(c, g, x, y);
        if (selected) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(java.awt.Color.RED);
            g2.drawRect(x - 2, y - 2, icon.getIconWidth() + 4, icon.getIconHeight() + 4);
        }
    }

    // Getters / setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public void setVelocity(int velocity) { this.velocity = velocity; }
    public int getVelocity() { return velocity; }

    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }

    public Icon getCurrentImage() { return imageList.get(currentImage); }

    public void setSelected(boolean sel) { this.selected = sel; }
    public boolean isSelected() { return selected; }

    // Abstract contract for subclasses
    public abstract void move(Canvas c);
    public abstract void setImage();
}
