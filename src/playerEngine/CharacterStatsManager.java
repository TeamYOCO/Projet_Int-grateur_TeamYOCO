/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import gameEngine.ResManager;
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
//    protected int hpMax;
//    protected int attack;
//    protected int speed;
//    protected int specialAttack;
//    protected int defence;
//    protected int specialDefence;
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
        stats[0] = 0;
        stats[1] = 0;
        stats[2] = 0;
        stats[3] = 0;
        stats[4] = 0;
        stats[5] = 0;
        
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
    
    public void setStats(int expNeeded, int exp, int level, Image anim){
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

    public void levelUp(){
        
    }
    
    public int[] getStats(){
        return stats;
    }

    public Image getAnim() {
        return anim;
    }
    
    public int getHp() {
        return hp;
    }
    
    public String getStatsName(int x){
        switch(x){
            case 0 : return "HP- ";
            case 1 : return "Attaque- ";
            case 2 : return "Defense- ";
            case 3 : return "Special Attaque- ";
            case 4 : return "Special Defense- ";
            case 5 : return "Speed- ";
            default : return "Noob";
        }
    }

//    public int getAttack() {
//        return attack;
//    }
//
//    public int getDefence() {
//        return defence;
//    }
//
//
//    public int getHpMax() {
//        return hpMax;
//    }
//    
//    public int getSpecialAttack() {
//        return specialAttack;
//    }
//
//    public int getSpecialDefence() {
//        return specialDefence;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }

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
    
    public void setHp(int hp) {
        this.hp = hp;
    }

//    public void setAttack(int attack) {
//        this.attack = attack;
//    }
//
//    public void setDefence(int defence) {
//        this.defence = defence;
//    }
//    
//
//    public void setHpMax(int hpMax) {
//        this.hpMax = hpMax;
//    }
//
//    public void setSpecialAttack(int specialAttack) {
//        this.specialAttack = specialAttack;
//    }
//
//    public void setSpecialDefence(int specialDefence) {
//        this.specialDefence = specialDefence;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
    
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
