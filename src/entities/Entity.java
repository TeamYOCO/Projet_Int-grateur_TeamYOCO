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

/**
 *
 * @author Seb
 */
public abstract class Entity {
    protected Animation[] animations = new Animation[8];
    protected Rectangle hitBox;
    protected Point coords;

    public Entity(Rectangle hitBox, Point coords) {
        this.hitBox = hitBox;
        this.coords = coords;
    }
    
    public abstract void update();
    
}
