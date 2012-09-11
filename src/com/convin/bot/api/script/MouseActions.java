package com.convin.bot.api.script;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: JuV
 * Date: 9.9.2012
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public abstract interface MouseActions {
    public abstract void mouseMoved(Point p);

    public abstract void mouseClicked(Point p, int button);

    public abstract void mouseReleased(Point p, int button);

    public abstract void mouseDragged(Point p, int button);

    public abstract void mousePressed(Point p, int button);
}
