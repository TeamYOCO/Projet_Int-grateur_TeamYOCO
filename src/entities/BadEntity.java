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

    /**
     *
     * @param damage
     * @param damageSpecial
     * @param hitDirection
     */
    public void takeHit(int damage, int damageSpecial, int hitDirection);
    
    /**
     *
     * @return
     */
    public int getDamagePhysical();
    
    /**
     *
     * @return
     */
    public int getDamageSpecial();
    
    /**
     *
     * @return
     */
    public boolean isHitable();
    
    /**
     *
     * @return
     */
    public int getDirection();
    
    /**
     *
     * @return
     */
    public int getExp();
}
