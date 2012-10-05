package com.convin.bot.utils.settings;

import com.convin.bot.utils.ConfigReader;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 22.3.2012
 * Time: 1:16
 * To change this template use File | Settings | File Templates.
 */
public class Settings {
    //COPS
    public static final String MAP_FILE_URL = "http://www.convinbot.net/cops/wm.data";
    // DIRECTORY STRUCTURE
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String USER_DIR = getUserDir();
    //public static final String REALDIR = System.getProperty("user.dir");
    //public static final String USER_DIR = REALDIR.substring(0, REALDIR.length() - 5);
    public static final String APPDATA_FOLDER = System.getenv("APPDATA") + FILE_SEPARATOR;
    public static final String CACHE_FOLDER = APPDATA_FOLDER + "Convin" + FILE_SEPARATOR;

    public static final String SCRIPTS_PATH = USER_DIR + FILE_SEPARATOR + "scripts" + FILE_SEPARATOR;
    public static final String DATA_PATH = USER_DIR + FILE_SEPARATOR + "data" + FILE_SEPARATOR;
    public static final String RANDOMS_PATH = DATA_PATH + FILE_SEPARATOR + "randoms" + FILE_SEPARATOR;
    public static final String DLL_PATH = DATA_PATH + FILE_SEPARATOR + "dlls" + FILE_SEPARATOR;
    //public static final String DL_LOCATION = DATA_PATH + FILE_SEPARATOR + "jars" + FILE_SEPARATOR + "dld.jar"; // The bot downloads .jar file from URL to this folder
    public static final String DL_LOCATION = CACHE_FOLDER + "gamepack.jar"; // The bot downloads .jar file from URL to this folder
    public static final String ICON_PATH = DATA_PATH + FILE_SEPARATOR + "icons" + FILE_SEPARATOR;
    //public static final String AUTH_FILE = DATA_PATH + FILE_SEPARATOR + "auth.ak";
    public static final String AUTH_FILE = CACHE_FOLDER + "auth.ak";
    // Initialize the config reader - for fututre use.
    private static final ConfigReader c = new ConfigReader(DATA_PATH + "config.ini");

    // Patterns for handling
    public static final String SOURCE_PATTERN = "src=\"(.*)\" ";
    public static final String ARCHIVE_PATTERN = "archive=(.*) ";
    public static final String CODE_PATTERN = "code=(.*) ";
    public static final String PARAMETER_PATTERN = "<param name=\"([^\\s]+)\"\\s+value=\"([^>]*)\">";
    public static final String URL = c.getProperty("DownloadFrom"); // URL to download "applet" from.
    public static final int APPLET_WIDTH = 765;
    public static final int APPLET_HEIGHT = 553;

    public static String getUserDir() {
        String userdir = System.getProperty("user.dir");
        if (userdir.endsWith("data")) {
            return userdir.substring(0, userdir.length() - 5);
        }
        return userdir;
    }
}
