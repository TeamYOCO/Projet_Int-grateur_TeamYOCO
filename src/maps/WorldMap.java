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

    /**
     *
     */
    public static WorldMap instance = null;

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
     *
     * @return
     */
    public static WorldMap getInstance() {
        if (instance == null){
            instance = new WorldMap();
        }
        return instance;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public MiniMap getMap(String name){
        return world.get(name);
    }
    
}
