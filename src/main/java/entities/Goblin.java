package main.java.entities;

import java.awt.Graphics; 

public class Goblin extends Monster {
	
	int _health = DEFAULT_HEALTH;
	int _speed = DEFAULT_SPEED;
	int _ticks = 0;
	private int _xDistance,_yDistance;
	private int _cannonX = 450, _cannonY = 600;  //coordinates of the end point
	private int _x, _y;
	private double _degrees;
	private double _radians;
	private int _currentDistance = 0;
	private int _currentX, _currentY;
	
	
	public Goblin(int x, int y){
		_alive = true;
		_x = x;
		_y = y;
		_xDistance = Math.abs(_x - _cannonX);
		_yDistance = Math.abs(_y - _cannonY);
	}
	
	@Override
	public void tick(){
		if(_ticks == 30){
			_ticks = 0;
		}
		_ticks += 1;
	}
	
	@Override
	public void render(Graphics g){
		if(_alive){
			_xDistance = Math.abs(_x - _cannonX);
			_yDistance = Math.abs(_y - _cannonY);
			try{
				_degrees = Math.toDegrees(Math.atan2(_yDistance,_xDistance));
				_radians = Math.toRadians(_degrees);
			}
			catch(ArithmeticException ae){
				_degrees = 90;
			}
			int resultY, resultX;
			resultY = (int) ((Math.sin(_radians) * _currentDistance));
			resultX = (int) ((Math.cos(_radians) * _currentDistance));
			_currentDistance += 1;
			if(_x > _cannonX){
				_currentX = _x - resultX;
				_currentY = _y + resultY;
				g.drawLine(_x - resultX + 25,_y + resultY + 25,_cannonX,_cannonY);
				g.drawImage(_goblin,_currentX,_currentY,null);
			}
			else{
				_currentY = _y + resultY;
				_currentX = _x + resultX;
				g.drawLine(_x + resultX + 25,_y + resultY + 25,_cannonX,_cannonY);
				g.drawImage(_goblin,_currentX,_currentY,null);
			}
//			g.drawImage(_goblin,_currentX + resultX,_currentY + resultY,null);

			if(_y + resultY >= 525){
				_alive = false;
				_attacked = true;
			}
		}

	}
	
	
}
