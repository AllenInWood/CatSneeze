package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.MovieVo;

import java.util.List;

public interface IBrowsingMovieService {
    ServerResponse<List<MovieVo>> listMoviesByGenre(String genre, String base, String ascOrDes, int limit, int offset);

    ServerResponse<List<MovieVo>> listMoviesByTitle(String headLetter, String base, String ascOrDes, int limit, int offset);
}
