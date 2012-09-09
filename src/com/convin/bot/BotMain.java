package com.convin.bot;

import com.convin.bot.core.CustomEventQueue;
import com.convin.bot.core.bot.Bot;
import com.convin.bot.core.components.CustomFrame;
import com.convin.bot.core.loader.AppletLoader;

import java.awt.*;

/**
 * User: JuV
 * Date: 26.5.2012
 * Time: 16:02
 */
public class BotMain {
    public static final CustomEventQueue eventQueue = new CustomEventQueue();
    private static Bot bot;

    public static void main(String args[]) {
        bot = new Bot(new AppletLoader());
    }

    static {
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(eventQueue);
    }


    public static void killProcess() {
        for (CustomFrame f : bot.getDisplayHandler().getFrames()) {
            f.dispose();
        }
        bot.shutdown();
        bot.getDisplayHandler().getApplet().destroy();
        System.exit(0);
    }
}
