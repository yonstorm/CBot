package com.convin.bot.core.io;

import com.convin.bot.api.script.ScriptManifest;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.utils.settings.Settings;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: Joni
 * Date: 20.8.2012
 * Time: 14:06
 */
public class ScriptLoader {
    private final ArrayList<ScriptData> scripts = new ArrayList<ScriptData>();

    public ScriptLoader(AccessorMethods ac) {
    }

    public boolean loadScripts() {
        scripts.clear();
        File dir = new File(Settings.SCRIPTS_PATH);
        if (dir == null || dir.listFiles() == null) {
            JOptionPane.showMessageDialog(null, "Check your scripts folder, anything there?", "ERROR: Folder problem", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        CustomClassLoader cl = new CustomClassLoader();

        for (File f : dir.listFiles()) {
            if (f.getName().contains(".class")) {
                String className = f.getName().replace(".class", "");
                Class<?> classToLoad;
                classToLoad = cl.loadCustomClass(className, CustomClassLoader.Type.SCRIPT);
                if (!isScript(classToLoad) || !classToLoad.isAnnotationPresent(ScriptManifest.class)) {
                    continue;
                }
                ScriptManifest manifest = classToLoad.getAnnotation(ScriptManifest.class);

                if (manifest == null || manifest.scriptName() == null || manifest.scriptName().length() < 1) {
                    continue;
                }

                scripts.add(new ScriptData(classToLoad, manifest.scriptName(), manifest.authors(), manifest.category(), manifest.description(), manifest.version()));
            } else if (f.getName().contains(".jar")) {
                try {
                    ClassLoader loader = URLClassLoader.newInstance(new URL[]{new URL("jar:file:" + f.getAbsolutePath() + "!/")});
                    ZipFile zip = new ZipFile(f.getAbsolutePath());
                    Enumeration<? extends ZipEntry> entries = zip.entries();
                    if (!entries.hasMoreElements()) {
                        continue;
                    }
                    for (ZipEntry e = entries.nextElement(); entries.hasMoreElements(); e = entries.nextElement()) {
                        if (e == null) {
                            continue;
                        }
                        String name = e.getName();
                        if (name == null || !name.contains(".class")) {
                            continue;
                        }
                        Class<?> classFile = null;
                        classFile = loader.loadClass(name.replace(".class", ""));

                        if (classFile == null) {
                            continue;
                        }
                        if (!isScript(classFile) || !classFile.isAnnotationPresent(ScriptManifest.class)) {
                            continue;
                        }
                        ScriptManifest manifest = classFile.getAnnotation(ScriptManifest.class);
                        if (manifest == null || manifest.scriptName() == null || manifest.scriptName().length() < 1) {
                            continue;
                        }

                        scripts.add(new ScriptData(classFile, manifest.scriptName(), manifest.authors(), manifest.category(), manifest.description(), manifest.version()));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return !scripts.isEmpty();
    }

    private boolean isScript(Class<?> clazz) {
        return clazz != null && clazz.getSuperclass().getSimpleName().equals("Script");
    }

    public String[] getScriptCategories() {
        LinkedList<String> names = new LinkedList<String>();
        for (ScriptData s : scripts) {
            boolean b = false;
            for (String name : names) {
                if (name.equalsIgnoreCase(s.category)) {
                    b = true;
                }
            }
            if (!b) {
                names.add(s.category);
            }
        }
        String[] result = names.toArray(new String[names.size()]);
        Arrays.sort(result);
        return result;
    }

    public ArrayList<ScriptData> getScriptsByCategory(String category) {
        ArrayList<ScriptData> sal = new ArrayList<ScriptData>();
        for (ScriptData s : scripts)
            if (s.category.equalsIgnoreCase(category))
                sal.add(s);

        return sal;
    }

    public ScriptData getScript(String category, String scriptName) {
        ScriptData result = null;
        for (ScriptData s : scripts) {
            if (s.category.equals(category) && s.name.equals(scriptName)) {
                result = s;
            }
        }
        return result;
    }
}
