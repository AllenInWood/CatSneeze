package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Stars;

import java.util.List;

public interface IStarsDao {

    List<Stars> selectStarsByMovieId(String movieId);

    Stars selectStarById(String starId);

    List<Stars> listTopNIdStars(Integer n);

    String SelectStarMaxId();

    int checkStarName(String name);

    boolean insertStar(String id, String name, int birthYear);

    List<Stars> listStarsByMatchingTitle(String query);
}
