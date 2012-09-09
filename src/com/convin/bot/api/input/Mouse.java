package com.convin.bot.api.input;

import com.convin.bot.api.common.Time;
import com.convin.bot.core.bot.Bot;
import com.convin.bot.core.handlers.DisplayHandler;
import com.convin.bot.utils.input.mouse.MousePathGenerator;
import com.convin.bot.utils.input.mouse.Spline;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * User: JuV
 * Date: 18.4.2012
 * Time: 11:15
 */
public class Mouse {
    /**
     * Represents the Left button of the Mouse
     */
    public static final int LEFT_BUTTON = MouseEvent.BUTTON1; //1
    /**
     * Represents the Middle button of the Mouse
     */
    public static final int MIDDLE_BUTTON = MouseEvent.BUTTON2; //2
    /**
     * Represents the Right button of the Mouse
     */
    public static final int RIGHT_BUTTON = MouseEvent.BUTTON3; //3

    private static final DisplayHandler dHandler = Bot.CURRENT.getDisplayHandler();
    private static final MousePathGenerator mp = new Spline();
    private static Point lastPoint;

    private Mouse() {
    }

    /**
     * Moves and clicks the mouse with the specified button at the specified point.
     *
     * @param p      Point to move to & click.
     * @param button Mouse button to click with.
     */
    public static void click(Point p, int button) {
        click(p.x, p.y, button);
    }

    /**
     * Moves and clicks the mouse with the specified button at the specified point.
     *
     * @param x      X coordinate of the point to move to & click.
     * @param y      Y coordinate of the point to move to & click.
     * @param button Mouse button to click with.
     */
    public static void click(int x, int y, int button) {
        move(x, y);
        clickMouse(x, y, button);
    }

    /**
     * Get the bots mouse position.
     *
     * @return The last known point where the mouse was
     */
    public static Point getLastMousePos() {
        Point lastPos = null;
        if (lastPos != null)
            return lastPos;
        if (lastPoint != null)
            return lastPoint;
        return new Point(1, 1);
    }

    /**
     * Moves the mouse to the specified point.
     *
     * @param p Point to move to.
     */
    public static void move(Point p) {
        move(p.x, p.y);
    }

    /**
     * Moves the mouse to the specifies point.
     *
     * @param x X coordinate of the point to move to.
     * @param y Y coordinate of the point to move to.
     */
    public static void move(int x, int y) {
        Point p = getLastMousePos();
        moveFrom(p.x, p.y, x, y);
    }

