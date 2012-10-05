package com.convin.bot.utils;

import com.convin.bot.core.io.URLDownloader;
import com.convin.bot.utils.settings.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * User: Joni
 * Date: 5.10.2012
 * Time: 13:00
 */
public class BotCache {
    private static final File cacheFolder = new File(Settings.CACHE_FOLDER);

    public static boolean exists() {
        return cacheFolder.isDirectory();
    }

    public static boolean fileExists(String fileName) {
        return new File(Settings.CACHE_FOLDER + fileName).isFile();
    }

    public static void createCache() {
        if (cacheFolder.mkdir()) {
            System.out.println("Created cache");
        }
    }

    public static void createDir(String dirName) {

    }

    public static void downloadMap() throws IOException {
        ByteBuffer file = URLDownloader.getAsByteArray(new URL(Settings.MAP_FILE_URL));
        FileOutputStream fos = new FileOutputStream(Settings.CACHE_FOLDER + "wm.data");
        fos.write(file.array());
        fos.close();
        System.out.println("Map downloaded");
    }
}
