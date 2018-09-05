package main.java.world;

import java.awt.Graphics;
import java.util.ArrayList;

import main.java.code.Checker;
import main.java.entities.Monster;
import main.java.entities.Player;
import main.java.system.Handler;


public class Battlefield {
	
	ArrayList<Monster> _enemies = new ArrayList<Monster>();
	Handler _h;
	Checker _c;
	Player _p;
	
	public Battlefield(Handler h){
		_h = h;
		_p = new Player(h);
		_c = new Checker();
	}
	
	public void tick(){
		if(_enemies.size() != 0 || _enemies != null){
			_p._beginFire = true;
			//System.out.println(_p._beginFire);
		}
//		int count = 0; //the count of enemies on the field remaining
//		for(int i = 0; i < _enemies.size();i++){
//			
//			if(_enemies.get(i)._alive){
//				
//			}
//
//		}
		_p.tick();
		_c.tick();
		if(_p._fired){ //checks if cannonball is outside
			checkCollision();
		}
		for(int i = 0; i < _enemies.size();i++){
			if(_enemies.get(i)._attacked){ //Remove instances that have done their job
				_enemies.remove(i);
			}
			_enemies.get(i).tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < _enemies.size();i++){
			_enemies.get(i).render(g);
		}
		_p.render(g);
	}
	
	public void addEnemy(Monster m){
		_enemies.add(m);
	}
	
	public void checkCollision(){
		int cX = _p._cb.getX();
		int cY = _p._cb.getY();
		Monster mon = _c.checkNearestEnemy(_enemies);
		
	}

	public ArrayList<Monster> getMonsters(){
		return _enemies;
	}
}
