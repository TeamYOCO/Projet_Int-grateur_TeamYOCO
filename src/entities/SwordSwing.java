/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author 1455367
 */
public class SwordSwing extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;

    /**
     * Crée le coup d"épée
     * @param x la position en x du coup d'épée
     * @param y la position en y du coup d'épée
     * @param direction la direction du coup d'épée
     * @param lifeSpam la durée du coup d'épée
     * @throws SlickException
     */
    public SwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        SpriteSheet anim = ResManager.getInstance().getSpriteSheet("sword_sheet_128");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.direction = direction;
        switch(direction){
            case 0: xOff = 48; yOff = 27; break;
            case 1: xOff = 11; yOff = 50; break;
            case 2: xOff = 48; yOff = 82; break;
            case 3: xOff = 85; yOff = 45; break;
        }
        this.hitBox = new Box(x+xOff, y+yOff, 0, 0);
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3];
        this.animation = loadAnimation(anim, 1, 6, direction);
        this.lifeSpam = lifeSpam;
    }

    /**
     * Permet de desssiner le coup d'épée
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
//        hitBox.render(g);
    }

    /**
     * Ce qui permet au coup d'épée de bouger
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        super.update(delta); 
        if (lifeSpam <= 400){
            this.hitBox = new Box(x+xOff, y+yOff, 32, 32);
        }
    }

    /**
     * Calcul le dégat physique du coup d'épée
     * @return le dégat physique du coup d'épée
     */
    @Override
    public int getDamagePhysical() {
        return damagePhysical;
    }

    /**
     * Retourne la direction du coup d'épée
     * @return un int de la direction du coup d'épée
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /**
     * Calcul le dégat magique du coup d'épée
     * @return le dégat magique du coup d'épée
     */
    @Override
    public int getDamageSpecial() {
        return 0;
    }
}
