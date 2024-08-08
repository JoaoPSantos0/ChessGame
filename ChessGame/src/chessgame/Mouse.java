
package chessgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Mouse extends MouseAdapter {
    
    public int x, y;
    public boolean pressed;
    
    @Override
    public void mousePressed(MouseEvent e){//Para quando o mouse for pressionado;
        pressed = true;
    }
    
    @Override
    public void mouseReleased(MouseEvent e){//Para quando o mouse parar de pressionar;
        pressed = false;
    }
    
    @Override
    public void mouseDragged(MouseEvent e){//Para o mouse arrastar um obj;
        x = e.getX();
        y = e.getY();
    }
    
    @Override
    public void mouseMoved(MouseEvent e){//Para o mouse se mover;
        x = e.getX();
        y = e.getY();
    }
}
