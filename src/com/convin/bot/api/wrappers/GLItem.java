package com.convin.bot.api.wrappers;

/**
 * User: JuV
 * Date: 1.8.2012
 * Time: 22:09
 */
public class GLItem extends GLTexture {

    private GLItem(int alpha, int red, int green, int black, int textureID, int id, int x, int y, int width, int height, int colorID) {
        super(alpha, red, green, black, textureID, id, x, y, width, height, colorID);
    }

    public GLItem() {
        this.isValid = false;
    }


}
