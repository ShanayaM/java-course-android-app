package space.harbour.java.movies;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MovieAsyncTask extends AsyncTask {
    private final String movieUrl = "https://api.myjson.com/bins/nfvfi";


    @Override
    protected Object doInBackground(Object[] objects) {
        HttpsURLConnection connection = null;
        BufferedReader in = null;

        try {
            URL url = new URL(movieUrl);
            connection = (HttpsURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();

            String line;

            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
