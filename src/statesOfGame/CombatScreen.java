/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import battleEntities.BattleMob;
import battleEntities.BattlePartyMember;
import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.WIDTH;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import partyMember.PartyMember;
import playerEngine.PlayerGameManager;

/**
 *
 * @author 1455367
 */
public class CombatScreen extends BasicGameState {

    private static int stateID;
    private BattleMob[] mobList;
    private BattlePartyMember[] partyList;
    private Image backgroundImage;
    private PlayerGameManager manager;

    public CombatScreen(int stateID, PlayerGameManager manager) {
        CombatScreen.stateID = stateID;
        this.manager = manager;
    }

    @Override
    public int getID() { 
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        partyList = new BattlePartyMember[3];
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        loadParty();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        try{
        g.drawImage(backgroundImage, 0, 0);
        for (int i = 0; i < mobList.length; i++) {
            mobList[i].render(g);
        }
        for (int i = 0; i < partyList.length; i++) {
            partyList[i].render(g);
        }
        } catch (NullPointerException e){
            
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

    private void loadParty() {
        try{
        for (int i = 0; i < manager.getPartyClass().getParty().length; i++) {
            if (manager.getPartyClass().isNotNull(i)) {
                this.partyList[0] = new BattlePartyMember(manager.getPartyClass().getParty()[0], WIDTH * (3 / 4), HEIGHT * ((2 * i) / 7));
            }
        }
        } catch (NullPointerException e){
            
        }
    }
}
