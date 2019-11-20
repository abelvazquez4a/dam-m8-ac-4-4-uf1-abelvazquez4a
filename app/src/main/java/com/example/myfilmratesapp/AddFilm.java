package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddFilm extends AppCompatActivity {
    TextInputLayout titulo,year,director;
    RatingBar ratingBar;
    private FilmLab mFilmLab;
    private Film mFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
        titulo=(findViewById(R.id.TextInputLayout_titulo));
        year=(findViewById(R.id.TextInputLayout_year));
        director=(findViewById(R.id.TextInputLayout_director));
        ratingBar=(findViewById(R.id.ratingBar));

        mFilmLab = FilmLab.get(this);
        List<Film> films = mFilmLab.getFilms();
        if(films.size() > 0) {
            mFilm = films.get(0);
            titulo.getEditText().setText(mFilm.getTitulo());
            year.getEditText().setText(mFilm.getYear());
            director.getEditText().setText(mFilm.getDirector());
            ratingBar.setNumStars(mFilm.getRate());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        guardar();
    }

    /**
     * Borra la nota si existe (si mNota no es null).
     */
    private void borrar() {
        if(mFilm != null) {
            mFilmLab.deleteFilm(mFilm);
            mFilm = null;
            titulo.getEditText().setText("");
            year.getEditText().setText("");
            director.getEditText().setText("");
            ratingBar.setNumStars(0);
            Toast.makeText(this, getString(R.string.deleted_film),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.film_not_found),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Guarda la nota si no existe (mNota es null), o la actualiza si existe.
     */
    private void guardar() {
        String sTitulo,sYear,sDirector;
        int iRating;
        sTitulo=titulo.getEditText().getText().toString();
        sYear=year.getEditText().getText().toString();
        sDirector=director.getEditText().getText().toString();
        iRating=ratingBar.getNumStars();
        if (!sTitulo.equals("")) {
            if (mFilm == null) {
                mFilm = new Film();
                mFilm.setTitulo(sTitulo);
                mFilm.setDirector(sDirector);
                mFilm.setYear(sYear);
                mFilm.setRate(iRating);
                mFilmLab.addFilm(mFilm);
                Toast.makeText(this, getString(R.string.created_film),
                        Toast.LENGTH_SHORT).show();
            } else {
                mFilm.setTitulo(sTitulo);
                mFilm.setDirector(sDirector);
                mFilm.setYear(sYear);
                mFilm.setRate(iRating);
                mFilmLab.updateFilm(mFilm);
                Toast.makeText(this, getString(R.string.updated_film),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.create_film_first),
                    Toast.LENGTH_SHORT).show();
        }
      }
    }
