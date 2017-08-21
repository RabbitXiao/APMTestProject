package com.dell.apm.testwebapp.com.dell.apm.testwebapp.servlet.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnUtil {
    private final static String UTF_8_ENCODING = "UTF-8";

    public static String visitURL(String url) throws IOException {
        return visitURL(url, UTF_8_ENCODING);
    }

    public static String visitURL(String url, String encoding) throws IOException {
        HttpURLConnection urlConn = null;
        try {
            urlConn = (HttpURLConnection) new URL(url).openConnection();
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("GET");
            urlConn.connect();
            String responseContent = IOUtils.toString(urlConn.getInputStream(), encoding);
            return responseContent;
        } catch (IOException e) {
            throw e;
        } finally {
            urlConn.disconnect();
            urlConn = null;
        }
    }
}
