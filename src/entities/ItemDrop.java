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
import playerEngine.CharacterStatsManager;

/**
 *
 * @author The Ninetail
 */
public class ItemDrop extends Entity {

    private Image image;
    private Equipment equipment;

    /**
     * Crée le drop d'item
     * @param x la position en x du drop d'item
     * @param y la position en y du drop d'item
     * @param equipment L'item qui est à terre
     */
    public ItemDrop(int x, int y, Equipment equipment) {
        this.x = x;
        this.y = y;
        this.dead = false;
        this.hitBox = new Box(x, y, 32, 32);
        try {
            this.image = ResManager.getInstance().getImage("drop");
        } catch (SlickException ex) {
        }
        this.equipment = equipment;
    }

    /**
     * la mise à jour du drop
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
    }

    /**
     * Permet de desssiner le drop
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawImage(image, x, y);
    }

    /**
     * Permet de rammasser l'item
     */
    public void pickUp() {
        try {
            CharacterStatsManager.getInstance().addItem(equipment);
            this.dead = true;
        } catch (SlickException ex) {
            Logger.getLogger(ItemDrop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
