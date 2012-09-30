package com.convin.bot.api.conditions.premade;

import com.convin.bot.api.conditions.Condition;
import com.convin.bot.api.methods.Player;

/**
 * User: JuV
 * Date: 25.9.2012
 * Time: 19:42
 */
public class PlayerMoving extends Condition {
    @Override
    public boolean verify() {
        return Player.isMoving();
    }

    @Override
    public String name() {
        return "PlayerMoving";
    }
}
