package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Ratings;

import java.util.List;

public interface IRatingsDao {

    List<Ratings> selectTopNRatings(Integer n);

    Ratings selectRatingsByMovieId(String movieId);
}
