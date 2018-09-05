package main.java.entities;

import java.awt.Graphics;

import main.java.asset.Assets;

public class Cannonball {
	
	private int _x,_y,_distance;
	public boolean _fired;
	
	public Cannonball(){
		
	}
	
	public void tick(int x, int y, int distance){
		_x = x;
		_y = y;
		_distance = distance;
	}
	
	public void render(Graphics g){
		g.drawImage(Assets._cannonBall,425 - _x,575 - _y,50,50,null);
		g.drawRect(425 - _x,575 - _y,50,50);
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
}
