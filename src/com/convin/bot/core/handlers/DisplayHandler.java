package com.convin.bot.core.handlers;

import com.convin.bot.api.common.Logging;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.components.AppletFrame;
import com.convin.bot.core.components.CustomFrame;
import com.convin.bot.core.components.ImagePanel;
import com.convin.bot.core.handlers.updaters.DisplayUpdater;
import com.convin.bot.utils.settings.Settings;
import org.apache.log4j.Priority;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 23.7.2012
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */

public class DisplayHandler {
    private Applet applet;
    private Thread displayHandlerThread;

    private final AccessorMethods ac;
    private final DisplayUpdater displayUpdater;
    private final ImagePanel imagePanel;
    private boolean isRenewing;

    private AppletFrame gameFrame;

    static {
        System.load(Settings.DLL_PATH + "opengl32.dll");
    }

    public DisplayHandler(AppletFrame frame, Applet applet, ImagePanel imagePanel, AccessorMethods ac) {
        this.ac = ac;
        this.imagePanel = imagePanel;
        this.displayUpdater = new DisplayUpdater(ac, imagePanel);

        this.applet = applet;

        this.gameFrame = frame;
        gameFrame.initApplet(applet, imagePanel);

        setup();
    }

    // Reloads the applet
    public void refreshApplet() {
        imagePanel.setRefreshing(true);
        gameFrame.getAppletTab().remove(applet);
        Applet newApplet = ac.getLoader().loadApplet();
        gameFrame.getAppletTab().add(newApplet);
        applet.destroy();
        this.applet = newApplet;
        gameFrame.revalidate();
        imagePanel.setRefreshing(false);
    }

    private void setup() {
        // prepare display handler thread
        displayHandlerThread = new Thread(displayUpdater);
        displayHandlerThread.setName("Thread - Display Handler");
    }

    private void prepareFrame(CustomFrame frame, Component c) {
        frame.add(c);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setTitle(frame.getTitle() + " " + String.valueOf(c.hashCode()));
        frame.pack();
        frame.setVisible(false);
    }

    public void showGame(boolean selected) {
        if (ac.getSettings().isPaintEnabled() && selected) {
            gameFrame.setVisible(true);
        } else if (selected) {
            gameFrame.setVisible(true);
        } else {
            gameFrame.setVisible(false);
        }
    }

    public Canvas getCanvas() {
        return (Canvas) applet.getComponent(0);
    }

    public Applet getApplet() {
        return applet;
    }

    public void start() {
        displayHandlerThread.start();
        Logging.log(Priority.INFO, "Display handler - Started");
    }

    public BufferedImage getImage() {
        return imagePanel.getImage();
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void showAll() {
        gameFrame.setVisible(true);
        gameFrame.setState(Frame.NORMAL);
    }

    public void hideAll() {
        gameFrame.setVisible(false);
    }

    public void setRenewing(boolean renewing) {
        this.isRenewing = renewing;
    }

    public boolean isRenewing() {
        return isRenewing;
    }

    public DisplayUpdater getDisplayUpdater() {
        return this.displayUpdater;
    }

    public AppletFrame getFrame() {
        return gameFrame;
    }
}
