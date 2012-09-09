package com.convin.bot.api.script;

import com.convin.bot.api.common.Time;
import com.convin.bot.core.bot.Bot;

/**
 * User: JuV
 * Date: 16.4.2012
 * Time: 18:11
 */
public abstract class RandomEventSolver extends ScriptTaskExecutor implements Runnable {

    /**
     * Checks if the RandomEvent can be solved.
     *
     * @return True if the RandomEvent can be solved, else false
     */
    public abstract boolean canBeSolved();

    /**
     * Checks if we are in the RandomEvent.
     *
     * @return True if we are in the RandomEvent, else false
     */
    public abstract boolean isActive();

    /**
     * The name of the RandomEvent.
     *
     * @return Name of the RandomEvent
     */
    public abstract String getName();

    /**
     * Used to initialize the RandomEventSolver, provide scriptTasks etc. here
     */
    public abstract void setup();

    public void run() {
        for (ScriptTask task : getScriptTasks()) {
            if (task.verify()) {
                task.run();
            }
        }
        Time.sleep(300);
    }

    /**
     * Reloads the applet. ( Circumvent the 6hour limit ).
     */
    public void refreshGame() {
        Bot.CURRENT.getDisplayHandler().refreshApplet();
    }
}
