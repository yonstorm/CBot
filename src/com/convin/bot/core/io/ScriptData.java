package com.convin.bot.core.io;

/**
 * User: JuV
 * Date: 20.6.2012
 * Time: 13:52
 */
public class ScriptData {
    public final Class<?> clazz;
    public final String name;
    public final String[] authors;
    public final String category;
    public final String description;
    public final double version;

    public ScriptData(Class<?> scriptClass, String scriptName, String[] scriptAuthors, String scriptCategory, String description, double scriptVersion) {
        this.clazz = scriptClass;
        this.name = scriptName;
        this.authors = scriptAuthors;
        this.category = scriptCategory;
        this.description = description;
        this.version = scriptVersion;
    }
}
