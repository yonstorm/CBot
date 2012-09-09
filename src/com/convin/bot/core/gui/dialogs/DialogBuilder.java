package com.convin.bot.core.gui.dialogs;

import javax.swing.*;

/**
 * User: JuV
 * Date: 21.8.2012
 * Time: 9:42
 */
public class DialogBuilder {
    public static void showErrorDialog(String errorTitle, String errorDesc) {
        JOptionPane pane = new JOptionPane(errorDesc, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog(errorTitle);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
