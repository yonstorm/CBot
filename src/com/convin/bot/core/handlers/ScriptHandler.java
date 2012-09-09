package com.convin.bot.core.handlers;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.script.Script;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.gui.ScriptSelector;
import com.convin.bot.core.gui.dialogs.DialogBuilder;
import com.convin.bot.core.io.ScriptData;
import com.convin.bot.core.io.ScriptLoader;
import com.convin.bot.utils.settings.Settings;
import org.apache.log4j.Priority;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 23.7.2012
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class ScriptHandler {
    private Script currentScript;
    private final AccessorMethods ac;
    private final ScriptLoader scriptLoader;
    private final ScriptSelector scriptSelector;

    public ScriptHandler(AccessorMethods ac) {
        this.ac = ac;
        this.scriptLoader = new ScriptLoader(ac);
        //scriptLoader.loadScripts();
        this.scriptSelector = new ScriptSelector(null, ac);
    }

    private boolean loadScript(ScriptData data) {
        try {
            Class<?> scriptClass = data.clazz;
            File dir = new File(Settings.SCRIPTS_PATH);
            URL[] urls;
            URL url = dir.toURI().toURL();
            urls = new URL[]{url};
            URLClassLoader scriptLoader = new URLClassLoader(urls);
            currentScript = (Script) scriptLoader.loadClass(scriptClass.getName()).newInstance();
            currentScript.start();
            return true;
        } catch (MalformedURLException e) {
            Logging.log(Priority.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            Logging.log(Priority.ERROR, e.getMessage());
        } catch (InstantiationException e) {
            Logging.log(Priority.ERROR, e.getMessage());
        } catch (IllegalAccessException e) {
            Logging.log(Priority.ERROR, e.getMessage());
        }

        return false;
    }

    public void stopScript() {
        currentScript.stop();
    }

    public Script getCurrentScript() {
        return currentScript;
    }

    public ScriptLoader getScriptLoader() {
        return scriptLoader;
    }

    public void showSelector() {
        SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
            @Override
            protected Integer doInBackground() throws Exception {
                scriptSelector.refresh();
                scriptSelector.setVisible(true);
                return 0;
            }

        };
        worker.execute();

    }

    public void loadSelectedScript() {
        ScriptData selected = scriptSelector.getSelectedScript();
        if (selected == null) {
            DialogBuilder.showErrorDialog("No script selected!", "Please select a script to run. Hint: Click the 'select script' button.");
            return;
        }
        loadScript(scriptSelector.getSelectedScript());
    }
}
