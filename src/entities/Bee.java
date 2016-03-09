/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1485246
 */
public class Bee extends Mob {

    
    @Override
    public void update(int delta) {
    }

    @Override
    public void init() throws SlickException {
        moving = false;
        moveAnimations = new Animation[4];
        this.x = 100;
        this.y = 100;
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/bee.png", 64, 64);
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 3, i);
        }
        
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(moveAnimations[2], x, y);
    }
    
    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
    
    
}