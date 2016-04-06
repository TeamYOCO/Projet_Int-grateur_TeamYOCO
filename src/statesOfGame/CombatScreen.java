/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import battleEntities.BattleEntity;
import battleEntities.BattleMob;
import battleEntities.BattlePartyMember;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import playerEngine.PlayerGameManager;

/**
 *
 * @author 1455367
 */
public class CombatScreen extends BasicGameState{
    private static int stateID;
    private BattleMob [] mobList;
    private BattlePartyMember [] partyList;
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
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(backgroundImage, 0, 0);
        for (int i = 0; i < mobList.length; i++) {
            mobList[i].render(g);
        }
        for (int i = 0; i < partyList.length; i++) {
            partyList[i].render(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
    
}
