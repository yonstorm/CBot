package com.convin.bot.api.wrappers;

import com.convin.bot.api.math.Calculations;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: JuV
 * Date: 6.8.2012
 * Time: 17:54
 */
public abstract class Entity {
    ArrayList<Point> coordinates;

    protected Entity() {
    }

    public Entity(ArrayList<Point> points) {
        this.coordinates = points;
    }

    public Point getLocation() {
        return coordinates.get(0);
    }

    protected Point randomPoint() {
        return coordinates.get(Calculations.getRandomNumber(0, coordinates.size()));
    }
}
