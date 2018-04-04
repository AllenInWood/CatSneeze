package com.cs122b.catsneeze.dao;

import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;

import java.util.List;

public interface IFuzzySearchDao {
    List<Movies> listMoviesByFuzzySearch(String query, int limit, int offset);
    List<Stars> listStarsByFuzzySearch(String query, int limit, int offset);
}
