/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import gameEngine.ResMng;
import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Olivier
 */
public class IconList{
    
    private static IconList instance = null;
    private final HashMap<String,Image> listIcon;
    private final int SPRITE_SHEET_HEIGHT = 13, SPRITE_SHEET_WIDTH = 5;
    
    private IconList() throws SlickException{
        listIcon = new HashMap();
        loadStrings();
    }
    
    public static IconList getInstance() throws SlickException {
        if (instance == null){
            instance = new IconList();
            
        }
        return instance;
    }

    private void loadStrings() throws SlickException{
//      constructeur Equipment : int inventoryX, int inventoryY, Animation itemIcon, String name, String description, int[] stats

        SpriteSheet spriteSheet = ResMng.getInstance().getSpriteSheet("new_items");
        Animation animation = new Animation();
        for (int i = 0; i < SPRITE_SHEET_HEIGHT; i++) {
            for (int j = 0; j < SPRITE_SHEET_WIDTH; j++) {
                animation.addFrame(spriteSheet.getSprite(j, i), 100);
            }
        }
        
        listIcon.put("Casque Antique",animation.getImage(0));
        listIcon.put("Casque Metallique",animation.getImage(1));
        listIcon.put("Casque d'Argent",animation.getImage(2));
        listIcon.put("Casque Emeraude",animation.getImage(3));
        listIcon.put("Casque Royal",animation.getImage(4));
        listIcon.put("Armure Antique",animation.getImage(5));
        listIcon.put("Armure Metallique",animation.getImage(6));
        listIcon.put("Armure d'Argent",animation.getImage(7));
        listIcon.put("Armure Emeraude",animation.getImage(8));
        listIcon.put("Armure Royale",animation.getImage(9));
        listIcon.put("Jambieres Antiques",animation.getImage(10));
        listIcon.put("Jambieres Metalliques",animation.getImage(11));
        listIcon.put("Jambieres d'Argent",animation.getImage(12));
        listIcon.put("Jambieres Emeraudes",animation.getImage(13));
        listIcon.put("Jambieres Royales",animation.getImage(14));
        listIcon.put("Bottes Antiques",animation.getImage(15));
        listIcon.put("Bottes Metalliques",animation.getImage(16));
        listIcon.put("Bottes d'Argent",animation.getImage(17));
        listIcon.put("Bottes Emeraudes",animation.getImage(18));
        listIcon.put("Bottes Royales",animation.getImage(19));
        listIcon.put("Bouclier Serpent",animation.getImage(20));
        listIcon.put("Bouclier de bois",animation.getImage(21));
        listIcon.put("Bouclier legendaire",animation.getImage(22));
        listIcon.put("Bouclier simple",animation.getImage(23));
        listIcon.put("Jesus",animation.getImage(24));
        listIcon.put("Epee de bois",animation.getImage(25));
        listIcon.put("Epee rudimentaire",animation.getImage(26));
        listIcon.put("Epee metallique",animation.getImage(27));
        listIcon.put("Epee d'argent",animation.getImage(28));
        listIcon.put("Epee Royale",animation.getImage(29));
        listIcon.put("Banane",animation.getImage(30));
        listIcon.put("Epee legendaire",animation.getImage(31));
        listIcon.put("Epee de glace",animation.getImage(32));
        listIcon.put("Epee rose",animation.getImage(33));
        listIcon.put("Epee extraterrestre",animation.getImage(34));
        listIcon.put("Livre bleu",animation.getImage(35));
        listIcon.put("Livre violet",animation.getImage(36));
        listIcon.put("Livre rouge",animation.getImage(37));
        listIcon.put("Livre mauve",animation.getImage(38));
        listIcon.put("Livre beige",animation.getImage(39));
        listIcon.put("Livre brun",animation.getImage(40));
        listIcon.put("Livre gris",animation.getImage(41));
        listIcon.put("Livre vert",animation.getImage(42));
        listIcon.put("Livre cyan",animation.getImage(43));
        listIcon.put("Livre orange",animation.getImage(44));
        listIcon.put("Arc simple",animation.getImage(50));
        listIcon.put("Arc elegant",animation.getImage(51));
        listIcon.put("Arc lourd",animation.getImage(52));
        listIcon.put("Arc sombre",animation.getImage(53));
        listIcon.put("Arc clair",animation.getImage(54));
        listIcon.put("Arc infernal",animation.getImage(55));
        listIcon.put("Arc de feu",animation.getImage(56));
        listIcon.put("Arc angelique",animation.getImage(57));
        listIcon.put("Arc metallique",animation.getImage(58));
        listIcon.put("Arc artisanal",animation.getImage(59));
        
    }
    
    public HashMap<String, Image> getListIcon() {
        return listIcon;
    }
}
