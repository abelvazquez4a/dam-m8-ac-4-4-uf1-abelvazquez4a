package com.example.myfilmratesapp;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.example.myfilmratesapp.database.FilmDao;
import com.example.myfilmratesapp.database.FilmDatabase;

import java.util.List;

public class FilmLab {
    @SuppressLint("StaticFieldLeak")
    private static FilmLab sFilmLab;

    private FilmDao mFilmDao;

    private FilmLab(Context context) {
        Context appContext = context.getApplicationContext();
        FilmDatabase database = Room.databaseBuilder(appContext, FilmDatabase.class, "film")
                .allowMainThreadQueries().build();
        mFilmDao = database.getNotaDao();
    }

    public static FilmLab get(Context context) {
        if (sFilmLab == null) {
            sFilmLab = new FilmLab(context);
        }
        return sFilmLab;
    }

    public List<Film> getFilms() {
        return mFilmDao.getFilms();
    }

    public Film getFilm(String id) {
        return mFilmDao.getFilm(id);
    }

    public void addFilm(Film film) {
        mFilmDao.addFilm(film);
    }

    public void updateFilm(Film film) {
        mFilmDao.updateFilm(film);
    }

    public void deleteFilm(Film film) {
        mFilmDao.deleteFilm(film);
    }
}
