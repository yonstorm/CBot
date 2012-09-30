package com.convin.bot.game.ui;

import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;

import java.awt.*;


public class InterfaceButton extends InterfaceComponent {
    private final Rectangle boundingBox;

    public InterfaceButton(Rectangle boundingBox, String buttonName) {
        super(buttonName, InterfaceComponentType.BUTTON);
        this.boundingBox = boundingBox;
    }

    public void click() {
        Point p = Calculations.getRandomPointInRect(this.boundingBox);
        Mouse.click(p, Mouse.LEFT_BUTTON);
    }

    public void hover() {
        Point p = Calculations.getRandomPointInRect(this.boundingBox);
        Mouse.move(p);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
