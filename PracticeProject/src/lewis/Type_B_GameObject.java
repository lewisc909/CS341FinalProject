package lewis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Type_B_GameObject extends GameObject implements KeyListener {
	
	private int movement=Direction.RIGHT;
	
	//movement variable up and down
	private int horizontal=1;
	private int vertical=1;

    public Type_B_GameObject(int x, int y) {
        super(x, y);
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_B__Up.png"));
        imageList.add(new ImageIcon("images/Type_B_Down.png"));
        imageList.add(new ImageIcon("images/Type_B_Left.png"));
        imageList.add(new ImageIcon("images/Type_B_Right.png"));
       
       
        
        setDirection(Direction.RIGHT);
    }
    
    @Override
    public void move(Canvas c) {
    	Icon icon = getCurrentImage();

    	int  iconHeight   = icon.getIconHeight();
        int  iconWidth    = icon.getIconWidth();
        int  canvasWidth  = (int)c.getSize().getWidth();
        int  canvasHeight = (int)c.getSize().getHeight();

        // move if selected
        if(isSelected()) {
        	
        	switch (getDirection()) {
        	//moves up, down, left and right
        	case Direction.UP:
                setY(getY() - getVelocity());
                if (getY() < 0) {
				  setY(0);
				}
                
                break;
           
              case Direction.DOWN:
                setY(getY() +getVelocity());
                if (getY() + iconHeight > canvasHeight) {
                	setY(canvasHeight - iconHeight);
                }
               
                break;
                
              case Direction.LEFT:
                setX(getX() - getVelocity());
                if (getX() < 0) {
                					  setX(0);
                }
         
                break;
                
              case Direction.RIGHT:
                setX(getX() + getVelocity());
                if (getX() + iconWidth > canvasWidth) {
					setX(canvasWidth - iconWidth);
				}
           
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

    

    public void setImage() {
    	switch (getDirection()) {
	      case Direction.NONE:
	        break;
	      case Direction.UP:
	        currentImage = 0;
	        break;
	      case Direction.DOWN:
	        currentImage = 1;
	        break;
	      case Direction.LEFT:
	        currentImage =3 ;
	        break;
	      case Direction.RIGHT:
	        currentImage = 2;
	        break;
	    }
	  }
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() != KeyEvent.VK_TAB) {
    	  horizontal=0;
    	  vertical=0;
        setDirection(Direction.NONE);
      }
    }

    public void keyPressed(KeyEvent e) {
   
      if (e.getKeyCode() == KeyEvent.VK_UP) {
    	  vertical=-getVelocity();
    	  horizontal=0;
        setDirection(Direction.UP);
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    	  vertical=getVelocity();
    	  horizontal=0;
        setDirection(Direction.DOWN);
      
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    	  horizontal=-getVelocity();
		  vertical=0;
		setDirection(Direction.LEFT);
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		  horizontal=getVelocity();
		  vertical=0;
		  setDirection(Direction.RIGHT);
	  }
    }

  
}
