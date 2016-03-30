/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partyMember;

import java.util.ArrayList;

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
    protected ArrayList skillSet;

    public PartyMember(int hpMax, int hp, int mpMax, int mp, int attack, int speed, int specialAttack, int defence, int specialDefence, int expNeeded, int exp, ArrayList skillSet) {
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
    }
    
    
}
