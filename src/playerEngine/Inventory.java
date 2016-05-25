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
     * constructeur de l'inventaire
     */
    public Inventory() {
        listItemFound = new ArrayList();
        listItemPlayer = new ArrayList();
        money = 0;
    }
    
    /**
     * retourne les équipements dans l'inventaire, mais pas équipé
     * @return les équipements dans l'inventaire, mais pas équipé
     */
    public ArrayList<Equipment> getListItemFound(){
        return listItemFound;
    }
    
    /**
     * retourne les équipements équipés
     * @return les équipements équipés
     */
    public ArrayList<Equipment> getListItemPlayer(){
        return listItemPlayer;
    }
    
    /**
     * change les équipements dans l'inventaire, mais pas équipé
     * @param listItemFound la nouvelle liste
     */
    public void setListItemFound(ArrayList<Equipment> listItemFound){
        this.listItemFound = listItemFound;
    }
    
    /**
     * change les équipements équipés
     * @param listItemPlayer la nouvelle liste
     */
    public void setListItemPlayer(ArrayList<Equipment> listItemPlayer){
        this.listItemPlayer = listItemPlayer;
    }
    
    /**
     * retourne si le joueur à une épée d"équipé
     * @return si le joueur à une épée d"équipé
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
     * retourne si le joueur à un arc d"équipé
     * @return si le joueur à un arc d"équipé
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
     *retourne si le joueur à un livre d"équipé
     * @return si le joueur à un livre d"équipé
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
     * retourne si l'équipement choisit est équipé
     * @param itemName l'équipement choisi
     * @return si l'équipement choisit est équipé
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
