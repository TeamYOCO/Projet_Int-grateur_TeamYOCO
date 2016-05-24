/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import entities.Entity;
import entities.Player;
import items.Equipment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                save.writeInt(CharacterStatsManager.getInstance().getStats()[i]);
            }
            save.writeInt(CharacterStatsManager.getInstance().getHp());
            save.writeUTF(MiniMap.getFileName());
            save.writeFloat(Entity.getSaveX());
            save.writeFloat(Entity.getSaveY());
            save.writeInt(CharacterStatsManager.getInstance().getLevel());
            save.writeObject((ArrayList<Equipment>)CharacterStatsManager.getInstance().getInventory().getListItemFound());
            save.writeObject((ArrayList<Equipment>)CharacterStatsManager.getInstance().getInventory().getListItemPlayer());
            save.flush();
            save.close();
        } catch (IOException ex) {
            System.out.println("Erreur d'entrées-sorties");
        }
    }
    
    public void load() throws SlickException{
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream("save.dat"));
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                CharacterStatsManager.getInstance().setStats(i, load.readInt());
            }
            CharacterStatsManager.getInstance().setHp(load.readInt());
            MiniMap.changeMap(load.readUTF());
            Player.setSaveX(load.readFloat());
            Player.setSaveY(load.readFloat());
            CharacterStatsManager.getInstance().setLevel(load.readInt());
            ArrayList<Equipment> list = (ArrayList<Equipment>)load.readObject();
            CharacterStatsManager.getInstance().getInventory().setListItemFound(list);
            list = (ArrayList<Equipment>) load.readObject();
            CharacterStatsManager.getInstance().getInventory().setListItemPlayer(list);
            load.close();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable");
        }
    }
    
    public boolean isEmpty(){
        boolean isEmpty = false;
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream("save.dat"));
        } catch (IOException ex) {
            isEmpty = true;
        }
            return isEmpty;
    }
    
}
