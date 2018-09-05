package main.java.graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class View {
	
	JFrame _frame;
	Canvas _canvas;
	int _length, _width;
	Dimension _d = new Dimension(_length,_width);
	
	public View(int length, int width){
		_length = length;
		_width = width;
		startUp();
	}
	
	public void startUp(){
		_frame = new JFrame("Cannon Game");
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(_length,_width);
		_frame.setLocationRelativeTo(null);
		_canvas = new Canvas();
		_frame.add(_canvas);
		_frame.setResizable(false);
		
	}
	
	public JFrame getJFrame(){
		return _frame;
	}
	
	public Canvas getCanvas(){
		return _canvas;
	}
}
