package com.marvel.dev.marvelappcomics;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Object, Object, String>() {
            @Override
            protected String doInBackground(Object... objects) {
                return Marvel.getInstance().test();
            }
            @Override
            protected void onPostExecute(String s) {
                ((TextView) findViewById(R.id.temp)).setText(s);
            }
        }.execute();
    }
}
