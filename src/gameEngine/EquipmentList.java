/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import items.Equipment;
import items.EquipmentType;
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
        for (int i = 0; i < SPRITE_SHEET_HEIGHT; i++) {
            for (int j = 0; j < SPRITE_SHEET_WIDTH; j++) {
                animation.addFrame(spriteSheet.getSprite(j, i), 100);
            }
            
        }
        
        listEquipment.put("Casque Antique",new Equipment(0,0,animation.getImage(0),"Casque Antique",
                "Casque ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nOffre une petite augmentation de la vie \nmaximale",
                new int[]{40,0,0,0,0,0,0}, EquipmentType.HELMET));
        listEquipment.put("Casque Metallique",new Equipment(0,0,animation.getImage(1),"Casque Metallique",
                "Casque en métal moderne. Procure une bonne \nOffre une bonne augmentation de la vie maximale",
                new int[]{80,0,0,0,0,0,0},EquipmentType.HELMET));
        listEquipment.put("Casque d'Argent",new Equipment(0,0,animation.getImage(2),"Casque d'Argent",
                "A appartenu a un général emerite. Offre une \ntrès bonne augmentation de la vie maximale",
                new int[]{120,0,0,0,0,0,0},EquipmentType.HELMET));
        listEquipment.put("Casque Emeraude",new Equipment(0,0,animation.getImage(3),"Casque Emeraude",
                "Casque forgé par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nla vie maximale",
                new int[]{160,0,0,0,0,0,0},EquipmentType.HELMET));
        listEquipment.put("Casque Royal",new Equipment(0,0,animation.getImage(4),"Casque Royal",
                "Casque dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Augmentation vitale maximale",
                new int[]{200,0,0,0,0,0,0},EquipmentType.HELMET));
        listEquipment.put("Armure Antique",new Equipment(0,0,animation.getImage(5),"Armure Antique",
                "Armure ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",
                new int[]{0,0,10,0,0,0,0},EquipmentType.ARMOR));
        listEquipment.put("Armure Metallique",new Equipment(0,0,animation.getImage(6),"Armure Metallique",
                "Armure en métal moderne. Procure une bonne \nprotection contre les attaques physiques",
                new int[]{0,0,20,0,0,0,0},EquipmentType.ARMOR));
        listEquipment.put("Armure d'Argent",new Equipment(0,0,animation.getImage(7),"Armure d'Argent",
                "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nphysiques",
                new int[]{0,0,30,0,0,0,0},EquipmentType.ARMOR));
        listEquipment.put("Armure Emeraude",new Equipment(0,0,animation.getImage(8),"Armure Emeraude",
                "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques physiques",
                new int[]{0,0,40,0,0,0,0},EquipmentType.ARMOR));
        listEquipment.put("Armure Royale",new Equipment(0,0,animation.getImage(9),"Armure Royale",
                "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",
                new int[]{0,0,50,0,0,0,0},EquipmentType.ARMOR));
        listEquipment.put("Jambieres Antiques",new Equipment(0,0,animation.getImage(10),"Jambieres Antiques",
                "Jambieres ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques magiques",
                new int[]{0,0,0,0,10,0,0},EquipmentType.PADS));
        listEquipment.put("Jambieres Metalliques",new Equipment(0,0,animation.getImage(11),"Jambieres Metalliques",
                "Jambieres en métal moderne. Procure une bonne \nOffre une bonne protection contre les attaques magique",
                new int[]{0,0,0,0,20,0,0},EquipmentType.PADS));
        listEquipment.put("Jambieres d'Argent",new Equipment(0,0,animation.getImage(12),"Jambieres d'Argent",
                "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nmagiques",
                new int[]{0,0,0,0,30,0,0},EquipmentType.PADS));
        listEquipment.put("Jambieres Emeraudes",new Equipment(0,0,animation.getImage(13),"Jambieres Emeraudes",
                "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques magiques",
                new int[]{0,0,0,0,40,0,0},EquipmentType.PADS));
        listEquipment.put("Jambieres Royales",new Equipment(0,0,animation.getImage(14),"Jambieres Royales",
                "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",
                new int[]{0,0,0,0,50,0,0},EquipmentType.PADS));
        listEquipment.put("Bottes Antiques",new Equipment(0,0,animation.getImage(15),"Bottes Antiques",
                "Bottes ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nPermet de courrir un peu plus vite",
                new int[]{0,0,0,0,0,0,1},EquipmentType.BOOTS));
        listEquipment.put("Bottes Metalliques",new Equipment(0,0,animation.getImage(16),"Bottes Metalliques",
                "Bottes en métal moderne. Procure une bonne \nPermet de courrir plus vite",
                new int[]{0,0,0,0,0,0,2},EquipmentType.BOOTS));
        listEquipment.put("Bottes d'Argent",new Equipment(0,0,animation.getImage(17),"Bottes d'Argent",
                "A appartenu a un général emerite. Permet de\ncourrir encore plus vite",
                new int[]{0,0,0,0,0,0,3},EquipmentType.BOOTS));
        listEquipment.put("Bottes Emeraudes",new Equipment(0,0,animation.getImage(18),"Bottes Emeraudes",
                "Bottes forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, permentant\nde courrir beaucoup plus vite",
                new int[]{0,0,0,0,0,0,4},EquipmentType.BOOTS));
        listEquipment.put("Bottes Royales",new Equipment(0,0,animation.getImage(19),"Bottes Royales",
                "Bottes dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Vitesse maximale",
                new int[]{0,0,0,0,0,0,5},EquipmentType.BOOTS));
        listEquipment.put("Bouclier Serpent",new Equipment(0,0,animation.getImage(20),"Bouclier Serpent",
                "Bouclier incruster d'une efigie de Serpent.\nIl offre une restiance contre la magie",
                new int[]{0,0,0,0,5,0,0},EquipmentType.SHIELD));
        listEquipment.put("Bouclier de bois",new Equipment(0,0,animation.getImage(21),"Bouclier de bois",
                "Simple boulcier de bois. Il offre une\nresistance contre les attaques physiques",
                new int[]{0,0,5,0,0,0,0},EquipmentType.SHIELD));
        listEquipment.put("Bouclier legendaire",new Equipment(0,0,animation.getImage(22),"Bouclier legendaire",
                "Bouclier ayant appartenue à un héro\nd'un ancien pays. Il offre une grande resistance\ncontre les attaques physiques et magiques",
                new int[]{0,0,10,0,10,0,0},EquipmentType.SHIELD));
        listEquipment.put("Bouclier simple",new Equipment(0,0,animation.getImage(23),"Bouclier simple",
                "Bouclier simple mais robuste, utilisé par\nles soldats lors des guerres. Il offre\nune grande résistance au attaques physiques.",
                new int[]{0,0,20,0,0,0,0},EquipmentType.SHIELD));
        listEquipment.put("Jesus",new Equipment(0,0,animation.getImage(24),"Jesus",
                "Où que vous ayez, Jesus notre sauveur veillera\nsur vous, vous protègeant des attaques magiques",
                new int[]{0,0,0,0,20,0,0},EquipmentType.SHIELD));
        listEquipment.put("Epee de bois",new Equipment(0,0,animation.getImage(25),"Epee de bois",
                "Simple épée de bois. Elle ne fait\nque peu de dégat",
                new int[]{0,5,0,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee rudimentaire",new Equipment(0,0,animation.getImage(26),"Epee rudimentaire",
                "Cette épée fut construite à la\nmanière des anciens chasseur. Elle est\nrustique mais efficasse.",
                new int[]{0,10,0,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee metallique",new Equipment(0,0,animation.getImage(27),"Epee metallique",
                "Cette arme est généralement utilisé\npar les soldats sur le champs de\nbattaille",
                new int[]{0,15,0,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee d'argent",new Equipment(0,0,animation.getImage(28),"Epee d'argent",
                "Lame forgé par le meilleur forgeron\ndu pays. Elle ne vous décevera pas",
                new int[]{0,20,0,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee Royale",new Equipment(0,0,animation.getImage(29),"Epee Royale",
                "Épée dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant",
                new int[]{0,25,0,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Banane",new Equipment(0,0,animation.getImage(30),"Banane",
                "Pourquoi utilisé une banane en combat?\nPourquoi pas!",
                new int[]{0,5,0,5,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee legendaire",new Equipment(0,0,animation.getImage(31),"Epee legendaire",
                "Épée ayant appartenue à un héro\nd'un ancien pays. Il fait du domage physique\net magique",
                new int[]{0,15,0,15,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee de glace",new Equipment(0,0,animation.getImage(32),"Epee de glace",
                "Le simple fait de tenir cette arme\nvous recouvre de glace, vous protègeant des\nattaques physiques",
                new int[]{0,15,10,0,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee rose",new Equipment(0,0,animation.getImage(33),"Epee rose",
                "Vous aurez l'air magnifique evec\ncette arme! Votre charme vous confère\nplus de résistance à la magie",
                new int[]{0,15,0,0,10,0,0},EquipmentType.SWORD));
        listEquipment.put("Epee extraterrestre",new Equipment(0,0,animation.getImage(34),"Epee extraterrestre",
                "Cette arme, qui ne semble pas avoir été\ncréer sur terre, ne fera que des\ndégats magiques. Curieux..",
                new int[]{0,0,0,25,0,0,0},EquipmentType.SWORD));
        listEquipment.put("Livre bleu",new Equipment(0,0,animation.getImage(35),"Livre bleu",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre violet",new Equipment(0,0,animation.getImage(36),"Livre violet",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre rouge",new Equipment(0,0,animation.getImage(37),"Livre rouge",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre mauve",new Equipment(0,0,animation.getImage(38),"Livre mauve",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre beige",new Equipment(0,0,animation.getImage(39),"Livre beige",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre brun",new Equipment(0,0,animation.getImage(40),"Livre brun",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre gris",new Equipment(0,0,animation.getImage(41),"Livre gris",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre vert",new Equipment(0,0,animation.getImage(42),"Livre vert",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre cyan",new Equipment(0,0,animation.getImage(43),"Livre cyan",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Livre orange",new Equipment(0,0,animation.getImage(44),"Livre orange",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.SPELL_TOME));
        listEquipment.put("Arc simple",new Equipment(0,0,animation.getImage(50),"Arc simple",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc elegant",new Equipment(0,0,animation.getImage(51),"Arc elegant",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc lourd",new Equipment(0,0,animation.getImage(52),"Arc lourd",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc sombre",new Equipment(0,0,animation.getImage(53),"Arc sombre",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc clair",new Equipment(0,0,animation.getImage(54),"Arc clair",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc infernal",new Equipment(0,0,animation.getImage(55),"Arc infernal",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc de feu",new Equipment(0,0,animation.getImage(56),"Arc de feu",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc angelique",new Equipment(0,0,animation.getImage(57),"Arc angelique",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc metallique",new Equipment(0,0,animation.getImage(58),"Arc metallique",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
        listEquipment.put("Arc artisanal",new Equipment(0,0,animation.getImage(59),"Arc artisanal",
                "",
                new int[]{0,0,0,0,0,0,0},EquipmentType.BOW));
    }
    
    public HashMap<String, Equipment> getListEquipment() {
        return listEquipment;
    }
    
    public Equipment getEquipment(String name){
        return listEquipment.get(name);
    }
}
