/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;

/**
 *
 * @author Seb
 */
public abstract class Mob extends Entity {

    protected Animation[] moveAnimations;
    protected int hitpoints;
    protected int direction;
    protected boolean moving;

    public Mob() {
    }

}
