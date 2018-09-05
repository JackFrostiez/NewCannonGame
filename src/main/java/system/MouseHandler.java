package main.java.system;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

	public boolean[] mouseKeys;
	public boolean _mousePressed;
	
	public MouseHandler(){
		mouseKeys = new boolean[1000];
	}
	
	public void tick(){
		_mousePressed = mouseKeys[MouseEvent.MOUSE_PRESSED];
	
		
	}
	
	public void render(){
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse has entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("mouse has left");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseKeys[arg0.getID()] = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseKeys[501] = false;
		/*Note: When button releases, 
		 * the mouseEvent ID becomes 502 and not 501 when released*/
	}

}
