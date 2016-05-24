/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import static items.Equipment.MAX_STATS;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1455367
 */
public class CharacterStatsManager {

    private static CharacterStatsManager instance = null;
    public final int NB_LVL_BOOST = 10;
    protected int[] stats = new int[MAX_STATS];
    protected int[] statsUpgrade = new int[]{0,0,0,0,0,0,0};
    protected int hp;
    protected int expNeeded;
    protected int exp;
    protected int level;
    protected int nbLvlBoost = 0;
    protected ArrayList skillSet;
    protected Image anim;
    protected boolean lvlIsUp = false;

    private CharacterStatsManager() {
        
        hp = 100;
        stats[0] = 100;
        stats[1] = 10;
        stats[2] = 10;
        stats[3] = 10;
        stats[4] = 10;
        stats[5] = 10;
        stats[6] = 10;

        this.expNeeded = 10;
        this.exp = 0;
        this.level = 1;
        this.anim = null;
    }

    public static CharacterStatsManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new CharacterStatsManager();
        }
        return instance;
    }

    public void setXPStats(int expNeeded, int exp, int level, Image anim) {
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
    }

    public void gainExp(int expGained) {
        exp += expGained;
        if (exp >= expNeeded) {
            levelUp();
        }
    }

    public void levelUp() {
            exp = 0;
            expNeeded = (int) ((float) expNeeded * 1.84);
            level++;
            nbLvlBoost += 10;
            lvlIsUp = true;
    }
    
    public void buffStat(int index){
        stats[index] += 1;
        statsUpgrade[index] += 1;
        nbLvlBoost -= 1;
    }
    
    public void nerfStat(int index){
        stats[index] -= 1;
        statsUpgrade[index] -= 1;
        nbLvlBoost += 1;
    }
    
    public void resetStats(){
        for (int j = 0; j < MAX_STATS; j++) {
            stats[j] -= statsUpgrade[j];
            nbLvlBoost += statsUpgrade[j];
            statsUpgrade[j] = 0;
        }
    }


    public int[] getStats() {
        return stats;
    }
    
    public int[] getStatsUpgrade(){
        return statsUpgrade;
    }
    
    public void setStatsUpgrade(int i, int change){
        this.statsUpgrade[i] = change;
    }
    
    public void setStats(int index, int stat){
        stats[index] = stat;
    }

    public Image getAnim() {
        return anim;
    }

    public int getHp() {
        return hp;
    }

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

    public int getExp() {
        return exp;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public int getLevel() {
        return level;
    }
    
    public int getLvlBoost(){
        return nbLvlBoost;
    }
    
    public ArrayList getSkillSet() {
        return skillSet;
    }

    public void setAnim(Image anim) {
        this.anim = anim;
    }
    
    public boolean getlvlIsUp(){
        return lvlIsUp;
    }
    
    public void setlvlIsUp(boolean bool){
        this.lvlIsUp = bool;
    }

    public void takeDamage(int damage){
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
            System.out.println(hp);
        }
        else if (this.hp > this.stats[0]){
            this.hp = this.stats[0];
        }
    }
    
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

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSkillSet(ArrayList skillSet) {
        this.skillSet = skillSet;
    }

}
