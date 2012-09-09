package com.convin.bot.api.common;

import com.convin.bot.api.math.Calculations;
import org.apache.log4j.Priority;

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
            Logging.log(Priority.DEBUG, "Sleep interrupted");
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

}
