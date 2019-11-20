package com.example.myfilmratesapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfilmratesapp.Film;

import java.util.List;

@Dao
public interface FilmDao {
    @Query("SELECT * FROM film")
    List<Film> getFilms();

    @Query("SELECT * FROM film WHERE mId LIKE :uuid")
    Film getFilm(String uuid);

    @Insert
    void addFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Update
    void updateFilm(Film film);
}
