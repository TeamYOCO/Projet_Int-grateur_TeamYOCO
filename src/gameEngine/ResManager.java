/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *
 * @author 1455367
 */
public class ResManager implements Serializable{

    private static ResManager instance = null;
    private HashMap<String, SpriteSheet> sprites;
    private HashMap<String, Image> images;

    private ResManager() throws SlickException {
        loadRes();
    }

    /**
     *
     * @return
     * @throws SlickException
     */
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
            String extention = name.substring(name.lastIndexOf('.'), name.length());
            name = name.substring(0, name.lastIndexOf('.'));
            if (extention.equals(".png")) {
                sprites.put(name, new SpriteSheet(path, 64, 64));
            }
        }

        File folderSprites128;
        if (System.getProperty("os.name").contains("Mac")) {
            folderSprites128 = new File("src/res/sprites/sprites128");
        } else {
            folderSprites128 = new File("src\\res\\sprites\\sprites128");
        }
        File[] listOfFile128 = folderSprites128.listFiles();
        for (int i = 0; i < listOfFile128.length; i++) {
            String path = listOfFile128[i].getPath();
            String name = listOfFile128[i].getName();
            String extention = name.substring(name.lastIndexOf('.'), name.length());
            name = name.substring(0, name.lastIndexOf('.'));
            if (extention.equals(".png")) {
                sprites.put(name, new SpriteSheet(path, 128, 128));
            }
        }
        
        File folderSprites192;
        if (System.getProperty("os.name").contains("Mac")) {
            folderSprites192 = new File("src/res/sprites/sprites192");
        } else {
            folderSprites192 = new File("src\\res\\sprites\\sprites192");
        }
        File[] listOfFile192 = folderSprites192.listFiles();
        for (int i = 0; i < listOfFile192.length; i++) {
            String path = listOfFile192[i].getPath();
            String name = listOfFile192[i].getName();
            String extention = name.substring(name.lastIndexOf('.'), name.length());
            name = name.substring(0, name.lastIndexOf('.'));
            if (extention.equals(".png")) {
                sprites.put(name, new SpriteSheet(path, 192, 192));
            }
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
            String extention = name.substring(name.lastIndexOf('.'), name.length());
            name = name.substring(0, name.lastIndexOf('.'));
            if (extention.equals(".png")) {
                sprites.put(name, new SpriteSheet(path, 32, 32));
            }
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
            String extention = name.substring(name.lastIndexOf('.'), name.length());
            name = name.substring(0, name.lastIndexOf('.'));
            if (extention.equals(".png")) {
                images.put(name, new Image(path));
            }

        }
    }
    
    /**
     *
     * @param fontName
     * @param fontSize
     * @return
     * @throws SlickException
     */
    public UnicodeFont getFont(String fontName, int fontSize) throws SlickException{
        Font font = new Font(fontName, Font.PLAIN, fontSize);
        UnicodeFont ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
        ufont.addAsciiGlyphs();
        ufont.addGlyphs(400, 600);
        ufont.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
        ufont.loadGlyphs();
        return ufont;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public SpriteSheet getSpriteSheet(String filePath) {
        return sprites.get(filePath);
    }

    /**
     *
     * @param name
     * @return
     */
    public Image getImage(String name) {
        return images.get(name);
    }
}
