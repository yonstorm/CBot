package com.convin.bot.core.handlers;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 23.7.2012
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener, FocusListener {

    private boolean hasFocus = true;
    private Object focus;

    public InputHandler() {
    }

    public void setupListeners(Applet applet, Canvas canvas) {
        for (MouseListener listener : applet.getMouseListeners())
            canvas.removeMouseListener(listener);

        for (MouseMotionListener listener : applet.getMouseMotionListeners())
            canvas.removeMouseMotionListener(listener);

        for (KeyListener listener : applet.getKeyListeners())
            canvas.removeKeyListener(listener);

        for (FocusListener listener : applet.getFocusListeners())
            canvas.removeFocusListener(listener);

        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addFocusListener(this);
        canvas.addKeyListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.hasFocus = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.hasFocus = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasFocus() {
        return hasFocus;
    }

}
