/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import items.Equipment;
import static items.Equipment.MAX_STATS;
import items.EquipmentList;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1455367
 */
public class CharacterStatsManager {

    private static CharacterStatsManager instance = null;

    /**
     *
     */
    public final int NB_LVL_BOOST = 5;

    /**
     *
     */
    protected int[] stats = new int[MAX_STATS];

    /**
     *
     */
    protected int[] statsUpgrade = new int[]{0,0,0,0,0,0,0};

    /**
     *
     */
    protected int hp;

    /**
     *
     */
    protected int expNeeded;

    /**
     *
     */
    protected int exp;

    /**
     *
     */
    protected int money;

    /**
     *
     */
    protected int level;

    /**
     *
     */
    protected int nbLvlBoost = 0;

    /**
     *
     */
    protected ArrayList skillSet;

    /**
     *
     */
    protected Image anim;

    /**
     *
     */
    protected boolean lvlIsUp = false;
    private Inventory inventory;

    private CharacterStatsManager() {
        
        hp = 100;
        stats[0] = 100;
        stats[1] = 5;
        stats[2] = 5;
        stats[3] = 5;
        stats[4] = 5;
        stats[5] = 10;
        stats[6] = 10;

        this.expNeeded = 50;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.anim = null;
        this.inventory = new Inventory();
    }

    /**
     *
     * @return
     * @throws SlickException
     */
    public static CharacterStatsManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new CharacterStatsManager();
        }
        return instance;
    }

    /**
     *
     * @param expNeeded
     * @param exp
     * @param level
     * @param anim
     */
    public void setXPStats(int expNeeded, int exp, int level, Image anim) {
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
    }

    /**
     *
     * @param expGained
     */
    public void gainExp(int expGained) {
        exp += expGained;
        if (exp >= expNeeded) {
            levelUp();
        }
    }
    
    /**
     *
     * @param moneyGained
     */
    public void gainMoney(int moneyGained){
        money += moneyGained;
    }

    /**
     *
     */
    public void levelUp() {
            exp = 0;
            expNeeded = (int) ((float) expNeeded * 1.84);
            level++;
            nbLvlBoost += NB_LVL_BOOST;
            lvlIsUp = true;
    }
    
    /**
     *
     * @param index
     * @param onlyOnce
     */
    public void buffStat(int index, boolean onlyOnce){
        System.out.println(index);
        stats[index] += 1;
        statsUpgrade[index] += 1;
        if(onlyOnce){
            nbLvlBoost -= 1;
        }
    }
    
    /**
     *
     * @param index
     * @param onlyOnce
     */
    public void nerfStat(int index, boolean onlyOnce){
        stats[index] -= 1;
        statsUpgrade[index] -= 1;
        if(onlyOnce){
            nbLvlBoost += 1;
        }
    }
    
    /**
     *
     */
    public void resetStats(){
        for (int j = 0; j < 5; j++) {
            stats[j] -= statsUpgrade[j];
            if(j != 4){
                nbLvlBoost += statsUpgrade[j];
            }
            statsUpgrade[j] = 0;
        }
    }

    /**
     *
     * @return
     */
    public int[] getStats() {
        return stats;
    }
    
    /**
     *
     * @return
     */
    public int getMoney(){
        return money;
    }
    
    /**
     *
     * @return
     */
    public int[] getStatsUpgrade(){
        return statsUpgrade;
    }
    
    /**
     *
     * @param i
     * @param change
     */
    public void setStatsUpgrade(int i, int change){
        this.statsUpgrade[i] = change;
    }
    
    /**
     *
     * @param index
     * @param stat
     */
    public void setStats(int index, int stat){
        stats[index] = stat;
    }

    /**
     *
     * @return
     */
    public Image getAnim() {
        return anim;
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     *
     * @param x
     * @return
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
                return "Speed- ";
            case 6 : 
                return "Energie- ";
            default:
                return "Noob";
        }
    }

    /**
     *
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     *
     * @return
     */
    public int getExpNeeded() {
        return expNeeded;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }
    
    /**
     *
     * @return
     */
    public int getLvlBoost(){
        return nbLvlBoost;
    }
    
    /**
     *
     * @return
     */
    public ArrayList getSkillSet() {
        return skillSet;
    }

    /**
     *
     * @param anim
     */
    public void setAnim(Image anim) {
        this.anim = anim;
    }
    
    /**
     *
     * @return
     */
    public boolean getlvlIsUp(){
        return lvlIsUp;
    }
    
    /**
     *
     * @param bool
     */
    public void setlvlIsUp(boolean bool){
        this.lvlIsUp = bool;
    }

    /**
     *
     * @param damage
     * @param damageSpecial
     */
    public void takeDamage(int damage, int damageSpecial){
        this.hp -= damage * (100-this.stats[2])/100;
        this.hp -= damageSpecial * (100-this.stats[4])/100;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        }
        else if (this.hp > this.stats[0]){
            this.hp = this.stats[0];
        }
    }
    
    /**
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        }
        else if (this.hp > this.stats[0]){
            this.hp = this.stats[0];
        }
    }

    /**
     *
     * @param exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public void buyItem(int moneySpent){
        this.money -= moneySpent;
    }

    /**
     *
     * @param expNeeded
     */
    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @param skillSet
     */
    public void setSkillSet(ArrayList skillSet) {
        this.skillSet = skillSet;
    }

    /**
     *
     * @param newItem
     * @throws SlickException
     */
    public void addItem(String newItem) throws SlickException{
        this.getInventory().getListItemFound().add(EquipmentList.getInstance().getListEquipment().get(newItem));
    }
    
    /**
     *
     * @param equipment
     */
    public void addItem(Equipment equipment){
        this.getInventory().getListItemFound().add(equipment);
    }

    /**
     *
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     *
     * @return
     */
    public void heal(int heal){
        this.hp += heal;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        }
        else if (this.hp > this.stats[0]){
            this.hp = this.stats[0];
        }
    }
}
