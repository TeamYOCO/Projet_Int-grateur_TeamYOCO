/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import static ca.qc.bdeb.info204.Game.manager;
import items.Equipment;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.SlickException;
import playerEngine.CharacterStatsManager;
import statesOfGame.Overworld;

/**
 *
 * @author Olivier
 */
public class DataManager {
    
    private static DataManager instance = null;
    
    private DataManager(){
        
    }
    
    public static DataManager getInstance(){
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    
    public void save() throws SlickException{
        try {
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("save.dat"));
            save.writeObject((ArrayList<Equipment>)manager.getInventory().getListItemFound());
            save.writeObject((ArrayList<Equipment>)manager.getInventory().getListItemPlayer());
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                save.writeInt(CharacterStatsManager.getInstance().getStats()[i]);
            }
            save.writeInt(CharacterStatsManager.getInstance().getHp());
            save.flush();
            save.close();
        } catch (IOException ex) {
            System.out.println("Erreur d'entrées-sorties");
        }
    }
    
    public void load() throws SlickException{
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream("save.dat"));
            ArrayList<Equipment> list = (ArrayList<Equipment>)load.readObject();
            manager.getInventory().setListItemFound(list);
            list = (ArrayList<Equipment>) load.readObject();
            manager.getInventory().setListItemPlayer(list);
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                CharacterStatsManager.getInstance().setStats(i, load.readInt());
            }
            CharacterStatsManager.getInstance().setHp(load.readInt());
            load.close();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
        } catch (ClassNotFoundException e) {
            System.out.println("Fichier introuvable");
        }
    }
    
}
