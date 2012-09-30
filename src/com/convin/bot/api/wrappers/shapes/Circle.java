package com.convin.bot.api.wrappers.shapes;

import java.awt.*;

/**
 * User: JuV
 * Date: 21.9.2012
 * Time: 22:39
 */
public class Circle {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(Point p) {
        return p.distance(center) <= radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean intersects(Circle c) {
        return center.distance(c.center) <= radius + c.radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

}
