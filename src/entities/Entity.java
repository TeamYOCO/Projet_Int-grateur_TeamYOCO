/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Seb
 */
public abstract class Entity {
    protected Image image;
    protected Rectangle hitBox;

    public Entity(Image image, Rectangle hitBox) {
        this.image = image;
        this.hitBox = hitBox;
    }
    
}
