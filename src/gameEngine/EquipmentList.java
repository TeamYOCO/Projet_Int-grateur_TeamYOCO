/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 1485246
 */
public class EquipmentList {
    
    private static EquipmentList instance = null;
    private ArrayList<String> listEquipment;
    
    private EquipmentList(){
        listEquipment = new ArrayList();
    }
    
    public static EquipmentList getInstance(){
        if (instance == null){
            instance = new EquipmentList();
        }
        return instance;
    }
    
    private void loadStrings(){
        listEquipment = new ArrayList();
        listEquipment.add("Casque Antique");
        listEquipment.add("Casque Metallique");
        listEquipment.add("Casque d'Argent");
        listEquipment.add("Casque Emeraude");
        listEquipment.add("Casque Royal");
        listEquipment.add("Armure Antique");
        listEquipment.add("Armure Metallique");
        listEquipment.add("Armure d'Argent");
        listEquipment.add("Armure Emeraude");
        listEquipment.add("Armure Royale");
        listEquipment.add("Jambieres Antiques");
        listEquipment.add("Jambieres Metalliques");
        listEquipment.add("Jambieres d'Argent");
        listEquipment.add("Jambieres Emeraudes");
        listEquipment.add("Jambieres Royales");
        listEquipment.add("Bottes Metalliques");
        listEquipment.add("Bottes d'Argent");
        listEquipment.add("Bottes Emeraudes");
        listEquipment.add("Bottes Royales");
        listEquipment.add("Bouclier Serpent");
        listEquipment.add("Bouclier de bois");
        listEquipment.add("Bouclier legendaire");
        listEquipment.add("Bouclier simple");
        listEquipment.add("Jesus");
        listEquipment.add("Epee de bois");
        listEquipment.add("Epee rudimentaire");
        listEquipment.add("Epee metallique");
        listEquipment.add("Epee d'argent");
        listEquipment.add("Epee Royale");
        listEquipment.add("Banane");
        listEquipment.add("Epee legendaire");
        listEquipment.add("Epee de glace");
        listEquipment.add("Epee rose");
        listEquipment.add("Epee extraterrestre");
        listEquipment.add("Hache de bois");
        listEquipment.add("Hache rudimentaire");
        listEquipment.add("Hache metallique");
        listEquipment.add("Hache d'argent");
        listEquipment.add("Hache Royale");
        listEquipment.add("Hache lourde");
        listEquipment.add("Hache legere");
        listEquipment.add("Hache de feu");
        listEquipment.add("Hache sinistre");
        listEquipment.add("Hache artisanale");
        listEquipment.add("Sceptre clair");
        listEquipment.add("Sceptre offensif");
        listEquipment.add("Tri-dent magique");
        listEquipment.add("Sceptre etrangleur");
        listEquipment.add("Large Sceptre");
        listEquipment.add("Sceptre clair 2.0");
        listEquipment.add("Sceptre metallique");
        listEquipment.add("Sceptre jaune");
        listEquipment.add("Cane de Noel");
        listEquipment.add("Sceptre precieux");
        listEquipment.add("Arc simple");
        listEquipment.add("Arc elegant");
        listEquipment.add("Arc lourd");
        listEquipment.add("Arc sombre");
        listEquipment.add("Arc clair");
        listEquipment.add("Arc infernal");
        listEquipment.add("Arc de feu");
        listEquipment.add("Arc angelique");
        listEquipment.add("Arc metallique");
        listEquipment.add("Arc artisanal");
    }
}
