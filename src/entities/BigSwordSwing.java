/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class BigSwordSwing extends SwordSwing implements FriendlyEntity {

    /**
     * Crée une épée
     * @param x Position en x du joueur
     * @param y Position en y du joueur
     * @param direction La direction du joueur
     * @param lifespam La durée de vie de l'épée
     * @param map La map où l'épée va être
     * @throws SlickException
     */
    public BigSwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        super(x, y, direction, lifeSpam);
        SpriteSheet sheet = ResManager.getInstance().getSpriteSheet("longsword");
        this.animation = loadAnimation(sheet, 1, 6, direction);
        switch (direction) {
            case 0:
                xOff = -24;
                yOff = -72;
                break;
            case 1:
                xOff = -64;
                yOff = -40;
                break;
            case 2:
                xOff = -24;
                yOff = -16;
                break;
            case 3:
                xOff = 16;
                yOff = -40;
                break;
        }
        this.hitBox = new Box(x, y, 0, 0);
    }

    /**
     * Permet de desssiner l'épée
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x - 98, y - 124);
//        hitBox.render(g);
    }

    /**
     * Ce qui permet à l'épée de bouger
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        super.update(delta);
        if (lifeSpam <= 400) {
            this.hitBox = new Box(x + xOff, y + yOff, 48, 48);
        }
    }
}
