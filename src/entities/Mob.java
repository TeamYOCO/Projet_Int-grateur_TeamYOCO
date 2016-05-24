/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import items.EquipmentList;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import playerEngine.CharacterStatsManager;

/**
 * Classe qui contient tous les éléments représentant un monstre, npc ou joueur
 * @author Seb
 */
public abstract class Mob extends Entity {

    protected Animation[] moveAnimations;
    protected int hitpoints;
    protected int direction = 2;
    protected boolean moving;
    protected int damagePhysical, damageSpecial, defence, specialDefence;
    protected float knockbackTimer = 0;
    protected int exp = 0;
    protected int money;
    protected ArrayList<Entity> list;
    
    public boolean isHitable(){
        if (knockbackTimer <= 0)
            return true;
        else
            return false;
    }
    
    public void takeHit(int damage, int damageSpecial, int hitDirection) {
        switch(hitDirection){
            case 0: this.direction = 2; break;
            case 1: this.direction = 3; break;
            case 2: this.direction = 0; break;
            case 3: this.direction = 1; break;
        }
        hitpoints -= damage * (100-defence)/100;
        hitpoints -= damageSpecial * (100-specialDefence)/100;
        if (hitpoints <= 0){
            this.dead = true;
            try {
                this.die();
            } catch (SlickException ex) {
                Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.knockbackTimer = 200;
    }

    public int getDamagePhysical() {
        return damagePhysical;
    }
    
    public int getDamageSpecial(){
        return damageSpecial;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public int getExp(){
        return exp;
    }
    
    public void die() throws SlickException{
        CharacterStatsManager.getInstance().gainExp(exp);
        CharacterStatsManager.getInstance().gainMoney(money);
        Random rdn=new Random();
        if(rdn.nextInt()%10000==1){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Casque Royal")));
        }else if(rdn.nextInt()%10000==2){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Armure Royale")));
        }else if(rdn.nextInt()%10000==3){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Jambieres Royales")));
        }else if(rdn.nextInt()%10000==4){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Bottes Royales")));
        }else if(rdn.nextInt()%10000==5){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Jesus")));
        }else if(rdn.nextInt()%10000==6){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Epee legendaire")));
        }else if(rdn.nextInt()%10000==7){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Epee extraterrestre")));
        }else if(rdn.nextInt()%10000==8){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Epee rose")));
        }else if(rdn.nextInt()%10000==9){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Epee de glace")));
        }else if(rdn.nextInt()%10000==10){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Banane")));
        }else if(rdn.nextInt()%10000==11){
            this.list.add(new ItemDrop((int)this.x,(int)this.y,EquipmentList.getInstance().getEquipment("Epee Royale")));
        }
    }
}
