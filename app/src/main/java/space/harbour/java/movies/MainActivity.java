package space.harbour.java.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String API = "https://api.myjson.com/bins/nfvfi";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectionHandler(this).execute(API);
    }
}
