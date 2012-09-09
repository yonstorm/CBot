package com.convin.bot.api.input;

import com.convin.bot.core.bot.Bot;
import com.convin.bot.core.handlers.DisplayHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

/**
 * User: JuV
 * Date: 23.7.2012
 * Time: 13:44
 */

//todo: Need to recode a better keyboard manager
public class Keyboard {
    private static final DisplayHandler dHandler = Bot.CURRENT.getDisplayHandler();

    private Keyboard() {
    }

    /**
     * Sends a String to the Applet
     *
     * @param msg String to send.
     */
    public static void send(String msg) {
        Focus.giveFocus();
        Canvas target = dHandler.getCanvas();
        pressTime = System.currentTimeMillis();
        for (char c : msg.toCharArray())
            for (KeyEvent ke : createKeyClick(target, c))
                target.processEvent(ke);
    }

    /**
     * Sends a character to the Applet
     *
     * @param c Character to send.
     */
    public static void send(char c) {
        Focus.giveFocus();
        Canvas target = dHandler.getCanvas();
        pressTime = System.currentTimeMillis();
        for (KeyEvent ke : createKeyClick(target, c))
            target.processEvent(ke);
    }

    /**
     * Sends a key code to the Applet
     *
     * @param keyCode keyCode to send.
     */
    public static void clickKey(int keyCode) {
        Focus.giveFocus();
        Canvas target = dHandler.getCanvas();
        pressTime = System.currentTimeMillis();
        for (KeyEvent ke : createKeyClick(target, keyCode))
            target.processEvent(ke);
    }

    /**
     * Causes a keyPressEvent, remember to call release afterwards.
     *
     * @param keyCode keyCode to send
     */
    public static void press(int keyCode) {
        Focus.giveFocus();
        Canvas target = dHandler.getCanvas();
        pressTime = System.currentTimeMillis();
        KeyEvent ke = createKeyPress(target, keyCode);
        target.processEvent(ke);
    }

    /**
     * Causes a keyReleaseEvent.
     *
     * @param keyCode keyCode to send
     */
    public static void release(int keyCode) {
        Focus.giveFocus();
        Canvas target = dHandler.getCanvas();
        pressTime = System.currentTimeMillis();
        KeyEvent ke = createKeyRelease(target, keyCode);
        target.processEvent(ke);
    }

    /* Internal Event construction  */

    private static final HashMap<Character, Character> specialChars;
    private static long pressTime;

    static {
        char[] spChars = {'~', '!', '@', '#', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', ':', '<', '>', '?', '"', '|'};
        char[] replace = {'`', '1', '2', '3', '5', '6', '7', '8', '9', '0', '-', '=', '[', ']', ';', ',', '.', '/', '\'', '\\'};
        specialChars = new HashMap<Character, Character>(spChars.length);
        for (int x = 0; x < spChars.length; ++x)
            specialChars.put(spChars[x], replace[x]);
    }

    /**
     * Gets a random number.
     * todo: need some testing to determine a legitimate lagtime
     *
     * @return RandomEventSolver number used in bewtween keystrokes and also presses.
     */
    private static long getRandom() {
        Random rand = new Random();
        return rand.nextInt(100) + 40;
    }

    /**
     * Generates events for pressing a key that sends a character, also takes care of the needed masks and
     * changes whatever is needed for special characters so that the events are legitimate.
     *
     * @param target Component the events are being sent to.
     * @param c      The character to send.
     * @return A KeyEvent array for you to dispatch to the component.
     */
    private static KeyEvent[] createKeyClick(Component target, char c) {
        //takes about 2x as long to get to another key than to release a key?
        pressTime += 2 * getRandom();

        Character newChar = specialChars.get(c);
        int keyCode = (int) Character.toUpperCase((newChar == null) ? c : newChar);

        if (Character.isLowerCase(c) || (!Character.isLetter(c) && (newChar == null))) {
            KeyEvent pressed = new KeyEvent(target, KeyEvent.KEY_PRESSED, pressTime, 0, keyCode, c);
            KeyEvent typed = new KeyEvent(target, KeyEvent.KEY_TYPED, pressTime, 0, 0, c);
            pressTime += getRandom();
            KeyEvent released = new KeyEvent(target, KeyEvent.KEY_RELEASED, pressTime, 0, keyCode, c);

            return new KeyEvent[]{pressed, typed, released};
        } else {
            KeyEvent shiftDown = new KeyEvent(target, KeyEvent.KEY_PRESSED, pressTime, KeyEvent.SHIFT_MASK, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED);

            pressTime += getRandom();
            KeyEvent pressed = new KeyEvent(target, KeyEvent.KEY_PRESSED, pressTime, KeyEvent.SHIFT_MASK, keyCode, c);
            KeyEvent typed = new KeyEvent(target, KeyEvent.KEY_TYPED, pressTime, KeyEvent.SHIFT_MASK, 0, c);
            pressTime += getRandom();
            KeyEvent released = new KeyEvent(target, KeyEvent.KEY_RELEASED, pressTime, KeyEvent.SHIFT_MASK, keyCode, c);
            pressTime += getRandom();
            KeyEvent shiftUp = new KeyEvent(target, KeyEvent.KEY_RELEASED, pressTime, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED);

            return new KeyEvent[]{shiftDown, pressed, typed, released, shiftUp};
        }
    }

    /**
     * Generates events for pressing a key that doesn't send a character, also takes care of the needed masks.
     *
     * @param target  Component the events are being sent to.
     * @param keyCode The keycode to send.
     * @return A KeyEvent array for you to dispatch to the component.
     */
    private static KeyEvent[] createKeyClick(Component target, int keyCode) {
        int modifier = 0;
        switch (keyCode) {
            case KeyEvent.VK_SHIFT:
                modifier = KeyEvent.SHIFT_MASK;
                break;
            case KeyEvent.VK_ALT:
                modifier = KeyEvent.ALT_MASK;
                break;
            case KeyEvent.VK_CONTROL:
                modifier = KeyEvent.CTRL_MASK;
                break;
        }
        KeyEvent pressed = new KeyEvent(target, KeyEvent.KEY_PRESSED, pressTime, modifier, keyCode, KeyEvent.CHAR_UNDEFINED);
        KeyEvent released = new KeyEvent(target, KeyEvent.KEY_RELEASED, pressTime + getRandom(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        return new KeyEvent[]{pressed, released};
    }

    private static KeyEvent createKeyPress(Component target, int keyCode) {
        int modifier = 0;
        switch (keyCode) {
            case KeyEvent.VK_SHIFT:
                modifier = KeyEvent.SHIFT_MASK;
                break;
            case KeyEvent.VK_ALT:
                modifier = KeyEvent.ALT_MASK;
                break;
            case KeyEvent.VK_CONTROL:
                modifier = KeyEvent.CTRL_MASK;
                break;
        }
        return new KeyEvent(target, KeyEvent.KEY_PRESSED, pressTime, modifier, keyCode, KeyEvent.CHAR_UNDEFINED);
    }

    //creates a key release event
    private static KeyEvent createKeyRelease(Component target, int keyCode) {
        int modifier = 0;
        switch (keyCode) {
            case KeyEvent.VK_SHIFT:
                modifier = KeyEvent.SHIFT_MASK;
                break;
            case KeyEvent.VK_ALT:
                modifier = KeyEvent.ALT_MASK;
                break;
            case KeyEvent.VK_CONTROL:
                modifier = KeyEvent.CTRL_MASK;
                break;
        }

        return new KeyEvent(target, KeyEvent.KEY_RELEASED, pressTime + getRandom(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }
}
