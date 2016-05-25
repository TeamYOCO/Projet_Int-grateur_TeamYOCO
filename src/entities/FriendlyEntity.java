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
public interface FriendlyEntity {
    
    /**
     * retourne le dommage physique de l'entitée
     * @return le dommage physique de l'entitée
     */
    public int getDamagePhysical();
    
    /**
     *retourne le dommage magique de l'entitée
     * @return le dommage magique de l'entitée
     */
    public int getDamageSpecial();
    
    /**
     * La direction de l'entitée
     * @return La direction de l'entitée
     */
    public int getDirection();
}
