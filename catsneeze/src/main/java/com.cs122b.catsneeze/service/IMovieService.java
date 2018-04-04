package com.cs122b.catsneeze.service;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.MovieVo;

import java.util.List;

public interface IMovieService {
    ServerResponse<List<MovieVo>> listTopRatingNMovie(Integer n);

    ServerResponse<List<MovieVo>> listTopIdNMovie(Integer n);

    ServerResponse<MovieVo> listProfileOfMovie(String movieId);

    ServerResponse<String> InsertMovieWithGenreAndStar(int flag, String title, int year, String director, String genreName, String starName, int birthYear);

    ServerResponse<String> InsertExistMovieInfo(int flag, String title, String genreName, String starName, int birthYear);
}
