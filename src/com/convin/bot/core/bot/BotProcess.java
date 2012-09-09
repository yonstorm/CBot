package com.convin.bot.core.bot;

import com.convin.bot.BotMain;
import com.convin.bot.core.gui.OneGlance;
import com.convin.bot.core.gui.dialogs.DialogBuilder;
import com.convin.bot.core.io.StreamGobbler;
import com.convin.bot.core.io.StreamInput;
import com.convin.bot.utils.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 26.7.2012
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class BotProcess {
    private Process process;
    private final StreamGobbler sg;

    private static int botCount = 0;
    public static final ArrayList<BotProcess> ACTIVE_BOTS = new ArrayList<BotProcess>();
    private final int botNumber;
    private OneGlance oneGlance;

    public BotProcess() {
        botCount++;
        botNumber = botCount;
        try {
            this.process = startBotProcess(BotMain.class);
            ACTIVE_BOTS.add(this);
        } catch (IOException e) {
            DialogBuilder.showErrorDialog("Could not start a new bot", "Starting of a new bot failed, have you used all of your system resources?");
        }

        sg = new StreamGobbler(process.getInputStream(), this);
        sg.start();

    }

    private Process startBotProcess(Class klass) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +
                File.separator + "bin" +
                File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = klass.getCanonicalName();
        ProcessBuilder builder = new ProcessBuilder(
                javaBin, "-Xbootclasspath/p:" + Settings.DATA_PATH + "jars/Canvas.jar;" + Settings.DATA_PATH + "lib/log4j.jar", "-XX:+RestoreMXCSROnJNICalls", "-Xverify:none", "-Xmx256M", "-Xcheck:jni", "-cp", classpath, className);
        builder.redirectErrorStream(true);
        return builder.start();
    }

    public boolean destroy() {
        sendCommand("killProcess");
        return true;
    }

    public void sendCommand(String command) {
        StreamInput si = new StreamInput(process.getOutputStream(), command);
        si.start();
    }

    public OneGlance getOneGlance() {
        return oneGlance;
    }

    public void setGui(OneGlance oneGlance) {
        this.oneGlance = oneGlance;
    }
}
