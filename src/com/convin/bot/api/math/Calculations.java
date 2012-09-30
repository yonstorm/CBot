package com.convin.bot.api.math;

import java.awt.*;

/**
 * User: JuV
 * Date: 27.4.2012
 * Time: 12:50
 */
public class Calculations {
    private Calculations() {
    }

    /**
     * Calculate distance between to specified points.
     *
     * @param from Point to start from
     * @param to   Point to end to
     * @return Distance between the points
     */
    public static double distance(Point from, Point to) {
        double dx = from.x - to.x;         // horizontal difference
        double dy = from.y - to.y;         // vertical difference
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculate random number between given min,max.
     *
     * @param min Smallest possible number to return
     * @param max Largest possible number to return
     * @return Random number between min,max
     */
    public static int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min)));
    }

    /**
     * Calculate random number between given min,max.
     *
     * @param min Smallest possible number to return
     * @param max Largest possible number to return
     * @return Random number between min,max
     */
    public static float getRandomNumberF(float min, float max) {
        return min + (float) (Math.random() * ((max - min)));
    }

    /**
     * Check if a given coordinate is inside a given rectangle.
     *
     * @param x      Coordinate X to check
     * @param y      Coordinate Y to check
     * @param left   Rectangles left boundary
     * @param right  Rectangles right boundary
     * @param bottom Rectangles bottom boundary
     * @param top    Rectangles top boundary
     * @return True if coordinate is inside given boundaries
     */
    public static boolean rectangleContains(int x, int y, int left, int right, int bottom, int top) {
        return x > left && x < right && y < bottom && y > top;
    }

    /**
     * Check if a given coordinate is inside the given rectangle.
     *
     * @param point     Point to check
     * @param rectangle Rectangle to check against
     * @return True if point is inside given rectangle
     */

    public static boolean rectangleContains(Point point, Rectangle rectangle) {
        return rectangleContains(point.x, point.y, (int) rectangle.getX(), (int) (rectangle.getX() + rectangle.getWidth()), (int) (rectangle.getY() + rectangle.getHeight()), (int) rectangle.getY());
    }

    /**
     * Gets a random Point in the provided rectangle.
     *
     * @param r Rectangle to get the Point from
     * @return A random Point in the rectangle
     */
    public static Point getRandomPointInRect(Rectangle r) {
        return new Point(Calculations.getRandomNumber(r.x, (r.x + r.width - 1)), Calculations.getRandomNumber(r.y, (r.y + r.height - 1)));
    }

}
