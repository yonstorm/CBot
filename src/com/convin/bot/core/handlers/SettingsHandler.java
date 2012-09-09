package com.convin.bot.core.handlers;

import com.convin.bot.BotMain;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.utils.settings.UserSettings;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 23.7.2012
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class SettingsHandler {
    private boolean paintEnabled;

    public SettingsHandler(AccessorMethods accessorMethods) {
        AccessorMethods ac = accessorMethods;
        int paintDrawDelay = UserSettings.getPropertyInt("PaintDrawDelayMS");
        int gameImageDrawDelay = UserSettings.getPropertyInt("GameImageDrawDelayMS");
        paintEnabled = UserSettings.getPropertyBoolean("EnablePaint");
    }

    public boolean isPaintEnabled() {
        return paintEnabled;
    }

    public void setPaintState(boolean paintState) {
        paintEnabled = paintState;
    }

    public boolean isInputBlocked() {
        return BotMain.eventQueue.blockInput;
    }

    public void setBlockInput(boolean shouldBlock) {
        BotMain.eventQueue.blockInput = shouldBlock;
    }

    public void paint(Graphics g) {
        //g.drawString("Has focus: " + String.valueOf(ac.getCanvas().hasFocus()), 20,50);
    }

    public boolean shouldDebugObjects() {
        boolean debugObjects = true;
        return debugObjects;
    }
}
