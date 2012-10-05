package com.convin.bot.core.bot;

import com.convin.bot.core.components.AppletFrame;
import com.convin.bot.core.components.ImagePanel;
import com.convin.bot.core.gui.InstanceSettings;
import com.convin.bot.core.gui.LogWindow;
import com.convin.bot.core.handlers.*;
import com.convin.bot.core.io.StreamGobbler;
import com.convin.bot.core.loader.AppletLoader;
import com.convin.bot.core.logging.LogManager;
import com.convin.bot.game.ui.GameInterfaceManager;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 23.7.2012
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class Bot {
    public static Bot CURRENT;
    private final SettingsHandler settingsHandler;
    private final DisplayHandler displayHandler;
    private final InputHandler inputHandler;
    private final ScriptHandler scriptHandler;
    private final DebugHandler debugHandler;
    private final RandomHandler randomHandler;
    private final AccessorMethods accessorMethods;
    private final OpenglHandler openglHandler;
    private final StreamGobbler commandReceiver;
    private final LogWindow logWindow = new LogWindow(null);
    private final InstanceSettings instanceSettingsWindow = new InstanceSettings(null);
    private final AppletLoader loader;
    private final GameInterfaceManager gameInterfaces = new GameInterfaceManager();
    private final AppletFrame gameFrame = new AppletFrame();

    public Bot(AppletLoader loader) {
        LogManager.setupLogger(gameFrame.getTextAreaLog());
        CURRENT = this;
        this.loader = loader;
        boolean botReady = false;
        accessorMethods = new AccessorMethods(this);
        settingsHandler = new SettingsHandler(accessorMethods);
        inputHandler = new InputHandler();
        displayHandler = new DisplayHandler(gameFrame, loader.loadApplet(), new ImagePanel(accessorMethods), accessorMethods);
        debugHandler = new DebugHandler(accessorMethods);
        openglHandler = new OpenglHandler(accessorMethods);
        randomHandler = new RandomHandler(accessorMethods);
        //DataHandler dataHandler = new DataHandler();
        scriptHandler = new ScriptHandler(accessorMethods);
        commandReceiver = new StreamGobbler(System.in, "cmd ", accessorMethods);

        displayHandler.start();
        commandReceiver.start();

        // Start wrapper updaters
        openglHandler.start();

        botReady = true;
    }

    public DisplayHandler getDisplayHandler() {
        return displayHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public SettingsHandler getSettingsHandler() {
        return settingsHandler;
    }

    public ScriptHandler getScriptHandler() {
        return scriptHandler;
    }

    public DebugHandler getDebugHandler() {
        return debugHandler;
    }

    public RandomHandler getRandomHandler() {
        return randomHandler;
    }

    public AppletLoader getLoader() {
        return loader;
    }

    public void showLogWindow() {
        logWindow.setVisible(true);
    }

    public void shutdown() {
        logWindow.dispose();
        instanceSettingsWindow.dispose();
    }

    public void showSettingsWindow() {
        instanceSettingsWindow.setVisible(true);
    }

    public GameInterfaceManager getGameInterfaceManager() {
        return gameInterfaces;
    }
}
