/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

/**
 *
 * @author 1455367
 */
public class ResManager {
    private static ResManager instance = null;
    
    protected ResManager(){
        
    }
    
    public static ResManager getInstance(){
        if (instance == null){
            instance = new ResManager();
        }
        return instance;
    }
    
    
}
