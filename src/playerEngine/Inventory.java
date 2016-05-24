/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import items.Equipment;
import items.EquipmentType;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 1455367
 */
public class Inventory implements Serializable{
    private ArrayList<Equipment> listItemFound,listItemPlayer;
    private int money;

    /**
     *
     */
    public Inventory() {
        listItemFound = new ArrayList();
        listItemPlayer = new ArrayList();
        money = 0;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Equipment> getListItemFound(){
        return listItemFound;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Equipment> getListItemPlayer(){
        return listItemPlayer;
    }
    
    /**
     *
     * @param listItemFound
     */
    public void setListItemFound(ArrayList<Equipment> listItemFound){
        this.listItemFound = listItemFound;
    }
    
    /**
     *
     * @param listItemPlayer
     */
    public void setListItemPlayer(ArrayList<Equipment> listItemPlayer){
        this.listItemPlayer = listItemPlayer;
    }
    
    /**
     *
     * @return
     */
    public boolean isSwordEquiped(){
        for (Equipment equipment : listItemPlayer){
            if (equipment.getType() == EquipmentType.SWORD){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isBowEquiped(){
        for (Equipment equipment : listItemPlayer){
            if (equipment.getType() == EquipmentType.BOW){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isSpellTomeEquiped(){
        for (Equipment equipment : listItemPlayer){
            if (equipment.getType() == EquipmentType.SPELL_TOME){
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param itemName
     * @return
     */
    public boolean isItemEquiped(String itemName){
        for(Equipment equipment : listItemPlayer){
            if (equipment.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
}
