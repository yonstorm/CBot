package com.convin.bot.api.methods.color;

import java.awt.*;

/**
 * User: JuV
 * Date: 28.9.2012
 * Time: 19:11
 */
public class ColorUtil {
    private static boolean similar(final int color1, final int color2, final int tolerance) {
        final Color c1, c2;
        c1 = new Color(color1);
        c2 = new Color(color2);
        return Math.abs(c1.getRed() - c2.getRed()) <= tolerance && Math.abs(c1.getBlue() - c2.getBlue()) <= tolerance && Math.abs(c1.getGreen() - c2.getGreen()) <= tolerance;
    }

    /**
     * Gets the tolerance between the colors provided.
     *
     * @param c1 Color one to compare
     * @param c2 Color two to compare
     * @return The tolerance between the provided colors
     */
    public static int distance(final Color c1, final Color c2) {
        return (Math.abs(c1.getRed() - c2.getRed()) + Math.abs(c1.getGreen() - c2.getGreen()) + Math.abs(c1.getBlue() - c2.getBlue())) / 3;
    }

    public static int equality(final Color c1, final Color c2) {
        final int r = (Math.abs(c1.getRed() - c2.getRed())) / 255;
        final int g = (Math.abs(c1.getGreen() - c2.getGreen())) / 255;
        final int b = (Math.abs(c1.getBlue() - c2.getBlue())) / 255;
        return (r + g + b) / 3 * 100;
    }
}
