/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Seb
 */
public abstract class Entity {

    protected Animation[] moveAnimations = new Animation[8];
    protected Rectangle hitBox;
    protected float x, y;


    public abstract void update(int delta);


    public Rectangle getHitBox() {
        return hitBox;
    }
    
    public abstract void init() throws SlickException;
    
    public abstract void render(Graphics g) throws SlickException;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
