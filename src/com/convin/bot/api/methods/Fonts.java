package com.convin.bot.api.methods;

import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.wrappers.GLFontCharacter;
import com.convin.bot.api.wrappers.GLText;
import com.convin.bot.core.handlers.updaters.OpenglObjectUpdater;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: JuV
 * Date: 4.6.2012
 * Time: 11:18
 */
public final class Fonts {
    private static final Rectangle UPTEXT_LOCATION = new Rectangle(1, 50, 200, 25);

    /**
     * Reads text in specified rectangle.
     *
     * @param rectangle The rectangle to look for the text in
     * @return GLText object, if no text found the object will be empty
     */
    public static GLText readFontInRectangle(Rectangle rectangle) {
        ArrayList<GLFontCharacter> matching = new ArrayList<GLFontCharacter>();
        for (GLFontCharacter c : OpenglObjectUpdater.getFontCache()) {
            if (Calculations.rectangleContains(c.getLocation(), rectangle) && c.isRendering()) {
                matching.add(c);
            }
        }

        return new GLText(matching.toArray(new GLFontCharacter[matching.size()]));
    }

    /**
     * Get the uptext in the applets top left corner.
     *
     * @return Uptext that is found
     */
    public static String getUpText() {
        return readFontInRectangle(UPTEXT_LOCATION).getText();
    }

    /**
     * Check if current uptext equals specified text.
     *
     * @param text Text to check uptext against
     * @return True if specified text equals current uptext
     */
    public static boolean isUpText(String text) {
        return readFontInRectangle(UPTEXT_LOCATION).textStartsWith(text);
    }

    /**
     * Reads text in the specified rectangle that the given filter accepts.
     *
     * @param rectangle Rectangle to look for the text in
     * @param filter    For the text to pass
     * @return GLText object, if no text found object will be empty
     */
    public static GLText readFontInRectangle(Rectangle rectangle, Filter<GLFontCharacter> filter) {
        ArrayList<GLFontCharacter> matching = new ArrayList<GLFontCharacter>();
        for (GLFontCharacter c : OpenglObjectUpdater.getFontCache()) {
            if (filter.accept(c) && Calculations.rectangleContains(c.getLocation(), rectangle)) {
                matching.add(c);
            }
        }
        return new GLText(matching.toArray(new GLFontCharacter[matching.size()]));
    }
}
