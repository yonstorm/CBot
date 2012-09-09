package com.convin.bot.utils.input.mouse;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 18.4.2012
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class MousePathGenerator {

    /**
     * Constructs a path from x1,y1 to x2,y2 using  algorithm supplied by the child class.
     *
     * @param x1 First x coord.
     * @param y1 First y coord.
     * @param x2 Second x coord.
     * @param y2 Second y coord.
     * @return Point array of coordinates generated between suplied coordinates.
     */
    public abstract Point[] makeMousePath(int x1, int y1, int x2, int y2);

    /**
     * Constructs a path from Point "from" to Point "to" algorithm supplied by the child class.
     *
     * @param from Coordinate to start constructing path from.
     * @param to   Coordinate to end constructing path to.
     * @return Point array of coordinates generated between supplied coordinates.
     */
    public final Point[] makeMousePath(Point from, Point to) {
        return makeMousePath(from.x, from.y, to.x, to.y);
    }

}
