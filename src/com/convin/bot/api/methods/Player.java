package com.convin.bot.api.methods;

import com.convin.bot.api.common.Logging;
import org.apache.log4j.Priority;

/**
 * User: JuV
 * Date: 1.8.2012
 * Time: 23:20
 */
public class Player {
    private static long id;

    private Player() {
    }

    /**
     * Checks if current player is present.
     *
     * @return True if present
     */
    public static boolean isPresent() {
        return Models.isPresent(id);
    }

    /**
     * Sets the current players id.
     * Note: This should work automatically and not be needed by a scripter or an user.
     *
     * @param playerID Current players ID
     */
    public static void setID(long playerID) {
        Logging.log(Priority.INFO, "Player id is " + playerID);
        id = playerID;
    }

}
