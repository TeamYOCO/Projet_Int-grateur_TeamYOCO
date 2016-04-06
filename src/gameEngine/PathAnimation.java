/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import battleEntities.BattleEntity;
import org.newdawn.slick.util.pathfinding.*;

/**
 * Work in progress
 *
 * @author 1455367
 */
public class PathAnimation {

    private AStarPathFinder pathfinder = new AStarPathFinder(null, 1000, true);
    private Path path;
    private int sx, sy, tx, ty;
    private BattleEntity taget;

    public PathAnimation(int sx, int sy, int tx, int ty, BattleEntity taget) {
        this.sx = sx;
        this.sy = sy;
        this.tx = tx;
        this.ty = ty;
        this.taget = taget;
        this.path = pathfinder.findPath(null, sx, sy, tx, ty);
    }

}
