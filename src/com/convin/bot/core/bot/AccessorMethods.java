package com.convin.bot.core.bot;

import com.convin.bot.api.script.Script;
import com.convin.bot.core.handlers.*;
import com.convin.bot.core.loader.AppletLoader;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: stromberg
 * Date: 18.4.2012
 * Time: 10:59
 */
public class AccessorMethods {

    private final Bot bot;

    public AccessorMethods(Bot bot) {
        this.bot = bot;
    }

    public Component getMouseListener() {
        return getCanvas();
    }

    public Component getMouseMotionListener() {
        return getCanvas();
    }

    public Component getKeyListener() {
        return getCanvas();
    }

    public Component getFocusListener() {
        return getCanvas();
    }

    public ScriptHandler getScriptHandler() {
        return bot.getScriptHandler();
    }

    public Canvas getCanvas() {
        return bot.getDisplayHandler().getCanvas();
    }

    public Applet getApplet() {
        return bot.getDisplayHandler().getApplet();
    }

    public Script getScript() {
        return bot.getScriptHandler().getCurrentScript();
    }

    public BufferedImage getImage() {
        return bot.getDisplayHandler().getImage();
    }

    public boolean getIsRenewing() {
        return bot.getDisplayHandler().isRenewing();
    }

    public DisplayHandler getDisplayHandler() {
        return bot.getDisplayHandler();
    }

    public SettingsHandler getSettings() {
        return bot.getSettingsHandler();
    }

    public InputHandler getInputHandler() {
        return bot.getInputHandler();
    }


    public Bot getBot() {
        return bot;
    }

    public DebugHandler getDebugHandler() {
        return bot.getDebugHandler();
    }

    public RandomHandler getRandomHandler() {
        return bot.getRandomHandler();
    }

    public AppletLoader getLoader() {
        return bot.getLoader();
    }
}
