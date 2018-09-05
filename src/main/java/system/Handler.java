package main.java.system;

import main.java.graphics.View;

public class Handler {
	
	private KeyHandler _k; 
	private MouseHandler _m; 
	private View _v;
	
	public Handler(int length, int width){
		_k = new KeyHandler();
		_m = new MouseHandler(); 
		_v = new View(length, width);
		_v.getJFrame().addKeyListener(_k);
		_v.getCanvas().addMouseListener(_m);
	}

	public KeyHandler getKeyHandler() {
		return _k;
	}

	public MouseHandler getMouseHandler() {
		return _m;
	}
	
	public View getView(){
		return _v;
	}
	
	
	
	
}
