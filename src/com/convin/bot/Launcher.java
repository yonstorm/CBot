package com.convin.bot;

import com.convin.bot.core.auth.EnterPasswordForm;
import com.convin.bot.core.auth.ProtectedAuthFile;
import com.convin.bot.core.auth.authForm;
import com.convin.bot.core.gui.NewMainGui;
import com.convin.bot.core.gui.dialogs.DialogBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: stromberg
 * Date: 22.3.2012
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 1) {
            DialogBuilder.showErrorDialog("Please use the launcher", "Please use the ConvinBot launcher");
            System.exit(1);
        }
        if (!args[0].equals("1351")) {
            DialogBuilder.showErrorDialog("Please use the launcher", "Please use the ConvinBot launcher");
            System.exit(1);
        }
        if (!ProtectedAuthFile.exists()) {
            CountDownLatch authFileCreateSignal = new CountDownLatch(1);
            new authForm(authFileCreateSignal);
            authFileCreateSignal.await();
        }
        if (ProtectedAuthFile.exists()) {
            CountDownLatch loginSignal = new CountDownLatch(1);
            new EnterPasswordForm(loginSignal);
            loginSignal.await();
            new NewMainGui();
        }
    }

}
