package com.convin.bot.core.components;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * User: JuV
 * Date: 5.8.2012
 * Time: 21:23
 */
public class CustomFrame extends JFrame {

    public CustomFrame() {
        Border noBorder = BorderFactory.createEmptyBorder();
        JComponent cont = (JComponent) getContentPane();
        setState(JFrame.NORMAL);
        cont.setBorder(noBorder);
    }

    @Override
    public boolean isShowing() {
        return true;
    }
}
