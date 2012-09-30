package com.convin.bot.api.common;

import com.convin.bot.api.conditions.Condition;
import com.convin.bot.api.conditions.Conditions;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.core.bot.Bot;

/**
 * User: Joni
 * Date: 15.8.2012
 * Time: 14:24
 */
public final class Time {

    /**
     * This makes the Calling thread sleep for a specified time.
     *
     * @param ms Milliseconds to sleep.
     */

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Logging.log(Logging.LogLevel.INFO, "Sleep interrupted");
        }
    }

    /**
     * This makes the Calling thread sleep for X milliseconds between min & max.
     *
     * @param min Minimum milliseconds to sleep.
     * @param max Maximum milliseconds to sleep.
     */
    public static void sleep(int min, int max) {
        sleep(Calculations.getRandomNumber(min, max));
    }

    /**
     * Causes the Calling thread to sleep while given <code>condition</code> is not verified.
     *
     * @param condition Condition to wait to verify
     * @param timeOut   Maximum time to wait for the condition
     * @return True if the condition was verified
     */

    public static boolean waitFor(Conditions condition, int timeOut) {
        long timeMark = System.currentTimeMillis();
        boolean conditionVerified = condition.getCondition().verify();
        while (Bot.CURRENT.getScriptHandler().getCurrentScript().isRunning() && !conditionVerified && System.currentTimeMillis() - timeMark < timeOut) {
            //Logging.log(Logging.LogLevel.INFO, "Waiting for: " + condition.getCondition().name());
            Time.sleep(300, 700);
            conditionVerified = condition.getCondition().verify();
        }
        return conditionVerified;
    }


    /**
     * Causes the Calling thread to sleep while given <code>condition</code> is not verified.
     *
     * @param condition Condition to wait to verify
     * @param timeOut   Maximum time to wait for the condition
     * @return True if the condition was verified
     */
    public static boolean waitFor(Condition condition, int timeOut) {
        long timeMark = System.currentTimeMillis();
        boolean conditionVerified = condition.verify();
        while (Bot.CURRENT.getScriptHandler().getCurrentScript().isRunning() && !conditionVerified && System.currentTimeMillis() - timeMark < timeOut) {
            //Logging.log(Logging.LogLevel.INFO,"Waiting for: " + condition.name());
            Time.sleep(300, 700);
            conditionVerified = condition.verify();
        }
        return conditionVerified;
    }

}
