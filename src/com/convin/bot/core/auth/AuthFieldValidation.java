package com.convin.bot.core.auth;

import com.convin.bot.core.gui.dialogs.DialogBuilder;

import javax.swing.*;
import java.util.Arrays;

/**
 * User: Joni
 * Date: 18.8.2012
 * Time: 12:02
 */
class AuthFieldValidation {
    public static boolean validate(JTextField authkeyField, JPasswordField passwordField1, JPasswordField passwordField2) {
        if (!validatePasswordField(passwordField1, passwordField2)) {
            DialogBuilder.showErrorDialog("Password two short or not equal", "Password entered is too short (min 6 letters) or the passwords don't match");
        }
        if (!validateTextField(authkeyField)) {
            DialogBuilder.showErrorDialog("Your authkey field is empty...", "Please enter a authkey.");
        }
        return validateTextField(authkeyField) && validatePasswordField(passwordField1, passwordField2);
    }

    private static boolean validatePasswordField(JPasswordField pass1, JPasswordField pass2) {
        return pass1.getPassword().length > 5 && pass2.getPassword().length > 5 && Arrays.equals(pass1.getPassword(), pass2.getPassword());
    }

    private static boolean validateTextField(JTextField authkeyField) {
        return !authkeyField.getText().isEmpty();
    }
}
