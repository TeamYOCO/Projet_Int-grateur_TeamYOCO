/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Rectangle;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.PlayerEngine;

/**
 *
 * @author Seb
 */
public class Player extends Mob {

    private PlayerEngine engine;
    private int direction = 2;
    private float speed = (float) 0.2;
    private boolean moving;
    private MiniMap map;
    private boolean attacking = false;
    private Animation[] attackAnimation = new Animation[4];

    public Player(MiniMap map) {
        this.map = map;
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    @Override
    public void init() throws SlickException {
        this.x = 620;
        this.y = 430;
        moving = false;
        this.hitpoints = 100;
        this.hitBox = new Rectangle(32, 32);
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/male_walkcycle.png", 64, 64);
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 1, i);
            this.moveAnimations[i+4] = loadAnimation(moveSpriteSheet, 1, 9, i);
        }
        SpriteSheet attackSpriteSheet = new SpriteSheet("res/sprites/male_slash.png", 64, 64);
        for (int i = 0; i < 4; i++) {
            attackAnimation[i] = loadAnimation(attackSpriteSheet, 1, 6, i);
        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        if (attacking) {
            g.drawAnimation(attackAnimation[direction], x - 32, y - 60);
        } else {
            g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 60);
        }
    }

    // Update la position du joueur
    @Override
    public void update(int delta) {
        if (moving /*&& !attacking*/) {
            if(!map.isCollision(futurX(x), futurY(y))){
            this.x=futurX(x);
            this.y=futurY(y);
            }else{
                System.out.println("coll");
            }
        }

    }
    
    private float futurX(float x){
        switch (direction){
                case 1:
                   return (int) (x-speed);
                case 3:
                    return (int) (x+speed);
            }
        return x;
    }
    
    private float futurY(float y){
         switch (direction){
                case 0:
                    return (int) (y-speed);
                case 2:
                    return (int) (y+speed);
            }
        return y;
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void attack(){
        attacking = true;
    }
}
