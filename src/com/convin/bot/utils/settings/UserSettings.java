package com.convin.bot.utils.settings;

import com.convin.bot.utils.ConfigReader;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 22.3.2012
 * Time: 1:35
 * To change this template use File | Settings | File Templates.
 */
public class UserSettings {
    private static final ConfigReader c = new ConfigReader(Settings.USER_DIR + Settings.FILE_SEPARATOR + "userconfig.ini");
    public boolean EnablePaint = Boolean.parseBoolean(c.getProperty("EnablePaint"));
    public int PaintDrawDelayMS = Integer.parseInt(c.getProperty("PaintDrawDelayMS"));
    public int GameImageDrawDelayMS = Integer.parseInt(c.getProperty("GameImageDrawDelayMS"));

    public static int getPropertyInt(String property) {
        return Integer.parseInt(c.getProperty(property));
    }

    public static boolean getPropertyBoolean(String property) {
        return Boolean.parseBoolean((c.getProperty("EnablePaint")));
    }
}
