package com.convin.bot.api.methods;

import com.convin.bot.api.common.Time;
import com.convin.bot.api.conditions.Condition;
import com.convin.bot.api.input.Keyboard;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.wrappers.GLItem;
import com.convin.bot.api.wrappers.GLTexture;
import com.convin.bot.core.bot.Bot;
import com.convin.bot.game.ui.interfaces.bank.BankActions;
import com.convin.bot.game.ui.interfaces.bank.BankInterface;

import java.awt.event.KeyEvent;

/**
 * User: JuV
 * Date: 14.9.2012
 * Time: 16:14
 */
public class Bank {
    private static final BankInterface UI = Bot.CURRENT.getGameInterfaceManager().getBankInterface();

    /**
     * Checks if the bank interface is open.
     *
     * @return True if the bank interface is open.
     */
    public static boolean isOpen() {
        return UI.isVisible();
    }

    /**
     * Closes the bank interface.
     *
     * @return True if the bank was closed
     */
    public static boolean close() {
        if (isOpen())
            UI.doAction(BankActions.CLOSE);
        return !isOpen();
    }

    /**
     * Deposit specified amount of Item to the bank.
     *
     * @param item  The item to deposit
     * @param count The amount of the item to deposit
     * @return True if the deposit was successful
     */
    public static boolean deposit(GLItem item, final int count) {
        if (isOpen()) {
            if (count > 1) {
                item.interact("Deposit-X");
                boolean canEnter = Time.waitFor(new Condition() {
                    @Override
                    public boolean verify() {
                        return UI.isWaitingInput();
                    }

                    @Override
                    public String name() {
                        return "isWaitingInput - Bank";
                    }
                }, 3000);
                if (canEnter) {
                    Keyboard.send(String.valueOf(count));
                    Time.sleep(300, 600);
                    Keyboard.clickKey(KeyEvent.VK_ENTER);
                    Time.sleep(300, 900);
                    return true;
                } else return false;
            }
            item.click();
            return true;
        }
        return false;
    }

    /**
     * Deposits all items from inventory with the depositAll button.
     *
     * @return True if deposit was successful
     */
    public static boolean depositAll() {
        if (!isOpen())
            return false;
        UI.doAction(BankActions.DEPOSIT_ALL);
        return true;
    }

    /**
     * Withdraw specified amount of Item from the bank.
     *
     * @param item  The item to withdraw
     * @param count The amount of the item to withdraw
     * @return True if the withdraw was successful
     */
    public static boolean withdraw(GLTexture item, final int count) {
        if (isOpen()) {
            if (count > 1) {
                item.interact("Withdraw-X");
                boolean canEnter = Time.waitFor(new Condition() {
                    @Override
                    public boolean verify() {
                        return UI.isWaitingInput();
                    }

                    @Override
                    public String name() {
                        return "isWaitingInput - Bank";
                    }
                }, 3000);
                if (canEnter) {
                    Keyboard.send(String.valueOf(count));
                    Time.sleep(300, 600);
                    Keyboard.clickKey(KeyEvent.VK_ENTER);
                    Time.sleep(700, 1000);
                    return true;
                } else return false;
            }
            item.click();
            return true;
        }
        return false;
    }

    /**
     * Gets the visible bank items.
     *
     * @param ids Texture ids to search for in the bank
     * @return Array of Textures as items, empty array if none is found
     */
    public static GLTexture[] find(int... ids) {
        GLTexture[] bankItems = new GLTexture[0];
        if (isOpen()) {
            bankItems = Textures.findAll(new Filter<GLTexture>() {
                @Override
                public boolean accept(GLTexture t) {
                    return t.isValid() && Calculations.rectangleContains(t.getLocation(), UI.getItemArea().getArea());
                }
            });
        }
        return bankItems;
    }
}
