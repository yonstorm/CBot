package com.convin.bot.api.common;

import com.convin.bot.core.logging.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * User: JuV
 * Date: 31.5.2012
 * Time: 21:27
 */
public class Logging {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Add a log message to the bot instances log area.
     *
     * @param priority Specifies the priority of the log message
     * @param message  Message to add to log
     */
    public static void log(Priority priority, Object message) {
        logger.log(priority, message);
    }
}
