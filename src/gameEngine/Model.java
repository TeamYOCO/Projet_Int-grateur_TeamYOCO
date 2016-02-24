/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import entities.Entity;
import entities.Player;
import java.util.ArrayList;
import playerEngine.PlayerEngine;
import java.util.Observable;

/**
 *
 * @author 1455367
 */
public class Model extends Observable{
    private static PlayerEngine playerEngine;
    private static Player player;
    private static boolean running = false;
    private static ArrayList<Entity> list = new ArrayList();
    private static ArrayList<Entity> listRemove = new ArrayList();
    
    private Thread thread = new Thread(){

        @Override
        public void run() {
            while (true){
                while (running) {
                    
                }
            }
        }
    };

    public Model() {
        
    }
    
    public void init(){
        playerEngine = new PlayerEngine();
        
    }

    public static Player getPlayer() {
        return player;
    }
}
