package com.convin.bot.api.methods;

/**
 * User: Joni
 * Date: 18.9.2012
 * Time: 11:19
 */
public class Camera {
    private static native float getCompassAngle();

    /**
     * Gets the current compass angle 0-360. ( 0 & 360 being North )
     *
     * @return Current compass angle
     */
    public static int getAngle() {
        return (int) getCompassAngle();
    }
}
