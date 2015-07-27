package com.example.jereczem.hasrpg.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jereczem on 03.07.15.
 */
public class HttpConnection {
    private static int len = 10000;
    private static int readTimeOut = 10000; //milisec
    private static int connectTimeOut = 15000;
    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    public static HttpResponse get(String myUrl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);


            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return new HttpResponse(response, contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static HttpResponse post(String myUrl, String urlParameters) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        byte[] postData = urlParameters.getBytes("UTF-8");

        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            // Sending data
            conn.getOutputStream().write(postData);

            int response = conn.getResponseCode();
            is = conn.getInputStream();


            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return new HttpResponse(response, contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    private static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
