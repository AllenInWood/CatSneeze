package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IRatingsDao;
import com.cs122b.catsneeze.pojo.Ratings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingsDaoImpl implements IRatingsDao {

    public List<Ratings> selectTopNRatings(Integer n) {
        List<Ratings> ratingsList = new ArrayList<Ratings>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("Select distinct * from ratings order by rating desc LIMIT " + n);

            while (result.next()) {
                Ratings ratings = new Ratings();
                ratings.setMovieId(result.getString(1));
                ratings.setRating(result.getFloat(2));
                ratings.setNumVotes(result.getInt(3));
                ratingsList.add(ratings);
            }
            select.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratingsList;
    }

    public Ratings selectRatingsByMovieId(String movieId) {
        Ratings ratings = new Ratings();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select * from ratings where movieId = ? LIMIT 1");
            pstmt.setString(1, movieId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                ratings.setMovieId(result.getString(1));
                ratings.setRating(result.getFloat(2));
                ratings.setNumVotes(result.getInt(3));
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratings;
    }
}
