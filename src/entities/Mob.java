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
public abstract class Mob extends Entity{
    
    protected int hitpoints;
    protected int direction;

    public Mob(Point coords) {
        super(coords);
    }
    
    
}
