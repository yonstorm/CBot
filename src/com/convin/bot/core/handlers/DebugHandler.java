package com.convin.bot.core.handlers;

import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.script.Painting;
import com.convin.bot.core.bot.AccessorMethods;

import java.awt.*;

/**
 * User: Joni
 * Date: 6.8.2012
 * Time: 13:23
 */
public class DebugHandler {
    private final AccessorMethods ac;

    public DebugHandler(AccessorMethods ac) {
        this.ac = ac;
    }

    public void paintDebug(Graphics g) {
        Mouse.paint(g);
        if (ac.getSettings().shouldDebugObjects()) {
            if (ac.getScript() != null) {
                if (ac.getScript().isRunning() && ac.getScriptHandler().getCurrentScript() instanceof Painting) {
                    ((Painting) ac.getScript()).paint(g);
                }
            }
        }
        if (ac.getRandomHandler().getCurrentActiveRandomEventSolver() instanceof Painting) {
            ((Painting) ac.getRandomHandler().getCurrentActiveRandomEventSolver()).paint(g);
        }
    }
}
