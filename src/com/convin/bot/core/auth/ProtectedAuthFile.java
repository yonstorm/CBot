package com.convin.bot.core.auth;

import com.convin.bot.core.gui.dialogs.DialogBuilder;
import com.convin.bot.utils.settings.Settings;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.GeneralSecurityException;

/**
 * User: Joni
 * Date: 18.8.2012
 * Time: 12:44
 */
public class ProtectedAuthFile {
    private static final byte[] SALT = {
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    private static String encrypt(String property, char[] password) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes()));
    }

    private static String base64Encode(byte[] bytes) {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Encoder().encode(bytes);
    }

    private static String decrypt(String property, char[] password) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)));
    }

    private static byte[] base64Decode(String property) throws IOException {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Decoder().decodeBuffer(property);
    }


    public static boolean createProtectedAuthFile(char[] password, String authkey) throws GeneralSecurityException, IOException {
        String crypted = encrypt(authkey, password);
        File authFile = new File(Settings.AUTH_FILE);
        if (authFile.exists()) {
            DialogBuilder.showErrorDialog("File already exists", "The file your trying to create already exists");
            return false;
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(authFile));
        out.write(crypted);
        out.close();
        return true;
    }

    public static String getAuth(char[] password) throws IOException, GeneralSecurityException {
        BufferedReader in = new BufferedReader(new FileReader(Settings.AUTH_FILE));
        String cryptedAuth = in.readLine();
        return decrypt(cryptedAuth, password);
    }

    public static boolean exists() {
        return new File(Settings.AUTH_FILE).exists();
    }
}
