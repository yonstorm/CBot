package com.convin.bot.api.methods;

import com.convin.bot.api.common.ImageManipulation;
import com.convin.bot.api.wrappers.shapes.Circle;
import com.convin.bot.cops.PositionFinder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * User: JuV
 * Date: 18.9.2012
 * Time: 12:04
 */
public class Minimap {
    private static native void getRGBData(byte[] pixelBuffer, boolean rawImage);

    private static final Point MINIMAP_CENTER_POINT = new Point(625, 135);
    private static final Circle MINIMAP_CIRCLE_AREA = new Circle(MINIMAP_CENTER_POINT, 72);

    private static final byte PIXEL_BUFFER_RAW[] = new byte[512 * 512 * 4];
    private static final byte PIXEL_BUFFER_NORM[] = new byte[161 * 157 * 4];

    /**
     * Gets the current active minimap region as a image.
     *
     * @return Image of the current minimap region
     */
    public static BufferedImage getRegionImage() {
        getRGBData(PIXEL_BUFFER_RAW, true);
        return ImageManipulation.createImageFromBytes(PIXEL_BUFFER_RAW, 512, 512, true);
    }

    /**
     * Gets the minimap image visible in game.
     *
     * @return Image of the minimap area
     */
    public static BufferedImage getMinimapImage() {
        getRGBData(PIXEL_BUFFER_NORM, false);
        return ImageManipulation.createImageFromBytes(PIXEL_BUFFER_NORM, 161, 157, true);
    }

    private static Point MMtoWM(Point dest) {
        Point curLoc = PositionFinder.findPosition();
        int x = MINIMAP_CENTER_POINT.x - dest.x;
        int y = MINIMAP_CENTER_POINT.y - dest.y;

        return new Point(curLoc.x + x, curLoc.y + y);
    }

    /**
     * Converts a coordinate from World map to Minimap coordinates
     *
     * @param dest Point to convert
     * @return dest in Minimap coordinates
     */
    public static Point WMtoMM(Point dest) {
        Point curLoc = PositionFinder.findPosition();
        int distX = curLoc.x - (int) dest.x; // distance from curLoc to dest
        int distY = curLoc.y - (int) dest.y;

        distX = -distX;
        distY = -distY;
        Point result = new Point(MINIMAP_CENTER_POINT.x + distX, MINIMAP_CENTER_POINT.y + distY);

        double[] pt = {result.x, result.y};
        AffineTransform.getRotateInstance(Math.toRadians(Camera.getAngle()), MINIMAP_CENTER_POINT.x, MINIMAP_CENTER_POINT.y)
                .transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
        double newX = pt[0];
        double newY = pt[1];

        return new Point((int) newX, (int) newY);
    }

    /**
     * Checks if given point is visible on the minimap.
     *
     * @param p Point to check
     * @return True if point is visible on minimap
     */
    public static boolean contains(Point p) {
        return getArea().contains(p);
    }

    /**
     * Gets the clickable minimap area in game.
     *
     * @return Circle of the minimap area
     */
    public static Circle getArea() {
        return MINIMAP_CIRCLE_AREA;
    }

}
