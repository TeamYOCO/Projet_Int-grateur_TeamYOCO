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
    private Image img;
    private MiniMap map;
    private ArrayList<Entity> list;

    /**
     * Crée une boule de feu
     * @param x Position en x du joueur
     * @param y Position en y du joueur
     * @param direction La direction du joueur
     * @param lifespam La durée de vie de la boule de feu
     * @param map La map où la boule de feu va être
     * @throws SlickException
     */
    public Fireball(float x, float y, int direction, int lifespam, MiniMap map) throws SlickException{
        SpriteSheet anim = ResManager.getInstance().getSpriteSheet("fireball_0");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.direction = direction;
        animation = loadAnimation(anim, 0, 4, 0);
        img = animation.getImage(direction);
        switch(direction){
            case 0: speedY = -0.5f; xOff = -2; this.hitBox = new Box(x, y, 4, 30); break;
            case 1: speedX = -0.5f; yOff = -2; this.hitBox = new Box(x, y, 30, 4); break;
            case 2: speedY = 0.5f; xOff = -2; yOff = -32; this.hitBox = new Box(x, y, 4, 30); break;
            case 3: speedX = 0.5f; yOff = -2; xOff = -32; this.hitBox = new Box(x, y, 30, 4); break;
        }
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3];
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
        switch(direction){
            case 0: g.drawImage(img, x - 32, y - 32,Color.white); break;
            case 1: g.drawImage(img, x - 32, y - 32,Color.white); break;
            case 2: g.drawImage(img, x - 32, y - 32,Color.white); break;
            case 3: g.drawImage(img, x - 32, y - 32,Color.white); break;
        }
        this.hitBox.render(g);
    }

    /**
     * Ce qui permet à la boule de feu de bouger
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
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
        dead = true;
        return direction;
    }

    /**
     * Calcul le dégat magique de la boule de feu
     * @return le dégat magique de la boule de feu
     */
    @Override
    public int getDamageSpecial() {
        dead=true;
        try {
            this.list.add(new Explosion(x-128,y-64,0,1000,this.map));
        } catch (SlickException ex) {
            Logger.getLogger(Fireball.class.getName()).log(Level.SEVERE, null, ex);
        }
        return damageSpecial;
    }
    
}
