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
    private static HashMap<String, NPC> npcList;

    /**
     * Crée une liste de PNJ
     */
    private NpcList() {
        loadNpcs();
    }

    /**
     * Retourne une instance dans la liste 
     * @return une instance dans la liste
     */
    public NpcList getInstance() {
        if (instance == null) {
            instance = new NpcList();
        }
        return instance;
    }

    /**
     * Charge les PNJ
     */
    private static void loadNpcs() {
        npcList = new HashMap();
        npcList.put("Princess",
                new NPC(0, 0, "Bonjour à toi brave guerrier. Tu doit vaincre le méchant chevalier qui rode dans la mine", NpcType.INTERACT, 0));
        npcList.put("Marchand", 
                new NPC(0, 0, "Bienvenue dans le magasin général!", NpcType.VENDOR, 1));
        npcList.put("Soldat", new NPC(0, 0, "", NpcType.NONE, 3));
        npcList.put("villageois 1", new NPC(0, 0, "Bonjours, belle journé n'est-ce pas?", NpcType.INTERACT, 4));
        npcList.put("villageoise 1", new NPC(0, 0, "420 blaze it", NpcType.INTERACT, 2));
        npcList.put("villageoise 2", new NPC(0, 0, "Coucou! Tu est une belle cruche toi!", NpcType.INTERACT, 5));
    }

    /**
     * Va prendre un PNJ dans la liste
     * @param name Le nom du PNJ
     * @param x la position en x du PNJ
     * @param y la position en y du PNJ
     * @return  le PNJ
     */
    public static NPC getNpc(String name, int x, int y) {
        NpcList.loadNpcs();
        npcList.get(name).setCoords(x, y);
        return npcList.get(name);
    }

}
