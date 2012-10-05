package com.convin.bot.core.components;

import com.convin.bot.utils.settings.Settings;

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
        setIconImage(new ImageIcon(Settings.ICON_PATH + "app_icon.png").getImage());
        setMinimumSize(new Dimension(800, 820));
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
            textAreaLog.setAutoscrolls(true);
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
