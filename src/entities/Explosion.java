/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import maps.MiniMap;
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
public class Explosion extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;
    private Image img;
    private MiniMap map;

    /**
     * Crée une explosion
     * @param x Position en x du joueur
     * @param y Position en y du joueur
     * @param direction La direction du joueur
     * @param lifespam La durée de vie de l'explosion
     * @param map La map où l'explosion va être
     * @throws SlickException
     */
    public Explosion(float x, float y, int direction, int lifespam, MiniMap map) throws SlickException{
        SpriteSheet anim = ResManager.getInstance().getSpriteSheet("explosion");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.hitBox = new Box(x, y, 256, 128);
        this.direction = direction;
        animation = loadAnimation(anim, 0, 4, 0);
        img = animation.getImage(direction);
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3];
        this.lifeSpam = lifespam;
        this.map = map;
    }

   /**
     * Permet de desssiner l'explosion
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {

            g.drawImage(img, x - 14, y,Color.white);
            this.hitBox.render(g);
    }

    /**
     * Ce qui permet à l'explosion de bouger
     * @param delta le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
        hitBox.setPos(x+xOff, y+yOff);
    }
    
    /**
     * Calcul le dégat physique de l'explosion
     * @return le dégat physique
     */
    @Override
    public int getDamagePhysical() {
        return 0;
    }

    /**
     * Retourne la direction de l'explosion
     * @return un int de la direction de l'explosion
     */
    @Override
    public int getDirection() {
        dead = true;
        return direction;
    }

    /**
     * Calcul le dégat magique de l'explosion
     * @return le dégat magique de l'explosion
     */
    @Override
    public int getDamageSpecial() {
        dead=true;
        return damageSpecial;
    }
    
}
