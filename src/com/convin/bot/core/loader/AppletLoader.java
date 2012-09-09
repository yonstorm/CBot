package com.convin.bot.core.loader;

import com.convin.bot.api.common.Logging;
import com.convin.bot.utils.settings.Settings;
import org.apache.log4j.Priority;

import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppletLoader extends Applet {

    private final Pattern SOURCE_PATTERN = Pattern.compile(Settings.SOURCE_PATTERN);
    private final Pattern ARCHIVE_PATTERN = Pattern.compile(Settings.ARCHIVE_PATTERN);
    private final Pattern CODE_PATTERN = Pattern.compile(Settings.CODE_PATTERN);
    private final Pattern PARAMETER_PATTERN = Pattern.compile(Settings.PARAMETER_PATTERN);
    private final String URL = Settings.URL;
    private final String DL_LOCATION = Settings.DL_LOCATION;
    private String first;
    private String frameSource;
    private String archive;
    private String appletClass;
    private Matcher codeMatcher;
    private Matcher matcher;
    private URL world;
    private CustomAppletStub stubCustom;
    private Applet applet;
    private URLClassLoader ucl;

    public Applet loadApplet() {
        try {
            return buildApplet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Applet buildApplet() throws Exception {
        String source = getPageSource(URL);
        matcher = SOURCE_PATTERN.matcher(source);
        if (matcher.find()) {
            generateAppletInfo();
            if (matcher.find() && codeMatcher.find()) {
                buildAppletStub();
                download(world.toString() + "/", archive);
                return initApplet(stubCustom, appletClass);
            }

        }
        return null;
    }

    private void generateAppletInfo() throws Exception {

        first = matcher.group(1);
        frameSource = getPageSource(first);
        matcher = ARCHIVE_PATTERN.matcher(frameSource);
        codeMatcher = CODE_PATTERN.matcher(frameSource);

    }

    private void buildAppletStub() throws MalformedURLException {
        stubCustom = new CustomAppletStub(PARAMETER_PATTERN, frameSource);
        world = new URL(first.substring(0, first.indexOf("/,")));
        appletClass = codeMatcher.group(1);
        stubCustom.setCodeBase(world);
        stubCustom.setDocumentBase(world);
        archive = matcher.group(1);
    }

    private Applet initApplet(CustomAppletStub stubCustom, String appletClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {

        Class<?> c = null;
        ucl = new URLClassLoader(new URL[]{new File(Settings.DL_LOCATION).toURI().toURL()});
        String appletName = appletClass.replaceAll(".class", "");
        c = ucl.loadClass(appletName);

        applet = (Applet) c.newInstance();
        applet.setStub(stubCustom);
        applet.init();
        applet.start();
        applet.setPreferredSize(new Dimension(765, 553));

        return applet;
    }

    private void download(String world, String archive) throws IOException {
        Logging.log(Priority.INFO, "Downloading game applet");
        URLConnection jarConnection = new URL(world + archive).openConnection();
        FileOutputStream fos = new FileOutputStream(DL_LOCATION);
        InputStream is = jarConnection.getInputStream();

        byte[] tmp = new byte[1024];
        int read;

        while ((read = is.read(tmp)) != -1) {
            fos.write(tmp, 0, read);
        }
        Logging.log(Priority.INFO, "Download finished");
    }

    private static String getPageSource(String sUrl) throws IOException {
        URL url = new URL(sUrl);
        URLConnection c = url.openConnection();
        c.addRequestProperty(
                "Accept",
                "text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        c.addRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        c.addRequestProperty("Accept-Encoding", "gzip,deflate");
        c.addRequestProperty("Accept-Language", "en-gb,en;q=0.5");
        c.addRequestProperty("Connection", "keep-alive");
        c.addRequestProperty("Host", "www.runescape.com");
        c.addRequestProperty("Keep-Alive", "300");
        c.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:9.0.1) Gecko/20100101 Firefox/9.0.1");
        DataInputStream di = new DataInputStream(c.getInputStream());
        byte[] tmp = new byte[c.getContentLength()];
        di.readFully(tmp);
        di.close();
        return new String(tmp);
    }

}
