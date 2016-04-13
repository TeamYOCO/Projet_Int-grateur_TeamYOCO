/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author 1455367
 */
public class ResManager {

    private static ResManager instance = null;
    private static ArrayList<String> sprites, maps, pictures;

    private ResManager() {
        loadRes();
    }

    public static ResManager getInstance() {
        if (instance == null) {
            instance = new ResManager();
        }
        return instance;
    }

    private void loadRes() {
        sprites = new ArrayList();
        File folder = new File("src\\res\\sprites");
        File[] listOfFile = folder.listFiles();
        for (int i = 0; i < listOfFile.length; i++) {
            
        }
    }
}
