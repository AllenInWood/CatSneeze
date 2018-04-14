package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Genres;

import java.util.List;

public interface IGenresDao {

    List<Genres> selectGenresByMovieId(String movieId);

    Genres selectGenreById(int genreId);

    List<Genres> selectAllGenres();
}
