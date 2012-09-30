package com.convin.bot.game.ui.interfaces.bank;

import com.convin.bot.api.common.Logging;
import com.convin.bot.api.math.Calculations;
import com.convin.bot.api.methods.Filter;
import com.convin.bot.api.methods.Fonts;
import com.convin.bot.api.methods.Textures;
import com.convin.bot.api.wrappers.GLTexture;
import com.convin.bot.game.ui.GameInterface;
import com.convin.bot.game.ui.InterfaceArea;
import com.convin.bot.game.ui.InterfaceButton;

import java.awt.*;

public class BankInterface extends GameInterface {
    private static final Rectangle BANK_WINDOW_TITLE_AREA = new Rectangle(188, 76, 143, 17);
    private static final String BANK_WINDOW_TITLE = "BankofRuneScap";

    private static final Rectangle CHAT_AREA = new Rectangle(7, 509, 506, 13);
    private static final int CHAT_BUBBLE = 27406;

    // BUTTONS
    private final InterfaceButton bntDepositAll = new InterfaceButton(new Rectangle(353, 348, 31, 21), "DepositAll");
    private final InterfaceButton btnClose = new InterfaceButton(new Rectangle(481, 78, 14, 14), "Close");
    private final InterfaceButton btnToggleNotes = new InterfaceButton(new Rectangle(214, 347, 28, 20), "NoteMode");
    // AREAS
    private final InterfaceArea areaBankItem = new InterfaceArea(new Rectangle(21, 134, 459, 210), "ItemArea");

    private static Filter<GLTexture> chatTextFilter = new Filter<GLTexture>() {
        @Override
        public boolean accept(GLTexture t) {
            return t.getId() == CHAT_BUBBLE && t.getTextureID() == 223 && Calculations.rectangleContains(t.getLocation(), CHAT_AREA);
        }
    };

    public BankInterface() {
        super("BankInterface");
    }

    public InterfaceArea getItemArea() {
        return areaBankItem;
    }

    @Override
    public boolean isVisible() {
        return Fonts.readFontInRectangle(BANK_WINDOW_TITLE_AREA).getText().startsWith(BANK_WINDOW_TITLE);
    }

    public boolean isWaitingInput() {
        return !Textures.find(chatTextFilter).isValid();
    }

    public boolean doAction(BankActions action) {
        switch (action) {
            case DEPOSIT_ALL:
                bntDepositAll.click();
                break;
            case CLOSE:
                btnClose.click();
                break;
            case TOGGLE_NOTE_MODE:
                btnToggleNotes.click();
                break;
            default:
                Logging.log(Logging.LogLevel.INFO, "No action specified - BankAction");
                break;
        }
        return true;
    }
}
