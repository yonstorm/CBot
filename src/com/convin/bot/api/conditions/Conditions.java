package com.convin.bot.api.conditions;

import com.convin.bot.api.conditions.premade.PlayerMoving;

/**
 * User: Joni
 * Date: 25.9.2012
 * Time: 19:46
 */
public enum Conditions {
    PLAYER_IS_MOVING(new PlayerMoving()),
    PLAYER_IS_NOT_MOVING(new PlayerMoving().reverse());

    private Condition condition;

    Conditions(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }
}
