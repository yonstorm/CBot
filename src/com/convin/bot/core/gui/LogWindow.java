/*
 * Created by JFormDesigner on Fri Aug 31 14:00:56 EEST 2012
 */

package com.convin.bot.core.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ka Str
 */
public class LogWindow extends JDialog {

    public LogWindow(Dialog owner) {
        super(owner);
        initComponents();
        this.setAlwaysOnTop(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        scrollPane1 = new JScrollPane();
        tAreaLog = new JTextArea();

        //======== this ========
        setTitle("ConvinBot - Log");
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- tAreaLog ----
            tAreaLog.setEditable(false);
            tAreaLog.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tAreaLog.setLineWrap(true);
            tAreaLog.setAutoscrolls(true);
            scrollPane1.setViewportView(tAreaLog);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 24, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public JTextArea getLogArea() {
        return tAreaLog;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ka Str
    private JScrollPane scrollPane1;
    private JTextArea tAreaLog;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
