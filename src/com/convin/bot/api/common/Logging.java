package com.convin.bot.api.common;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * User: JuV
 * Date: 31.5.2012
 * Time: 21:27
 */
public class Logging {
    public static enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    private static final Logger logger = Logger.getLogger("Logging");

    /**
     * Add a log message to the bot instances log area.
     *
     * @param priority Specifies the priority of the log message
     * @param message  Message to add to log
     */
    @Deprecated
    public static void log(Priority priority, Object message) {
        logger.log(priority, message);
    }

    /**
     * Adds a log message to the bot instances log area.
     *
     * @param level   Specifies the level of the log message
     * @param message Message to add to the log
     */
    public static void log(LogLevel level, Object message) {
        switch (level) {
            case DEBUG:
                logger.log(Priority.DEBUG, message);
                break;
            case INFO:
                logger.log(Priority.INFO, message);
                break;
            case WARN:
                logger.log(Priority.WARN, message);
                break;
            case ERROR:
                logger.log(Priority.ERROR, message);
                break;
            case FATAL:
                logger.log(Priority.FATAL, message);
                break;
            default:
                logger.log(Priority.DEBUG, message);
                break;
        }
    }
}
