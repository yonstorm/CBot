package com.convin.bot.core.handlers;

import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.handlers.updaters.OpenglObjectUpdater;

/**
 * User: Joni
 * Date: 6.8.2012
 * Time: 14:43
 */
public class OpenglHandler {
    private final OpenglObjectUpdater objectUpdater;

    public OpenglHandler(AccessorMethods ac) {
        AccessorMethods ac1 = ac;
        this.objectUpdater = new OpenglObjectUpdater(ac);
    }

    public void start() {
        objectUpdater.start();
    }
}
