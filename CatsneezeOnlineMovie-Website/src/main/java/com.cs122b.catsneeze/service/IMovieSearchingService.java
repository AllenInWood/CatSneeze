package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.MovieVo;

import java.util.List;

public interface IMovieSearchingService {
    ServerResponse<List<MovieVo>> listMovieSearchingResults(String title, String year, String director, String star, String base, String ascOrDes, int limit, int offset);

    ServerResponse<List<MovieVo>> listMovieFulltextSearchingResults(String query, int limit, int offset);
}
