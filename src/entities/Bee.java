/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import maps.MiniMap;
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
    private int comptDirection = 0;
    private MiniMap map;
    private boolean rndMove;

    public Bee(MiniMap map) {
        this.moveAnimations = new Animation[4];
        this.map = map;
        this.direction = 0;
    }

    @Override
    public void update(int delta) {
        this.x = futurX(delta);
        this.y = futurY(delta);
    }

    @Override
    public void init() throws SlickException {
        this.x = 564;
        this.y = 154;
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/bee.png", 32, 32);
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 3, i);
        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(moveAnimations[direction], x, y);
    }

    // Teste la position pour se déplacer en X
    private float futurX(int delta) {
        float futurX = this.x;
        if (rndMove) {
            switch (this.direction) {
                case 1:
                    futurX = this.x - speed * delta;
                    comptDirection += 1;
                    if (comptDirection % 300 == 0) {
                        this.direction = 2;
                    }
                    break;
                case 3:
                    futurX = this.x + speed * delta;
                    comptDirection += 1;
                    if (comptDirection % 300 == 0) {
                        this.direction = 0;
                    }
                    break;
            }
        }
        return futurX;
    }

    // Teste la position pour se déplacer en Y
    private float futurY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0:
                futurY = this.y + speed * delta;
                comptDirection += 1;
                if (comptDirection % 300 == 0) {
                    this.direction = 1;
                }
                break;
            case 2:
                futurY = this.y - speed * delta;
                comptDirection += 1;
                if (comptDirection % 300 == 0) {
                    this.direction = 3;
                }
                break;
        }
        return futurY;
    }

}
