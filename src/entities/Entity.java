/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Point;
import java.awt.Rectangle;
import org.newdawn.slick.Animation;

/**
 *
 * @author Seb
 */
public abstract class Entity {

    protected Animation[] animations = new Animation[8];
    protected Rectangle hitBox;
    protected Point coords;

    public Entity(Point coords) {
        this.coords = coords;
    }

    public abstract void update();

    public Point getCoords() {
        return coords;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
