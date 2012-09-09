package com.convin.bot.api.wrappers;

import com.convin.bot.api.common.Time;
import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.methods.ContextMenu;

import java.awt.*;
import java.util.ArrayList;


/**
 * User: JuV
 * Date: 30.7.2012
 * Time: 11:43
 */
public class GLModel implements Interactive {
    private final long fullID;
    private final int triangles;
    private final int stride;
    private final boolean isValid;
    private final long id;
    private final ArrayList<Point> points;

    private GLModel(ArrayList<Point> points, long id, long fullID, int triangles, int stride) {
        this.points = points;
        this.id = id;
        this.fullID = fullID;
        this.triangles = triangles;
        this.stride = stride;
        this.isValid = true;

    }

    public GLModel() {
        this.fullID = 0;
        this.triangles = 0;
        this.stride = 0;
        this.id = 0;
        this.points = new ArrayList<Point>();
        this.isValid = false;
    }

    public long getFullID() {
        return fullID;
    }

    public int getTriangles() {
        return triangles;
    }

    public int getStride() {
        return stride;
    }

    public String toString() {
        return "id: " + getID() + " stride: " + getStride();
    }

    @Override
    public boolean interact(String menuOption) {
        Mouse.click(this.randomPoint(), Mouse.RIGHT_BUTTON);
        Time.sleep(253, 311);
        ContextMenu.interact(menuOption);
        Time.sleep(265, 420);
        return true;
    }

    @Override
    public void click() {
        Mouse.click(this.randomPoint(), Mouse.LEFT_BUTTON);
    }

    public boolean isValid() {
        return isValid;
    }

    public ArrayList<Point> getPoints() {
        return points; //clone or not?
    }

    public long getID() {
        return id;
    }

    private Point randomPoint() {
        return points.get(Calculations.getRandomNumber(0, points.size()));
    }

    public Point getLocation() {
        return points.get(points.size() - 1);
    }
}
