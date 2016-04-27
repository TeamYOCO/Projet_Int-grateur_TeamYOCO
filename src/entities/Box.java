/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author The Ninetail
 */
public class Box {
    private float x1,x2,y1,y2;
    private float width,height;

    public Box(float x1, float y1, float width, float height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x1 + width;
        this.y2 = y1 +height;
    }
    
    public void render(Graphics g) throws SlickException{
        g.fillRect(x1, y1, width, height);
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }
    
    public void setPos(float x, float y){
        x1 = x;
        y1 = y;
        x2 = x+width;
        y2 = y+height;
    }
    
    public boolean collision(Box box){
        if (y2 < box.getY1())
            return false;
        if (x2 < box.getX1())
            return false;
        if (y1 > box.getY2())
            return false;
        if (x1 > box.getX2())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "x1: "+x1+" width: "+width+" y1: "+y1+" height: "+height;
    }
    
    
}
