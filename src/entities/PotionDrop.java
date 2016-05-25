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
import org.newdawn.slick.SlickException;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author The Ninetail
 */
public class PotionDrop extends ItemDrop{
    
    public PotionDrop(float x, float y, Equipment equipment) throws SlickException {
        super(x, y, null);
        this.image = ResManager.getInstance().getImage("potion");
    }

    @Override
    public void pickUp() {
        try {
            CharacterStatsManager.getInstance().pickUpPotion();
            this.dead = true;
        } catch (SlickException ex) {}
    }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g); //To change body of generated methods, choose Tools | Templates.
        hitBox.render(g);
    }
    
    
    
    
}
