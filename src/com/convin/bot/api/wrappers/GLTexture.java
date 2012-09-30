package com.convin.bot.api.wrappers;

import com.convin.bot.api.common.Time;
import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.methods.ContextMenu;

import java.awt.*;

/**
 * User: JuV
 * Date: 5.7.2012
 * Time: 23:57
 */
public class GLTexture extends Entity implements Interactive {
    protected int textureID;
    protected int id;
    protected Point location;
    protected int width;
    protected int height;
    protected boolean isValid;
    protected int precisionID;

    protected GLTexture(int alpha, int red, int green, int black, int textureID, int id, int x, int y, int width, int height, int colorID) {
        this.textureID = textureID;
        this.id = id;
        this.location = new Point(x, y);
        this.width = width;
        this.height = height;
        this.precisionID = colorID;
        this.isValid = true;
    }

    private GLTexture(int alpha, int red, int green, int black, int textureID, int id, int x, int y) {
        this.textureID = textureID;
        this.id = id;
        this.location = new Point(x, y);
        width = 0;
        height = 0;
        this.isValid = true;
    }

    public GLTexture() {
        this.isValid = false;
    }

    /**
     * Gets the bounding rectangle of this texture.
     *
     * @return Rectangle of this texture
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(location.x, location.y, width, height);
    }

    /**
     * Gets the textureID of this texture.
     *
     * @return TextureID of this texture
     */
    public int getTextureID() {
        return textureID;
    }

    /**
     * Gets the precisionID of this texture as Color object.
     *
     * @return Color object made from precisionID
     */
    public Color getColorAvg() {
        return new Color(precisionID);
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

    @Override
    public String toString() {
        return "ID: " + this.id + " colorAVG: " + getColorAvg() + " tid: " + this.textureID;
    }

    @Override
    public boolean interact(String menuOption) {
        Mouse.click(getRandomPoint(), Mouse.RIGHT_BUTTON);
        Time.sleep(253, 311);
        ContextMenu.interact(menuOption);
        Time.sleep(265, 420);
        return true;
    }

    @Override
    public void click() {
        Mouse.click(getRandomPoint(), Mouse.LEFT_BUTTON);
    }

    private Point getRandomPoint() {
        return new Point(Calculations.getRandomNumber(location.x, (location.x + width - 1)), Calculations.getRandomNumber(location.y, (location.y + height - 1)));
    }

    /**
     * Gets the precisionID of this texture( Average color as int ).
     *
     * @return
     */
    public int getPrecisionID() {
        return precisionID;
    }
}
