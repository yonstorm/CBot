package com.convin.bot.core;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 29.4.2012
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class CustomEventQueue extends EventQueue {
    public boolean blockInput = false;

    protected void dispatchEvent(AWTEvent event) {
        if (superShouldDispatch(event)) {
            super.dispatchEvent(event);
        }
    }

    private boolean superShouldDispatch(AWTEvent event) {
        return (!(blockInput && (event.toString().matches(".* on canvas\\d+") &&
                ((event instanceof MouseEvent) || (event instanceof KeyEvent) ||
                        (event instanceof FocusEvent)))));
    }

    public boolean isInputBlocked() {
        return blockInput;
    }

    public void setBlockInput(boolean blockInput) {
        this.blockInput = blockInput;
    }
}
