/*
 * Created by JFormDesigner on Fri Aug 24 19:08:44 EEST 2012
 */

package com.convin.bot.core.gui;

import com.convin.bot.core.bot.BotProcess;
import com.convin.bot.utils.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author Ka Str
 */
public class NewMainGui extends JFrame implements WindowListener {
    private final NewMainGui frame;

    public NewMainGui() {
        useLookAndFeel();
        initComponents();
        setVisible(true);
        frame = this;
        addWindowListener(this);
    }

    private static void useLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    private synchronized void close() {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                ArrayList<BotProcess> bots = BotProcess.ACTIVE_BOTS;
                for (BotProcess p : bots) {
                    p.destroy();
                }
                return true;
            }

            @Override
            protected void done() {
                System.exit(0);
            }

        };
        worker.execute();
        this.dispose();
    }

    private void mnAddBotActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return new OneGlance(new BotProcess(), frame);
            }
        };
        worker.execute();
        try {
            pnlBotPanel.add((OneGlance) worker.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        revalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        menuBar1 = new JMenuBar();
        mnFile = new JMenu();
        mnExit = new JMenuItem();
        mnActions = new JMenu();
        mnAddBot = new JMenuItem();
        jscrollOneGlance = new JScrollPane();
        pnlBotPanel = new JPanel();

        //======== this ========
        setResizable(false);
        setTitle("ConvinBot - Passion for automation");
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(Settings.ICON_PATH + "app_icon.png").getImage());
        setBackground(new Color(228, 219, 198));
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mnFile ========
            {
                mnFile.setText("File");
                mnFile.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

                //---- mnExit ----
                mnExit.setText("Exit");
                mnExit.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                mnFile.add(mnExit);
            }
            menuBar1.add(mnFile);

            //======== mnActions ========
            {
                mnActions.setText("Actions");
                mnActions.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

                //---- mnAddBot ----
                mnAddBot.setText("Add bot");
                mnAddBot.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                mnAddBot.setIcon(new ImageIcon(Settings.ICON_PATH + "plus-16.png"));
                mnAddBot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mnAddBotActionPerformed(e);
                    }
                });
                mnActions.add(mnAddBot);
            }
            menuBar1.add(mnActions);
        }
        setJMenuBar(menuBar1);

        //======== jscrollOneGlance ========
        {
            jscrollOneGlance.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jscrollOneGlance.setBorder(null);
            jscrollOneGlance.setMaximumSize(new Dimension(784, 417));
            jscrollOneGlance.setMinimumSize(new Dimension(784, 417));
            jscrollOneGlance.setAlignmentX(0.0F);
            jscrollOneGlance.setAlignmentY(0.0F);
            jscrollOneGlance.setBackground(new Color(214, 217, 223));
            jscrollOneGlance.setPreferredSize(new Dimension(784, 417));

            //======== pnlBotPanel ========
            {
                pnlBotPanel.setBackground(new Color(214, 214, 214));
                pnlBotPanel.setAlignmentY(0.0F);
                pnlBotPanel.setBorder(null);


                pnlBotPanel.setLayout(new BoxLayout(pnlBotPanel, BoxLayout.Y_AXIS));
            }
            jscrollOneGlance.setViewportView(pnlBotPanel);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(jscrollOneGlance, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(jscrollOneGlance, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );
        setSize(815, 490);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ka Str
    private JMenuBar menuBar1;
    private JMenu mnFile;
    private JMenuItem mnExit;
    private JMenu mnActions;
    private JMenuItem mnAddBot;
    private JScrollPane jscrollOneGlance;
    private JPanel pnlBotPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close ConvinBot?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_OPTION) {
            frame.close();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void removeOneGlance(OneGlance oneGlance) {
    }
}