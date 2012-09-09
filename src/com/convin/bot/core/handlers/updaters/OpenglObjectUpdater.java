package com.convin.bot.core.handlers.updaters;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.wrappers.GLFontCharacter;
import com.convin.bot.api.wrappers.GLItem;
import com.convin.bot.api.wrappers.GLModel;
import com.convin.bot.api.wrappers.GLTexture;
import com.convin.bot.core.bot.AccessorMethods;
import org.apache.log4j.Priority;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: Joni
 * Date: 6.8.2012
 * Time: 14:44
 */
public class OpenglObjectUpdater extends Thread {
    private boolean isRunning = true;

    private static native CopyOnWriteArrayList<GLModel> getAllObjects();

    private static native CopyOnWriteArrayList<GLTexture> getAllTextures();

    private static native CopyOnWriteArrayList<GLFontCharacter> getAllFontCharacters();

    private static native CopyOnWriteArrayList<GLItem> getAllInventoryItems();

    private static CopyOnWriteArrayList<GLModel> modelCache = new CopyOnWriteArrayList<GLModel>();
    private static CopyOnWriteArrayList<GLTexture> textureCache = new CopyOnWriteArrayList<GLTexture>();
    private static CopyOnWriteArrayList<GLFontCharacter> fontCache = new CopyOnWriteArrayList<GLFontCharacter>();
    private static CopyOnWriteArrayList<GLItem> inventoryItemCache = new CopyOnWriteArrayList<GLItem>();

    public OpenglObjectUpdater(AccessorMethods ac) {
        AccessorMethods ac1 = ac;
        this.setName("Object updater");
    }

    public static CopyOnWriteArrayList<GLModel> getModelCache() {
        return modelCache;
    }

    public static CopyOnWriteArrayList<GLTexture> getTextureCache() {
        return textureCache;
    }

    public static CopyOnWriteArrayList<GLFontCharacter> getFontCache() {
        return fontCache;
    }

    public static CopyOnWriteArrayList<GLItem> getInventoryItemCache() {
        return inventoryItemCache;
    }

    @Override
    public void run() {
        Logging.log(Priority.INFO, "Started " + this.getName());
        while (isRunning()) {
            fontCache = getAllFontCharacters();
            inventoryItemCache = getAllInventoryItems();
            modelCache = getAllObjects();
            textureCache = getAllTextures();
            try {
                sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
