package com.convin.bot.api.wrappers.colour;

/**
 * User: JuV
 * Date: 21.8.2012
 * Time: 19:30
 */
public class RGB {
    private final int red;
    private final int green;
    private final int black;

    public RGB(int red, int green, int black) {
        this.red = red;
        this.green = green;
        this.black = black;
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

    public String toString() {
        return "RGB:" + getRed() + "," + getGreen() + "," + getBlack();
    }

    public boolean equals(RGB rgb) {
        return this.red == rgb.red && this.green == rgb.green && this.black == rgb.black;
    }

}
