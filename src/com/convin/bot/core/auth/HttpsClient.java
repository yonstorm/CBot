package com.convin.bot.core.auth;

/**
 * User: JuV
 * Date: 18.8.2012
 * Time: 18:59
 */

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;

class HttpsClient {
    HttpsURLConnection getConnection(String authkey) throws IOException {


        HttpsURLConnection con = null;
        String https_url = "https://convinbot.net/authValidation.php?auth=" + authkey + "&id=" + (this.getClass().getCanonicalName().getBytes().length + authkey.length());
        URL url;

        url = new URL(https_url);
        con = (HttpsURLConnection) url.openConnection();


        return con;

    }

    private String getAnswer(HttpsURLConnection con) {
        if (con != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    if (input.startsWith("K")) {
                        return input.substring(1);
                    }
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "1";
    }

    public boolean validate(String auth) throws IOException, GeneralSecurityException {
        HttpsURLConnection con = getConnection(auth);
        con.connect();
        return (Integer.parseInt(getAnswer(con)) - auth.length()) == this.getClass().getCanonicalName().getBytes().length;

    }
}
