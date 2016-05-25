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
     *La méthode appelée lorsque un ennemie est touché
     * @param damage Le dommage physique de l'attaque
     * @param damageSpecial Le dommage magique de l'attaque
     * @param hitDirection La direction de l'attaque
     */
    public void takeHit(int damage, int damageSpecial, int hitDirection);
    
    /**
     * Retourne le dégat physique de l'ennemie
     * @return le dégat physique
     */
    public int getDamagePhysical();
    
    /**
     *Retourne le dégat magique de l'ennemie
     * @return le dégat magique
     */
    public int getDamageSpecial();
    
    /**
     * Renvoie un booléen qui dit si l'ennemie peut être touché par le joueur
     * @return booléen qui dit si l'ennemie peut être touché par le joueur
     */
    public boolean isHitable();
    
    /**
     * Retourne la direction de cette entitée
     * @return la direction
     */
    public int getDirection();
    
    /**
     * Retourne la valeur en expérience de l'ennemie
     * @return L'expérience
     */
    public int getExp();
}
