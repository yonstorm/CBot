package com.convin.bot.api.methods;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.methods.color.ColorUtil;
import com.convin.bot.api.wrappers.GLModel;
import com.convin.bot.api.wrappers.GLTexture;
import com.convin.bot.cops.PositionFinder;

import java.awt.*;

/**
 * User: JuV
 * Date: 1.8.2012
 * Time: 23:20
 */
public class Player {
    private static long id = 0L;

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
     * Note: This should work automatically and should not be needed by a scripter or an user.
     *
     * @param playerID Current players ID
     */
    public static void setID(long playerID) {
        Logging.log(Logging.LogLevel.INFO, "Player id is " + playerID);
        id = playerID;
    }

    /**
     * Gets current location on World map.
     *
     * @return Location of the player
     */
    public static Point getLocation() {
        return PositionFinder.findPosition();
    }

    /**
     * Checks if the player is moving.
     *
     * @return True if player is moving
     */
    public static boolean isMoving() {
        GLTexture flag = Textures.find(new Filter<GLTexture>() {
            @Override
            public boolean accept(GLTexture t) {
                return t.getId() == 14401 && t.getTextureID() == 203 && ColorUtil.distance(new Color(t.getPrecisionID()), new Color(5507594)) <= 5;
            }
        });
        return flag.isValid();
    }

    /**
     * Gets current players ID.
     *
     * @return Current players ID
     */
    public static long getId() {
        return id;
    }

    /**
     * Gets current players model from cache.
     *
     * @return GLModel of current player
     */
    public static GLModel getModel() {
        return Models.find(id);
    }


}
