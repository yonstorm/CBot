package com.convin.bot.api.script;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: Joni
 * Date: 20.8.2012
 * Time: 11:59
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ScriptManifest {
    String[] authors();

    String scriptName();

    double version() default 1.0;

    String category();

    String description() default "";
}
