/*
 * Created by JFormDesigner on Mon Sep 03 01:36:55 EEST 2012
 */

package com.convin.bot.core.gui;

import com.convin.bot.api.methods.Commands;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ka Str
 */
public class InstanceSettings extends JDialog {
    public InstanceSettings(Frame owner) {
        super(owner);
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        Commands.setGLPaint(cBoxEnablePaint.isSelected());
        Commands.setPerformanceBoost(cBoxOldComp.isSelected());
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        cBoxEnablePaint = new JCheckBox();
        cBoxOldComp = new JCheckBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setAlwaysOnTop(true);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setTitle("Settings");
        Container contentPane = getContentPane();

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            //======== contentPanel ========
            {

                //---- cBoxEnablePaint ----
                cBoxEnablePaint.setText("Enable paint");
                cBoxEnablePaint.setToolTipText("Enable or disable game image painting");
                cBoxEnablePaint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cBoxEnablePaint.setSelected(true);

                //---- cBoxOldComp ----
                cBoxOldComp.setText("I have a old computer");
                cBoxOldComp.setToolTipText("Enable or disable performance boost");
                cBoxOldComp.setFont(new Font("Segoe UI", Font.PLAIN, 14));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(cBoxEnablePaint, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cBoxOldComp, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(293, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(cBoxEnablePaint)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cBoxOldComp)
                                        .addContainerGap(242, Short.MAX_VALUE))
                );
            }

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }

            GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
            dialogPane.setLayout(dialogPaneLayout);
            dialogPaneLayout.setHorizontalGroup(
                    dialogPaneLayout.createParallelGroup()
                            .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBar, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
            );
            dialogPaneLayout.setVerticalGroup(
                    dialogPaneLayout.createParallelGroup()
                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                    .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(buttonBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ka Str
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JCheckBox cBoxEnablePaint;
    private JCheckBox cBoxOldComp;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
