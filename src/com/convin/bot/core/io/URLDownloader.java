package com.convin.bot.core.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * User: JuV
 * Date: 29.9.2012
 * Time: 15:30
 */
public class URLDownloader {

    public static ByteBuffer getAsByteArray(URL url) throws IOException {
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = url.openStream();
            byte[] byteChunk = new byte[4096];
            int n;

            while ((n = is.read(byteChunk)) > 0) {
                bais.write(byteChunk, 0, n);
            }
        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        byte[] array = bais.toByteArray();

//        FileOutputStream fos = new FileOutputStream("C:\\abc.png");
//        fos.write(array);
//        fos.close();

        return ByteBuffer.wrap(array);
    }
}
