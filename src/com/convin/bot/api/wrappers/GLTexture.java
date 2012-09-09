package com.convin.bot.api.wrappers;

import java.awt.*;

/**
 * User: JuV
 * Date: 5.7.2012
 * Time: 23:57
 */
public class GLTexture extends Entity {
    protected int textureID;
    protected int id;
    protected int red;
    protected int green;
    protected int black;
    protected int alpha;
    protected Point location;
    protected int width;
    protected int height;
    protected boolean isValid;
    protected Rectangle textureRect;

    private GLTexture(int alpha, int red, int green, int black, int textureID, int id, int x, int y, int width, int height) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.black = black;
        this.textureID = textureID;
        this.id = id;
        this.location = new Point(x, y);
        this.width = width;
        this.height = height;
        this.isValid = true;
    }

    private GLTexture(int alpha, int red, int green, int black, int textureID, int id, int x, int y) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.black = black;
        this.textureID = textureID;
        this.id = id;
        this.location = new Point(x, y);
        width = 0;
        height = 0;
        this.isValid = true;
    }

    public GLTexture() {
        textureID = 0;
        id = 0;
        red = 0;
        green = 0;
        black = 0;
        alpha = 0;
        location = new Point(0, 0);
        this.width = 0;
        this.height = 0;
        this.isValid = false;
    }

    public int getTextureID() {
        return textureID;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlack() {
        return black;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getId() {
        return id;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isValid() {
        return isValid;
    }
}
