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
    /**
     * Crée la potion
     * @param x position x de la potion
     * @param y position y de la potion
     * @param equipment il a besoin d'être là pour le constructeur
     * @throws SlickException 
     */
    public PotionDrop(float x, float y, Equipment equipment) throws SlickException {
        super(x, y, null);
        this.image = ResManager.getInstance().getImage("potion");
    }

    /**
     * Prendre les potions a terre
     */
    @Override
    public void pickUp() {
        try {
            CharacterStatsManager.getInstance().pickUpPotion();
            this.dead = true;
        } catch (SlickException ex) {}
    }

    /**
     * Permet de desssiner la potion
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g);
    }
    
    
    
    
}
