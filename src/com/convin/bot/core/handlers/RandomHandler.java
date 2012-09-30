package com.convin.bot.core.handlers;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.common.Time;
import com.convin.bot.api.script.RandomEventSolver;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.io.RandomLoader;

import java.util.ArrayList;

/**
 * User: JuV
 * Date: 3.5.2012
 * Time: 18:24
 */
public class RandomHandler implements Runnable {
    private ArrayList<RandomEventSolver> randomEventSolvers = new ArrayList<RandomEventSolver>();
    private final AccessorMethods ac;
    private RandomEventSolver currentActiveRandomEventSolver;
    private boolean shouldRun;

    public RandomHandler(AccessorMethods ac) {
        this.ac = ac;
    }

    @Override
    public void run() {
        shouldRun = true;
        if (randomEventSolvers.isEmpty()) {
            Logging.log(Logging.LogLevel.INFO, "No randomEventSolvers loaded! Reason: None found");
            shouldRun = false;
        }

        while (shouldRun) {
            for (RandomEventSolver r : randomEventSolvers) {
                if (r.isActive()) {
                    currentActiveRandomEventSolver = r;
                    Logging.log(Logging.LogLevel.INFO, "Antirandom activated - " + r.getName());
                    if (r.canBeSolved()) {
                        ac.getScript().pause(true);
                        r.setup();
                        while (r.isActive()) {
                            r.run();
                            Time.sleep(220);
                        }
                        ac.getScript().pause(false);
                    } else {
                        Logging.log(Logging.LogLevel.ERROR, "Random event " + r.getName() + " cannot be solved.");
                        ac.getScript().stop();
                        //todo implement logout if random cannot be solved.
                    }

                }
            }
            Time.sleep(1000);
        }
    }

    public void start(String threadName) {
        Logging.log(Logging.LogLevel.INFO, "Starting RandomEvent handler");
        randomEventSolvers = RandomLoader.loadRandoms();
        Thread t = new Thread(this, threadName);
        t.start();
    }

    public void stop() {
        Logging.log(Logging.LogLevel.INFO, "Stopping RandomEvent handler");
        shouldRun = false;
    }

    public RandomEventSolver getCurrentActiveRandomEventSolver() {
        return currentActiveRandomEventSolver;
    }
}
