/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.PlayerEngine;

/**
 *
 * @author Seb
 */
public class Player extends Mob{
    
    private PlayerEngine engine;
    private int direction = 4;
    private int speed = 1;
    
    

    public Player(Point coords,int direction) throws SlickException {
        super(coords, direction);
        this.hitBox=new Rectangle(32,32);
        SpriteSheet spriteSheet = new SpriteSheet("res/textures/sprites/people/soldier_altcolor.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
    }
     private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}
    
    @Override
    public void update() {
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
}
