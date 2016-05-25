/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Tomas
 */
public class Boss extends Mob implements BadEntity {

    private float speed = 0.15f;
    private MiniMap map;
    private Player player;
    private int aggroRange = 200;
    private boolean aggro = false;
    private int attCounter = 0;
    private int attCooldown = 0;
    private boolean attaquing = false;
    private Animation[] attackAnimation;

    /**
     *  Crée le boss
     * @param x La position en x de départ du boss
     * @param yLa position en y de départ du boss
     * @param player Le joueur
     * @param map La carte
     * @param list la liste d'entité de la carte
     */
    public Boss(int x, int y, Player player, MiniMap map, ArrayList<Entity> list) throws SlickException {
        this.moving = false;
        this.moveAnimations = new Animation[8];
        this.attackAnimation = new Animation[4];
        this.list = list;
        this.x = x;
        this.y = y;
        this.map = map;
        this.xOff = -16;
        this.yOff = -60;
        this.player = player;
        this.hitBox = new Box(x + xOff, y + yOff, 32, 60);
        this.hitpoints = 500;
        this.damagePhysical = 25;
        this.damageSpecial = 0;
        this.defence = 0;
        this.specialDefence = 0;
        this.money = 15;
        this.exp = 100;
        SpriteSheet moveSpriteSheet = ResManager.getInstance().getSpriteSheet("boss_walk");
        SpriteSheet attackSpriteSheet = ResManager.getInstance().getSpriteSheet("boss_swing");
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 1, i);
            this.moveAnimations[i + 4] = loadAnimation(moveSpriteSheet, 1, 9, i);
            this.attackAnimation[i] = loadAnimation(attackSpriteSheet, 1, 6, i);
        }
    }

    /**
     * Permet au boss de se déplacer
     * @param delta La durée d'une ittération
     */
    @Override
    public void update(int delta) {
        if (player.getX() < this.x + aggroRange && player.getX() > this.x - aggroRange && player.getY() < this.y + aggroRange && player.getY() > this.y - aggroRange) {
            moving = true;
            aggro = true;
        }
        if (moving && knockbackTimer <= 0 && !attaquing) {
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
            this.attack();
        } else if (knockbackTimer > 0) {
            moving = true;
            float tempSpeed = speed;
            speed = 0.5f;
            if (!map.isCollision(futurX(-delta), futurY(-delta))) {
                this.x = futurX(-delta);
                this.y = futurY(-delta);
            }
            speed = tempSpeed;
            knockbackTimer -= delta;

        } else if (attCounter > 0 && attaquing) {
            attCounter -= delta;
            System.out.println("attCounter: " + attCounter);
            
            if (attCounter <= 0) {
                attaquing = false;
            }
        }
        if (attCooldown > 0) {
            
            attCooldown -= delta;
            System.out.println("attCooldown: " + attCooldown);
        }
        hitBox.setPos(x + xOff, y + yOff);
    }

    /**
     * Calcul la position en x après l'ittération
     * @param delta La durée d'une ittération
     * @return la position en x après l'ittération
     */
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

    /**
     * Calcul la position en y après l'ittération
     * @param delta La durée d'une ittération
     * @return la position en y après l'ittération
     */
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

   /**
     * Dessine le boss pour la première fois
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        if (isHitable() && !attaquing) {
            g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 64);
        } else if (attaquing) {
            System.out.println("il attaque");
            g.drawAnimation(attackAnimation[direction], x - 96, y - 128);
        } else {
            g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 64, Color.red);
        }
        g.setColor(Color.red);
        g.drawString("" + this.hitpoints, x - 20, y - 80);
        hitBox.render(g);
        g.setColor(Color.red);
        g.fillOval(x, y, 2, 2);
    }
    
    /**
     * L'animation d'attaque du boss
     */
    public void attack() {
        boolean isInRange = false;
        switch (direction) {
            case 0:
                if (player.getX() > this.x - 32 && player.getX() < this.x + 32 && player.getY() > this.y - 128 && player.getY() < this.y - 64) {
                    isInRange = true;
                }
                break;
            case 1:
                if (player.getX() > this.x - 96 && player.getX() < this.x - 32 && player.getY() > this.y - 64 && player.getY() < this.y) {
                    isInRange = true;
                }
                break;
            case 2:
                if (player.getX() > this.x - 32 && player.getX() < this.x + 32 && player.getY() > this.y && player.getY() < this.y + 64) {
                    isInRange = true;
                }
                break;
            case 3:
                if (player.getX() > this.x + 96 && player.getX() < this.x + 32 && player.getY() > this.y - 64 && player.getY() < this.y) {
                    isInRange = true;
                }
                break;
        }
        if (isInRange && attCooldown <= 0) {
            attaquing = true;
            attCooldown = 1000;
            attCounter = 500;
            for (int i = 0; i < attackAnimation.length; i++) {
                attackAnimation[i].restart();
            }
            switch (direction) {

            }
        }
    }

}
