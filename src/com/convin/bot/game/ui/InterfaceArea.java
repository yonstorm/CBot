package com.convin.bot.game.ui;

import java.awt.*;

/**
 * User: Joni
 * Date: 27.9.2012
 * Time: 0:38
 */
public class InterfaceArea extends InterfaceComponent {

    private Rectangle area;

    public InterfaceArea(Rectangle area, String name) {
        super(name, InterfaceComponentType.ENTITY_AREA);
        this.area = area;
    }

    public Rectangle getArea() {
        return area;
    }
}
