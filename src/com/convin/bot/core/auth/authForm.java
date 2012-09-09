/*
 * Created by JFormDesigner on Sat Aug 18 10:59:53 EEST 2012
 */

package com.convin.bot.core.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.CountDownLatch;

/**
 * @author unknown
 */
public class authForm {
    private final CountDownLatch afcSignal;

    public authForm(CountDownLatch authFileCreatedSignal) {
        super();
        initComponents();
        this.afcSignal = authFileCreatedSignal;
        authForm.setVisible(true);
        authForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        if (AuthFieldValidation.validate(authkeyField, passwordField1, passwordField2)) {
            try {
                if (ProtectedAuthFile.createProtectedAuthFile(passwordField1.getPassword(), authkeyField.getText())) {
                    this.authForm.dispose();
                    afcSignal.countDown();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong while creating a new auth file.", "ERROR: Could not create a new Auth file", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ka Str
        authForm = new JFrame();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        lblAuthkey = new JLabel();
        authkeyField = new JTextField();
        btnValidate = new JButton();
        lblStatus = new JLabel();
        lblStatusValidate = new JLabel();
        lblPassword = new JLabel();
        passwordField1 = new JPasswordField();
        label4 = new JLabel();
        passwordField2 = new JPasswordField();
        textAreaPasswordHint = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        helpButton = new JButton();

        //======== authForm ========
        {
            authForm.setTitle("ConvinBot - Add authkey");
            authForm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            Container authFormContentPane = authForm.getContentPane();
            authFormContentPane.setLayout(new BorderLayout());

            //======== dialogPane ========
            {
                dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane.setLayout(new BorderLayout());

                //======== contentPanel ========
                {

                    //---- lblAuthkey ----
                    lblAuthkey.setText("Your authkey:");

                    //---- authkeyField ----
                    authkeyField.setSelectionColor(new Color(57, 105, 138));

                    //---- btnValidate ----
                    btnValidate.setText("Validate");
                    btnValidate.setVisible(false);

                    //---- lblStatus ----
                    lblStatus.setText("Status");
                    lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    lblStatus.setVisible(false);

                    //---- lblStatusValidate ----
                    lblStatusValidate.setText("OK");
                    lblStatusValidate.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    lblStatusValidate.setForeground(new Color(0, 153, 51));
                    lblStatusValidate.setVisible(false);

                    //---- lblPassword ----
                    lblPassword.setText("Password");
                    lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                    //---- passwordField1 ----
                    passwordField1.setBackground(Color.white);
                    passwordField1.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                    //---- label4 ----
                    label4.setText("Password again");
                    label4.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                    //---- passwordField2 ----
                    passwordField2.setBackground(Color.white);
                    passwordField2.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                    //---- textAreaPasswordHint ----
                    textAreaPasswordHint.setForeground(Color.black);
                    textAreaPasswordHint.setBackground(new Color(214, 217, 223));
                    textAreaPasswordHint.setEditable(false);
                    textAreaPasswordHint.setBorder(new TitledBorder("Why is password needed?"));
                    textAreaPasswordHint.setText("The password you supply is used to protect your authkey from theft. Should you forget this password your authkey cannot be recovered!");
                    textAreaPasswordHint.setLineWrap(true);
                    textAreaPasswordHint.setWrapStyleWord(true);
                    textAreaPasswordHint.setFont(new Font("Segoe UI", Font.PLAIN, 12));

                    GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                    contentPanel.setLayout(contentPanelLayout);
                    contentPanelLayout.setHorizontalGroup(
                            contentPanelLayout.createParallelGroup()
                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                    .addComponent(authkeyField, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblAuthkey, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(btnValidate, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblStatusValidate, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                                    .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addComponent(textAreaPasswordHint, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap(1, Short.MAX_VALUE))
                    );
                    contentPanelLayout.setVerticalGroup(
                            contentPanelLayout.createParallelGroup()
                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(lblAuthkey)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(authkeyField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnValidate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblStatusValidate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                                            .addComponent(lblStatus, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                                            .addGap(53, 53, 53)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                    .addComponent(textAreaPasswordHint, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(lblPassword)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(label4)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                            .addContainerGap(21, Short.MAX_VALUE))
                    );
                }
                dialogPane.add(contentPanel, BorderLayout.CENTER);

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

                    //---- helpButton ----
                    helpButton.setText("Help");
                    buttonBar.add(helpButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                }
                dialogPane.add(buttonBar, BorderLayout.SOUTH);
            }
            authFormContentPane.add(dialogPane, BorderLayout.CENTER);
            authForm.pack();
            authForm.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ka Str
    private JFrame authForm;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel lblAuthkey;
    private JTextField authkeyField;
    private JButton btnValidate;
    private JLabel lblStatus;
    private JLabel lblStatusValidate;
    private JLabel lblPassword;
    private JPasswordField passwordField1;
    private JLabel label4;
    private JPasswordField passwordField2;
    private JTextArea textAreaPasswordHint;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton helpButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
