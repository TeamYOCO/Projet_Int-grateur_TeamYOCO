/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.MAINMENU;
import static ca.qc.bdeb.info204.Game.WIDTH;
import gameEngine.ResManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Olivier
 */
public class Help extends BasicGameState {

    private static int stateID;
    private Image background;
    
    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public Help(int stateID) throws SlickException {
        Help.stateID = stateID;
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
        background = ResManager.getInstance().getImage("treesun");
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
        background.draw(0,0);
        g.setColor(Color.black);
        g.drawString("Vous pouvez effectuer les attaque suivante dans le jeu\n"
                + "A : attaque magique\nS : Tirer une flèche\nD : Coup d'épée\nW: Boire une potion\n"
                + "Vous ne pouvez pas utiliser ces touches si vous n'avez pas au moins un des types d'item suivant\n"
                + "- Épée (pour utiliser la touche D : Coup d'épée\n"
                + "- Arc (pour utiliser la touche S : Tirer une flèche\n"
                + "- Livre magique (pour utiliser la touche A : Attaque magique\n"
                + "Pour équiper ces items, vous devez entrer dans l'inventaire\n grâce à la touche 'E'\n"
                + "Vous commencez le jeu avec une épée en bois\n"
                + "Vous pouvez obtenir d'autres items en combattant des ennemis ou en en achetant en \nparlant aux NPC's vendeurs"
                + "pour intéragir avec les NPC's, appuyez sur la barre d'espace\n"
                + "Chaque attaque inflige des dégats aux ennemis en fonction de vos \nstatistiques d'attaque et d'attaque spéciale et vous coûte une certaine quantité d'énergie\n"
                + "Chaque ennemi tué vous fait gagner de l'argent et de l'expérience\n"
                + "À chaque fois que vous augmenté de niveau, vous obtenez 5 points \nd'augmentation de stats que vous pouvez utiliser pour augmenter n'importe quelle des stats suivantes :\n"
                + "- Vie\n- Attaque\n- Défense/Défense Spéciales\n- Spécial Attaque\n"
                + "La défense et la défense spéciale augmentent en même temps\n"
                + "Vous pouvez également accéder à l'inventaire avec la touche 'E'\n"
                + "Pour sauvegarder votre proression, cliquer sur 'p'", 100, 100);
        g.drawString("< Retour", 15, HEIGHT-25);
        
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

        if ((mouseX > 0 && mouseX < 100) && (mouseY > 0 && mouseY < 25)) {
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(MAINMENU);
            }
        }
    }
}
