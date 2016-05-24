/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author The Ninetail
 */
public class NPC extends Entity {

    private NpcType type;
    private String text;
    private Animation moveAnimations[];

    /**
     *
     * @param x
     * @param y
     * @param text
     * @param type
     * @param spriteType
     */
    public NPC(int x, int y, String text, NpcType type, int spriteType) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.moveAnimations = new Animation[8];
        SpriteSheet sheet = null;
        try {
            switch (spriteType) {
                case 0: sheet = ResManager.getInstance().getSpriteSheet("princess"); break;
                case 1: sheet = ResManager.getInstance().getSpriteSheet("Npc1"); break;
                case 2: sheet = ResManager.getInstance().getSpriteSheet("Npc2"); break;
                case 3: sheet = ResManager.getInstance().getSpriteSheet("Npc3"); break;
                case 4: sheet = ResManager.getInstance().getSpriteSheet("Npc4"); break;
                case 5: sheet = ResManager.getInstance().getSpriteSheet("Npc5"); break;
            }
        } catch (SlickException ex) {
            Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(sheet, 0, 1, i);
            this.moveAnimations[i + 4] = loadAnimation(sheet, 1, 9, i);
        }
    }

    /**
     *
     * @param delta
     */
    @Override
    public void update(int delta) {
        
    }

    /**
     *
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        
    }

}
