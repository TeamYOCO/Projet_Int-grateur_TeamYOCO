/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import gameEngine.ResManager;
import static items.Equipment.MAX_STATS;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author Olivier
 */
public class LevelUpScreen extends BasicGameState {

    private static int stateID;
    private Image background;
    private UnicodeFont ufont50,ufont24;
    
    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public LevelUpScreen(int stateID) throws SlickException {
        LevelUpScreen.stateID = stateID;
    }

    /**
     *
     * @return
     */
    @Override
    public int getID() {
        return stateID;
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ufont50 = ResManager.getInstance().getFont("Abadi MT Condensed Light", 50);
        ufont24 = ResManager.getInstance().getFont("Abadi MT Condensed Light", 24);
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        ResManager.getInstance().getImage("LevelUpScreenPic").draw(0, 0);
        g.setFont(ufont50);
        g.drawString(""+CharacterStatsManager.getInstance().getLvlBoost(), 475, 260);
        g.setFont(ufont24);
        for (int i = 0; i < 4; i++) {
            g.drawString(""+CharacterStatsManager.getInstance().getStats()[i] + "+" + CharacterStatsManager.getInstance().getStatsUpgrade()[i], 127+(235*i), 555);
        }
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        
        //si le joueur clique sur "vie"
        if((mouseX > 55 && mouseX < 255) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(0,true);
                    CharacterStatsManager.getInstance().setHp(CharacterStatsManager.getInstance().getHp()+1);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < CharacterStatsManager.getInstance().NB_LVL_BOOST) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[0] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(0,true);
                    CharacterStatsManager.getInstance().setHp(CharacterStatsManager.getInstance().getHp()-1);
            }
        }
        
        //si le joueur clique sur "att"
        if((mouseX > 282 && mouseX < 482) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(1,true);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < CharacterStatsManager.getInstance().NB_LVL_BOOST) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[1] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(1,true);
            }
        }
        
        //si le joueur clique sur "def spdef"
        if((mouseX > 526 && mouseX < 726) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(2,true);
                    CharacterStatsManager.getInstance().buffStat(4,false);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < CharacterStatsManager.getInstance().NB_LVL_BOOST) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[2] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(2,true);
                    CharacterStatsManager.getInstance().nerfStat(4,false);
            }
        }
        
        //si le joueur clique sur "spAtt"
        if((mouseX > 770 && mouseX < 970) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(3,true);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < CharacterStatsManager.getInstance().NB_LVL_BOOST) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[3] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(3,true);
            }
        }
        
        //si le joueur clique sur "effacer", réinitialiser toutes les stats
        if((mouseX > 252 && mouseX < 472) && (mouseY > 37 && mouseY < 87) && (input.isMousePressed(0))){
           CharacterStatsManager.getInstance().resetStats();
        }
        
        //si le joueur clique sur "enregistrer", retourner dans le overworld
        if((mouseX > 552 && mouseX < 772) && (mouseY > 37 && mouseY < 87) && (input.isMousePressed(0))){
            for (int j = 0; j < MAX_STATS; j++) {
                CharacterStatsManager.getInstance().setStatsUpgrade(j, 0);
            }
            sbg.enterState(Game.OVERWORLD);
        }

    }
}
