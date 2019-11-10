package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("prueba","onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("prueba","onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("prueba","onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("prueba","onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("prueba","onDestroy");
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
