package hr.abc.psd2.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.LoggerFactory;

import hr.abc.psd2.util.Psd2Constants;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Psd2AsyncTask {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Psd2AsyncTask.class);

    // fields
    private String url;
    private String requestMethod;
    private String jsonEntity;

    // constructors
    public Psd2AsyncTask(String url, String requestMethod, String jsonEntity) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.jsonEntity = jsonEntity;

    }

    public Psd2AsyncTask(String url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public String doInBackground() throws IOException {
        String result = null;
        try {
            result = httpRequestProcess();
        } catch (IOException e) {
            log.error("Exception is:", e);
            result = Psd2Constants.HTTP_STATUS_CODE_INTERNAL_ERROR_500;
        }
        return result;
    }

    private String httpRequestProcess() throws IOException {
        // init
        InputStream stream = null;
        String result = null;
        try {
            // definiranje URL objekta
            URL url = new URL(this.url);
            // otvaranje HTTP URL konekcije
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod(this.requestMethod);
            urlConnection.setDoInput(true);

            // ako postoji JSON objekt, dodaje se u zahtjev
            if (jsonEntity != null && !jsonEntity.trim().equals("")) {
                urlConnection.setRequestProperty("accept", "application/json");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Connection", "Keep-Alive");

                OutputStream outputStream = null;
                try {
                    urlConnection.setDoOutput(true);
                    outputStream = urlConnection.getOutputStream();
                    outputStream.write(jsonEntity.getBytes("UTF-8"));
                } finally {
                    if (outputStream != null)
                        outputStream.close();
                }
            }

            urlConnection.connect();
            if (Psd2Constants.LIST_HTTP_STATUS_ERROR_CODES.contains("" + urlConnection.getResponseCode())) { // error codes
                result = "" + urlConnection.getResponseCode();
            } else {
                stream = urlConnection.getInputStream();
                result = convertStreamToString(stream);
                stream.close();
            }
            return result;
        } catch (Exception e) {
            log.error("Exception is:", e);
            return Psd2Constants.HTTP_STATUS_CODE_INTERNAL_ERROR_500;
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            log.error("Exception is:", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("Exception is:", e);
            }
        }
        return sb.toString();
    }
}
