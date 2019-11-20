package com.example.myfilmratesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myfilmratesapp.Film;

@Database(entities = {Film.class}, version = 1)
public abstract class FilmDatabase extends RoomDatabase {
    public abstract FilmDao getNotaDao();
}
