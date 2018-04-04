package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.ITestDao;
import com.cs122b.catsneeze.pojo.Stars;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestDaoImpl implements ITestDao {

    public List<Stars> selectStarsByMovieId(String movieId) {
        //for return
        List<Stars> starNameList = new ArrayList<Stars>();
        //for getting stars id first from 'stars_in_movies' database
        Set<String> starIdsSet = new HashSet<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            //first get star id list
            PreparedStatement pstmt = connection.prepareStatement("Select starId from stars_in_movies where movieId = ?");
            pstmt.setString(1, movieId);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                starIdsSet.add(result.getString(1));
            }

            //for each star id, get star info
            for (String starId : starIdsSet) {
                pstmt = connection.prepareStatement("Select * from stars where id = ?" );
                pstmt.setString(1, starId);
                ResultSet starsResult = pstmt.executeQuery();
                while (starsResult.next()) {
                    Stars stars = new Stars();
                    stars.setId(starsResult.getString(1));
                    stars.setName(starsResult.getString(2));
                    stars.setBirthYear(starsResult.getInt(3));

                    starNameList.add(stars);
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starNameList;
    }
}
