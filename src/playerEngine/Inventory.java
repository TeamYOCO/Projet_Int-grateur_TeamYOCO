/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import items.Equipment;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 1455367
 */
public class Inventory implements Serializable{
    private ArrayList<Equipment> listItemFound,listItemPlayer;
    private int money;

    public Inventory() {
        listItemFound = new ArrayList();
        listItemPlayer = new ArrayList();
        money = 0;
    }
    
    public ArrayList<Equipment> getListItemFound(){
        return listItemFound;
    }
    
    public ArrayList<Equipment> getListItemPlayer(){
        return listItemPlayer;
    }
    
    public void setListItemFound(ArrayList<Equipment> listItemFound){
        this.listItemFound = listItemFound;
    }
    
    public void setListItemPlayer(ArrayList<Equipment> listItemPlayer){
        this.listItemPlayer = listItemPlayer;
    }
    
    
}
