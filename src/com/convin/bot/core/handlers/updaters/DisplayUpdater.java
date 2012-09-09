package com.convin.bot.core.handlers.updaters;

import com.convin.bot.api.common.Time;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.components.ImagePanel;

import java.awt.*;


/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 31.7.2012
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */

public class DisplayUpdater implements Runnable, CanvasUpdateListener {

    private final AccessorMethods ac;
    private final ImagePanel imagePanel;
    private boolean isRunning = false;
    private boolean isRenewing;
    private boolean isPaused = false;

    public DisplayUpdater(AccessorMethods ac, ImagePanel imagePanel) {
        this.ac = ac;
        this.imagePanel = imagePanel;

    }

    @Override
    public void run() {
        while (ac.getCanvas() == null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //ac.getInputHandler().setupListeners(ac.getCanvas());
        ac.getCanvas().addObjectUpdateEventListener(this);
        while (ac.getSettings().isPaintEnabled()) {
            while (isPaused) {
                Time.sleep(1000);
            }
            imagePanel.updateImage();
            Time.sleep(44);

        }
    }

    @Override
    public void CanvasUpdateOccured(CanvasUpdateEvent e) {
        ac.getDisplayHandler().setRenewing(true);
        ac.getInputHandler().setupListeners(ac.getApplet(), (Canvas) e.getSource());
        ac.getDisplayHandler().setRenewing(false);
    }

    public void setPaused(boolean pause) {
        this.isPaused = pause;
    }

}
