/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;

/**
 * Classe qui contient tous les éléments représentant un monstre, npc ou joueur
 * @author Seb
 */
public abstract class Mob extends Entity {

    protected Animation[] moveAnimations;
    protected int hitpoints;
    protected int direction;
    protected boolean moving;
    protected int damage;
    protected float knockbackTimer = 0;
    
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
        System.out.println(direction);
        hitpoints -= damage;
        if (hitpoints <= 0){
            this.dead = true;
        }
        this.knockbackTimer = 100;
    }

    public int getDamage() {
        return 0;
    }
}
