/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import items.Equipment;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author The Ninetail
 */
public class ItemDrop extends Entity{
    
    private Image image;
    private Equipment equipment;

    public ItemDrop(int x, int y, Image image, Equipment equipment) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.equipment = equipment;
    }
    
    
  

    @Override
    public void update(int delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) throws SlickException {
    }
    
    
}
