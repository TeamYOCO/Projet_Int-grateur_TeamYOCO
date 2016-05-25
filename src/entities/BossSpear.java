/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author The Ninetail
 */
public class BossSpear extends Particle implements BadEntity{
    
    private int direction;
    private int damagePhysical;
    private int damageSpecial;

    @Override
    public void takeHit(int damage, int damageSpecial, int hitDirection) {
    }

    @Override
    public int getDamagePhysical() {
        return damagePhysical;
    }

    @Override
    public int getDamageSpecial() {
        return damageSpecial;
    }

    @Override
    public boolean isHitable() {
        return false;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public int getExp() {
        return 0;
    }
    
}
