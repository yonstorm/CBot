package com.convin.bot.api.script;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.common.Time;
import com.convin.bot.api.input.Focus;
import com.convin.bot.core.bot.Bot;
import org.apache.log4j.Priority;

import java.util.ArrayList;

/**
 * User: JuV
 * Date: 1.4.2012
 * Time: 18:24
 */

/**
 * Every script made extends this framework class.
 */

public abstract class Script extends ScriptTaskExecutor implements Runnable {
    private final ArrayList<ScriptTask> scriptTasks = new ArrayList<ScriptTask>();
    private boolean onStartDone = false;
    private boolean isRunning = true;
    private boolean isPaused = false;
    private Thread scriptThread;

    /**
     * Used to initialize the Script, provide scriptTasks etc. here.
     */
    public abstract void setup();

    /**
     * Will be run everytime before the script starts, if returns false script will not start.
     *
     * @return True if onStart is succesful.
     */
    public abstract boolean onStart();

    /**
     * Used to provide needed ScriptTasks for execution.
     *
     * @param scriptTask The task you want to provide for execution.
     */
    public void provide(final ScriptTask scriptTask) {
        scriptTasks.add(scriptTask);
        Logging.log(Priority.INFO, "ScriptTask provided: " + scriptTask.getClass().getSimpleName());
    }

    @Override
    public void run() {
        Logging.log(Priority.DEBUG, "Starting script");
        setup();
        while (isRunning) {
            synchronized (this) {
                while (isPaused)
                    try {
                        Logging.log(Priority.INFO, "Script paused");
                        wait();
                    } catch (InterruptedException e) {
                        Logging.log(Priority.INFO, "Pause interrupted");
                    }
            }
            if (onStartDone) {
                for (ScriptTask scriptTask : scriptTasks) {
                    if (scriptTask.verify() && !Thread.currentThread().isInterrupted()) {
                        scriptTask.run();
                    }
                    Time.sleep(200, 300);
                }
            } else if (!onStartDone) {
                Focus.giveFocus();
                onStartDone = onStart();
            }
        }

    }

    public void start() {
        scriptThread = new Thread(this);
        scriptThread.setName("Script Thread");
        scriptThread.start();
        System.out.println("dCMDST" + System.currentTimeMillis());
        Bot.CURRENT.getRandomHandler().start("RandomEventSolver - Thread");
    }

    /**
     * Pause the current script.
     *
     * @param pause True to pause the script, false to resume
     */
    public synchronized void pause(boolean pause) {
        isPaused = pause;
        if (!isPaused) {
            notify();
            Logging.log(Priority.INFO, "Script resumed");
        }
    }

    /**
     * Stops current script totally.
     */
    public void stop() {
        Logging.log(Priority.INFO, "Stopping script");
        System.out.println("dCMDRT");
        isRunning = false;
        Bot.CURRENT.getRandomHandler().stop();
        scriptThread.interrupt();
    }

    /**
     * Check if current script is running.
     *
     * @return True if current script is running, else false
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Check if current script is paused.
     *
     * @return True if current script is paused, else false
     */
    public boolean isPaused() {
        return isPaused;
    }


}
