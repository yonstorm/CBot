package com.convin.bot.cops;

import com.convin.bot.api.common.ImageManipulation;
import com.convin.bot.api.common.Logging;
import com.convin.bot.api.methods.Camera;
import com.convin.bot.api.methods.Minimap;
import com.convin.bot.core.bot.Bot;
import com.convin.bot.utils.settings.Settings;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 * User: JuV
 * Date: 21.9.2012
 * Time: 16:16
 */
public class PositionFinder {
    private static opencv_core.IplImage WORLD_MAP_COMP;
    private static int width;
    private static int height;

    private static boolean isSetup = false;
    private static Point lastPosition;

    private static boolean setup() {
        BufferedImage WORLD_MAP_ORIG = null;
        if (!mapExists()) {
            try {
                WORLD_MAP_ORIG = ImageManipulation.stripAlpha(MapHandler.downloadMap());
                //WORLD_MAP_ORIG = ImageManipulation.stripAlpha(ImageIO.read(new File("C:/rsmap/worldmap.png")));
            } catch (IOException e) {
                Logging.log(Logging.LogLevel.ERROR, "Could not download map file");
                Bot.CURRENT.getScriptHandler().stopScript();
            }
        } else {
            try {
                WORLD_MAP_ORIG = ImageManipulation.stripAlpha(ImageIO.read(new File(Settings.CACHE_FOLDER + "wm.data")));
            } catch (IOException e) {
                Logging.log(Logging.LogLevel.ERROR, "Could not load map file");
                Bot.CURRENT.getScriptHandler().stopScript();
            }
        }
        width = WORLD_MAP_ORIG.getWidth();
        height = WORLD_MAP_ORIG.getHeight();
        WORLD_MAP_COMP = convert2CompatibleImage(WORLD_MAP_ORIG);
        isSetup = true;
        return true;
    }

    private static boolean mapExists() {
        File map = new File(Settings.CACHE_FOLDER + "wm.data");
        return map.isFile();
    }

    public static Point findPosition() {
        if (!isSetup) {
            setup();
        }
        Rectangle maxRegion = null;
        // strip alpha
        IplImage region_img;
        if (lastPosition == null) {
            region_img = convert2CompatibleImage(ImageManipulation.stripAlpha(Minimap.getRegionImage().getSubimage(45, 45, 412, 412)));
        } else {
            maxRegion = getMaxRegionRect(lastPosition.x, lastPosition.y);
            if (maxRegion.getX() < 0 || maxRegion.getY() < 0 || maxRegion.width < 0 || maxRegion.height < 0) {
                Logging.log(Logging.LogLevel.ERROR, "Region position is negative, weird. Please report this.");
                return new Point(lastPosition);
            }
            region_img = IplImage.create(maxRegion.width, maxRegion.height, IPL_DEPTH_8U, 3);
            cvSetImageROI(WORLD_MAP_COMP, cvRect(maxRegion.x, maxRegion.y, maxRegion.width, maxRegion.height));
            cvCopy(WORLD_MAP_COMP, region_img);
            cvResetImageROI(WORLD_MAP_COMP);
        }

        // add rotation & strip alpha
        BufferedImage minimap_img_orig = ImageManipulation.stripAlpha(Minimap.getMinimapImage());
        IplImage minimap_img = rotateImage(convert2CompatibleImage(minimap_img_orig), Camera.getAngle());
        Point regionPosition = null;

        if (lastPosition == null) {
            regionPosition = TemplateMatcher.find(region_img, WORLD_MAP_COMP, true);
        } else regionPosition = new Point(maxRegion.x, maxRegion.y);

        Point minimapPosition = TemplateMatcher.find(minimap_img, region_img, false);

        region_img.release();
        minimap_img.release();
        minimap_img_orig.flush();
        Point result = new Point(regionPosition.x + minimapPosition.x, regionPosition.y + minimapPosition.y);
        lastPosition = result;

        return result;
    }

    private static Rectangle getMaxRegionRect(int x, int y) {
        if (width - x >= 256 && height - y >= 256 && x >= 256 && y >= 256) {
            return new Rectangle(lastPosition.x - 256, lastPosition.y - 256, 512, 512);
        }
        int maxX = 256;
        int maxY = 256;
        int maxW = 512;
        int maxH = 512;
        if (x < 256) {
            maxX = x;
        }
        if (y < 256) {
            maxY = y;
        }
        if (width - x < 256) {
            maxW = width - x;
        }
        if (height - y < 256) {
            maxH = height - y;
        }
        return new Rectangle(lastPosition.x - maxX, lastPosition.y - maxY, maxW, maxH);
    }

    private static opencv_core.IplImage convert2CompatibleImage(BufferedImage img) {
        opencv_core.IplImage image = opencv_core.IplImage.createFrom(img);
        opencv_core.IplImage result = opencv_core.IplImage.create(image.width(), image.height(), opencv_core.IPL_DEPTH_8U, 3);
        cvCvtColor(image, result, opencv_imgproc.CV_BGR2RGB);
        image.release();
        img.flush();
        return result;
    }

    private static opencv_core.IplImage rotateImage(opencv_core.IplImage img, float rotationDegrees) {
        opencv_core.IplImage my_dest = cvCreateImage(new opencv_core.CvSize(img.width(), img.height()), img.depth(), img.nChannels());

        opencv_core.CvPoint2D32f my_center = new opencv_core.CvPoint2D32f(img.width() / 2,
                img.height() / 2);
        int flags = CV_INTER_LINEAR + CV_WARP_FILL_OUTLIERS;

        opencv_core.CvMat map_matrix = cvCreateMat(2, 3, CV_32FC1);
        cv2DRotationMatrix(my_center, rotationDegrees, 1.0d, map_matrix);
        cvWarpAffine(img, my_dest, map_matrix, flags,
                cvScalarAll(0));


        IplImage small = IplImage.create(my_dest.width() - 40, my_dest.height() - 40, my_dest.depth(), my_dest.nChannels());
        cvSetImageROI(my_dest, cvRect(20, 20, my_dest.width() - 40, my_dest.height() - 40));
        cvCopy(my_dest, small);

        // Release image memory
        cvReleaseImage(my_dest);
        img.release();

        return small;
    }
}
