package com.convin.bot.utils.input.mouse;

import java.awt.*;
import java.util.ArrayList;

/**
 * The mouse path generator,
 * Creates a straight line, with points getting closer towards the end
 * that creates the impression that its slowing down
 */
public class Spline extends MousePathGenerator {

    /**
     * Creates a Point array for this MousePathGenerator
     *
     * @param x1 the x of the first point
     * @param y1 the y of the second point
     * @param x2 the x of the first point
     * @param y2 the y of the second point
     * @return Point array for this mouse path
     */
    public Point[] makeMousePath(int x1, int y1, int x2, int y2) {

        int x = x1;
        int y = y1;
        int step = 20;
        ArrayList<Point> path = new ArrayList<Point>();

        while (distance(x, y, x2, y2) > 1) {
            path.add(new Point(x, y));
            x = x + (x2 - x) / step;
            y = y + (y2 - y) / step;
            if (distance(x, y, x2, y2) < 100)
                if (distance(x, y, x2, y2) != 0)
                    step = step - (10 / distance(x, y, x2, y2)) - 1;
            if (step < 1)
                step = 1;
        }
        path.add(new Point(x2, y2));
        return path.toArray(new Point[path.size()]);
    }

    /**
     * calculates the distance between the two points using pyragorus theorm
     *
     * @param x1 the x of the first point
     * @param y1 the y of the second point
     * @param x2 the x of the first point
     * @param y2 the y of the second point
     * @return the distance between the points
     */
    private int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.round(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));
    }

}