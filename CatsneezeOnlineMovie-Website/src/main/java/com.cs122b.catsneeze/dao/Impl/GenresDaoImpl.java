package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IGenresDao;
import com.cs122b.catsneeze.pojo.Genres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenresDaoImpl implements IGenresDao {

    public List<Genres> selectGenresByMovieId(String movieId) {
        List<Genres> genresList = new ArrayList<Genres>();
        List<Integer> genreIdsList = new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from genres_in_movies where movieId = ?");
            pstmt.setString(1, movieId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                genreIdsList.add(result.getInt(1));
            }

            for (Integer genreId : genreIdsList) {
                Genres genres = selectGenreById(genreId);
                genresList.add(genres);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genresList;
    }

    public Genres selectGenreById(int genreId) {
        Genres genres = new Genres();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from genres where id = ?");
            pstmt.setInt(1, genreId);
            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                genres.setId(result.getInt(1));
                genres.setName(result.getString(2));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }

    public List<Genres> selectAllGenres() {
        List<Genres> genresList = new ArrayList<Genres>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
//            Statement select = connection.createStatement();
//            ResultSet result = select.executeQuery("Select * from genres ORDER by name");

            PreparedStatement pstmt = connection.prepareStatement("Select * from genres ORDER by name");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Genres genres = new Genres();
                genres.setId(result.getInt(1));
                genres.setName(result.getString(2));
                genresList.add(genres);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genresList;
    }
}
