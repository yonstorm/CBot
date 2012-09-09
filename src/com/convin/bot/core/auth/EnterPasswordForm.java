/*
 * Created by JFormDesigner on Sat Aug 18 15:07:48 EEST 2012
 */

package com.convin.bot.core.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Ka Str
 */
public class EnterPasswordForm extends JFrame {
    private final CountDownLatch loginSignal;

    public EnterPasswordForm(CountDownLatch loginSignal) {
        super();
        initComponents();
        this.loginSignal = loginSignal;
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        if (!(passwordFieldValidator.getPassword().length < 5)) {
            try {
                HttpsClient https = new HttpsClient();
                if (https.validate(ProtectedAuthFile.getAuth(passwordFieldValidator.getPassword()))) {
                    this.dispose();
                    loginSignal.countDown();
                } else {
                    JOptionPane.showMessageDialog(null, "Something wrong with your authkey or file.", "ERROR: Wrong auth or invalid authfile", JOptionPane.ERROR_MESSAGE);
                }


            } catch (GeneralSecurityException e1) {
                JOptionPane.showMessageDialog(null, "Decrypting failed. Password rejected!", "ERROR: Decrypting failed", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error reading your protected auth file", "ERROR: Could not read auth file", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "The password you entered is too short.", "ERROR: Password too short", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        JPanel dialogPane = new JPanel();
        JPanel contentPanel = new JPanel();
        JLabel label1 = new JLabel();
        passwordFieldValidator = new JPasswordField();
        JButton okButton = new JButton();

        //======== this ========
        setTitle("Auth validator");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                label1.setText("Please enter your password");

                //---- passwordFieldValidator ----
                passwordFieldValidator.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                .addComponent(passwordFieldValidator, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(passwordFieldValidator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(okButton))
                                        .addContainerGap(34, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JPasswordField passwordFieldValidator;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
