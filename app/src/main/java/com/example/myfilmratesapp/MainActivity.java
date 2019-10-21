package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startProfile(View view) {
        Intent intentProfile = new Intent(this,Profile.class);
        startActivity(intentProfile);
    }

    public void startMyRates(View view) {
        Intent intentMyRates = new Intent (this,MyRates.class);
        startActivity(intentMyRates);
    }
}
