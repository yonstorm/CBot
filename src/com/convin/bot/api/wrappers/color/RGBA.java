package com.convin.bot.api.wrappers.color;

/**
 * User: JuV
 * Date: 16.8.2012
 * Time: 15:49
 */
@Deprecated
public class RGBA extends RGB {
    private final int alpha;

    public RGBA(int red, int green, int black, int alpha) {
        super(red, green, black);
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "RGBA:" + getRed() + "," + getGreen() + "," + getBlack() + "," + getAlpha();
    }

    public boolean equals(RGBA rgba) {
        return this.getRed() == rgba.getRed() && this.getGreen() == rgba.getGreen() && this.getBlack() == rgba.getBlack() && this.getAlpha() == rgba.getAlpha();
    }

    public int getAlpha() {
        return alpha;
    }

}
