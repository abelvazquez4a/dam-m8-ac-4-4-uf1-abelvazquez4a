package com.example.myfilmratesapp;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class Profile extends AppCompatActivity {
    SharedPreferences prefs;
    Spinner spinner;
    TextInputLayout username, email, name, surname, biography;
    CheckBox newsletter;
    boolean suscrito;

    String nameS, surnameS, emailS, usernameS, biographyS, genreS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        prefs = getSharedPreferences("MisPreferencias",this.MODE_PRIVATE);
        username = (findViewById(R.id.textInputLayout_userName));
        email = (findViewById(R.id.textInputLayout_email));
        name = (findViewById(R.id.textInputLayout_name));
        surname = (findViewById(R.id.textInputLayout_surname));
        biography = (findViewById(R.id.textInputLayout_biography));
        newsletter = (findViewById(R.id.checkBox));
        spinner();

    }
    @Override
    protected void onPause(){
        super.onPause();
        guardarPreferencias();
        Log.d("prueba","guardando preferencias en onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        guardarPreferencias();
        Log.d("prueba","guardando preferencias en onStop");
    }

    public void spinner(){
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void startWeb(View view) {
        String url = "https://www.rottentomatoes.com/";
        Intent intentStartWeb = new Intent(Intent.ACTION_VIEW);
        intentStartWeb.setData(Uri.parse(url));
        startActivity(intentStartWeb);
    }

    public void guardarPreferencias  (){
        genreS = spinner.getSelectedItem().toString();
        nameS = name.getEditText().getText().toString();
        surnameS = surname.getEditText().getText().toString();
        emailS = email.getEditText().getText().toString();
        usernameS = username.getEditText().getText().toString();
        biographyS = biography.getEditText().getText().toString();
        if (newsletter.isChecked())suscrito=true;
        else suscrito=false;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name",nameS);
        editor.putString("surname",surnameS);
        editor.putString("email",emailS);
        editor.putString("username",usernameS);
        editor.putString("biography",biographyS);
        editor.putBoolean("subscribed",suscrito);
        editor.putString("genre",genreS);
        editor.commit();
    }
}
