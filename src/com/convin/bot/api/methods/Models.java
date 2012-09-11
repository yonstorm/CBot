package com.convin.bot.api.methods;

import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.wrappers.GLModel;
import com.convin.bot.core.handlers.updaters.OpenglObjectUpdater;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: Joni
 * Date: 6.8.2012
 * Time: 18:28
 */
public final class Models {
    private static final Point _CENTER = new Point(260, 220);

    private Models() {
    }

    /**
     * Checks if there is a model present with the specified ids
     *
     * @param ids GLModel IDs to check for
     * @return True if a model is present else false.
     */
    public static boolean isPresent(long... ids) {
        return !(findAll(generateIDFilter(ids)).length == 0);
    }

    /**
     * Generates filter for specified ids
     *
     * @param ids GLModel ids to generate filter from
     * @return Filter for specified ids
     */

    private static Filter<GLModel> generateIDFilter(final long... ids) {
        return new Filter<GLModel>() {
            @Override
            public boolean accept(GLModel o) {
                for (Long i : ids) {
                    if (o.getID() == i) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Searches for a Model in opengl cache.
     *
     * @param id Specifies the objects id to search for in objects cache
     * @return GLModel found with given id or if no objects are found returns empty && non valid GLModel
     */
    public static GLModel find(long id) {
        return getMatchingObject(generateIDFilter(id));
    }

    /**
     * Searches for a Model in opengl cache by specified filter.
     *
     * @param filter Specifies the filter which conditions have to be accepted
     * @return the object that accepted the rules provided by filter. If none accepts returns empty GLModel
     */
    public static GLModel find(Filter<GLModel> filter) {
        return getMatchingObject(filter);
    }

    /**
     * Searches for Models in opengl cache by specified ids.
     *
     * @param ids Specifies the ids to search for in Object cache
     * @return Array of Models that were found by ids specified if none is found returns 0 length array
     */
    public static GLModel[] findAll(long... ids) {
        ArrayList<GLModel> matching = getMatchingObjects(generateIDFilter(ids));
        return matching.toArray(new GLModel[matching.size()]);
    }

    /**
     * Searches for Models in opengl cache by specified filter.
     *
     * @param filter Specifies the filter which conditions have to be accepted
     * @return Array of Models that were found by ids specified if none is found returns 0 length array
     */
    public static GLModel[] findAll(Filter<GLModel> filter) {
        ArrayList<GLModel> matching = getMatchingObjects(filter);
        return matching.toArray(new GLModel[matching.size()]);
    }

    private static GLModel getNearestObject(ArrayList<GLModel> matching) {
        GLModel currentNearest = new GLModel();
        double lastDistance = 500;
        for (GLModel o : matching) {
            double distance = Calculations.distance(_CENTER, o.getLocation());
            if (distance < lastDistance && distance != 0) {
                lastDistance = distance;
                currentNearest = o;
            }
        }
        return currentNearest;
    }

    private static ArrayList<GLModel> getMatchingObjects(Filter<GLModel> filter) {
        ArrayList<GLModel> matching = new ArrayList<GLModel>();
        for (GLModel o : OpenglObjectUpdater.getModelCache()) {
            if (filter.accept(o) && o.isValid()) {
                matching.add(o);
            }
        }
        return matching;
    }

    private static GLModel getMatchingObject(Filter<GLModel> filter) {
        for (GLModel o : OpenglObjectUpdater.getModelCache()) {
            if (filter.accept(o) && o.isValid()) {
                return o;
            }
        }
        return new GLModel();
    }
}
