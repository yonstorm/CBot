package com.convin.bot.core.components;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * @author Ka Str
 */
public class AppletFrame extends JFrame {
    public AppletFrame() {
        initComponents();
    }

    public AppletFrame(Applet applet, ImagePanel imagePanel) {
        initComponents();

    }

    public void initApplet(Applet applet, ImagePanel imagePanel) {
        pnlAppletTab.add(applet);
        pnlPaintableTab.add(imagePanel);
        this.pack();
        this.revalidate();

        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Joni StrÃ¶mberg
        displayTabs = new JTabbedPane();
        pnlAppletTab = new JPanel();
        pnlPaintableTab = new JPanel();
        scrollPaneLog = new JScrollPane();
        textAreaLog = new JTextArea();

        //======== this ========
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setTitle("Game Applet display");
        setIconImage(new ImageIcon("E:\\Projects\\ConvinBot\\ConvinBotGitHub\\data\\icons\\app_icon.png").getImage());
        setMinimumSize(new Dimension(800, 860));
        Container contentPane = getContentPane();

        //======== displayTabs ========
        {
            displayTabs.setMinimumSize(new Dimension(1, 1));
            displayTabs.setPreferredSize(new Dimension(800, 565));
            displayTabs.setBackground(UIManager.getColor("DesktopPane.background"));

            //======== pnlAppletTab ========
            {
                pnlAppletTab.setPreferredSize(new Dimension(800, 565));
                pnlAppletTab.setBackground(Color.black);

                // JFormDesigner evaluation mark
                pnlAppletTab.setBorder(new javax.swing.border.CompoundBorder(
                        new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                                java.awt.Color.red), pnlAppletTab.getBorder()));
                pnlAppletTab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent e) {
                        if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                    }
                });

                pnlAppletTab.setLayout(new GridLayout());
            }
            displayTabs.addTab("Applet", pnlAppletTab);


            //======== pnlPaintableTab ========
            {
                pnlPaintableTab.setPreferredSize(new Dimension(800, 565));
                pnlPaintableTab.setBackground(Color.black);
                pnlPaintableTab.setLayout(new GridLayout());
            }
            displayTabs.addTab("Paintable", pnlPaintableTab);

        }

        //======== scrollPaneLog ========
        {

            //---- textAreaLog ----
            textAreaLog.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textAreaLog.setEditable(false);
            textAreaLog.setLineWrap(true);
            textAreaLog.setPreferredSize(new Dimension(507, 75));
            textAreaLog.setMinimumSize(new Dimension(507, 75));
            scrollPaneLog.setViewportView(textAreaLog);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(displayTabs, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                        .addComponent(scrollPaneLog)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(displayTabs, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneLog, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public JPanel getAppletTab() {
        return pnlAppletTab;
    }

    public JPanel getPaintableTab() {
        return pnlPaintableTab;
    }

    public JTextArea getTextAreaLog() {
        return textAreaLog;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Joni StrÃ¶mberg
    private JTabbedPane displayTabs;
    private JPanel pnlAppletTab;
    private JPanel pnlPaintableTab;
    private JScrollPane scrollPaneLog;
    private JTextArea textAreaLog;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
