package main.java.world;

import java.awt.Graphics; 
import java.util.Random;

import main.java.asset.Assets;
import main.java.entities.Goblin;
import main.java.system.Handler;

public class World {
	
	Goblin _goblin;
	Battlefield _battlefield;
	private int _ticks;
	private Random _rand;
	
	public World(Handler h){
		_rand = new Random();
		_battlefield = new Battlefield(h);
	}
	
	public void tick(){
		if(_ticks == 60){ //1 seconds spawn time
			_battlefield.addEnemy(new Goblin(_rand.nextInt(700),0));
			_ticks = 0;
		}
		_battlefield.tick();
		_ticks += 1;
	}
	
	public void render(Graphics g){
		g.drawImage(Assets._grassland,0,0,null);
		_battlefield.render(g);

	}
}
