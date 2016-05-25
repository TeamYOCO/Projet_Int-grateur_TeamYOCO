/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashMap;

/**
 *
 * @author The Ninetail
 */
public class NpcList {
    private NpcList instance = null;
    private HashMap <String, NPC> npcList;

    private NpcList() {
    }
    
    public NpcList getInstance(){
        if (instance == null){
            instance = new NpcList();
        }
        return instance;
    }
    
    private void loadNpcs(){
        npcList.put("Princess", new NPC(0, 0, "Bonjour à toi brâve guerrier. Tu doit vaincre\nle méchant chevalier qui rode dans la mine", NpcType.INTERACT, 0));
        
    }
}
