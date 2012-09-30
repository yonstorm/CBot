package com.convin.bot.api.wrappers;

import com.convin.bot.api.wrappers.color.RGB;
import com.convin.bot.api.wrappers.color.RGBA;

import java.awt.*;

/**
 * User: JuV
 * Date: 4.7.2012
 * Time: 10:39
 */

// todo: Finish the Javadoc
public class GLFontCharacter {
    private final int x;
    private final int y;
    private final char symbol;
    private final Point location;
    private final int id;
    private final RGBA rgba;
    private final boolean isShadow;
    private final boolean isRendering;
    private final RGB rgb;

    public GLFontCharacter(int x, int y, char symbol, int id, int red, int green, int black, int alpha, boolean shadow, boolean rendering) {
        this.x = x;
        this.y = y;
        this.location = new Point(x, y);
        this.symbol = symbol;
        this.id = id;
        this.rgba = new RGBA(red, green, black, alpha);
        this.rgb = new RGB(red, green, black);
        this.isShadow = shadow;
        this.isRendering = rendering;
    }

    public String toString() {
        return symbol + " - " + rgba.toString() + " - Shadow: " + isShadow() + " - Rendering: " + isRendering();
    }

    /**
     * Gets the Characters screen location.
     *
     * @return Location on screen
     */
    public Point getLocation() {
        return location;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public RGBA getRGBA() {
        return rgba;
    }

    public RGB getRGB() {
        return rgb;
    }

    /**
     * Checks if this Character is a shadow of an another Character
     *
     * @return True if this is only a shadow
     */
    public boolean isShadow() {
        return isShadow;
    }

    /**
     * Checks if this Character is being rendered on screen.
     *
     * @return True if rendered on screen
     */
    public boolean isRendering() {
        return isRendering;
    }
}
