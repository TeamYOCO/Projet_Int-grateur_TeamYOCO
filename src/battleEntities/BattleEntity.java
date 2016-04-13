/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleEntities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import shionn.slick.animation.PathAnimation;

/**
 *
 * @author Seb
 */
public abstract class BattleEntity {
    
    protected PathAnimation waitAnimation;
    protected Image image;
    protected float x, y;
    
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
    
    // Méthode qui permet de dessiner l'élément
    public abstract void render(Graphics g) throws SlickException;
}
