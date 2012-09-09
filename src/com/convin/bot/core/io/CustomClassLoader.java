package com.convin.bot.core.io;

import com.convin.bot.utils.settings.Settings;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: Joni
 * Date: 16.8.2012
 * Time: 20:09
 */


public class CustomClassLoader extends ClassLoader {
    private Type currentType;

    public static enum Type {
        RANDOM, SCRIPT
    }

    public CustomClassLoader() {
        super(CustomClassLoader.class.getClassLoader());
    }

    public Class<?> loadCustomClass(String className, Type type) {
        this.currentType = type;
        return findClass(className);
    }

    public Class<?> findClass(String className) {
        byte[] classByte;
        Class<?> result = null;

        try {
            String classFile = className.replace('.', '/') + ".class";
            switch (currentType) {
                case SCRIPT:
                    classByte = loadClassData(Settings.SCRIPTS_PATH + classFile);
                    break;
                case RANDOM:
                    classByte = loadClassData(Settings.RANDOMS_PATH + classFile);
                    break;
                default:
                    classByte = loadClassData(Settings.SCRIPTS_PATH + classFile);

            }
            result = defineClass(className, classByte, 0, classByte.length);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] loadClassData(String className) throws IOException {
        File f;
        f = new File(className);
        int size = (int) f.length();
        byte buff[] = new byte[size];
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        dis.readFully(buff);
        dis.close();

        return buff;
    }

}
