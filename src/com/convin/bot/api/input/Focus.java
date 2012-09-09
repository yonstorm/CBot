package com.convin.bot.api.input;

import com.convin.bot.core.bot.Bot;
import com.convin.bot.core.handlers.DisplayHandler;

import java.awt.event.FocusEvent;

/**
 * User: JuV
 * Date: 29.4.2012
 * Time: 22:35
 */
public final class Focus {

    private static final DisplayHandler dHandler = Bot.CURRENT.getDisplayHandler();

    private Focus() {
    }

    /**
     * Causes the Applet to gain focus
     */

    public static void giveFocus() {
        if (!Bot.CURRENT.getInputHandler().hasFocus()) {
            FocusEvent fe = new FocusEvent(dHandler.getCanvas(), FocusEvent.FOCUS_GAINED, false);
            dHandler.getCanvas().processEvent(fe);
        }
    }

    /**
     * Causes the Applet to lose focus
     */
    public static void loseFocus() {
        FocusEvent fe = new FocusEvent(dHandler.getCanvas(), FocusEvent.FOCUS_LOST, true);
        dHandler.getCanvas().processEvent(fe);
    }

}
