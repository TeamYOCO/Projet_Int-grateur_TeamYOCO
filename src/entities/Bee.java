/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1485246
 */
public class Bee extends Mob implements BadEntity {

    private float speed = 0.1f;
    private MiniMap map;
    private Player player;
    private int aggroRange = 200;
    private boolean aggro = false;

    public Bee(int x, int y, Player player, MiniMap map, ArrayList<Entity> list) {
        this.moving = false;
        moveAnimations = new Animation[4];
        this.list = list;
        this.x = x;
        this.y = y;
        this.map = map;
        this.xOff = -16;
        this.yOff = -32;
        this.player = player;
        this.hitBox = new Box(x + xOff, y + yOff, 32, 32);
        this.hitpoints = 50;
        this.damage = 5;
        this.xpGiven = 10;
        SpriteSheet moveSpriteSheet = null;
        try {
            moveSpriteSheet = ResManager.getInstance().getSpriteSheet("bee");
        } catch (SlickException ex) {
        }
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 3, i);
        }
    }

    @Override
    public void update(int delta) {
        if (player.getX()<this.x+aggroRange && player.getX()>this.x-aggroRange && player.getY()<this.y+aggroRange && player.getY()>this.y-aggroRange){
            moving = true;
            aggro = true;
        }
        if (moving && knockbackTimer <= 0) {
                int relativeX = Math.abs((int) this.x - (int) player.getX()), relativeY = Math.abs((int) this.y - (int) player.getY());
                if (this.x < player.getX() && relativeX > relativeY) {
                    this.direction = 3;
                } else if (this.x > player.getX() && relativeX > relativeY) {
                    this.direction = 1;
                } else if (this.y < player.getY() && relativeY > relativeX) {
                    this.direction = 2;
                } else if (this.y > player.getY() && relativeY > relativeX) {
                    this.direction = 0;
                }
            if (!map.isCollision(futurX(delta), futurY(delta))) {
                this.x = futurX(delta);
                this.y = futurY(delta);
            }
        } else if (knockbackTimer > 0) {
            moving=true;
            float tempSpeed = speed;
            speed = 0.5f;
            if (!map.isCollision(futurX(-delta), futurY(-delta))) {
                this.x = futurX(-delta);
                this.y = futurY(-delta);
            }
            speed = tempSpeed;
            knockbackTimer -= delta;

        }
        hitBox.setPos(x + xOff, y + yOff);
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

    @Override
    public void render(Graphics g) throws SlickException {
        if (isHitable()) {
            g.drawAnimation(moveAnimations[direction], x - 16, y - 32);
        } else {
            g.drawAnimation(moveAnimations[direction], x - 16, y - 32, Color.red);
        }
        g.setColor(Color.red);
        g.drawString(""+this.hitpoints, x - 10, y - 45);
//        hitBox.render(g);
    }

}
