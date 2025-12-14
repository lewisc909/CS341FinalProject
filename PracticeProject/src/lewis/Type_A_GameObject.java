package lewis;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject implements KeyListener {
	private int movement=Direction.RIGHT;
	//movement variable up and down
	
	private int vertical = 2;

    public Type_A_GameObject(int x, int y) {
        super(x, y);
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_A_Up.png"));
        imageList.add(new ImageIcon("images/Type_A_Down.png"));
        setDirection(Direction.DOWN);
    }

    @Override
    public void move(Canvas c) {
    	Icon icon = getCurrentImage();

        int  canvasHeight = (int)c.getSize().getHeight();
        int  iconHeight   = icon.getIconHeight();
        int  iconWidth    = icon.getIconWidth();
        int  canvasWidth  = (int)c.getSize().getWidth();

    	    //up and down movement
        setY(getY()+vertical);
        
    	if(isSelected()) {
		if (getY() < 0) {
			  setY(0);
			  }
		if (getY() + iconHeight > canvasHeight) {
			setY(canvasHeight - iconHeight);
		}
			  }
    

    else {
    	  //movement of object without control
    	  if(movement==Direction.RIGHT) {
    		  setX(getX() + getVelocity());
    	        if (getX() +iconWidth >=canvasWidth) {
    	          setX(canvasWidth - iconWidth);
    	          movement=Direction.LEFT;
    	        }
    	  }
    	  else if(movement==Direction.LEFT) {
    		  setX(getX() - getVelocity());
    	        if (getX() <0) {
    	          setX(0);
    	          movement=Direction.RIGHT;
    	        }
    	  }
    	  setDirection(movement);
      }
    }
    
            @Override
    public void setImage() {
        switch (getDirection()) {
        case Direction.UP:
            currentImage = 0;
            break;
            
            case Direction.DOWN:
            	currentImage = 1;
            	break;
            	
            	default:
            		break;
            		
            		
        }}
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    	if (e.getKeyCode() != KeyEvent.VK_TAB) {
	  setDirection(Direction.NONE);
	}
	}
    public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			vertical=-getVelocity();
				setDirection(Direction.UP);
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			vertical=getVelocity();
				setDirection(Direction.DOWN);
		}
		}
	}
