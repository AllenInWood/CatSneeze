package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.pojo.Movies;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MoviesDaoImpl implements IMoviesDao {

    public List<Movies> listTopNMoviesInfo(Integer n) {

        List<Movies> moviesList = new ArrayList<Movies>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from movies limit ?");
            pstmt.setInt(1, n);
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

    public List<Movies> listTopNIdMoviesInfo(Integer n) {

        List<Movies> moviesList = new ArrayList<Movies>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from movies order by id desc LIMIT ?");
            pstmt.setInt(1, n);
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

    public Movies selectMovieById(String movieId) {
        Movies movies = new Movies();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select * from movies WHERE id = ?");
            pstmt.setString(1, movieId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                movies.setId(result.getString(1));
                movies.setTitle(result.getString(2));
                movies.setYear(result.getInt(3));
                movies.setDirector(result.getString(4));
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }


    public List<Movies> selectMoviesByStarId(String starId) {
        List<Movies> moviesList = new ArrayList<Movies>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select DISTINCT movieId from stars_in_movies WHERE starId = ?");
            pstmt.setString(1, starId);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                String movieId = result.getString(1);
                Movies movies = selectMovieById(movieId);
                moviesList.add(movies);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public List<Movies> searchingMovies(String title, String year, String director, String star, String base, String ascOrDes, int limit, int offset) {
        List<Movies> moviesList = new ArrayList<Movies>();
        base = base + " " + ascOrDes;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            String cmd = "Select distinct m.id, m.title, m.year, m.director from movies m, stars_in_movies sm, stars s" +
                    " WHERE m.id = sm.movieId AND sm.starId = s.id AND m.title LIKE \"%" + title + "%\" AND m.year LIKE \"%" + year + "%\" AND m.director LIKE \"%" + director + "%\" AND s.name LIKE \"%" + star + "%\"" +
                    " ORDER BY " + base + " LIMIT " + limit + " OFFSET " + offset;
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
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    public List<Movies> browsingMoviesByGenres(String genres, String base, String ascOrDes, int limit, int offset) {
        List<Movies> moviesList = new ArrayList<Movies>();
        Set<String> movieIds = new HashSet<String>();
        base = base + " " + ascOrDes;
        if (!ascOrDes.equals("desc")) {
            ascOrDes = "";
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("SELECT m.id, m.title, m.year, m.director FROM movies m, genres_in_movies gm, genres g " +
                    "WHERE g.id = gm.genreId AND m.id = gm.movieId AND g.name = \"" + genres + "\" ORDER BY " + base + " LIMIT " + limit + " OFFSET " + offset);

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

    public List<Movies> browsingMoviesByTitle(String headLetter, String base, String ascOrDes, int limit, int offset) {
        List<Movies> moviesList = new ArrayList<Movies>();
        headLetter = headLetter + "%";
        base = base + " " + ascOrDes;
        if (!ascOrDes.equals("desc")) {
            ascOrDes = "";
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("SELECT * from movies WHERE title LIKE \"" + headLetter + "\" ORDER BY " + base + " LIMIT " + limit + " OFFSET " + offset);

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

    public String SelectMovieMaxId() {
        String maxId = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select max(id) from movies");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                maxId = result.getString(1);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public String addMovieByProcedure(int flag, String movieId, String title, int year, String director, String genreName, String starId, String starName, int starBirthYear) {
        String result = "";
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            //todo: movie id bug
            if (starBirthYear == -1) {
                System.out.println("null call stored procedure");
                //CallableStatement cstmt = connection.prepareCall("call add_movie(\"" + movieId + "\", \"" + title + "\", " + year + ", \"" + director + "\", \"" + genreName + "\", \"" + starId + "\", \"" + starName + "\", NULL, ?)");
                CallableStatement cstmt = connection.prepareCall("call add_movie(?, ?, ?, ?, ?, ?, ?, ?, NULL, ?)");
                cstmt.setInt(1, flag);
                cstmt.setString(2, movieId);
                cstmt.setString(3, title);
                cstmt.setInt(4, year);
                cstmt.setString(5, director);
                cstmt.setString(6, genreName);
                cstmt.setString(7, starId);
                cstmt.setString(8, starName);
                cstmt.registerOutParameter(9, Types.VARCHAR);
                cstmt.execute();
                result = cstmt.getString(9);
                cstmt.close();
            } else {
                System.out.println("here call stored procedure");
                //CallableStatement cstmt = connection.prepareCall("call add_movie(\"" + movieId + "\", \"" + title + "\", " + year + ", \"" + director + "\", \"" + genreName + "\", \"" + starId + "\", \"" + starName + "\", " + starBirthYear + ", ?)");
                CallableStatement cstmt = connection.prepareCall("call add_movie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                cstmt.setInt(1, flag);
                cstmt.setString(2, movieId);
                cstmt.setString(3, title);
                cstmt.setInt(4, year);
                cstmt.setString(5, director);
                cstmt.setString(6, genreName);
                cstmt.setString(7, starId);
                cstmt.setString(8, starName);
                cstmt.setInt(9, starBirthYear);
                cstmt.registerOutParameter(10, Types.VARCHAR);
                cstmt.execute();
                result = cstmt.getString(10);
                cstmt.close();
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String selectMovieIdByTitle(String title) {
        String movieId = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select id from movies WHERE title = \"" + title + "\"");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                movieId = result.getString(1);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("movieId:" + movieId);
        return movieId;
    }

    public List<Movies> listMoviesByMatchingTitle(String query) {
        List<Movies> moviesList = new ArrayList<Movies>();
        query = "%" + query + "%";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select * from movies where LOWER(title) LIKE \"" + query + "\" LIMIT 5");
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

    public List<Movies> listMoviesByFullTextIndexing(String query, int limit, int offset) {
        List<Movies> moviesList = new ArrayList<Movies>();
        String[] queryArray = query.split(" ");
        List<String> queryStrings = new ArrayList<String>();
        for (String s : queryArray) {
            queryStrings.add("+" + s.trim() + "*");
        }
        StringBuilder queryString = new StringBuilder();
        for (int i = 0; i < queryStrings.size(); i++) {
            if (i != 0) {
                queryString.append(" ");
            }
            queryString.append(queryStrings.get(i));
        }

        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
//            Statement pstmt = connection.createStatement();
//            ResultSet result = pstmt.executeQuery("SELECT * FROM movies WHERE MATCH (title) AGAINST (\"" + queryString.toString() + "\" IN BOOLEAN MODE) LIMIT " + limit + " OFFSET " + offset);
//            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM movies WHERE MATCH (title) AGAINST (\"" + queryString.toString() + "\" IN BOOLEAN MODE) LIMIT " + limit + " OFFSET " + offset);
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM movies WHERE MATCH (title) AGAINST (? IN BOOLEAN MODE) LIMIT ? OFFSET ?");
            pstmt.setString(1, queryString.toString());
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
//            System.out.println("SELECT * FROM movies WHERE MATCH (title) AGAINST (\"" + queryString.toString() + "\" IN BOOLEAN MODE) LIMIT " + limit + " OFFSET " + offset);
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
}
