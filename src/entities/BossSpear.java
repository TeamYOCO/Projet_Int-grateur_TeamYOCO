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

    /**
     *La méthode appelée lorsque un ennemie est touché
     * @param damage Le dommage physique de l'attaque
     * @param damageSpecial Le dommage magique de l'attaque
     * @param hitDirection La direction de l'attaque
     */
    @Override
    public void takeHit(int damage, int damageSpecial, int hitDirection) {
    }

    /**
     * Retourne le dégat physique de l'ennemie
     * @return le dégat physique
     */
    @Override
    public int getDamagePhysical() {
        return damagePhysical;
    }

    /**
     *Retourne le dégat magique de l'ennemie
     * @return le dégat magique
     */
    @Override
    public int getDamageSpecial() {
        return damageSpecial;
    }

    /**
     * Renvoie un booléen qui dit si l'ennemie peut être touché par le joueur
     * @return booléen qui dit si l'ennemie peut être touché par le joueur
     */
    @Override
    public boolean isHitable() {
        return false;
    }

    /**
     * Retourne la direction de cette entitée
     * @return la direction
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /**
     * Retourne la valeur en expérience de l'ennemie
     * @return L'expérience
     */
    @Override
    public int getExp() {
        return 0;
    }
    
}
