package main.java.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.java.asset.Assets;

public class Monster extends Unit {
	
	public boolean _alive,_attacked;
	BufferedImage _goblin = Assets._goblin;
//	BufferedImage _golem = Assets._goblin;
//	BufferedImage _rogue = Assets._goblin;

	
	public Monster(){
		//Begin travel calculation
		//End travel calculation
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		//g.drawImage(_monster, _currentX, _currentY, null);
	}
}
