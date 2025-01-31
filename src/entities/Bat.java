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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Tomas
 */
public class Bat extends Mob implements BadEntity {

    private float speed = 0.15f;
    private MiniMap map;
    private Player player;
    private int aggroRange = 400;
    private boolean aggro = false;

    /**
     *  Crée une chauve-souris
     * @param x La position en x de départ de la chauve-souris
     * @param yLa position en y de départ de la chauve-souris
     * @param player Le joueur
     * @param map La carte
     * @param list la liste d'entité de la carte
     */
    public Bat(int x, int y, Player player, MiniMap map, ArrayList<Entity> list) {
        this.moveAnimations = new Animation[4];
        this.moving = false;
        this.list = list;
        this.x = x;
        this.y = y;
        this.map = map;
        this.xOff = -16;
        this.yOff = -32;
        this.player = player;
        this.hitBox = new Box(x + xOff, y + yOff, 64, 64);
        this.hitpoints = 40;
        this.damagePhysical = 10;
        this.damageSpecial = 0;
        this.defence = 0;
        this.specialDefence = 0;
        this.exp = 15;
        this.money = 10;
        SpriteSheet moveSpriteSheet = null;
        try {
            moveSpriteSheet = ResManager.getInstance().getSpriteSheet("bat");
        } catch (SlickException ex) {
        }
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 3, i);
        }
    }

    /**
     * Permet à la chauve-souris de se déplacer
     * @param delta La durée d'une ittération
     */
    @Override
    public void update(int delta) {
        if (player.getX()<this.x+aggroRange && player.getX()>this.x-aggroRange && player.getY()<this.y+aggroRange && player.getY()>this.y-aggroRange){
            moving = true;
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
     * Dessine la chauve-souris pour la première fois
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        if (isHitable()) {
            g.drawAnimation(moveAnimations[direction], x - 16, y - 32);
        } else {
            g.drawAnimation(moveAnimations[direction], x - 16, y - 32, Color.red);
        }
        g.setColor(Color.red);
        g.drawString(""+this.hitpoints, x, y - 45);
//        hitBox.render(g);
    }

    
}
