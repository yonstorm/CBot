package com.convin.bot.core.components;

import com.convin.bot.api.input.Mouse;
import com.convin.bot.api.script.MouseActions;
import com.convin.bot.api.script.Script;
import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.utils.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import static java.awt.image.AffineTransformOp.TYPE_NEAREST_NEIGHBOR;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 31.7.2012
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private static final Dimension PREFERRED_SIZE = new Dimension(Settings.APPLET_WIDTH, Settings.APPLET_HEIGHT);
    private BufferedImage image;
    private BufferedImage paintImage;
    private BufferedImage offscreenImage;
    private final AccessorMethods ac;
    private final byte pixels[];
    private boolean refreshing;


    public native void updateGameImage(byte[] pixels);

    public ImagePanel(AccessorMethods ac) {
        //super();
        this.ac = ac;
        setup();
        pixels = new byte[4 * 765 * 553 + 1];
        this.setDoubleBuffered(true);
        //this.setOpaque(false);
        this.setVisible(true);
        this.setFocusable(true);

        // add input listeners
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        //this.addFocusListener(ac.getFocus());


    }

    @Override
    public boolean isShowing() {
        return true;
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, Color.BLACK, null);
        }
    }

    private void setup() {
        image = new BufferedImage(Settings.APPLET_WIDTH, Settings.APPLET_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        offscreenImage = new BufferedImage(Settings.APPLET_WIDTH, Settings.APPLET_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void updateImage() {
        updateGameImage(pixels); //todo: rename method
        offscreenImage.getRaster().setDataElements(0, 0, Settings.APPLET_WIDTH, Settings.APPLET_HEIGHT, pixels);

        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -offscreenImage.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_NEAREST_NEIGHBOR);
        image.getGraphics().clearRect(0, 0, Settings.APPLET_WIDTH, Settings.APPLET_HEIGHT);
        image = op.filter(offscreenImage, null);

        Graphics g = image.getGraphics();
        // Do drawing here
        if (!refreshing)
            ac.getDebugHandler().paintDebug(g);

        offscreenImage.flush();
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Component target = ac.getKeyListener();
        target.dispatchEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Component target = ac.getKeyListener();
        target.dispatchEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Component target = ac.getKeyListener();
        target.dispatchEvent(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Mouse.click(e.getPoint(), e.getButton());

        if (ac.getScript() != null) {
            Script curScript = ac.getScript();
            if (curScript.isRunning() && curScript instanceof MouseActions) {
                MouseActions mouse = (MouseActions) curScript;
                mouse.mouseClicked(e.getPoint(), e.getButton());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (ac.getScript() != null) {
            Script curScript = ac.getScript();
            if (curScript.isRunning() && curScript instanceof MouseActions) {
                MouseActions mouse = (MouseActions) curScript;
                mouse.mousePressed(e.getPoint(), e.getButton());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (ac.getScript() != null) {
            Script curScript = ac.getScript();
            if (curScript.isRunning() && curScript instanceof MouseActions) {
                MouseActions mouse = (MouseActions) curScript;
                mouse.mouseReleased(e.getPoint(), e.getButton());
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Mouse.enter(e.getX(), e.getY());
        Component target = ac.getMouseListener();
        target.dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Mouse.exit(e.getX(), e.getY());
        Component target = ac.getMouseListener();
        target.dispatchEvent(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Component target = ac.getMouseListener();
        target.dispatchEvent(e);
        if (ac.getScript() != null) {
            Script curScript = ac.getScript();
            if (curScript.isRunning() && curScript instanceof MouseActions) {
                MouseActions mouse = (MouseActions) curScript;
                mouse.mouseDragged(e.getPoint(), e.getButton());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component target = ac.getMouseListener();
        target.dispatchEvent(e);
        if (ac.getScript() != null) {
            Script curScript = ac.getScript();
            if (curScript.isRunning() && curScript instanceof MouseActions) {
                MouseActions mouse = (MouseActions) curScript;
                mouse.mouseMoved(e.getPoint());
            }
        }
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
    }
}
