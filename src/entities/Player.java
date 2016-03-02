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
    private int speed = 1;
    private boolean moving;
    private MiniMap map;

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
        SpriteSheet spriteSheet = new SpriteSheet("res/maps/New folder/LPC Base Assets/sprites/people/male_walkcycle.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32, y - 60);
    }

    // Update la position du joueur
    @Override
    public void update(int delta) {
        if (moving) {
            switch (direction){
                case 0:
                    this.y = this.y - .2f * delta;
                    break;
                case 1:
                    this.x = this.x - .2f * delta;
                    break;
                case 2:
                    this.y = this.y + .2f * delta;
                    break;
                case 3:
                    this.x = this.x + .2f * delta;
                    break;
            }
        }
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

}