    /**
     * Moves the mouse from one point to another.
     *
     * @param p1 Start point.
     * @param p2 End point.
     */
    public static void moveFrom(Point p1, Point p2) {
        moveFrom(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * Moves the mouse from one point to another.
     *
     * @param x1 Starting x coordinate.
     * @param y1 Starting y coordinate.
     * @param x2 Ending x coordinate.
     * @param y2 Ending y coordinate.
     */
    public static void moveFrom(int x1, int y1, int x2, int y2) {
        Canvas target = dHandler.getCanvas();
        for (MouseEvent me : createMousePath(target, mp.makeMousePath(x1, y1, x2, y2))) {
            //target.processEvent(me);
            sendMouseEvent(me);
            lastPoint = me.getPoint();
            try {
                Thread.sleep(2 + (int) (Math.random() * 2));
            } catch (Exception ex) {
            } //the lagtime isnt human enough
        }
        lastPoint = new Point(x2, y2);
    }

    /**
     * Drags the mouse to the specified point with the specified button held down.
     *
     * @param p      Point to drag to.
     * @param button Button to drag with.
     * @throws IllegalArgumentException if the button is not valid.
     */
    public static void drag(Point p, int button) throws IllegalArgumentException {
        drag(p.x, p.y, button);
    }

    /**
     * Drags the click to the specified point with the specified button held down.
     *
     * @param x      X coord of point to drag to.
     * @param y      Y coord of point to drag to.
     * @param button Button to drag with.
     * @throws IllegalArgumentException If the button is not valid.
     */
    public static void drag(int x, int y, int button) throws IllegalArgumentException {
        Point p = getLastMousePos();
        dragFrom(p.x, p.y, x, y, button);
    }

    /**
     * Drags the click to the from the first point to the second with the specified button held down.
     *
     * @param p1     Start point
     * @param p2     End point
     * @param button Button to drag with
     * @throws IllegalArgumentException if the button is not valid
     */
    public static void dragFrom(Point p1, Point p2, int button) throws IllegalArgumentException {
        dragFrom(p1.x, p1.y, p2.x, p2.y, button);
    }

    /**
     * Drag the mouse from the first point to the second with the specified button held down.
     *
     * @param x1     Start X coordinate
     * @param y1     Start Y coordinate
     * @param x2     End X coordinate
     * @param y2     End Y coordinate
     * @param button Button to hold down while dragging
     * @throws IllegalArgumentException If the button is not valid
     */
    public static void dragFrom(int x1, int y1, int x2, int y2, int button) throws IllegalArgumentException {
        Canvas mouseTarget = dHandler.getCanvas();
        Canvas mouseMotionTarget = dHandler.getCanvas();
        MouseEvent[] me = createDragPath(mouseMotionTarget, mouseTarget, mp.makeMousePath(x1, y1, x2, y2), button);
        mouseTarget.processEvent(me[0]);
        for (int i = 1; i < me.length - 1; ++i) {
            mouseMotionTarget.processEvent(me[i]);
            try {
                Thread.sleep(2 + (int) (Math.random() * 2));
            } catch (Exception ex) {
            }//the lagtime isnt human enough
        }
        mouseTarget.processEvent(me[me.length - 1]);
        lastPoint = new Point(x2, y2);
    }

    /**
     * Clicks the mouse at the specified point with the specified button.
     *
     * @param x      X coordinate to click at.
     * @param y      Y coordinate to click at.
     * @param button Button to click with.
     * @throws IllegalArgumentException If the button is not valid.
     */
    private static void clickMouse(int x, int y, int button) throws IllegalArgumentException {
        click(x, y, button, 1);
    }

    /**
     * Clicks the mouse at the specified point, button, the specified number of times.
     *
     * @param p          Point to click
     * @param button     Button to click with
     * @param clickCount Number of times to repeat the click
     * @throws IllegalArgumentException If the button is not valid
     */
    public static void click(Point p, int button, int clickCount) throws IllegalArgumentException {
        click(p.x, p.y, button, clickCount);
    }

    /**
     * Clicks the mouse at the specified point, with specified button and specified number of times.
     *
     * @param x          X coordinate to click at
     * @param y          Y coordinate to click at
     * @param button     Button to click with
     * @param clickCount Number of times to repeat click
     * @throws IllegalArgumentException If the button is not valid
     */
    public static void click(int x, int y, int button, int clickCount) throws IllegalArgumentException {
        Canvas target = dHandler.getCanvas();//ac.getCanvas();
        for (MouseEvent me : createMouseClick(target, x, y, button, clickCount))
            sendMouseEvent(me);
        //target.processEvent(me);
    }

    /**
     * Causes a mouse exit event at x, y
     *
     * @param x X coordinate to exit at
     * @param y Y coordinate to exit at
     */
    public static void exit(int x, int y) {
        Canvas target = dHandler.getCanvas();
        MouseEvent me = new MouseEvent(target, MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), 0, x, y, 0, false, MouseEvent.NOBUTTON);
        target.processEvent(me);
    }

    /**
     * Causes a mouse enter event at x, y
     *
     * @param x X coordinate to exit at
     * @param y Y coordinate to exit at
     */
    public static void enter(int x, int y) {
        Canvas target = dHandler.getCanvas();
        MouseEvent me = new MouseEvent(target, MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, x, y, 0, false, MouseEvent.NOBUTTON);
        target.processEvent(me);
    }

    //--------Helper Methods-----------\\

    /**
     * Gets a random number. Krichevskoy found these values and wrote this function.
     * todo: need some testing to determine a legitimate lagtime
     *
     * @return RandomEventSolver number used in bewtween click moves and clicks.
     */
    private static long getRandom() {
        Random rand = new Random();
        //lag var 47-219, usu 78-94; currently tends low
        return rand.nextInt(100) + 40;
    }

    /**
     * Generates legit click events for a click drag.
     *
     * @param mouseMotionTarget Component that implements MouseMotionListener.
     * @param mouseTarget       Component that implements MouseListener.
     * @param path              A Point array to move along.
     * @param button            Button to drag with.
     * @return MouseEvent array to be dispatched to the proper components.
     * @throws IllegalArgumentException If the button is not valid.
     */
    private static MouseEvent[] createDragPath(Component mouseMotionTarget, Component mouseTarget, Point[] path, int button) throws IllegalArgumentException {
        MouseEvent[] me = new MouseEvent[path.length + 2];
        int buttonModifiers = getButtonModifiers(button);
        long lagTime = System.currentTimeMillis();
        me[0] = new MouseEvent(mouseTarget, MouseEvent.MOUSE_PRESSED, lagTime, buttonModifiers, path[0].x, path[0].y, 1, false, button);
        lagTime += getRandom();
        for (int i = 1; i < me.length - 1; ++i) {
            me[i] = new MouseEvent(mouseMotionTarget, MouseEvent.MOUSE_DRAGGED, lagTime, button, path[i - 1].x, path[i - 1].y, 0, false, 0);
            lagTime += getRandom();
        }
        me[path.length + 1] = new MouseEvent(mouseTarget, MouseEvent.MOUSE_RELEASED, lagTime, buttonModifiers, path[path.length - 1].x, path[path.length - 1].y, 1, false, button);
        return me;
    }

    /**
     * Generates Mouse movement events.
     *
     * @param target Component that implements MouseMotionListener.
     * @param path   A Point array to move along.
     * @return MouseEvent array to be dispatched to the proper component.
     */
    private static MouseEvent[] createMousePath(Component target, Point[] path) {
        MouseEvent[] me = new MouseEvent[path.length];
        long lagTime = System.currentTimeMillis();
        for (int i = 0; i < me.length; ++i) {
            me[i] = new MouseEvent(target, MouseEvent.MOUSE_MOVED, lagTime, 0, path[i].x, path[i].y, 0, false, 0);
            lagTime += getRandom();
        }

        return me;
    }

    /**
     * Generates legit click clicking events.
     *
     * @param target     Component that implements MouseListener.
     * @param x          X coordinate to be clicked.
     * @param y          Y coordinate to be clicked.
     * @param button     Button to click with.
     * @param clickCount Number of times to click.
     * @return MouseEvent array to be dispatched to the proper component.
     * @throws IllegalArgumentException If the button is not valid.
     */
    private static MouseEvent[] createMouseClick(Component target, int x, int y, int button, int clickCount) throws IllegalArgumentException {
        int buttonModifiers = getButtonModifiers(button);
        MouseEvent[] me = new MouseEvent[clickCount * 3];
        long lagTime = System.currentTimeMillis();
        int count = 1;

        for (int i = 0; i < me.length; i += 3) {
            me[i] = new MouseEvent(target, MouseEvent.MOUSE_PRESSED, lagTime, buttonModifiers, x, y, count, false, button);
            lagTime += getRandom();
            me[i + 1] = new MouseEvent(target, MouseEvent.MOUSE_RELEASED, lagTime, buttonModifiers, x, y, count, false, button);
            me[i + 2] = new MouseEvent(target, MouseEvent.MOUSE_CLICKED, lagTime, buttonModifiers, x, y, count, false, button);
            lagTime += getRandom();
            ++count;
        }

        return me;
    }

    /**
     * Gets the appropriate click button modifiers for the chosen button.
     *
     * @param button Button that modifiers are needed for.
     * @return The appropriate modifier for the chosen button.
     * @throws IllegalArgumentException If the button is not valid.
     */
    private static int getButtonModifiers(int button) throws IllegalArgumentException {
        switch (button) {
            case LEFT_BUTTON:
                return MouseEvent.BUTTON1_MASK;
            case MIDDLE_BUTTON:
                return MouseEvent.BUTTON2_MASK;
            case RIGHT_BUTTON:
                return MouseEvent.BUTTON3_MASK;
            default:
                throw new IllegalArgumentException("Not a valid button choice.");
        }
    }

    private static void sendMouseEvent(MouseEvent me) {
        long t = System.currentTimeMillis();

        while (dHandler.isRenewing() && System.currentTimeMillis() - t < 5000)
            Time.sleep(10);

        if (!Bot.CURRENT.getInputHandler().hasFocus())
            Focus.giveFocus();

        dHandler.getCanvas().processEvent(me);

    }

    public static void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawLine(getLastMousePos().x - 6, getLastMousePos().y, getLastMousePos().x + 6, getLastMousePos().y);
        g.drawLine(getLastMousePos().x, getLastMousePos().y - 6, getLastMousePos().x, getLastMousePos().y + 6);
    }

}

