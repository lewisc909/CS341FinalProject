package lewis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject implements KeyListener {

	private int movement=Direction.RIGHT;
	
  public Type_C_GameObject(int x, int y) {
    super(x, y);
    setDirection(Direction.NONE);
    
    imageList = new LinkedList<Icon>();
    imageList.add(new ImageIcon("images/Type_C_Right.png"));
    imageList.add(new ImageIcon("images/Type_C_Left.png"));
    
  }

  public void move(Canvas c) {
    Icon icon = getCurrentImage();

    int  iconWidth    = icon.getIconWidth();
    int  canvasWidth  = (int)c.getSize().getWidth();
    
    
    //when selected, MOVE PURPLE GAME OBJECT
    if(isSelected()) {
    	
    switch (getDirection()) {
    //only moves left and right
      case Direction.LEFT:
        setX(getX() - getVelocity());
        if (getX() < 0) setX(0);
        break;
        
      case Direction.RIGHT:
        setX(getX() + getVelocity());
        if (getX() +iconWidth >canvasWidth) {
          setX(canvasWidth - iconWidth);
        }
        break;
	default:
		break;
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
  //SPECIFY THE IMAGE TO DISPLAY
  //   USED FOR ANIMATION
  public void setImage() {
	    switch (getDirection()) {
	    
	      case Direction.LEFT:
	        currentImage = 0;
	        break;
	      case Direction.RIGHT:
	        currentImage = 1;
	        break;
	        
	      case Direction.NONE:
	    	  default:
		        break;
	    }
	  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() != KeyEvent.VK_TAB) {
      setDirection(Direction.NONE);
    }
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      setDirection(Direction.RIGHT);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      setDirection(Direction.LEFT);
    }
  }

}
