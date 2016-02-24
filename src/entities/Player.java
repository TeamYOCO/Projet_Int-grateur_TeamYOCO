/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.awt.Rectangle;
import playerEngine.PlayerEngine;

/**
 *
 * @author Seb
 */
public class Player extends Mob{
    
    protected PlayerEngine engine;
    
    public Player(Image image, Rectangle hitBox) {
        super(image, hitBox);
    }
    
}
