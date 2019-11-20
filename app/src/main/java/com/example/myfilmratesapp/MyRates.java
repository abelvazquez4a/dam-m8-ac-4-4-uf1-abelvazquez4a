package com.example.myfilmratesapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfilmratesapp.database.FilmDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MyRates extends AppCompatActivity {

    ScrollView scrollView;
    FilmDao filmDao;
    FilmLab mFilmLab;
    List<Film> listaPeliculas;
    ListView listViewPeliculas;
    static final int ADD_FILM = 1;
    ArrayList<String> nombrePeliculas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rates);
        FloatingActionButton add = (findViewById(R.id.fab));
        listViewPeliculas = (findViewById(R.id.listView_peliculas));
        mFilmLab=FilmLab.get(this);
    }

    private void actualitzaListView(ArrayList<String > array){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, array);
        listViewPeliculas.setAdapter(adapter);
        }

    public void startAddFilm(View view) {
        Intent intentAddFilm = new Intent(this,AddFilm.class);
        startActivityForResult(intentAddFilm, ADD_FILM);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Log.i("pruebaPeli",""+requestCode+" result code "+resultCode);
        if (requestCode == ADD_FILM) {
            // Make sure the request was successful
            if (resultCode == 0) {
                listaPeliculas=mFilmLab.getFilms();
                for (Film film : listaPeliculas){
                    Log.i("pruebaPeli",film.getTitulo());
                    nombrePeliculas.add(film.getTitulo());
                }
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
                actualitzaListView(nombrePeliculas);
            }
        }
    }
}
