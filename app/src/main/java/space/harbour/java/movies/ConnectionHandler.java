package space.harbour.java.movies;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;

public class ConnectionHandler extends AsyncTask<String, Void, Void> {
    MainActivity movieList;
    List<Movie> movies;

    public ConnectionHandler(MainActivity movieList) {
        this.movieList = movieList;
        movies = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(String... strings) {
        Log.i("Connector", "Started downloading");
        try  {
            URL url = new URL(strings[0]);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            String result = sb.toString();
            javax.json.JsonReader jsonReader = Json.createReader(new StringReader(result));
            JsonArray jsonArray = jsonReader.readArray();
            for (int i = 0; i < jsonArray.size(); ++i) {
                Movie elem = new Movie();
                elem.fromJson(jsonArray.getJsonObject(i).toString());
                movies.add(elem);
            }
        } catch (MalformedURLException e) {
            Log.e("doInBackground", "URL invalid");
        } catch (IOException e) {
            Log.e("doInBackground", "IO Error");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MovieListAdapter listAdapter = new MovieListAdapter(movies);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(movieList);
        RecyclerView list = movieList.findViewById(R.id.recyclerView);
        list.setAdapter(listAdapter);
        list.setLayoutManager(manager);
    }
}
