package com.convin.bot.api.methods;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.common.Time;
import com.convin.bot.api.conditions.Condition;
import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.wrappers.location.Tile;
import com.convin.bot.core.bot.Bot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * User: JUV
 * Date: 28.9.2012
 * Time: 22:47
 */
public class Walking {
    /**
     * Navigates along the path given.
     *
     * @param path    Path of tiles to navigate along
     * @param reverse Should the path be reversed
     * @return True if navigating the path happened successfully
     */
    public static boolean navigatePath(Tile[] path, boolean reverse) {
        ArrayList<Tile> walkPath = new ArrayList<Tile>(Arrays.asList(path));
        if (reverse) Collections.reverse(walkPath);
        int startIndex = walkPath.indexOf(getClosestPathPoint(walkPath));
        Logging.log(Logging.LogLevel.INFO, "Starting walking the path from point #" + startIndex);
        for (int i = startIndex; i < walkPath.size(); i++) {
            if (!Bot.CURRENT.getScriptHandler().getCurrentScript().isRunning()) {
                break;
            }
            final Tile dest = walkPath.get(i);
            final Point destm = new Point(dest.getX(), dest.getY());
            Time.waitFor(new Condition() {
                @Override
                public boolean verify() {
                    return Minimap.contains(Minimap.WMtoMM(destm));
                }

                @Override
                public String name() {
                    return "PointInMinimap";
                }
            }, 11000);
            if (!walkTo(dest)) {
                Logging.log(Logging.LogLevel.INFO, "Could not walk to point: " + walkPath.get(i));
                return false;
            }

        }
        return true;
    }

    /**
     * Walk to the specified tile using the minimap
     * NOTE: Use only for Tiles that are near you ( Visible on minimap )
     *
     * @param dest Tile to walk to
     * @return True if walking happened succesfully
     */
    public static boolean walkTo(final Tile dest) {
        Point destMM = Minimap.WMtoMM(new Point(dest.getX(), dest.getY()));
        int tries = 0;
        boolean succesfulAction = false;
        while (tries < 15 && !succesfulAction) {
            if (Minimap.getArea().contains(destMM)) {
                succesfulAction = true;
                Mouse.click(destMM, Mouse.LEFT_BUTTON);
                break;
            }
            tries++;
            Time.sleep(300, 600);
        }
        if (succesfulAction) {
            Time.waitFor(new Condition() {
                @Override
                public boolean verify() {
                    return !Player.isMoving();
                }

                @Override
                public String name() {
                    return "PlayerNotMoving";
                }
            }, 4200);
            return true;
        }

        return false;
    }

    private static Tile getClosestPathPoint(ArrayList<Tile> points) {
        Point curLoc = Player.getLocation();
        Point smallestDist = new Point(0, 0);
        for (Tile p : points) {
            if (curLoc.distance(new Point(p.getX(), p.getY())) < Calculations.distance(curLoc, smallestDist)) {
                smallestDist = new Point(p.getX(), p.getY());
            }
        }
        return new Tile(smallestDist.x, smallestDist.y, 0);
    }
}
