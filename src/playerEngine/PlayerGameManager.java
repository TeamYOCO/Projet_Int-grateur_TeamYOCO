/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import gameEngine.EquipmentList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Seb
 */
public class PlayerGameManager {
    
    private Inventory inventory;
    private CharacterStatsManager stats;

    public PlayerGameManager() throws SlickException {
        inventory = new Inventory();
    }

    public void addItem(String newItem) throws SlickException{
        this.getInventory().getListItemFound().add(EquipmentList.getInstance().getListEquipment().get(newItem));
    }

    public Inventory getInventory() {
        return inventory;
    }
}
