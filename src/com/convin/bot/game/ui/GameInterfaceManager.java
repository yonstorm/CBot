package com.convin.bot.game.ui;

import com.convin.bot.game.ui.interfaces.bank.BankInterface;

/**
 * User: Joni
 * Date: 26.9.2012
 * Time: 23:43
 */
public class GameInterfaceManager {
    private final BankInterface bank;

    public GameInterfaceManager() {
        this.bank = new BankInterface();
    }

    public BankInterface getBankInterface() {
        return bank;
    }
}
