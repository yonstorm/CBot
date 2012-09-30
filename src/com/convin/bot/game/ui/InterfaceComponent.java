package com.convin.bot.game.ui;

public abstract class InterfaceComponent {
    private String name;
    private InterfaceComponentType componentType;

    protected InterfaceComponent(String name, InterfaceComponentType componentType) {
        this.componentType = componentType;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public InterfaceComponentType getComponentType() {
        return componentType;
    }

}
