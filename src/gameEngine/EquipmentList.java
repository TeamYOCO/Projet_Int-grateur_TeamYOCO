/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import items.Equipment;
import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1485246
 */
public class EquipmentList {
    
    private static EquipmentList instance = null;
    private final HashMap<String,Equipment> listEquipment;
    private final int SPRITE_SHEET_HEIGHT = 13, SPRITE_SHEET_WIDTH = 5;
    
    private EquipmentList() throws SlickException{
        listEquipment = new HashMap();
        loadStrings();
    }
    
    public static EquipmentList getInstance() throws SlickException {
        if (instance == null){
            instance = new EquipmentList();
            
        }
        return instance;
    }

    private void loadStrings() throws SlickException{
//      constructeur Equipment : int inventoryX, int inventoryY, Animation itemIcon, String name, String description, int[] stats

        SpriteSheet spriteSheet = ResManager.getInstance().getSpriteSheet("new_items");
        Animation animation = new Animation();
        for (int i = 0; i < SPRITE_SHEET_WIDTH; i++) {
            for (int j = 0; j < SPRITE_SHEET_HEIGHT; j++) {
                animation.addFrame(spriteSheet.getSprite(i, j), 100);
            }
            
        }
        
        listEquipment.put("Casque Antique",new Equipment(0,0,animation.getImage(0),"Casque Antique",
                "Casque ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nOffre une petite augmentation de la vie \nmaximale",new int[]{420,420,420,420,420,420}));
        listEquipment.put("Casque Metallique",new Equipment(0,0,animation.getImage(1),"Casque Metallique",
                "Casque en métal moderne. Procure une bonne \nOffre une bonne augmentation de la vie maximale",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Casque d'Argent",new Equipment(0,0,animation.getImage(2),"Casque d'Argent",
                "A appartenu a un général emerite. Offre une \ntrès bonne augmentation de la vie maximale",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Casque Emeraude",new Equipment(0,0,animation.getImage(3),"Casque Emeraude",
                "Casque forgé par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nla vie maximale",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Casque Royal",new Equipment(0,0,animation.getImage(4),"Casque Royal",
                "Casque dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Augmentation vitale maximale",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Armure Antique",new Equipment(0,0,animation.getImage(5),"Armure Antique",
                "Armure ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Armure Metallique",new Equipment(0,0,animation.getImage(6),"Armure Metallique",
                "Armure en métal moderne. Procure une bonne \nprotection contre les attaques physiques",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Armure d'Argent",new Equipment(0,0,animation.getImage(7),"Armure d'Argent",
                "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nphysiques",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Armure Emeraude",new Equipment(0,0,animation.getImage(8),"Armure Emeraude",
                "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques physiques",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Armure Royale",new Equipment(0,0,animation.getImage(9),"Armure Royale",
                "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jambieres Antiques",new Equipment(0,0,animation.getImage(10),"Jambieres Antiques",
                "Jambieres ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jambieres Metalliques",new Equipment(0,0,animation.getImage(11),"Jambieres Metalliques",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jambieres d'Argent",new Equipment(0,0,animation.getImage(12),"Jambieres d'Argent",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jambieres Emeraudes",new Equipment(0,0,animation.getImage(13),"Jambieres Emeraudes",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jambieres Royales",new Equipment(0,0,animation.getImage(14),"Jambieres Royales",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bottes Antiques",new Equipment(0,0,animation.getImage(15),"Bottes Antiques",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bottes Metalliques",new Equipment(0,0,animation.getImage(16),"Bottes Metalliques",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bottes d'Argent",new Equipment(0,0,animation.getImage(17),"Bottes d'Argent",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bottes Emeraudes",new Equipment(0,0,animation.getImage(18),"Bottes Emeraudes",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bottes Royales",new Equipment(0,0,animation.getImage(19),"Bottes Royales",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bouclier Serpent",new Equipment(0,0,animation.getImage(20),"Bouclier Serpent",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bouclier de bois",new Equipment(0,0,animation.getImage(21),"Bouclier de bois",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bouclier legendaire",new Equipment(0,0,animation.getImage(22),"Bouclier legendaire",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Bouclier simple",new Equipment(0,0,animation.getImage(23),"Bouclier simple",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Jesus",new Equipment(0,0,animation.getImage(24),"Jesus",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee de bois",new Equipment(0,0,animation.getImage(25),"Epee de bois",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee rudimentaire",new Equipment(0,0,animation.getImage(26),"Epee rudimentaire",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee metallique",new Equipment(0,0,animation.getImage(27),"Epee metallique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee d'argent",new Equipment(0,0,animation.getImage(28),"Epee d'argent",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee Royale",new Equipment(0,0,animation.getImage(29),"Epee Royale",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Banane",new Equipment(0,0,animation.getImage(30),"Banane",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee legendaire",new Equipment(0,0,animation.getImage(31),"Epee legendaire",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee de glace",new Equipment(0,0,animation.getImage(32),"Epee de glace",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee rose",new Equipment(0,0,animation.getImage(33),"Epee rose",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Epee extraterrestre",new Equipment(0,0,animation.getImage(34),"Epee extraterrestre",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache de bois",new Equipment(0,0,animation.getImage(35),"Hache de bois",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache rudimentaire",new Equipment(0,0,animation.getImage(36),"Hache rudimentaire",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache metallique",new Equipment(0,0,animation.getImage(37),"Hache metallique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache d'argent",new Equipment(0,0,animation.getImage(38),"Hache d'argent",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache Royale",new Equipment(0,0,animation.getImage(39),"Hache Royale",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache lourde",new Equipment(0,0,animation.getImage(40),"Hache lourde",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache legere",new Equipment(0,0,animation.getImage(41),"Hache legere",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache de feu",new Equipment(0,0,animation.getImage(42),"Hache de feu",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache sinistre",new Equipment(0,0,animation.getImage(43),"Hache sinistre",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Hache artisanale",new Equipment(0,0,animation.getImage(44),"Hache artisanale",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre clair",new Equipment(0,0,animation.getImage(45),"Sceptre clair",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre offensif",new Equipment(0,0,animation.getImage(46),"Sceptre offensif",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Tri-dent magique",new Equipment(0,0,animation.getImage(47),"Tri-dent magique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre etrangleur",new Equipment(0,0,animation.getImage(48),"Sceptre etrangleur",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Baton Large",new Equipment(0,0,animation.getImage(49),"Baton Large",
                "That's what she said",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre clair 2.0",new Equipment(0,0,animation.getImage(50),"Sceptre clair 2.0",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre metallique",new Equipment(0,0,animation.getImage(51),"Sceptre metallique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre jaune",new Equipment(0,0,animation.getImage(52),"Sceptre jaune",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Cane de Noel",new Equipment(0,0,animation.getImage(53),"Cane de Noel",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Sceptre precieux",new Equipment(0,0,animation.getImage(54),"Sceptre precieux",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc simple",new Equipment(0,0,animation.getImage(55),"Arc simple",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc elegant",new Equipment(0,0,animation.getImage(56),"Arc elegant",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc lourd",new Equipment(0,0,animation.getImage(57),"Arc lourd",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc sombre",new Equipment(0,0,animation.getImage(58),"Arc sombre",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc clair",new Equipment(0,0,animation.getImage(59),"Arc clair",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc infernal",new Equipment(0,0,animation.getImage(60),"Arc infernal",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc de feu",new Equipment(0,0,animation.getImage(61),"Arc de feu",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc angelique",new Equipment(0,0,animation.getImage(62),"Arc angelique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc metallique",new Equipment(0,0,animation.getImage(63),"Arc metallique",
                "",new int[]{1,1,1,1,1,1}));
        listEquipment.put("Arc artisanal",new Equipment(0,0,animation.getImage(64),"Arc artisanal",
                "",new int[]{1,1,1,1,1,1}));
    }
    
    public HashMap<String, Equipment> getListEquipment() {
        return listEquipment;
    }
}
