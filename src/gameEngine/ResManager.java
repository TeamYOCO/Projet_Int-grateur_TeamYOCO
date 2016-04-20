/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.io.File;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class ResManager {

    private static ResManager instance = null;
    private static ArrayList<IndexSpriteSheet> sprites;

    public ResManager() throws SlickException {
        loadRes();
    }

    public static ResManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new ResManager();
        }
        return instance;
    }

    private void loadRes() throws SlickException {
        sprites = new ArrayList();
        File folder = new File("src\\res\\sprites");
        File[] listOfFile = folder.listFiles();
        for (int i = 0; i < listOfFile.length; i++) {
            System.out.println(listOfFile[i].getPath());
            sprites.add(new IndexSpriteSheet(listOfFile[i].getPath()));
        }
    }
    
    public SpriteSheet getSpriteSheet(String filePath){
        SpriteSheet sheet = null;
        for (int i = 0; i < sprites.size(); i++) {
            if (filePath.equals(sprites.get(i).file))
                System.out.println("ok");
                sheet = sprites.get(i).sheet;
        }
        return sheet;
    }
    
    private class IndexSpriteSheet{
        String file;
        SpriteSheet sheet;
        public IndexSpriteSheet(String file) throws SlickException {
            this.file = file;
            this.sheet = new SpriteSheet(file, 64, 64);
        }
    }
}
