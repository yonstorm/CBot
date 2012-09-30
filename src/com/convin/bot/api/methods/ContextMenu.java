package com.convin.bot.api.methods;

import com.convin.bot.api.common.Time;
import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.wrappers.GLFontCharacter;
import com.convin.bot.api.wrappers.GLText;
import com.convin.bot.api.wrappers.color.RGB;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: JuV
 * Date: 13.7.2012
 * Time: 18:22
 */
public class ContextMenu {
    private static final ArrayList<Rectangle> rect = new ArrayList<Rectangle>();

    private ContextMenu() {
    }

    public static void interact(String option) {
        GLText text = Fonts.readFontInRectangle(new Rectangle(0, 50, 765, 503), new Filter<GLFontCharacter>() {
            @Override
            public boolean accept(GLFontCharacter glFontCharacter) {
                return glFontCharacter.getRGB().equals(new RGB(198, 184, 149)) || glFontCharacter.getRGB().equals(new RGB(184, 209, 209)) || glFontCharacter.getRGB().equals(new RGB(198, 184, 149));
            }
        });

        ArrayList<GLText> lines = text.splitToLines();
        for (GLText w : lines) {
            if (w.textStartsWith(option)) {
                Mouse.click(w.getGlFontCharacters()[Calculations.getRandomNumber(1, w.getGlFontCharacters().length - 1)].getLocation(), Mouse.LEFT_BUTTON);
                Time.sleep(120, 200);
            }
        }
    }
}
