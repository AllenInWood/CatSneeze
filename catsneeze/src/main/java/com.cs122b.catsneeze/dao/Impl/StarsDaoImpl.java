package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.pojo.Stars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StarsDaoImpl implements IStarsDao {

    public List<Stars> selectStarsByMovieId(String movieId) {
        List<Stars> starsList = new ArrayList<Stars>();
        Set<String> starIdsSet = new HashSet<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select starId from stars_in_movies where movieId = ?");
            pstmt.setString(1, movieId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                starIdsSet.add(result.getString(1));
            }
            for (String starId : starIdsSet) {
                Stars stars = selectStarById(starId);
                starsList.add(stars);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starsList;
    }

    public Stars selectStarById(String starId) {
        Stars stars = new Stars();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from stars where id = ?");
            pstmt.setString(1, starId);
            ResultSet result = pstmt.executeQuery();

            while(result.next()) {
                stars.setId(result.getString(1));
                stars.setName(result.getString(2));
                stars.setBirthYear(result.getInt(3));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stars;
    }

    public List<Stars> listTopNIdStars(Integer n) {

        List<Stars> starsList = new ArrayList<Stars>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from stars ORDER BY SUBSTRING(id FROM 3 FOR 7) DESC LIMIT ?");
            pstmt.setInt(1, n);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                Stars stars = new Stars();
                stars.setId(result.getString(1));
                stars.setName(result.getString(2));
                stars.setBirthYear(result.getInt(3));
                starsList.add(stars);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starsList;
    }

    public String SelectStarMaxId() {
        String maxId = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select max(id) from stars");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                maxId = result.getString(1);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public int checkStarName(String name) {
        int resultCount = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select count(1) from stars where name = ?");
            pstmt.setString(1, name);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                resultCount = result.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCount;
    }

    public boolean insertStar(String id, String name, int birthYear) {
        boolean resultBoolean = false;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt;
            if (birthYear != -1) {
                pstmt = connection.prepareStatement("INSERT INTO stars (id, name, birthYear) VALUES (?, ?, ?)");
                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, birthYear);
            } else {
                pstmt = connection.prepareStatement("INSERT INTO stars (id, name, birthYear) VALUES (?, ?, NULL)");
                pstmt.setString(1, id);
                pstmt.setString(2, name);
            }
            pstmt.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Stars> listStarsByMatchingTitle(String query) {
        List<Stars> starsList = new ArrayList<Stars>();
        query = "%" + query + "%";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select * from stars where LOWER(name) LIKE \"" + query + "\" LIMIT 5");
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
