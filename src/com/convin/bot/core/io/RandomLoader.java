package com.convin.bot.core.io;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.script.RandomEventSolver;
import com.convin.bot.utils.settings.Settings;
import org.apache.log4j.Priority;

import java.io.File;
import java.util.ArrayList;

/**
 * User: Joni
 * Date: 16.8.2012
 * Time: 19:55
 */
public class RandomLoader {

    public static ArrayList<RandomEventSolver> loadRandoms() {
        ArrayList<RandomEventSolver> randomEventSolvers = new ArrayList<RandomEventSolver>();

        File dir = new File(Settings.RANDOMS_PATH);
        if (dir.listFiles() == null) {
            Logging.log(Priority.ERROR, "Could not load Randoms!");
            return null;
        }
        CustomClassLoader loader = new CustomClassLoader();

        for (File f : dir.listFiles()) {
            if (f.getName().contains(".class")) {
                String className = f.getName().replace(".class", "");
                Class<?> randomClass;
                if (className.equals("RandomEventSolver")) {
                    continue;
                }
                randomClass = loader.loadCustomClass(className, CustomClassLoader.Type.RANDOM);
                if (!isRandomClass(randomClass)) {
                    continue;
                }

                try {
                    randomEventSolvers.add((RandomEventSolver) randomClass.newInstance());
                } catch (InstantiationException e) {
                    Logging.log(Priority.ERROR, "Could not load random: " + className + "!");
                } catch (IllegalAccessException e) {
                    Logging.log(Priority.INFO, " Could not load random: " + className + "!");
                }

            }
        }

        return randomEventSolvers;
    }

    private static boolean isRandomClass(Class<?> randomClass) {
        return randomClass != null && randomClass.getSuperclass().getSimpleName().equals("RandomEventSolver");
    }
}
