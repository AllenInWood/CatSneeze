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
        String[] queryArray = query.split(" ");

        // Combine the commands that will be used
        List<String> fuzzyCmd = new ArrayList<String>();
        List<String> fullTextCmd = new ArrayList<String>();
        String baseCmd = "SELECT * FROM movies WHERE ";
        fuzzyCmd.add("edrec(\"" + queryArray[0] + "\", lower(title), " + String.valueOf(maxWrongWord)+ ") = 1 ");
        for (int i = 1; i < querryArray.length(); ++ i) {
            BigDecimal maxWrongWord = new BigDecimal(String.valueOf(queryArray[i].length() * 0.3)).setScale(0, BigDecimal.ROUND_HALF_UP);
            fuzzyCmd.add("AND edrec(\"" + queryArray[i] + "\", lower(title), " + String.valueOf(maxWrongWord)+ ") = 1 ");
            fullTextCmd.add("AND MATCH (title) AGAINST ('+" + queryArray[i] + "*' IN BOOLEAN MODE) ");
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            for (int i = 0; i < queryArray.length(); ++ i) {
                String cmd = baseCmd;
                for (int j = 0; j <= i; ++ j) {
                    cmd += fuzzyCmd.get(j);
                }
                for (int j = i + 1; j < queryArray.length(); ++ j) {
                    cmd += fullTextCmd.get(j);
                }

                PreparedStatement pstmt = connection.prepareStatement(cmd);
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
                if (moviesList.length() >= 5) {
                    break;
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public List<Stars> listStarsByFuzzySearch(String query, int limit, int offset) {
        List<Stars> starsList = new ArrayList<Stars>();
        String[] queryArray = query.split(" ");

        // Combine the commands that will be used
        List<String> fuzzyCmd = new ArrayList<String>();
        List<String> fullTextCmd = new ArrayList<String>();
        String baseCmd = "SELECT * FROM stars WHERE ";
        fuzzyCmd.add("edrec(\"" + queryArray[0] + "\", lower(name), " + String.valueOf(maxWrongWord)+ ") = 1 ");
        for (int i = 1; i < querryArray.length(); ++ i) {
            BigDecimal maxWrongWord = new BigDecimal(String.valueOf(queryArray[i].length() * 0.3)).setScale(0, BigDecimal.ROUND_HALF_UP);
            fuzzyCmd.add("AND edrec(\"" + queryArray[i] + "\", lower(name), " + String.valueOf(maxWrongWord)+ ") = 1 ");
            fullTextCmd.add("AND MATCH (name) AGAINST ('+" + queryArray[i] + "*' IN BOOLEAN MODE) ");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            for (int i = 0; i < queryArray.length(); ++ i) {
                String cmd = baseCmd;
                for (int j = 0; j <= i; ++ j) {
                    cmd += fuzzyCmd.get(j);
                }
                for (int j = i + 1; j < queryArray.length(); ++ j) {
                    cmd += fullTextCmd.get(j);
                }

                PreparedStatement pstmt = connection.prepareStatement(cmd);
                ResultSet result = pstmt.executeQuery();

                while (result.next()) {
                    Stars stars = new Stars();
                    stars.setId(result.getString(1));
                    stars.setName(result.getString(2));
                    stars.setBirthYear(result.getInt(3));
                    starsList.add(stars);
                }
                pstmt.close();
                if (starsList.length() >= 5) {
                    break;
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starsList;
    }

}
