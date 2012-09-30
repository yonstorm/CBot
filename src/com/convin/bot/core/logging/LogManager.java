package com.convin.bot.core.logging;

import com.convin.bot.utils.settings.Settings;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import java.util.Properties;

/**
 * User: Joni
 * Date: 31.8.2012
 * Time: 21:04
 */
public class LogManager {
    private static final Logger logger = Logger.getRootLogger();//Logger.getRootLogger();

    public static Logger getLogger() {
        return logger;
    }

    public static void setupLogger(JTextArea jTextArea) {
        TextAreaAppender.setTextArea(jTextArea);

        Properties logProperties = new Properties();
        logProperties.put("log4j.rootLogger", "INFO, CONSOLE, TEXTAREA,ReportFileAppender");
        logProperties.put("log4j.appender.ReportFileAppender", "org.apache.log4j.FileAppender");
        logProperties.put("log4j.appender.ReportFileAppender.File", Settings.DATA_PATH + "report.log");
        logProperties.put("log4j.appender.ReportFileAppender.layout", "org.apache.log4j.PatternLayout");
        logProperties.put("log4j.appender.ReportFileAppender.layout.ConversionPattern", " %-4r [%t] %-5p %c %x - %m%n");


        logProperties.put("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender"); // A standard console appender
        logProperties.put("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout"); //See: http://logging.apache.org/log4j/docs/api/org/apache/log4j/PatternLayout.html
        logProperties.put("log4j.appender.CONSOLE.layout.ConversionPattern", "%d{HH:mm:ss}: %5.5p %m%n");

        logProperties.put("log4j.appender.TEXTAREA", "com.convin.bot.core.logging.TextAreaAppender");  // Our custom appender
        logProperties.put("log4j.appender.TEXTAREA.layout", "org.apache.log4j.PatternLayout"); //See: http://logging.apache.org/log4j/docs/api/org/apache/log4j/PatternLayout.html
        logProperties.put("log4j.appender.TEXTAREA.layout.ConversionPattern", "%d{HH:mm:ss}: %5.5p %m%n");

        PropertyConfigurator.configure(logProperties);
    }

}
