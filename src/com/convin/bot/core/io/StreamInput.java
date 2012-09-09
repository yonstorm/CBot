package com.convin.bot.core.io;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Joni
 * Date: 26.7.2012
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class StreamInput extends Thread {
    final OutputStream os;
    final String input;

    public StreamInput(OutputStream is, String input) {
        this.os = is;
        this.input = input;
    }

    public void run() {
        PrintWriter in = null;
        if (os != null)
            in = new PrintWriter(os);

        if (in != null) {
            in.println(input);
            System.out.println(">" + input); //debug
        }
        if (in != null) {
            in.flush();
        }

    }

}
