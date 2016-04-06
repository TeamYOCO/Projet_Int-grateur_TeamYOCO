/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleEntities;

import gameEngine.PathAnimation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import partyMember.PartyMember;

/**
 *
 * @author 1455367
 */
public class BattlePartyMember extends BattleEntity{
    
    private PathAnimation attackAnimation;
    private PathAnimation spellAnimation;
    private PartyMember activeCharacter;
    

    public BattlePartyMember(PartyMember activeCharacter, float x, float y) {
        this.activeCharacter = activeCharacter;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawImage(activeCharacter.getAnim(), x, y);
    }
}
