package ca.qc.bdeb.info204;

import statesOfGame.MainMenu;
import statesOfGame.Overworld;
import gameEngine.Model;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.tiled.TiledMap;


public class Game extends StateBasedGame{
    public static final String gameName = "Projet intégrateur V1.0";
    public static final int MAINMENU = 0;
    public static final int OVERWORLD = 1;
    public static final int INVENTORY = 2;
    public static final int SKILLTREE = 3;
    public static final int MAP = 4;
    public static final int MENU = 5;
    public static final int WIDTH = 512;
    public static final int HEIGHT = 352;
    public static Model model;
    private GameContainer container;
    private TiledMap map;
    
    //C'est ic la classe main du jeu
    public Game(String name) {
        super(name);
        this.addState(new MainMenu(MAINMENU));
        this.addState(new Overworld(OVERWORLD, model));
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MAINMENU).init(gc, this);
        this.getState(OVERWORLD).init(gc, this);
        this.enterState(MAINMENU);
        this.container=gc;
        this.map=new TiledMap("res/maps/untitled.tmx");
    }
    
    public static void main(String[] args) throws SlickException {
        loadNatives(Game.class);
        AppGameContainer appGc;
        try{
            appGc = new AppGameContainer(new Game(gameName));
            appGc.setDisplayMode(WIDTH, HEIGHT, false);
            appGc.start();
        }catch(SlickException ex){
            ex.printStackTrace();
        }
    }
    private static void loadNatives(Class c) {
        final File jarFile = new File(c.getProtectionDomain().getCodeSource().getLocation().getPath());
        final String path = "res/native/";
 
        if (jarFile.isFile()) {
            try {
                // Run with JAR file
                final JarFile jar = new JarFile(jarFile);
 
                final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
                final String nativeFolderPath = jarFile.getParent() + "/native/";
                final File nativeFolder = new File(nativeFolderPath);
                nativeFolder.delete();
                nativeFolder.mkdir();
 
                while (entries.hasMoreElements()) {
                    final String name = entries.nextElement().getName();
 
                    if (name.startsWith(path)) { //filter according to the path
                        Object temp = null;
 
                        InputStream is = c.getResourceAsStream("/" + name);
                        String nativeName = name.replace(path, "");
                        File nativePath = new File(nativeFolderPath + nativeName);
                        if (!nativeName.isEmpty()) {
                            System.out.println("Extracting: " + nativeName);
                            OutputStream os = null;
                            try {
                                os = new FileOutputStream(nativePath);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }
                            } finally {
                                is.close();
                                os.close();
                            }
 
                        }
                    }
                }
                jar.close();
 
                System.setProperty("org.lwjgl.librarypath", nativeFolderPath);
            } catch (IOException ex) {
                Logger.getLogger(c.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                System.setProperty("org.lwjgl.librarypath", new File(c.getResource("/" + path).toURI()).getAbsolutePath());
            } catch (URISyntaxException ex) {
                Logger.getLogger(c.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
}

    

    
}
