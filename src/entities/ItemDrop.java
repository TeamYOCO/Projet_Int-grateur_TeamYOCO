/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import items.Equipment;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public ItemDrop(int x, int y, Equipment equipment) {
        this.x = x;
        this.y = y;
        try {
            this.image = ResManager.getInstance().getImage("drop");
        } catch (SlickException ex) {
            Logger.getLogger(ItemDrop.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.equipment = equipment;
    }
    
    
  

    @Override
    public void update(int delta) {
    }

    @Override
    public void render(Graphics g) throws SlickException {
    }
    
    
}
