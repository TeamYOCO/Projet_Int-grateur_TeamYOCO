/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.awt.Rectangle;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public abstract class Equipment {
    
    protected float x,y;
    protected MiniMap map;
    protected Rectangle hitBox;
    
    public abstract void update(int delta);

    // Méthode init de la classe
    public abstract void init() throws SlickException;

    // Méthode qui permet de dessiner l'élément
    public abstract void render(Graphics g) throws SlickException;

    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
    
    public boolean isCollision(Rectangle rectObjet){
        boolean collision = false;
        if(hitBox.getBounds().intersects(rectObjet)) collision = true;
        return collision;
    }
    
    public Rectangle getHitBox() {
        return hitBox;
    }
}
