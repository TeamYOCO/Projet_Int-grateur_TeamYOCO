/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class ResManager {

    private static ResManager instance = null;
    private HashMap<String, SpriteSheet> sprites;
    private HashMap<String, Image> images;

    private ResManager() throws SlickException {
        loadRes();
    }

    public static ResManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new ResManager();
        }
        return instance;
    }

    private void loadRes() throws SlickException {
        sprites = new HashMap();
        images = new HashMap();
        File folderSprites64;
        if (System.getProperty("os.name").contains("Mac")) {
            folderSprites64 = new File("src/res/sprites/sprites64");
        } else {
            folderSprites64 = new File("src\\res\\sprites\\sprites64");
        }
        File[] listOfFile64 = folderSprites64.listFiles();
        for (int i = 0; i < listOfFile64.length; i++) {
            String path = listOfFile64[i].getPath();
            String name = listOfFile64[i].getName();
            name = name.substring(0, name.lastIndexOf('.'));
            sprites.put(name, new SpriteSheet(path, 64, 64));
        }
        
        File folderSprites32;
        if (System.getProperty("os.name").contains("Mac")) {
            folderSprites32 = new File("src/res/sprites/sprites32");
        } else {
            folderSprites32 = new File("src\\res\\sprites\\sprites32");
        }
        File[] listOfFile32 = folderSprites32.listFiles();
        for (int i = 0; i < listOfFile32.length; i++) {
            String path = listOfFile32[i].getPath();
            String name = listOfFile32[i].getName();
            name = name.substring(0, name.lastIndexOf('.'));
            sprites.put(name, new SpriteSheet(path, 32, 32));
        }
        
        File folderPictures;
        File[] listOfFilesPictures;
        if (System.getProperty("os.name").contains("Mac")) {
            folderPictures = new File("src/res/pictures");
            listOfFilesPictures = folderPictures.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals(".DS_Store");
                }
            });
        } else {
            folderPictures = new File("src\\res\\pictures");
            listOfFilesPictures = folderPictures.listFiles();
            
        }
        for (int i = 0; i < listOfFilesPictures.length; i++) {
            String path = listOfFilesPictures[i].getPath();
            String name = listOfFilesPictures[i].getName();
            name = name.substring(0,name.lastIndexOf('.'));
            images.put(name, new Image(path));
            
        }
    }

    public SpriteSheet getSpriteSheet(String filePath) {
        return sprites.get(filePath);
    }

    public Image getImage(String name) {
        return images.get(name);
    }
}
