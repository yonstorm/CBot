package com.convin.bot.cops;

import com.googlecode.javacv.cpp.opencv_core;

import java.awt.*;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_TM_SQDIFF_NORMED;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvMatchTemplate;

/**
 * User: JuV
 * Date: 21.9.2012
 * Time: 20:09
 */
public class TemplateMatcher {
    //private static IplImage res;
    private static double[] minVal = new double[1];
    private static double[] maxVal = new double[1];
    private static CvPoint minLoc = new CvPoint();
    private static CvPoint maxLoc = new CvPoint();

    private static CvPoint tempRect0 = new CvPoint();
    private static CvPoint tempRect1 = new CvPoint();

    public static Point find(opencv_core.IplImage template, opencv_core.IplImage scene, boolean region) {
        Point result = new Point(0, 0);
        if (template == null) return result;

        IplImage res = cvCreateImage(cvSize(scene.width() - template.width() + 1,
                scene.height() - template.height() + 1), IPL_DEPTH_32F, 1);
        if (res != null) {
            cvMatchTemplate(scene, template, res, CV_TM_SQDIFF_NORMED); // CV_TM_SQDIFF_NORMED
            cvMinMaxLoc(res, minVal, maxVal, minLoc, maxLoc, null);
            //Logging.log(Logging.LogLevel.INFO, "Match %" + (maxVal[0] * 100));
            tempRect0.x(minLoc.x());
            tempRect0.y(minLoc.y());
            tempRect1.x(minLoc.x() + template.width());
            tempRect1.y(minLoc.y() + template.height());
            if (!region) {
                result = new Point((tempRect1.x() - tempRect0.x()) / 2 + tempRect0.x(), (tempRect1.y() - tempRect0.y()) / 2 + tempRect0.y());
            } else {
                result = new Point(tempRect0.x(), tempRect0.y());
            }
        }
        if (res != null) {
            cvReleaseImage(res);
        }
        return result;

    }
}
