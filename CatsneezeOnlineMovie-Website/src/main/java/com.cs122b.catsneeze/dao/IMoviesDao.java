package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Movies;

import java.util.List;

public interface IMoviesDao {

    List<Movies> listTopNMoviesInfo(Integer n);

    List<Movies> listTopNIdMoviesInfo(Integer n);

    Movies selectMovieById(String movieId);

    List<Movies> selectMoviesByStarId(String starId);

    List<Movies> searchingMovies(String title, String year, String director, String star, String base, String ascOrDes, int limit, int offset);

    List<Movies> browsingMoviesByGenres(String genres, String base, String ascOrDes, int limit, int offset);

    List<Movies> browsingMoviesByTitle(String headLetter, String base, String ascOrDes, int limit, int offset);

    String SelectMovieMaxId();

    String addMovieByProcedure(int flag, String movieId, String title, int year, String director, String genreName, String starId, String starName, int starBirthYear);

    String selectMovieIdByTitle(String title);

    List<Movies> listMoviesByMatchingTitle(String query);

    List<Movies> listMoviesByFullTextIndexing(String query, int limit, int offset);

}
