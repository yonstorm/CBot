package com.convin.bot.core.loader;


import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAppletStub implements AppletStub {
    private URL documentBase;
    private URL codeBase;

    private final Map<String, String> parameters = new HashMap<String, String>();

    public CustomAppletStub(Pattern parameterPattern, String frameSource) {
        Matcher param = parameterPattern.matcher(frameSource);
        while (param.find()) {
            String key = param.group(1);
            String value = param.group(2);
            parameters.put(key, value);
        }
    }

    public void setDocumentBase(URL documentBase) {
        this.documentBase = documentBase;
    }

    public void setCodeBase(URL codeBase) {
        this.codeBase = codeBase;
    }

    @Override
    public URL getDocumentBase() {
        return this.documentBase;
    }

    @Override
    public URL getCodeBase() {
        return this.codeBase;
    }

    @Override
    public String getParameter(String name) {
        return this.parameters.get(name);
    }

    @Override
    public boolean isActive() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(int width, int height) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

}
