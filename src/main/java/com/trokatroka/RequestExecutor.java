package com.trokatroka;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class RequestExecutor implements Executor{

    private static String URL = "http://localhost:8082/auth";
    private static HttpClient client;

    public RequestExecutor() {
        client = HttpClient.newHttpClient();
    }

    @Override
    public boolean run(String value) {

        try {
            URL url = new URL(URL);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            byte[] out = ("{\"username\":\"root\",\"password\":\""+ value +"\"}") .getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

             return (http.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return false;
    }
}
