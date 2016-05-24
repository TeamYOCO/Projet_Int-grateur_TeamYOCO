/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import playerEngine.StatsMng;

/**
 * Classe qui contient tous les éléments représentant un monstre, npc ou joueur
 * @author Seb
 */
public abstract class Mob extends Entity {

    protected Animation[] moveAnimations;
    protected int hitpoints;
    protected int direction = 2;
    protected boolean moving;
    protected int damage;
    protected float knockbackTimer = 0;
    protected int exp = 0;
    protected ArrayList<Entity> list;
    
    public boolean isHitable(){
        if (knockbackTimer <= 0)
            return true;
        else
            return false;
    }
    
    public void takeHit(int damage, int hitDirection) {
        switch(hitDirection){
            case 0: this.direction = 2; break;
            case 1: this.direction = 3; break;
            case 2: this.direction = 0; break;
            case 3: this.direction = 1; break;
        }
        hitpoints -= damage;
        if (hitpoints <= 0){
            this.dead = true;
            try {
                this.die();
            } catch (SlickException ex) {
                Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.knockbackTimer = 200;
    }

    public int getDamage() {
        return damage;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public int getExp(){
        return exp;
    }
    
    public void die() throws SlickException{
        StatsMng.getInstance().gainExp(exp);
    }
}
