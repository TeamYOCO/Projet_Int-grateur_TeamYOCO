/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author Tomas
 */
public class Fireball extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;
    private MiniMap map;
    private Animation explosionAnimation;
    private ArrayList<Entity> list;
    private boolean exploding = false;

    /**
     * Crée une boule de feu
     * @param x Position en x du joueur
     * @param y Position en y du joueur
     * @param direction La direction du joueur
     * @param lifespam La durée de vie de la boule de feu
     * @param map La map où la boule de feu va être
     * @throws SlickException
     */
    public Fireball(float x, float y, int direction, int lifespam, MiniMap map, ArrayList<Entity> list) throws SlickException{
        SpriteSheet anim1 = ResManager.getInstance().getSpriteSheet("fireball");
        Image temp = ResManager.getInstance().getSpriteSheet("explosion");
        SpriteSheet anim2 = new SpriteSheet(temp, 256, 128);
        animation = new Animation();
        explosionAnimation = new Animation();
        this.list = list;
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.direction = direction;
        switch (direction){
            case 0: speedY = -0.3f; break;
            case 1: speedX = -0.3f; break;
            case 2: speedY = 0.3f; break;
            case 3: speedX = 0.3f; break;
        }
        xOff = -16;
        yOff = -16;
        this.hitBox = new Box(x+xOff, y+yOff, 32, 32);
        animation = loadAnimation(anim1, 0, 8, direction);
        for (int i = 0; i < 4; i++) {
            explosionAnimation = addAnimation(anim2, 0, 3, i, explosionAnimation);
        }
        explosionAnimation.setSpeed(1.3f);
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3]*2;
        this.lifeSpam = lifespam;
        this.map = map;
    }

    /**
     * Permet de desssiner la boule de feu
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        if (!exploding)
            g.drawAnimation(animation, x-32, y-32);
        else if (exploding)
            g.drawAnimation(explosionAnimation, x-128, y-64);
    }

    /**
     * Ce qui permet à la boule de feu de bouger
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
        if (exploding)
        hitBox.setPos(x+xOff, y+yOff);
    }
    
    /**
     * Calcul le dégat physique de la boule de feu
     * @return le dégat physique
     */
    @Override
    public int getDamagePhysical() {
        return 0;
    }

    /**
     * Retourne la direction de la boule de feu
     * @return un int de la direction de la boule de feu
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /**
     * Calcul le dégat magique de la boule de feu
     * @return le dégat magique de la boule de feu
     */
    @Override
    public int getDamageSpecial() {
        explode();
        return damageSpecial;
    }
    
    private void explode(){
        exploding = true;
        lifeSpam = 500;
        speedX = 0;
        speedY = 0;
        xOff = -100;
        yOff = -50;
        hitBox = new Box(x+xOff, y+yOff, 200, 100);
    }
    
}
