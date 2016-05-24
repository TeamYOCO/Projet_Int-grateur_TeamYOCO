/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Graphics;

/**
 *
 * @author The Ninetail
 */
public interface BadEntity {

    public void takeHit(int damage, int damageSpecial, int hitDirection);
    
    public int getDamagePhysical();
    
    public int getDamageSpecial();
    
    public boolean isHitable();
    
    public int getDirection();
    
    public int getExp();
}
