/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Seb
 */
public abstract class Mob extends Entity{
    
    protected int hitpoints;

    public Mob(Image image, Rectangle hitBox, Point coords) {
        super(image, hitBox, coords);
    }
    
    
}
