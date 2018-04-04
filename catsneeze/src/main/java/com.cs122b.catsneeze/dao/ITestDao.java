package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Stars;

import java.util.List;

public interface ITestDao {

    List<Stars> selectStarsByMovieId(String movieId);
}
