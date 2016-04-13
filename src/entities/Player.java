/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Rectangle;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Classe qui contient l'affichage et le déplacement du joueur dans le overworld
 * @author Seb
 */
public class Player extends Mob {
    
    private int direction = 2;
    private float speed = 0.2f;
    private boolean moving;
    private MiniMap map;
    private ArrayList<Entity> list;

    public Player(MiniMap map, ArrayList<Entity> list) {
        this.map = map;
        this.list = list;
    }

    @Override
    public void init() throws SlickException {
        this.moveAnimations = new Animation[8];
        this.x = 620;
        this.y = 430;
        moving = false;
        this.hitpoints = 100;
        this.hitBox = new Rectangle(32, 32);
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/male_walkcycle.png", 64, 64);
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 1, i);
            this.moveAnimations[i + 4] = loadAnimation(moveSpriteSheet, 1, 9, i);
        }
//        for (int i = 0; i < moveAnimations.length; i++) {
//            moveAnimations[i].setSpeed(5);
//        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 60);
    }

    // Update la position du joueur
    @Override
    public void update(int delta) {
        if (moving) {
            if (!map.isCollision(futurX(delta), futurY(delta))) {
                this.x = futurX(delta);
                this.y = futurY(delta);
            }
        }
    }

    // Teste la position pour se déplacer en X
    private float futurX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1:
                futurX = this.x - speed * delta;
                break;
            case 3:
                futurX = this.x + speed * delta;
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
                break;
            case 2:
                futurY = this.y + speed * delta;
                break;
        }
        return futurY;
    }

    // Les méthodes suivantes sont des getters/setters
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
