/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import items.Equipment;
import static items.Equipment.MAX_STATS;
import items.EquipmentList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import statesOfGame.Overworld;

/**
 *
 * @author 1455367
 */
public class CharacterStatsManager {

    private static CharacterStatsManager instance = null;

    public final int NB_LVL_BOOST = 5;
    protected int[] stats = new int[MAX_STATS];
    protected int[] statsUpgrade = new int[]{0, 0, 0, 0, 0, 0, 0};
    protected int hp;
    protected int expNeeded;
    protected int exp;
    protected int money;
    protected int level;
    protected int nbLvlBoost = 0;
    protected Image anim;
    protected boolean lvlIsUp = false;
    private int potionCount = 1;

    private Inventory inventory;

    /**
     * Crée le gestionnaire des statistiques du personnage
     */
    private CharacterStatsManager() {

        hp = 100;
        stats[0] = 100;
        stats[1] = 5;
        stats[2] = 5;
        stats[3] = 5;
        stats[4] = 5;
        stats[5] = 10;
        stats[6] = 5000;
        this.potionCount = 1;
        this.expNeeded = 50;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.anim = null;
        this.inventory = new Inventory();
    }

    /**
     * Pour s'assurer qu'il n'y ait qu'une seule classe comme celle-ci
     *
     * @return une instance
     * @throws SlickException
     */
    public static CharacterStatsManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new CharacterStatsManager();
        }
        return instance;
    }

    /**
     * Change l'expérience et le niveau du personnage
     *
     * @param expNeeded l'expérience nécéssaire
     * @param exp l'expérience déjà acquise
     * @param level le niveau du joueur
     * @param anim l'image
     */
    public void setXPStats(int expNeeded, int exp, int level, Image anim) {
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
    }

    /**
     * Le joueur gagne de l'expérience
     *
     * @param expGained le nombre d'expérience gagné
     */
    public void gainExp(int expGained) {
        exp += expGained;
        if (exp >= expNeeded) {
            levelUp();
        }
    }

    /**
     * Le joueur gagne de l'argent
     *
     * @param moneyGained le nombre d'argent gagné
     */
    public void gainMoney(int moneyGained) {
        money += moneyGained;
    }

    /**
     * le joueur gagne un niveau
     */
    public void levelUp() {
        exp = 0;
        expNeeded = (int) ((float) expNeeded * 1.84);
        level++;
        nbLvlBoost += NB_LVL_BOOST;
        lvlIsUp = true;
    }

    /**
     * le joueur augmente ces statistiques
     *
     * @param index quelle statistiques qui augmente
     * @param onlyOnce si on augmente défense
     */
    public void buffStat(int index, boolean onlyOnce) {
        System.out.println(index);
        stats[index] += 1;
        statsUpgrade[index] += 1;
        if (onlyOnce) {
            nbLvlBoost -= 1;
        }
    }

    /**
     * le joueur perd des statistiques
     *
     * @param index quelle statistiques qui augmente
     * @param onlyOnce si on augmente défense
     */
    public void nerfStat(int index, boolean onlyOnce) {
        stats[index] -= 1;
        statsUpgrade[index] -= 1;
        if (onlyOnce) {
            nbLvlBoost += 1;
        }
    }

    /**
     * réinitialiser les statistiques
     */
    public void resetStats() {
        for (int j = 0; j < 5; j++) {
            stats[j] -= statsUpgrade[j];
            if (j != 4) {
                nbLvlBoost += statsUpgrade[j];
            }
            statsUpgrade[j] = 0;
        }
    }

    /**
     * retourne le tableau des statistiques
     *
     * @return le tableau des statistiques
     */
    public int[] getStats() {
        return stats;
    }

    /**
     * retourne l'argent du joueur
     *
     * @return l'argent du joueur
     */
    public int getMoney() {
        return money;
    }

    /**
     * retourne les statistiques qui ont augmenté
     *
     * @return les statistiques qui ont augmenté
     */
    public int[] getStatsUpgrade() {
        return statsUpgrade;
    }

    /**
     * change la valeur des statistiques ajoutées à une valeur
     *
     * @param i la statistique choisie
     * @param change la valeur choisie
     */
    public void setStatsUpgrade(int i, int change) {
        this.statsUpgrade[i] = change;
    }

    /**
     * change une statistique a une valeur discrète
     *
     * @param index la statistique a changer
     * @param stat la valeur de la statistique
     */
    public void setStats(int index, int stat) {
        stats[index] = stat;
    }

    /**
     * retourne l'image de l'objet
     *
     * @return l'image de l'objet
     */
    public Image getAnim() {
        return anim;
    }

    /**
     * retourne les points de vie
     *
     * @return les points de vie
     */
    public int getHp() {
        return hp;
    }

    /**
     * retourne le nom de la statistique
     *
     * @param x le numéro de la stitistique
     * @return le nom de la statistique
     */
    public String getStatsName(int x) {
        switch (x) {
            case 0:
                return "HP- ";
            case 1:
                return "Attaque- ";
            case 2:
                return "Defense- ";
            case 3:
                return "Special Attaque- ";
            case 4:
                return "Special Defense- ";
            case 5:
                return "Vitesse- ";
            case 6:
                return "Energie- ";
            default:
                return "Noob";
        }
    }

    /**
     * retourne l'expérience du joueur
     *
     * @return l'expérience du joueur
     */
    public int getExp() {
        return exp;
    }

    /**
     * retourne l'expérience que le joueur a besoin pour son prochain niveau
     *
     * @return l'expérience que le joueur a besoin pour son prochain niveau
     */
    public int getExpNeeded() {
        return expNeeded;
    }

    /**
     * retourne le niveau du joueur
     *
     * @return le niveau du joueur
     */
    public int getLevel() {
        return level;
    }

    /**
     * retourne le nombre de statistique à augmenter lors d'un changement de
     * niveau
     *
     * @return le nombre de statistique à augmenter lors d'un changement de
     * niveau
     */
    public int getLvlBoost() {
        return nbLvlBoost;
    }

    /**
     * change l'image
     *
     * @param anim la nouvelle image
     */
    public void setAnim(Image anim) {
        this.anim = anim;
    }

    /**
     * retourne si le joueur change de niveau
     *
     * @return si le joueur change de niveau
     */
    public boolean getlvlIsUp() {
        return lvlIsUp;
    }

    /**
     * change si le joueur change de niveau
     *
     * @param bool le nouveau booléen
     */
    public void setlvlIsUp(boolean bool) {
        this.lvlIsUp = bool;
    }

    /**
     * Le joueur prend du dégat
     *
     * @param damage le dégat physique de l'ennemi
     * @param damageSpecial le dégat magique de l'ennemi
     */
    public void takeDamage(int damage, int damageSpecial) {
        this.hp -= damage * (100 - this.stats[2]) / 100;
        this.hp -= damageSpecial * (100 - this.stats[4]) / 100;
        if (this.hp <= 0) {
            this.hp = 0;
            Overworld.setGameOver(true);
        } else if (this.hp > this.stats[0]) {
            this.hp = this.stats[0];
        }
    }

    /**
     * change les points de vie du joueur
     *
     * @param hp les points de vie perdu
     */
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        } else if (this.hp > this.stats[0]) {
            this.hp = this.stats[0];
        }
    }

    /**
     * change l'expérience du joueur
     *
     * @param exp la nouvelle expérience
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * change l'expérience que le joueur a besoin pour changer de niveau
     *
     * @param expNeeded la nouvelle expérience requise
     */
    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    /**
     * change le niveau du joueur
     *
     * @param level le nouveau niveau
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * le joueur achète un équipement
     *
     * @param moneySpent le nombre d'argent dépensé
     */
    public void buyItem(int moneySpent) {
        money -= moneySpent;
    }

    public void addItem(String newItem) throws SlickException {
        boolean addOk = true;
        for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemFound()) {
            if (EquipmentList.getInstance().getListEquipment().get(newItem) == equipment) {
                addOk = false;
            }
        }
        for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {
            if (EquipmentList.getInstance().getListEquipment().get(newItem) == equipment) {
                addOk = false;
            }
        }
        if (addOk) {
            this.getInventory().getListItemFound().add(EquipmentList.getInstance().getListEquipment().get(newItem));
        }
    }

    /**
     * rajoute un équipement dans l'inventaire du joueur
     *
     * @param equipment l'équipement à ajouter
     */
    public void addItem(Equipment equipment) {
        this.getInventory().getListItemFound().add(equipment);
    }

    /**
     * retourne l'inventaire
     *
     * @return l'inventaire
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * guérir le joueur
     *
     * @param heal les points de vie guérit
     */
    public void heal(int heal) {
        this.hp += heal;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        } else if (this.hp > this.stats[0]) {
            this.hp = this.stats[0];
        }
    }

    /**
     * réinitialise les statistiques du joueur
     */
    public void reset() {
        hp = 100;
        stats[0] = 100;
        stats[1] = 5;
        stats[2] = 5;
        stats[3] = 5;
        stats[4] = 5;
        stats[5] = 10;
        stats[6] = 10;
        this.potionCount = 1;
        this.expNeeded = 50;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.anim = null;
        this.inventory = new Inventory();
    }

    /**
     * mise à jour du temps de chargement du sort du joueur
     */

    public void updateCooldown(int delta) {
        if (stats[6] < 5000) {
            stats[6] += delta;
        }
        if (stats[6] > 5000) {
            stats[6] = 5000;
        }
    }
    
    /**
     * ajoute une potion à l'inventaire
     */
    public void pickUpPotion(){
        potionCount ++;
    }
    
    /**
     * utilise une potion si il y en a au moins une
     */
    public void drinkPotion(){
        if (potionCount > 0){
            this.heal((int)(stats[0]*0.5f));
            potionCount--;
        }
    }
    
    public int getPotionNb(){
        return potionCount;
    }

    public void setPotionCount(int potionCount) {
        this.potionCount = potionCount;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    
}
