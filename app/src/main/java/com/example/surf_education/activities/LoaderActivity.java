package com.example.surf_education.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.surf_education.R;

public class LoaderActivity extends AppCompatActivity {

    private static final int DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(LoaderActivity.this, AuthorizationActivity.class));
                LoaderActivity.this.finish();
            }
        }, DELAY); //specify the number of milliseconds
    }
}
