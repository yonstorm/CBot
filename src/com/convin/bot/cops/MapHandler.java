package com.convin.bot.cops;

import com.convin.bot.api.common.ImageManipulation;
import com.convin.bot.core.io.URLDownloader;
import com.convin.bot.utils.settings.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import static javax.imageio.ImageIO.read;

/**
 * User: Joni
 * Date: 29.9.2012
 * Time: 13:52
 */
public class MapHandler {
    public static BufferedImage downloadMap() throws IOException {
        return ImageManipulation.createImageFromBytes(URLDownloader.getAsByteArray(new URL(Settings.MAP_FILE_URL)));
    }

    public static void writeMapBytesToFile() {
        String strFilePath = "C:/rsmap/wm.data";
        try {
            FileOutputStream fos = new FileOutputStream(strFilePath);
            BufferedImage WORLD_MAP_ORIG = read(new File("C:/rsmap/WorldMap.png"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(WORLD_MAP_ORIG, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            fos.write(imageInByte);
            fos.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException : " + ex);
        } catch (IOException ioe) {
            System.out.println("IOException : " + ioe);
        }
    }
}
