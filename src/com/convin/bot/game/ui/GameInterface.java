package com.convin.bot.game.ui;

public abstract class GameInterface {
    private final String name;

    public GameInterface(String name) {
        this.name = name;
    }

    public abstract boolean isVisible();

    public String getName() {
        return name;
    }
}
