/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Color;
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

    /**
     *
     * @param x1
     * @param y1
     * @param width
     * @param height
     */
    public Box(float x1, float y1, float width, float height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x1 + width;
        this.y2 = y1 +height;
    }
    
    /**
     *
     * @param g
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException{
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillRect(x1, y1, width, height);
    }

    /**
     *
     * @return
     */
    public float getX1() {
        return x1;
    }

    /**
     *
     * @return
     */
    public float getY1() {
        return y1;
    }

    /**
     *
     * @return
     */
    public float getX2() {
        return x2;
    }

    /**
     *
     * @return
     */
    public float getY2() {
        return y2;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setPos(float x, float y){
        x1 = x;
        y1 = y;
        x2 = x+width;
        y2 = y+height;
    }
    
    /**
     *
     * @param box
     * @return
     */
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

}
