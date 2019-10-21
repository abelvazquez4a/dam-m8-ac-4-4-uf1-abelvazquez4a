package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void startWeb(View view) {
        String url = "https://www.rottentomatoes.com/";
        Intent intentStartWeb = new Intent(Intent.ACTION_VIEW);
        intentStartWeb.setData(Uri.parse(url));
        startActivity(intentStartWeb);
    }
}
