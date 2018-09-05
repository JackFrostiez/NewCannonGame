package main.java.code;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.java.asset.Assets;
import main.java.system.Handler;
import main.java.world.World;

public class Game implements Runnable {
	
	private Graphics _g;
	private Thread _t;
	private boolean _running;
	private BufferStrategy _bs;
	private int _length, _width;
	private World _w;
	private Handler _h;
	
	public Game(int length, int width){
		_h = new Handler(length, width);
		_length = length;
		_width = width;
	}

	@Override
	public void run() {
		initiate();
		loop();
		stop();
		
	}
	
	public void initiate(){
		Assets.loadFiles();
		_w = new World(_h);
	}
	
	public void loop(){
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(_running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				everySecondAction();
				ticks = 0;
				timer = 0;
			}
		}
	}
	
	public void everySecondAction(){
		//System.out.println("still running...");
	}
	
	public void tick(){
		_h.getMouseHandler().tick();
		_w.tick();
	}
	
	public void render(){
		_bs = _h.getView().getCanvas().getBufferStrategy();
		if(_bs == null){
			 _h.getView().getCanvas().createBufferStrategy(3);
			return;
		}
		_g = _bs.getDrawGraphics();
		//Clear Screen
		_g.clearRect(0, 0, _length, _width);
		//Draw Start
		_g.setColor(Color.RED);
		_w.render(_g);
		
		//Draw End
		_g.dispose();
		_bs.show();
	}
	
	public synchronized void start() {
		if(_running){
			return;
		}
		_running = true;
		_t = new Thread(this);
		_t.start();
	}
	
	public synchronized void stop(){
		if(_running){
			return;
		}
		_running = false;
		try {
			_t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
