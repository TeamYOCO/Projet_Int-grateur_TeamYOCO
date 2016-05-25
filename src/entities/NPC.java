/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import ca.qc.bdeb.info204.Game;
import static ca.qc.bdeb.info204.Game.DIALOG;
import static ca.qc.bdeb.info204.Game.SHOP;
import gameEngine.ResManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import statesOfGame.Dialog;

/**
 *
 * @author The Ninetail
 */
public class NPC extends Entity {
    
    private NpcType type;
    private String text;
    private Animation moveAnimations[];
    private int direction = 2;

    /**
     * Crée un personnage non joueur
     * @param x la position en x du PNJ
     * @param y la position en y du PNJ
     * @param text le texte que le PNJ va dire
     * @param type le type de PNJ
     * @param spriteType l'image du PNJ
     */
    public NPC(int x, int y, String text, NpcType type, int spriteType) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.moveAnimations = new Animation[8];
        this.xOff = -32;
        this.yOff = -64;
        this.type = type;
        this.hitBox = new Box(x - xOff, y - yOff, 64, 64);
        SpriteSheet sheet = null;
        try {
            switch (spriteType) {
                case 0:
                    sheet = ResManager.getInstance().getSpriteSheet("princess");
                    break;
                case 1:
                    sheet = ResManager.getInstance().getSpriteSheet("Npc1");
                    break;
                case 2:
                    sheet = ResManager.getInstance().getSpriteSheet("Npc2");
                    break;
                case 3:
                    sheet = ResManager.getInstance().getSpriteSheet("Npc3");
                    break;
                case 4:
                    sheet = ResManager.getInstance().getSpriteSheet("Npc4");
                    break;
                case 5:
                    sheet = ResManager.getInstance().getSpriteSheet("Npc5");
                    break;
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
     * Permet la mise à jour du PNJ
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        
    }

    /**
     * Dessine le PNJ
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(moveAnimations[direction], x, y);
    }
    /**
     * Permet de mettre de nouvelles coordonnées au PNJ
     * @param x La nouvelle position en x
     * @param y La nouvelle position en y
     */
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
        hitBox.setPos(x, y);
    }
    /**
     * Permet d'interragir avec un PNJ
     * @param sbg Représente les différent états d'affichage
     */
    public void interact(StateBasedGame sbg) {
        switch (type) {
            case VENDOR:
                sbg.enterState(Game.SHOP);
                break;
            case INTERACT:
                Dialog.setText(text);
                sbg.enterState(Game.DIALOG);
        }
    }
    
}
