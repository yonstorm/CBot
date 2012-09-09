package com.convin.bot.api.methods;

/**
 * User: JuV
 * Date: 26.4.2012
 * Time: 15:39
 */
public interface Filter<T> {

    /**
     * Filter out unwanted results.
     *
     * @param t Type to use for filtering
     * @return True if your filter parameters are accepted
     */
    public boolean accept(T t);
}
