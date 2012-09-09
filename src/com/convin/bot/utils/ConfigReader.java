package com.convin.bot.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 22.3.2012
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
public class ConfigReader {
    final Properties p;

    public ConfigReader(String file) {
        p = new Properties();
        load(file);
    }

    private void load(String file) {
        try {
            p.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getProperty(String propertyName) {
        return p.getProperty(propertyName);
    }
}
