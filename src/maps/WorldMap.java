/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Seb
 */
public class WorldMap {
    private HashMap<String, MiniMap> world;

    
    public static WorldMap instance = null;

    /**
     * crée la carte du monde
     */
    private WorldMap() {
        world = new HashMap();
        File fichier = new File("src\\res\\maps");
        File[] listOfFile = fichier.listFiles();
        for (int i = 0; i < listOfFile.length; i++) {
            String path = listOfFile[i].getPath();
            String name = listOfFile[i].getName();
            name = name.substring(0, name.lastIndexOf('.'));
            world.put(name, new MiniMap(path));
            System.out.println(name);
        }
    }

    /**
     * Pour s'assurer qu'il n'y ait qu'une seule classe comme celle-ci
     * @return une instance
     */
    public static WorldMap getInstance() {
        if (instance == null){
            instance = new WorldMap();
        }
        return instance;
    }
    
    /**
     * retourne la carte du nom choisit
     * @param name le nom de la carte
     * @return la carte du nom choisit
     */
    public MiniMap getMap(String name){
        return world.get(name);
    }
    
}
