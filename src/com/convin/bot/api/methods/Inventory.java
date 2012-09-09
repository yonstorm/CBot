package com.convin.bot.api.methods;

import com.convin.bot.api.wrappers.GLItem;
import com.convin.bot.core.handlers.updaters.OpenglObjectUpdater;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: JuV
 * Date: 4.5.2012
 * Time: 17:40
 */
public class Inventory {
    private static Rectangle INVENTORY_AREA = new Rectangle(550, 260, 190, 240);

    private Inventory() {
    }

    /**
     * Checks if the inventory is full.
     *
     * @return True if inventory is full else false
     */
    public static boolean isFull() {
        return getItemCount() == 28;
    }

    /**
     * Checks if the inventory is empty.
     *
     * @return True if inventory is empty else false
     */
    public static boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * Counts the items in inventory.
     *
     * @return The number of items in inventory
     */
    public static int getItemCount() {
        return OpenglObjectUpdater.getInventoryItemCache().size();
    }

    /**
     * Checks if inventory contains any of the items specified by ids.
     *
     * @param ids The ids to look for in inventory
     * @return True if inventory contains any of the items specified by ids else false
     */
    public static boolean contains(int... ids) {
        for (GLItem t : OpenglObjectUpdater.getInventoryItemCache()) {
            for (int id : ids) {
                if (t.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets all the items in inventory.
     *
     * @return An array of GLItems, if inventory is empty returns an empty array
     */
    public static GLItem[] getAllItems() {
        return OpenglObjectUpdater.getInventoryItemCache().toArray(new GLItem[OpenglObjectUpdater.getInventoryItemCache().size()]);
    }

    /**
     * Searches for a item in inventory by specified id.
     *
     * @param id Item id to search for
     * @return A valid GLItem object - if no item is found returns a invalid GLItem
     */
    public static GLItem findItem(int id) {
        for (GLItem t : OpenglObjectUpdater.getInventoryItemCache()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return new GLItem();
    }

    /**
     * Searches for items in inventory by specified ids.
     *
     * @param ids Item ids to search for
     * @return An array of GLItems, if no items are found returns an empty array
     */
    public static GLItem[] findItems(int... ids) {
        ArrayList<GLItem> matching = new ArrayList<GLItem>();
        for (GLItem t : OpenglObjectUpdater.getInventoryItemCache()) {
            for (int id : ids) {
                if (t.getId() == id) {
                    matching.add(t);
                }
            }
        }
        return matching.toArray(new GLItem[matching.size()]);
    }

}
