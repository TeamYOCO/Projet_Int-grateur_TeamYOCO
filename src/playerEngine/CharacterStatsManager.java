/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import gameEngine.EquipmentList;
import gameEngine.ResManager;
import items.Equipment;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1455367
 */
public class CharacterStatsManager {

    private static CharacterStatsManager instance = null;
    protected int[] stats = new int[6];
    protected int hp;
    protected int expNeeded;
    protected int exp;
    protected int level;
    protected ArrayList skillSet;
    protected Image anim;

    private CharacterStatsManager() {
        hp = 0;
//        hpMax = 0;
//        attack = 0;
//        speed = 0;
//        specialAttack = 0;
//        defence = 0;
//        specialDefence = 0;
        stats[0] = 10;
        stats[1] = 10;
        stats[2] = 10;
        stats[3] = 10;
        stats[4] = 10;
        stats[5] = 10;
        
        hp = 100;
        stats[0] = 100;
        stats[1] = 10;
        stats[2] = 10;
        stats[3] = 10;
        stats[4] = 10;
        stats[5] = 10;

        this.expNeeded = 0;
        this.exp = 0;
        this.level = 0;
        this.anim = null;
    }

    public static CharacterStatsManager getInstance() throws SlickException {
        if (instance == null) {
            instance = new CharacterStatsManager();
        }
        return instance;
    }

    public void setStats(int expNeeded, int exp, int level, Image anim) {
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
    }

    public void gainExp(int expGained) {
        exp += expGained;
        if (exp >= expNeeded) {
            exp -= expNeeded;
            expNeeded = (int) ((float) expNeeded * 1.5);
            level++;
            levelUp();
        }
    }

    public void levelUp() {

    }

    public int[] getStats() {
        return stats;
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

    public ArrayList getSkillSet() {
        return skillSet;
    }

    public void setAnim(Image anim) {
        this.anim = anim;
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
