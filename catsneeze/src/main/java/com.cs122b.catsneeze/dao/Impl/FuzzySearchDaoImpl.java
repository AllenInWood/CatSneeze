package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IFuzzySearchDao;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class FuzzySearchDaoImpl implements IFuzzySearchDao {
    public List<Movies> listMoviesByFuzzySearch(String query, int limit, int offset) {
        List<Movies> moviesList = new ArrayList<Movies>();
        BigDecimal maxWrongWord = new BigDecimal(String.valueOf(query.length() * 0.3)).setScale(0, BigDecimal.ROUND_HALF_UP);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM movies WHERE edrec(\"" + query + "\", lower(title), " + String.valueOf(maxWrongWord)+ ") = 1 LIMIT " + String.valueOf(limit) + " OFFSET " + String.valueOf(offset));
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Movies movies = new Movies();
                movies.setId(result.getString(1));
                movies.setTitle(result.getString(2));
                movies.setYear(result.getInt(3));
                movies.setDirector(result.getString(4));
                moviesList.add(movies);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public List<Stars> listStarsByFuzzySearch(String query, int limit, int offset) {
        List<Stars> starsList = new ArrayList<Stars>();
        BigDecimal maxWrongWord = new BigDecimal(String.valueOf(query.length() * 0.3)).setScale(0, BigDecimal.ROUND_HALF_UP);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM stars WHERE edrec(\"" + query + "\", lower(name), " + String.valueOf(maxWrongWord)+ ") = 1 LIMIT " + String.valueOf(limit) + " OFFSET " + String.valueOf(offset));
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Stars stars = new Stars();
                stars.setId(result.getString(1));
                stars.setName(result.getString(2));
                stars.setBirthYear(result.getInt(3));
                starsList.add(stars);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starsList;
    }

}
