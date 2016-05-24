/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import gameEngine.ResManager;
import java.io.Serializable;
import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1485246
 */
public class EquipmentList implements Serializable{
    
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
        for (int i = 0; i < SPRITE_SHEET_HEIGHT; i++) {
            for (int j = 0; j < SPRITE_SHEET_WIDTH; j++) {
                animation.addFrame(spriteSheet.getSprite(j, i), 100);
            }
            
        }
        
        listEquipment.put("Casque Antique",new Equipment(0,0,"Casque Antique",
                "Casque ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nOffre une petite augmentation de la vie \nmaximale",
                new int[]{40,0,0,0,0,0,0}, EquipmentType.HELMET,25));
        listEquipment.put("Casque Metallique",new Equipment(0,0,"Casque Metallique",
                "Casque en métal moderne. Procure une bonne \nOffre une bonne augmentation de la vie maximale",
                new int[]{80,0,0,0,0,0,0},EquipmentType.HELMET,50));
        listEquipment.put("Casque d'Argent",new Equipment(0,0,"Casque d'Argent",
                "A appartenu a un général emerite. Offre une \ntrès bonne augmentation de la vie maximale",
                new int[]{120,0,0,0,0,0,0},EquipmentType.HELMET,75));
        listEquipment.put("Casque Emeraude",new Equipment(0,0,"Casque Emeraude",
                "Casque forgé par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nla vie maximale",
                new int[]{160,0,0,0,0,0,0},EquipmentType.HELMET,100));
        listEquipment.put("Casque Royal",new Equipment(0,0,"Casque Royal",
                "Casque dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Augmentation vitale maximale",
                new int[]{200,0,0,0,0,0,0},EquipmentType.HELMET,125));
        listEquipment.put("Armure Antique",new Equipment(0,0,"Armure Antique",
                "Armure ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",
                new int[]{0,0,10,0,0,0,0},EquipmentType.ARMOR,25));
        listEquipment.put("Armure Metallique",new Equipment(0,0,"Armure Metallique",
                "Armure en métal moderne. Procure une bonne \nprotection contre les attaques physiques",
                new int[]{0,0,20,0,0,0,0},EquipmentType.ARMOR,50));
        listEquipment.put("Armure d'Argent",new Equipment(0,0,"Armure d'Argent",
                "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nphysiques",
                new int[]{0,0,30,0,0,0,0},EquipmentType.ARMOR,75));
        listEquipment.put("Armure Emeraude",new Equipment(0,0,"Armure Emeraude",
                "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques physiques",
                new int[]{0,0,40,0,0,0,0},EquipmentType.ARMOR,100));
        listEquipment.put("Armure Royale",new Equipment(0,0,"Armure Royale",
                "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",
                new int[]{0,0,50,0,0,0,0},EquipmentType.ARMOR,125));
        listEquipment.put("Jambieres Antiques",new Equipment(0,0,"Jambieres Antiques",
                "Jambieres ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques magiques",
                new int[]{0,0,0,0,10,0,0},EquipmentType.PADS,25));
        listEquipment.put("Jambieres Metalliques",new Equipment(0,0,"Jambieres Metalliques",
                "Jambieres en métal moderne. Procure une bonne \nOffre une bonne protection contre les attaques magique",
                new int[]{0,0,0,0,20,0,0},EquipmentType.PADS,50));
        listEquipment.put("Jambieres d'Argent",new Equipment(0,0,"Jambieres d'Argent",
                "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nmagiques",
                new int[]{0,0,0,0,30,0,0},EquipmentType.PADS,75));
        listEquipment.put("Jambieres Emeraudes",new Equipment(0,0,"Jambieres Emeraudes",
                "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques magiques",
                new int[]{0,0,0,0,40,0,0},EquipmentType.PADS,100));
        listEquipment.put("Jambieres Royales",new Equipment(0,0,"Jambieres Royales",
                "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",
                new int[]{0,0,0,0,50,0,0},EquipmentType.PADS,125));
        listEquipment.put("Bottes Antiques",new Equipment(0,0,"Bottes Antiques",
                "Bottes ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nPermet de courrir un peu plus vite",
                new int[]{0,0,0,0,0,0,1},EquipmentType.BOOTS,25));
        listEquipment.put("Bottes Metalliques",new Equipment(0,0,"Bottes Metalliques",
                "Bottes en métal moderne. Procure une bonne \nPermet de courrir plus vite",
                new int[]{0,0,0,0,0,0,2},EquipmentType.BOOTS,50));
        listEquipment.put("Bottes d'Argent",new Equipment(0,0,"Bottes d'Argent",
                "A appartenu a un général emerite. Permet de\ncourrir encore plus vite",
                new int[]{0,0,0,0,0,0,3},EquipmentType.BOOTS,75));
        listEquipment.put("Bottes Emeraudes",new Equipment(0,0,"Bottes Emeraudes",
                "Bottes forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, permentant\nde courrir beaucoup plus vite",
                new int[]{0,0,0,0,0,0,4},EquipmentType.BOOTS,100));
        listEquipment.put("Bottes Royales",new Equipment(0,0,"Bottes Royales",
                "Bottes dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Vitesse maximale",
                new int[]{0,0,0,0,0,0,5},EquipmentType.BOOTS,125));
        listEquipment.put("Bouclier Serpent",new Equipment(0,0,"Bouclier Serpent",
                "Bouclier incruster d'une efigie de Serpent.\nIl offre une restiance contre la magie",
                new int[]{0,0,0,0,5,0,0},EquipmentType.SHIELD,25));
        listEquipment.put("Bouclier de bois",new Equipment(0,0,"Bouclier de bois",
                "Simple boulcier de bois. Il offre une\nresistance contre les attaques physiques",
                new int[]{0,0,5,0,0,0,0},EquipmentType.SHIELD,25));
        listEquipment.put("Bouclier legendaire",new Equipment(0,0,"Bouclier legendaire",
                "Bouclier ayant appartenue à un héro\nd'un ancien pays. Il offre une grande resistance\ncontre les attaques physiques et magiques",
                new int[]{0,0,10,0,10,0,0},EquipmentType.SHIELD,125));
        listEquipment.put("Bouclier simple",new Equipment(0,0,"Bouclier simple",
                "Bouclier simple mais robuste, utilisé par\nles soldats lors des guerres. Il offre\nune grande résistance au attaques physiques.",
                new int[]{0,0,20,0,0,0,0},EquipmentType.SHIELD,75));
        listEquipment.put("Jesus",new Equipment(0,0,"Jesus",
                "Où que vous ayez, Jesus notre sauveur veillera\nsur vous, vous protègeant des attaques magiques",
                new int[]{0,0,0,0,20,0,0},EquipmentType.SHIELD,75));
        listEquipment.put("Epee de bois",new Equipment(0,0,"Epee de bois",
                "Simple épée de bois. Elle ne fait\nque peu de dégat",
                new int[]{0,5,0,0,0,0,0},EquipmentType.SWORD,10));
        listEquipment.put("Epee rudimentaire",new Equipment(0,0,"Epee rudimentaire",
                "Cette épée fut construite à la\nmanière des anciens chasseur. Elle est\nrustique mais efficasse.",
                new int[]{0,10,0,0,0,0,0},EquipmentType.SWORD,25));
        listEquipment.put("Epee metallique",new Equipment(0,0,"Epee metallique",
                "Cette arme est généralement utilisé\npar les soldats sur le champs de\nbattaille",
                new int[]{0,15,0,0,0,0,0},EquipmentType.SWORD,50));
        listEquipment.put("Epee d'argent",new Equipment(0,0,"Epee d'argent",
                "Lame forgé par le meilleur forgeron\ndu pays. Elle ne vous décevera pas",
                new int[]{0,20,0,0,0,0,0},EquipmentType.SWORD,75));
        listEquipment.put("Epee Royale",new Equipment(0,0,"Epee Royale",
                "Épée dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant",
                new int[]{0,25,0,0,0,0,0},EquipmentType.SWORD,100));
        listEquipment.put("Banane",new Equipment(0,0,"Banane",
                "Pourquoi utilisé une banane en combat?\nPourquoi pas!",
                new int[]{0,5,0,5,0,0,0},EquipmentType.SWORD,0));
        listEquipment.put("Epee legendaire",new Equipment(0,0,"Epee legendaire",
                "Épée ayant appartenue à un héro\nd'un ancien pays. Il fait du domage physique\net magique",
                new int[]{0,15,0,15,0,0,0},EquipmentType.SWORD,0));
        listEquipment.put("Epee de glace",new Equipment(0,0,"Epee de glace",
                "Le simple fait de tenir cette arme\nvous recouvre de glace, vous protègeant des\nattaques physiques",
                new int[]{0,15,10,0,0,0,0},EquipmentType.SWORD,0));
        listEquipment.put("Epee rose",new Equipment(0,0,"Epee rose",
                "Vous aurez l'air magnifique evec\ncette arme! Votre charme vous confère\nplus de résistance à la magie",
                new int[]{0,15,0,0,10,0,0},EquipmentType.SWORD,0));
        listEquipment.put("Epee extraterrestre",new Equipment(0,0,"Epee extraterrestre",
                "Cette arme, qui ne semble pas avoir été\ncréer sur terre, ne fera que des\ndégats magiques. Curieux...",
                new int[]{0,0,0,25,0,0,0},EquipmentType.SWORD,0));
        listEquipment.put("Livre bleu",new Equipment(0,0,"Livre bleu",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre violet",new Equipment(0,0,"Livre violet",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre rouge",new Equipment(0,0,"Livre rouge",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre mauve",new Equipment(0,0,"Livre mauve",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre beige",new Equipment(0,0,"Livre beige",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre brun",new Equipment(0,0,"Livre brun",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre gris",new Equipment(0,0,"Livre gris",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre vert",new Equipment(0,0,"Livre vert",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre cyan",new Equipment(0,0,"Livre cyan",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Livre orange",new Equipment(0,0,"Livre orange",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME,0));
        listEquipment.put("Arc simple",new Equipment(0,0,"Arc simple",
                "Cet arc fera peu de dégat, mais\nvous permettra de vous défendre\nà distance",
                new int[]{0,5,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc elegant",new Equipment(0,0,"Arc elegant",
                "Cet arc, fabriqué par un artisan\nde renom",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc lourd",new Equipment(0,0,"Arc lourd",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc sombre",new Equipment(0,0,"Arc sombre",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc clair",new Equipment(0,0,"Arc clair",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc infernal",new Equipment(0,0,"Arc infernal",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc de feu",new Equipment(0,0,"Arc de feu",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc angelique",new Equipment(0,0,"Arc angelique",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc metallique",new Equipment(0,0,"Arc metallique",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        listEquipment.put("Arc artisanal",new Equipment(0,0,"Arc artisanal",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW,0));
        
    }
    
    public HashMap<String, Equipment> getListEquipment() {
        return listEquipment;
    }
    
    public Equipment getEquipment(String name){
        return listEquipment.get(name);
    }
}
