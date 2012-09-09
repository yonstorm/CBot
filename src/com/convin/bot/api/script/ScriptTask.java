package com.convin.bot.api.script;

/**
 * User: JuV
 * Date: 30.7.2012
 * Time: 18:27
 */
public abstract class ScriptTask implements Runnable {
    /**
     * Condition to verify for task to execute.
     *
     * @return True if Condition is verified
     */
    public abstract boolean verify();

    /**
     * Will be run if verify() returns true.
     */
    public abstract void run();

}
