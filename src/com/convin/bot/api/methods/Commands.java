package com.convin.bot.api.methods;

import com.convin.bot.core.bot.Bot;

/**
 * User: JuV
 * Date: 24.8.2012
 * Time: 1:50
 */
public class Commands {
    private static native void sendCommand(int command, boolean state);

    private Commands() {
    }

    /**
     * Enable or disable painting of image captured from opengl
     *
     * @param shouldPaint True to enable
     */
    public static void setGLPaint(boolean shouldPaint) {
        sendCommand(1, shouldPaint);
        Bot.CURRENT.getDisplayHandler().getDisplayUpdater().setPaused(!shouldPaint);
    }

    /**
     * Temporary solution, should lower the cpu usage 4-8%. Caution: might affect scripts quality & stability
     *
     * @param enabled True to enable
     */
    public static void setPerformanceBoost(boolean enabled) {
        sendCommand(2, !enabled);
    }
}
