package com.convin.bot.core.io;

import com.convin.bot.BotMain;
import com.convin.bot.api.common.Logging;
import com.convin.bot.api.script.Script;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.bot.Bot;
import com.convin.bot.core.bot.BotProcess;
import com.convin.bot.core.gui.dialogs.DialogBuilder;
import org.apache.log4j.Priority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 26.7.2012
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class StreamGobbler extends Thread {
    private final InputStream is;
    private AccessorMethods ac;
    private boolean isBot = false;
    private boolean shouldStop = false;
    private InputStreamReader isr;
    private BufferedReader br;
    private static final String DATACMD = "dCMD";
    private BotProcess process;

    public StreamGobbler(InputStream is, BotProcess process) {
        this.is = is;
        this.process = process;
        init();
    }

    public StreamGobbler(InputStream is, String type, AccessorMethods accessorMethods) {
        this.is = System.in;
        this.ac = accessorMethods;
        this.isBot = true;
        init();
    }

    private void init() {
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
    }

    public void run() {
        if (isBot && !shouldStop) {
            while (!shouldStop) {
                readCommand();
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (!shouldStop) {
                readCommand();
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void shutdown() {
        shouldStop = true;
        try {
            br.close();
            isr.close();
        } catch (IOException e) {
            Logging.log(Priority.DEBUG, e.getMessage());
        }
    }

    private void readCommand() {
        try {
            String line;
            if ((line = br.readLine()) != null) {
                if (isBot) {
                    botHandleCommand(line);
                } else {
                    clientHandleCommand(line);
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void clientHandleCommand(String line) {
        if (line.startsWith(DATACMD)) {
            if (line.substring(4).startsWith("SN")) {
                process.getOneGlance().dataUpdateScript(line.substring(6));
            } else if (line.substring(4).startsWith("ST")) {
                process.getOneGlance().dataStartTime(line.substring(6));
            } else if (line.equals(DATACMD + "RT")) {
                process.getOneGlance().resetAndStopTimer();
            }

        } else {
            System.out.println(line);
        }
    }

    private void botHandleCommand(String line) {
        if (line.equals("showGametrue")) {
            //ac.getDisplayHandler().showGame(true);
            ac.getDisplayHandler().showAll();
        } else if (line.equals("showGamefalse")) {
            //ac.getDisplayHandler().showGame(false);
            ac.getDisplayHandler().hideAll();
        } else if (line.equals(("startScript"))) {
            ac.getScriptHandler().loadSelectedScript();
        } else if (line.equals("selectScript")) {
            ac.getScriptHandler().showSelector();
        } else if (line.equals("pauseScript")) {
            Script script = ac.getScript();
            if (script != null) {
                if (script.isRunning()) {
                    script.pause(!script.isPaused());
                }
                DialogBuilder.showErrorDialog("Script is not running", "The script your trying to pause is not running");
            } else
                DialogBuilder.showErrorDialog("No script selected", "There is no script to pause");
        } else if (line.equals("stopScript")) {
            Script script = ac.getScript();
            if (script != null) {
                if (script.isRunning()) {
                    script.stop();
                } else
                    DialogBuilder.showErrorDialog("Script is not running", "The script your trying to stop is not running");
            } else
                DialogBuilder.showErrorDialog("No script selected", "There is no script to stop");
        } else if (line.equals("blockHumantrue")) {
            BotMain.eventQueue.setBlockInput(true);
        } else if (line.equals("blockHumanfalse")) {
            BotMain.eventQueue.setBlockInput(false);
        } else if (line.equals("showLog")) {
            Bot.CURRENT.showLogWindow();
        } else if (line.equals("showSettings")) {
            Bot.CURRENT.showSettingsWindow();
        } else if (line.equals("killProcess")) {
            BotMain.killProcess();
        }
    }
}
