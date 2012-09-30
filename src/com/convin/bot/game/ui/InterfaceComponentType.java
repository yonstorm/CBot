package com.convin.bot.game.ui;

public enum InterfaceComponentType {
    COMPONENT(InterfaceComponent.class.getSimpleName()),
    BUTTON(InterfaceButton.class.getSimpleName()),
    SELECTABLE_BUTTON("SelectableButton"),
    ENTITY_AREA("EntityArea");

    private String typeName;

    InterfaceComponentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
