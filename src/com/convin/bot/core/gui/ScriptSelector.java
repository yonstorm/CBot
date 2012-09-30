/*
 * Created by JFormDesigner on Mon Aug 20 15:40:47 EEST 2012
 */

package com.convin.bot.core.gui;

import com.convin.bot.core.bot.AccessorMethods;
import com.convin.bot.core.io.ScriptData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author Ka Str
 */
public class ScriptSelector extends JDialog {
    private AccessorMethods ac;
    private ScriptData selectedScript = null;

    public ScriptSelector(Dialog owner, AccessorMethods ac) {
        super(owner);
        this.ac = ac;
        this.setAlwaysOnTop(true);
        initComponents();
        setup();
    }

    private void setup() {
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                if (node.getChildCount() <= 0) {
                    int i = e.getPath().getPathCount();
                    String selectionName = ((JTree) e.getSource()).getSelectionPath().getLastPathComponent().toString();
                    String selectionCat = ((JTree) e.getSource()).getSelectionPath().getPathComponent(i - 2).toString();
                    selectedScript = ac.getScriptHandler().getScriptLoader().getScript(selectionCat, selectionName);
                    System.out.println("dCMDSN" + selectionName);
                }
            }
        });
    }

    public void refresh() {
        if (!ac.getScriptHandler().getScriptLoader().loadScripts()) return;
        String[] categories = ac.getScriptHandler().getScriptLoader().getScriptCategories();
        LinkedList<DefaultMutableTreeNode> rootList = new LinkedList<DefaultMutableTreeNode>();
        for (String category1 : categories) {
            DefaultMutableTreeNode category = new DefaultMutableTreeNode(category1);
            for (ScriptData data : ac.getScriptHandler().getScriptLoader().getScriptsByCategory(category1)) {
                category.add(new DefaultMutableTreeNode(data.name));
            }
            rootList.add(category);
        }
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Scripts");
        for (DefaultMutableTreeNode aRootList : rootList) {
            root.add(aRootList);
        }
        //final JTree tree2 = new JTree(root);
        DefaultTreeModel m = (DefaultTreeModel) tree.getModel();
        m.setRoot(root);
        m.reload();
        revalidate();

    }

    private void selectButtonActionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        JPanel dialogPane = new JPanel();
        JPanel contentPanel = new JPanel();
        JScrollPane scrollPane2 = new JScrollPane();
        tree = new JTree();
        JLabel label1 = new JLabel();
        JPanel buttonBar = new JPanel();
        JButton okButton = new JButton();
        JButton cancelButton = new JButton();

        //======== this ========
        setTitle("Select script");
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //======== scrollPane2 ========
                {

                    //---- tree ----
                    tree.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    scrollPane2.setViewportView(tree);
                }

                //---- label1 ----
                label1.setIcon(new ImageIcon("E:\\Legerity - Runescape bot\\convin-software-bot-core\\data\\icons\\refresh.png"));
                label1.setText("Refresh");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addGap(0, 165, Short.MAX_VALUE))
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Select");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectButtonActionPerformed(e);
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
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JTree tree;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public ScriptData getSelectedScript() {
        return selectedScript;
    }
}
