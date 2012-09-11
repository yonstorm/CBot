package com.convin.bot.api.methods;

import com.convin.bot.api.wrappers.GLTexture;
import com.convin.bot.core.handlers.updaters.OpenglObjectUpdater;

import java.util.ArrayList;

/**
 * User: JuV
 * Date: 10.6.2012
 * Time: 1:31
 */
public class Textures {
    private Textures() {
    }

    /**
     * Searches for GLTextures with specified ids.
     *
     * @param ids Texture ids to search for
     * @return An array of textures, if no textures are found array will be empty
     */
    public static GLTexture[] findAll(int... ids) {
        ArrayList<GLTexture> matching = new ArrayList<GLTexture>();
        for (GLTexture t : OpenglObjectUpdater.getTextureCache()) {
            for (int id : ids) {
                if (t.getId() == id) {
                    matching.add(t);
                }
            }
        }
        return matching.toArray(new GLTexture[matching.size()]);
    }

    /**
     * Searches for Textures in opengl cache by specified filter.
     *
     * @param filter Specifies the filter which conditions have to be accepted
     * @return Array of Textures, if none are found returns a empty array
     */
    public static GLTexture[] findAll(Filter<GLTexture> filter) {
        ArrayList<GLTexture> matching = new ArrayList<GLTexture>();
        for (GLTexture t : OpenglObjectUpdater.getTextureCache()) {
            if (filter.accept(t)) {
                matching.add(t);
            }
        }
        return matching.toArray(new GLTexture[matching.size()]);
    }

    /**
     * Checks if texture is present by specified ids.
     *
     * @param ids Texture ids to check against
     * @return True if texture is present, else false
     */
    public static boolean isPresent(int... ids) {
        for (GLTexture t : OpenglObjectUpdater.getTextureCache()) {
            for (int id : ids) {
                if (t.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Searches for a Texture with the specified id.
     *
     * @param id Texture id to search for
     * @return A valid texture object - if no textures are found returns an invalid texture object
     */
    public static GLTexture find(int id) {
        GLTexture texture = new GLTexture();
        for (GLTexture t : OpenglObjectUpdater.getTextureCache()) {
            if (t.getId() == id) {
                texture = t;
                break;
            }
        }
        return texture;
    }

    /**
     * Searches for a GLTexture in opengl cache by filter
     *
     * @param filter Specifies the filter which conditions have to be accepted
     * @return A GLTexure if one is found that is accepted by the filter else returns a invalid GLTexture
     */

    public static GLTexture find(Filter<GLTexture> filter) {
        for (GLTexture t : OpenglObjectUpdater.getTextureCache()) {
            if (filter.accept(t)) {
                return t;
            }
        }
        return new GLTexture();
    }
}
