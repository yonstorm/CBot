package com.convin.bot.api.wrappers;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: JuV
 * Date: 1.6.2012
 * Time: 18:34
 */
public class GLText {
    private final GLFontCharacter[] glFontCharacters;
    private final String text;
    private int[] x;
    private int[] y;

    public GLText(GLFontCharacter[] fontCharacters) {
        this.text = getString(fontCharacters);
        this.glFontCharacters = fontCharacters;
        splitPoints(glFontCharacters);
        sort(x);
        sort(y);
    }

    private void splitPoints(GLFontCharacter[] glFontCharacters) {
        x = new int[glFontCharacters.length];
        y = new int[glFontCharacters.length];
        for (int i = 0; i < glFontCharacters.length; i++) {
            x[i] = glFontCharacters[i].getLocation().x;
            y[i] = glFontCharacters[i].getLocation().y;
        }

    }

    public void prepareForMultipleLines() {
        sortFontCharacters(glFontCharacters);
    }

    private void sortFontCharacters(GLFontCharacter[] c) {
        GLFontCharacter temp;
        for (int i = 0; i < c.length - 1; i++) {
            if (c[i].getY() > c[i + 1].getY()) {
                temp = c[i];
                c[i] = c[i + 1];
                c[i + 1] = temp;
                i = -1;
            }
        }
    }

    public Rectangle getTextArea() {
        return createRectangle(x, y);
    }

    public GLFontCharacter[] getGlFontCharacters() {
        return glFontCharacters;
    }

    public int[] getYs() {
        return y;
    }

    private Rectangle createRectangle(int[] x, int[] y) {
        return new Rectangle(x[0], y[0] - 4, x[x.length - 1] - x[0], y[y.length - 1] + 4);
    }

    private static String getString(GLFontCharacter[] fonts) {
        sortSmallestToLargest(fonts);
        char[] chars = new char[fonts.length];
        if (fonts.length > 0) {
            for (int i = 0; i < fonts.length; i++) {
                chars[i] = fonts[i].getSymbol();
            }
        }
        return new String(chars);
    }

    private void sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = -1;
            }
        }
    }

    public String getText() {
        return text;
    }

    public boolean textStartsWith(String text) {
        return this.text.startsWith(text);
    }

    public ArrayList<GLText> splitToLines() {
        ArrayList<GLText> lines = new ArrayList<GLText>();
        if (this.getGlFontCharacters().length > 0) {
            this.prepareForMultipleLines();
            int currentY = this.getGlFontCharacters()[0].getY();
            ArrayList<GLFontCharacter> currentLine = new ArrayList<GLFontCharacter>();
            for (GLFontCharacter c : this.getGlFontCharacters()) {
                if (currentY == c.getY() || Math.abs(c.getY() - currentY) <= 8) {
                    currentLine.add(c);
                } else {
                    lines.add(new GLText(currentLine.toArray(new GLFontCharacter[currentLine.size()])));
                    currentLine.clear();
                    currentY = c.getY();
                    currentLine.add(c);
                }

            }
        }
        return lines;
    }

    // todo: Ugly and not safe - change( intended just for testing )
    private static GLFontCharacter[] sortSmallestToLargest(GLFontCharacter array[]) {
        GLFontCharacter temp;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getLocation().x > array[i + 1].getLocation().x) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = -1;
            }
        }
        return array;
    }

}
