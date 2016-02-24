/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import playerEngine.PlayerEngine;

/**
 *
 * @author Seb
 */
public class Player extends Mob{
    
    private PlayerEngine engine;
    private int direction = 4;
    private int speed = 1;
    
    

    public Player(Image image, Rectangle hitBox, Point coords) {
        super(image, hitBox, coords);
    }
    
    @Override
    public void update() {
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
}
