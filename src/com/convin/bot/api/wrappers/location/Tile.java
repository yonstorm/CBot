package com.convin.bot.api.wrappers.location;

import java.awt.*;

/**
 * User: JuV
 * Date: 29.9.2012
 * Time: 10:05
 */
public class Tile extends Vector2d {
    private int maxOffset;

    /**
     * Creates a new Tile that is used for walking & positioning.
     *
     * @param x         The x coordinate of the Tile on world map
     * @param y         The y coordinate of the Tile on world map
     * @param maxOffset Max randomization applied to the tiles coordinates.
     */
    public Tile(int x, int y, int maxOffset) {
        super(x, y);
        this.maxOffset = maxOffset;
        randomize(maxOffset);
    }

    /**
     * Creates a new Tile that is used for walking & positioning.
     *
     * @param p         The coordinates of the Tile on world map
     * @param maxOffset Max randomization applied to the tiles coordinates.
     */
    public Tile(Point p, int maxOffset) {
        super(p.x, p.y);
        randomize(maxOffset);
    }

    public Tile() {
        super();
    }

    /**
     * Gets the max randomization applied to the Tile coordinates.
     *
     * @return Max randomization applied
     */
    public int getMaxOffset() {
        return maxOffset;
    }

    /**
     * Set the max randomization of The tile coordinates.
     *
     * @param maxOffset The max randomization to be applied
     */
    public void setMaxOffset(int maxOffset) {
        this.maxOffset = maxOffset;
    }
}
