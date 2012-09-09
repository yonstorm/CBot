/*
 * Created by JFormDesigner on Fri Aug 24 16:52:10 EEST 2012
 */

package com.convin.bot.core.gui;

import com.convin.bot.api.common.Time;
import com.convin.bot.core.bot.BotProcess;
import com.convin.bot.utils.settings.Settings;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author strömberg
 */
public class OneGlance extends JPanel {
    private final BotProcess currentProcess;
    private final NewMainGui mainGui;
    private long startTime;
    private TimeUpdater timeUpdater;

    public OneGlance(BotProcess process, NewMainGui mainGui) {
        this.currentProcess = process;
        currentProcess.setGui(this);
        this.mainGui = mainGui;
        setMaximumSize(new Dimension(805, 150));
        initComponents();
    }

    private void shutdownMouseClicked() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this bot instance?", "Close the instance?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_OPTION) {
            JPanel mainPanel = (JPanel) this.getParent();
            mainPanel.remove(this);
            mainPanel.revalidate();
            mainGui.repaint();
            BotProcess.ACTIVE_BOTS.remove(currentProcess);
            SwingWorker worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    return currentProcess.destroy();
                }
            };
            worker.execute();
        }

    }

    private void settingsMouseClicked() {
        currentProcess.sendCommand("showSettings");
    }

    private void logMouseClicked() {
        currentProcess.sendCommand("showLog");
    }

    private void btnShowActionPerformed(ActionEvent e) {
        String s = String.valueOf(btnShow.isSelected());
        currentProcess.sendCommand("showGame" + s);
    }

    private void btnSelectScriptActionPerformed(ActionEvent e) {
        currentProcess.sendCommand("selectScript");
    }

    private void btnStartActionPerformed(ActionEvent e) {
        currentProcess.sendCommand("startScript");
    }

    private void btnStopActionPerformed(ActionEvent e) {
        currentProcess.sendCommand("stopScript");
    }

    private void btnPauseActionPerformed(ActionEvent e) {
        currentProcess.sendCommand("pauseScript");
    }

    private void btnBlockHumanActionPerformed(ActionEvent e) {
        String s = String.valueOf(btnBlockHuman.isSelected());
        currentProcess.sendCommand("blockHuman" + s);
    }

    public void dataUpdateScript(String script) {
        this.script.setText(script);
        if (startTime != 0) {
            this.timeRunning.setText("00:00:00");
            timeUpdater.stopped = true;
            startTime = 0;
        }
        this.revalidate();
    }

    public void dataStartTime(String startTime) {
        this.startTime = 0;
        this.startTime = Long.parseLong(startTime);
        this.timeUpdater = new TimeUpdater(this);
    }

    public void resetAndStopTimer() {
        timeUpdater.stopped = true;
        this.startTime = 0;
        this.timeRunning.setText("00:00:00");

    }

    class TimeUpdater implements Runnable {

        private boolean stopped;
        private final OneGlance ogc;

        public TimeUpdater(OneGlance ogc) {
            this.stopped = false;
            this.ogc = ogc;
            Thread timeThread = new Thread(this);
            timeThread.setName("TimeUpdater");
            timeThread.start();
        }

        @Override
        public void run() {
            while (!stopped) {
                long millis = (System.currentTimeMillis() - startTime);
                int h = (int) ((millis / 1000) / 3600);
                int m = (int) (((millis / 1000) / 60) % 60);
                int s = (int) ((millis / 1000) % 60);
                timeRunning.setText(formatTime(h, m, s));
                timeRunning.revalidate();
                Time.sleep(1000);
            }
        }

        private String formatTime(int hours, int minutes, int seconds) {
            String h, m, s;
            h = Integer.toString(hours);
            m = Integer.toString(minutes);
            s = Integer.toString(seconds);
            if (hours < 10)
                h = "0" + Integer.toString(hours);
            if (minutes < 10)
                m = "0" + Integer.toString(minutes);
            if (seconds < 10)
                s = "0" + Integer.toString(seconds);
            return h + ":" + m + ":" + s;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        btnSelectScript = new JButton();
        btnStart = new JButton();
        btnPause = new JButton();
        btnBlockHuman = new JToggleButton();
        btnShow = new JToggleButton();
        btnShutdown = new JLabel();
        lblAccount = new JLabel();
        lblScript = new JLabel();
        lbltimeRunning = new JLabel();
        account = new JLabel();
        timeRunning = new JLabel();
        script = new JLabel();
        btnStop = new JButton();
        btnSettings = new JLabel();
        btnLog = new JLabel();
        lblStatus = new JLabel();
        status = new JLabel();
        lblUserSpecified1 = new JLabel();
        scriptSpecific1 = new JLabel();
        lblUserSpecified2 = new JLabel();
        scriptSpecific2 = new JLabel();

        //======== this ========
        setForeground(Color.white);
        setBackground(new Color(228, 219, 198));
        setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));


        //---- btnSelectScript ----
        btnSelectScript.setText("Select script");
        btnSelectScript.setIcon(new ImageIcon(Settings.ICON_PATH + "Eject24.png"));
        btnSelectScript.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSelectScript.setBackground(UIManager.getColor("Button.background"));
        btnSelectScript.setForeground(new Color(51, 51, 51));
        btnSelectScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSelectScriptActionPerformed(e);
            }
        });

        //---- btnStart ----
        btnStart.setText("Start");
        btnStart.setIcon(new ImageIcon(Settings.ICON_PATH + "Play24.png"));
        btnStart.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnStart.setBackground(UIManager.getColor("Button.background"));
        btnStart.setForeground(new Color(51, 51, 51));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartActionPerformed(e);
            }
        });

        //---- btnPause ----
        btnPause.setText("Pause");
        btnPause.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnPause.setIcon(new ImageIcon(Settings.ICON_PATH + "Pause24.png"));
        btnPause.setBackground(UIManager.getColor("Button.background"));
        btnPause.setForeground(new Color(51, 51, 51));
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPauseActionPerformed(e);
            }
        });

        //---- btnBlockHuman ----
        btnBlockHuman.setText("Block human");
        btnBlockHuman.setIcon(new ImageIcon(Settings.ICON_PATH + "User-green24.png"));
        btnBlockHuman.setSelectedIcon(new ImageIcon(Settings.ICON_PATH + "User-red24.png"));
        btnBlockHuman.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBlockHuman.setBackground(UIManager.getColor("Button.background"));
        btnBlockHuman.setForeground(new Color(51, 51, 51));
        btnBlockHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBlockHumanActionPerformed(e);
            }
        });

        //---- btnShow ----
        btnShow.setText("Show");
        btnShow.setForeground(new Color(51, 51, 51));
        btnShow.setBackground(UIManager.getColor("Button.background"));
        btnShow.setIcon(new ImageIcon(Settings.ICON_PATH + "Eye24.png"));
        btnShow.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnShowActionPerformed(e);
            }
        });

        //---- btnShutdown ----
        btnShutdown.setIcon(new ImageIcon(Settings.ICON_PATH + "power_24.png"));
        btnShutdown.setToolTipText("Shutdown this instance");
        btnShutdown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shutdownMouseClicked();
            }
        });

        //---- lblAccount ----
        lblAccount.setText("Account:");
        lblAccount.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- lblScript ----
        lblScript.setText("Script: ");
        lblScript.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- lbltimeRunning ----
        lbltimeRunning.setText("Running: ");
        lbltimeRunning.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- account ----
        account.setText("0Test");
        account.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        //---- timeRunning ----
        timeRunning.setText("00:00:00");
        timeRunning.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        //---- script ----
        script.setText("TestScript");
        script.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        //---- btnStop ----
        btnStop.setText("Stop");
        btnStop.setIcon(new ImageIcon(Settings.ICON_PATH + "Stop24.png"));
        btnStop.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnStop.setBackground(UIManager.getColor("Button.background"));
        btnStop.setForeground(new Color(51, 51, 51));
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStopActionPerformed(e);
            }
        });

        //---- btnSettings ----
        btnSettings.setIcon(new ImageIcon(Settings.ICON_PATH + "settings-24.png"));
        btnSettings.setToolTipText("View settings");
        btnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingsMouseClicked();
            }
        });

        //---- btnLog ----
        btnLog.setIcon(new ImageIcon(Settings.ICON_PATH + "page-24.png"));
        btnLog.setToolTipText("View log");
        btnLog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logMouseClicked();
            }
        });

        //---- lblStatus ----
        lblStatus.setText("Status: ");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- status ----
        status.setText("Not yet implemented");
        status.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        //---- lblUserSpecified1 ----
        lblUserSpecified1.setText("User1: ");
        lblUserSpecified1.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- scriptSpecific1 ----
        scriptSpecific1.setText("Script Specified");
        scriptSpecific1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        //---- lblUserSpecified2 ----
        lblUserSpecified2.setText("User2: ");
        lblUserSpecified2.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- scriptSpecific2 ----
        scriptSpecific2.setText("Script Specified");
        scriptSpecific2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(btnSelectScript)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnStart)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnStop)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnPause)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBlockHuman)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(btnShutdown, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnSettings, GroupLayout.Alignment.TRAILING)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                        .addComponent(lblScript)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(script, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                        .addComponent(lblAccount)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(account, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbltimeRunning)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(timeRunning, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(41, 41, 41)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblStatus)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(status, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                                                                .addComponent(btnLog))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblUserSpecified1)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(scriptSpecific1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(lblUserSpecified2)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(scriptSpecific2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 377, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnShow)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnStart)
                                                .addComponent(btnSelectScript, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnStop)
                                                .addComponent(btnPause, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnBlockHuman)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblScript)
                                                .addComponent(script))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblStatus)
                                                .addComponent(status)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(account)
                                                        .addComponent(lblAccount))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbltimeRunning)
                                                        .addComponent(timeRunning)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(lblUserSpecified1)
                                                        .addComponent(scriptSpecific1))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(scriptSpecific2)
                                                        .addComponent(lblUserSpecified2))))
                                .addGap(11, 11, 11))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnShutdown)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSettings)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLog)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ka Str
    private JButton btnSelectScript;
    private JButton btnStart;
    private JButton btnPause;
    private JToggleButton btnBlockHuman;
    private JToggleButton btnShow;
    private JLabel btnShutdown;
    private JLabel lblAccount;
    private JLabel lblScript;
    private JLabel lbltimeRunning;
    private JLabel account;
    private JLabel timeRunning;
    private JLabel script;
    private JButton btnStop;
    private JLabel btnSettings;
    private JLabel btnLog;
    private JLabel lblStatus;
    private JLabel status;
    private JLabel lblUserSpecified1;
    private JLabel scriptSpecific1;
    private JLabel lblUserSpecified2;
    private JLabel scriptSpecific2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
