package com.convin.bot.api.wrappers;

import com.convin.bot.api.common.Time;
import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.methods.ContextMenu;

import java.awt.*;

/**
 * User: JuV
 * Date: 1.8.2012
 * Time: 22:09
 */
public class GLItem extends GLTexture implements Interactive {

    private GLItem(int alpha, int red, int green, int black, int textureID, int id, int x, int y, int width, int height) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.black = black;
        this.textureID = textureID;
        this.id = id;
        this.textureRect = new Rectangle(x, y, width, height);
        this.location = new Point(x, y);
        this.width = width;
        this.height = height;
        this.isValid = true;
    }

    public GLItem() {
        this.isValid = false;
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
        return new Point(Calculations.getRandomNumber(textureRect.x, (textureRect.x + textureRect.width - 1)), Calculations.getRandomNumber(textureRect.y, (textureRect.y + textureRect.height - 1)));
    }
}
