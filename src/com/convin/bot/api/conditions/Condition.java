package com.convin.bot.api.conditions;

/**
 * User: Joni
 * Date: 25.9.2012
 * Time: 19:38
 */
public abstract class Condition {
    private final Condition thisCond = this;

    /**
     * Check if the condition is met.
     *
     * @return True if condition is met
     */
    public abstract boolean verify();

    /**
     * Name of this Condition
     *
     * @return The name of the condition
     */
    public abstract String name();

    public Condition reverse() {
        return new Condition() {
            @Override
            public boolean verify() {
                return !thisCond.verify();
            }

            @Override
            public String name() {
                return " reversed " + thisCond.name();
            }
        };
    }
}
