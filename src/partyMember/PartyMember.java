/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partyMember;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author 1455367
 */
public abstract class PartyMember {
    
    protected int hpMax;
    protected int hp;
    protected int mpMax;
    protected int mp;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected int expNeeded;
    protected int exp;
    protected int level;
    protected ArrayList skillSet;
    protected Image anim;

    public PartyMember(int hpMax, int hp, int mpMax, int mp, int attack, int speed, int specialAttack, int defence, int specialDefence, int expNeeded, int exp, ArrayList skillSet, Image anim, int level) {
        this.hpMax = hpMax;
        this.hp = hp;
        this.mpMax = mpMax;
        this.mp = mp;
        this.attack = attack;
        this.speed = speed;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.specialDefence = specialDefence;
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.skillSet = skillSet;
        this.anim = anim;
        this.level = level;
    }
    
    public void gainExp(int expGained){
        exp += expGained;
        if (exp >= expNeeded){
            exp -= expNeeded;
            expNeeded = (int)((float)expNeeded *1.5);
            level ++;
            levelUp();
        }
    }
    
    public abstract void levelUp();

    
    
    
}
