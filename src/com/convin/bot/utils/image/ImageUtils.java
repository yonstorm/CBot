package com.convin.bot.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 28.4.2012
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class ImageUtils {

    public static BufferedImage flip(BufferedImage img) {
        BufferedImage buffImage;
        buffImage = horizontalFlip(img);
        return verticalflip(buffImage);
    }

    public static BufferedImage horizontalFlip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        /*
         * img - the specified image to be drawn. This method does nothing if
         * img is null. dx1 - the x coordinate of the first corner of the
         * destination rectangle. dy1 - the y coordinate of the first corner of
         * the destination rectangle. dx2 - the x coordinate of the second
         * corner of the destination rectangle. dy2 - the y coordinate of the
         * second corner of the destination rectangle. sx1 - the x coordinate of
         * the first corner of the source rectangle. sy1 - the y coordinate of
         * the first corner of the source rectangle. sx2 - the x coordinate of
         * the second corner of the source rectangle. sy2 - the y coordinate of
         * the second corner of the source rectangle. observer - object to be
         * notified as more of the image is scaled and converted.
         *
         */
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }

    /**
     * This method flips the image vertically
     *
     * @param img --> BufferedImage object to be flipped
     * @return flipped image
     */
    public static BufferedImage verticalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getColorModel()
                .getTransparency());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        img.flush();
        return dimg;
    }

    /**
     * This method reads an image from a file
     *
     * @param fileLocation -- > eg. "C:/testImage.jpg"
     * @return BufferedImage of the file read
     */
    public BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;

    }

    /**
     * This method writes a buffered image to a file.
     *
     * @param img          BufferedImage to write to the file
     * @param fileLocation --> e.g. "C:/testImage.jpg"
     * @param extension    --> e.g. "jpg","gif","png"
     */
    public void writeImage(BufferedImage img, String fileLocation,
                           String extension) {
        try {
            File outputfile = new File(fileLocation);
            ImageIO.write(img, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
