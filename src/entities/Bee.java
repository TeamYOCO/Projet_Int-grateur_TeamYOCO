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
 * @author 1485246
 */
public class Bee extends Mob {

    private final float speed = 0.2f;
    private int comptDirection = 1;

    public Bee(boolean moving, int x, int y) {
        this.moving = moving;
        direction = 0;
        moving = false;
        moveAnimations = new Animation[4];
        this.x = x;
        this.y = y;
        this.hitBox = new Box(x, y, 32, 32);
        SpriteSheet moveSpriteSheet = null;
        try {
            moveSpriteSheet = ResManager.getInstance().getSpriteSheet("bee");
        } catch (SlickException ex) {
        }
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 3, i);
            System.out.println(i+ " "+moveAnimations[i]);
        }
    }

    @Override
    public void update(int delta) {
        if (moving) {
            this.x = futurX(delta);
            this.y = futurY(delta);
        }
        hitBox.setPos(x, y);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(moveAnimations[direction], x, y);
        hitBox.render(g);
    }

    // Teste la position pour se déplacer en X
    private float futurX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1:
                futurX = this.x - speed * delta;
                comptDirection += 1;
                if (comptDirection % 10 == 0) {
                    this.direction = 2;
                }
                break;
            case 3:
                futurX = this.x + speed * delta;
                comptDirection += 1;
                if (comptDirection % 10 == 0) {
                    this.direction = 0;
                }
                break;
        }
        return futurX;
    }

    // Teste la position pour se déplacer en Y
    private float futurY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0:
                futurY = this.y - speed * delta;
                comptDirection += 1;
                if (comptDirection % 10 == 0) {
                    this.direction = 1;
                }
                break;
            case 2:
                futurY = this.y + speed * delta;
                comptDirection += 1;
                if (comptDirection % 10 == 0) {
                    this.direction = 3;
                }
                break;
        }
        return futurY;
    }

}
