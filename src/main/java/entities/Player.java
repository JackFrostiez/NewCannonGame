package main.java.entities;

import java.awt.Color;  
import java.awt.Graphics;
import java.awt.Point;
import main.java.system.Handler;

public class Player extends Unit{

	public static int _hp = 10;
	private Handler _h;
	private Point _p;
	private int _startX = 450, _startY = 600,  //coordinates of the starting point
			_endX, _endY;
	private int ticks = 0;									
	public boolean _fired,_cooldown,_beginFire;
	private int _xDistance,_yDistance;
	private int _y,_x;
	private double _degrees, _radians,_currentRadians;
	private int _seconds = 0;
	private int _cdTimer = 1, _cdCount = _cdTimer;
	private int _cannonballX = 0,_cannonballY = 0, _currentDistance = 0;
	public Cannonball _cb;
	
	public Player(Handler h){
		_h = h;
		_cb = new Cannonball();
	}
	
	public void tick(){
		if(ticks == 60){
			_seconds += 1;
			System.out.println(_seconds);
			if(_cooldown == true){
				_cdCount -= 1;
			}
			ticks = 0;
		}
		update();
		shooting();
		_cb.tick(_cannonballX,_cannonballY,_currentDistance);
		ticks += 1;
		
	}
	
	public void update(){
		if(_h.getView().getCanvas().getMousePosition() != null){
			_p = _h.getView().getCanvas().getMousePosition();
			_endX = (int)_p.getX();
			_endY = (int)_p.getY();
			//////Math stuff//////
			_xDistance = (_startX - _endX);
			_yDistance = (_startY - _endY);
			try{
				_degrees = Math.toDegrees(Math.atan2(_yDistance,_xDistance));
				_radians = Math.toRadians(_degrees);
			}
			catch(ArithmeticException ae){
				_degrees = 90;
			}
			_y = Math.negateExact((int) ((Math.sin(_radians) * 300)));
			_x = (int) ((Math.cos(_radians) * 300));
			//////End Math stuff//////
		}
	}
	
	public void render(Graphics g){
		//coordinates of a square centering on starting point
		//image of cannonball should be 50 x 50 i guess
		//firing range is radius of 300
		g.drawRect(425, 575, 50,50); 
		g.drawRect(150, 300, 600, 600);
		g.drawOval(150, 300, 600, 600);
		g.drawLine(150, 600, 750, 600);
		
		g.drawLine(_startX,_startY, _startX - _x, _startY + _y);
		g.setColor(Color.black);
		infoCords(g);
		
		try{
			g.drawLine(_startX,_startY, _startX - _x, _startY + _y);				
		}
		catch(NullPointerException npe){
					
		}
		if(_beginFire){
			if(_cb._fired){
				_fired = true;
				if(_currentDistance == 300){
					_currentDistance = 0;
					_cb._fired = false;
					_fired = false;
				}
				_cannonballY = (int) ((Math.sin(_currentRadians) * _currentDistance));
				_cannonballX = (int) ((Math.cos(_currentRadians) * _currentDistance));
				_currentDistance += 5;		
				_cb.render(g);
			}
		}
		else{
			_currentRadians = _radians;
		}
		
	}
	
	public void shooting(){
		if(_cdCount == 0){
			_cooldown = false;
			_cdCount = _cdTimer;
		}
		if(_h.getMouseHandler()._mousePressed == true && _cooldown == false){
			_cb._fired = true;
			System.out.println("BOOM!!!");
			_cooldown = true;
		}
	}
	
	public void infoCords(Graphics g){
		if(_cooldown){
			g.drawString("Next cannon shot in : " + _cdCount, 10, 575);
		}
		g.drawString("x is " + (_startX -_x) + " y is " + (_startY + _y) , _startX - _x, _startY + _y);
		g.drawString("x is " + _endX + " y is " + _endY , _endX, _endY);
		g.drawString("Seconds: " + _seconds, 10, 590);
		g.drawString("x1: " + 0 + " y1: " + 0, 10,605);
		g.drawString("x2: " + _endX + " y2: " + _endY, 10,620 );
		g.drawString("x diff : " + _xDistance + " y diff: " + _yDistance, 10, 635);
		g.drawString("x : " + _x + " y :" + _y, 10, 650);
		g.drawString("Degrees : " + _degrees, 10, 665);
	}	
}


