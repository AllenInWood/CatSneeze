package dom;
import dom.Movies;
import dom.Stars;
import dom.DomParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BatchInsert {

    public int starId;

    public int genreId;

    public int movieId; 

    public HashMap<String, String> moviesList;

    public HashMap<String, String> actorsList;

    public HashMap<String, Integer> genresList;

    public HashMap<String, String> oldNewMovieId;

    public DomParser dpe;

    BatchInsert() {
        moviesList = new HashMap<String, String>();
        actorsList = new HashMap<String, String>();
        genresList = new HashMap<String, Integer>();
        oldNewMovieId = new HashMap<String, String>();
        dpe = new DomParser();

        getMoviesList();
        getActorsList();
        getGenresList();
        getMaxStarId();
        getMaxGenreId();
        getMaxMovieId();

        dpe.run();
    }

    private void getMaxStarId() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select max(id) from stars");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                String id = result.getString(1);
                if (id != null) {
                    id = id.substring(2);
                    starId = Integer.parseInt(id) + 1;
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMaxGenreId() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select max(id) from genres");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                genreId = result.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMaxMovieId() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select max(id) from movies");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                String id = result.getString(1);
                if (id != null) {
                    id = id.substring(2);
                    movieId = Integer.parseInt(id) + 1;
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMoviesList() {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select title, id from movies");
            ResultSet result = pstmt.executeQuery();
            moviesList.clear();

            while (result.next()) {
                String title = result.getString(1);
                String id = result.getString(2);
                moviesList.put(title, id);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getMoviesList2() {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select title, id from movies");
            ResultSet result = pstmt.executeQuery();
            moviesList.clear();

            while (result.next()) {
                String title = result.getString(1);
                String id = result.getString(2);
                moviesList.put(id, title);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getActorsList() {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select name, id from stars");
            ResultSet result = pstmt.executeQuery();
            actorsList.clear();

            while (result.next()) {
                String name = result.getString(1);
                String id = result.getString(2);
                actorsList.put(name, id);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getActorsList2() {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select id from stars");
            ResultSet result = pstmt.executeQuery();
            actorsList.clear();

            while (result.next()) {
                String id = result.getString(1);
                actorsList.put(id, "0");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGenresList() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select * from genres");
            ResultSet result = pstmt.executeQuery();
            genresList.clear();

            while (result.next()) {
                Integer id = result.getInt(1);
                String name = result.getString(2);
                genresList.put(name, id);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertGenres() {
        Iterator it = dpe.allGenres.keySet().iterator();
        PreparedStatement psInsertGenres = null;
        String sqlInserGenres = "Insert into genres (name) values (?)";
        int[] iNoRows=null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            psInsertGenres = conn.prepareStatement(sqlInserGenres);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Object key = it.next();
            if (genresList.containsKey(String.valueOf(key))) {
                Integer t = genresList.get(String.valueOf(key));
                dpe.allGenres.put(String.valueOf(key), t);
            }
            else if (!String.valueOf(key).equals("null")) {                
                try {

                    psInsertGenres.setString(1, String.valueOf(key));
                    psInsertGenres.addBatch();

                    dpe.allGenres.put(String.valueOf(key), genreId);
                    genreId ++;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            iNoRows = psInsertGenres.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(psInsertGenres != null) psInsertGenres.close();
            if(conn!=null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete updating the genres table!");
    }

    private void insertStars() {
        Iterator it = dpe.myStars.keySet().iterator();
        PreparedStatement psInsertStars = null;
        String sqlInserStars = "Insert into stars (id, name, birthYear) values (?, ?, ?)";
        int[] iNoRows=null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            psInsertStars = conn.prepareStatement(sqlInserStars);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Object key = it.next();
            Stars star = dpe.myStars.get(String.valueOf(key));
            if (actorsList.containsKey(star.getName())) {
                String id = actorsList.get(star.getName());
                Stars newStar = new Stars();
                newStar = star;
                newStar.setId(id);
                dpe.myStars.put(String.valueOf(key), newStar);
            }
            else {                
                try {
                    Stars newStar = new Stars();
                    newStar = star;
                    String id = "nm" + String.valueOf(starId);
                    newStar.setId(id);
                    starId ++;
                    psInsertStars.setString(1, id);
                    if (star.getName() != null && star.getName() != "") {
                        psInsertStars.setString(2, star.getName());
                    }
                    else {
                        psInsertStars.setString(2, null);
                    }
                    if (star.getBirthYear() != -1) {
                        psInsertStars.setInt(3, star.getBirthYear());
                    }
                    else {
                        psInsertStars.setNull(3, Types.INTEGER);
                    }
                    psInsertStars.addBatch();

                    dpe.myStars.put(String.valueOf(key), newStar);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            iNoRows = psInsertStars.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(psInsertStars != null) psInsertStars.close();
            if(conn!=null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete updating the stars table!");
    }

    public void insertMovies() {
        Iterator it = dpe.myMovies.keySet().iterator();
        PreparedStatement psInsertMovies = null;
        String sqlInsertMovies = "Insert into movies (id, title, year, director) values (?, ?, ?, ?)";
        int[] iNoRows = null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            psInsertMovies = conn.prepareStatement(sqlInsertMovies);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Object key = it.next();
            Movies movie = dpe.myMovies.get(String.valueOf(key));
            if (movie != null && moviesList.containsKey(movie.getTitle())) {
                // Update the movieId into the original one
                // No newly inserted movies
                String id = moviesList.get(movie.getTitle());
                // New id is the key, the original id is the value
                if (movie.getId() != null) {
                    oldNewMovieId.put(movie.getId(), id);
                }
                movie.setId(id);
                dpe.myMovies.put(String.valueOf(key), movie);
            }
            else if (movie != null) {                
                try {
                    // Insert new movies
                    if (movie.getId() != null) {
                        psInsertMovies.setString(1, movie.getId());
                    }
                    else {
                        // Generate a movieId for this movie
                        String id = "tt" + String.valueOf(movieId);
                        movie.setId(id);
                        movieId ++;
                        psInsertMovies.setString(1, movie.getId());
                    }
                    if (movie.getTitle() != null && movie.getTitle() != "") {
                        psInsertMovies.setString(2, movie.getTitle());
                    }
                    else {
                        psInsertMovies.setString(2, "Unknown name");
                    }
                    psInsertMovies.setInt(3, movie.getYear());
                    if (movie.getDirector() != null) {
                        psInsertMovies.setString(4, movie.getDirector());
                    }
                    else {
                        psInsertMovies.setString(4, "None");
                    }
                    psInsertMovies.addBatch();

                    dpe.myMovies.put(String.valueOf(key), movie);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            iNoRows = psInsertMovies.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(psInsertMovies != null) psInsertMovies.close();
            if(conn != null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete updating the movies table!");
    }

    public void insertGenresMovies() {
        Iterator it = dpe.myMovies.keySet().iterator();
        PreparedStatement psInsertMovies = null;
        String sqlInsertMovies = "Insert into genres_in_movies (genreId, movieId) values (?, ?)";
        int[] iNoRows = null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            psInsertMovies = conn.prepareStatement(sqlInsertMovies);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Object key = it.next();
            Movies movie = dpe.myMovies.get(key);
            List<String> genres = new ArrayList<String>();
            if (movie.getGenres() != null) {
                genres = movie.getGenres();
            }
            else {
                continue;
            }
            if (genres != null) {
                for (int i = 0 ; i < genres.size(); ++ i) {
                    int gId = -1;
                    if (genresList.get(genres.get(i)) != null) {
                        gId = genresList.get(genres.get(i));
                    }
                    String mId = movie.getId();
                    try {
                        if (gId != -1 && mId != null) {
                            psInsertMovies.setInt(1, gId);
                            psInsertMovies.setString(2, mId);
                            psInsertMovies.addBatch();
                        }
                        else {
                            if (gId == -1) {
                                System.out.println("The genres_in_movies table requires a non-NULL value of genre Id.");
                            }
                            if (mId == null) {
                                System.out.println("The genres_in_movies table requires a non-NULL value of movie Id.");
                            }
                            continue;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            iNoRows = psInsertMovies.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(psInsertMovies != null) psInsertMovies.close();
            if(conn != null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete updating the genres_in_movies table!");
    }

    public void insertStarsMovies() {
        Iterator it = dpe.myStars.keySet().iterator();
        PreparedStatement psInsertStars = null;
        String sqlInserStars = "Insert into stars_in_movies (starId, movieId) values (?, ?)";
        int[] iNoRows = null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            psInsertStars = conn.prepareStatement(sqlInserStars);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Object key = it.next();
            Stars star = dpe.myStars.get(key);
            List<String> movies = new ArrayList<String>();
            if (star.getMovieId() != null) {
                movies = star.getMovieId();
            }
            else {
                movies = null;
            }
            String sId = star.getId();
            for (int i = 0 ; i < movies.size(); ++ i) {
                String mId = movies.get(i);
                try {
                    
                    if (moviesList.containsKey(mId) && actorsList.containsKey(sId)) {
                        psInsertStars.setString(1, sId);
                        psInsertStars.setString(2, mId);
                        psInsertStars.addBatch();
                    }
                    else {
                        if (!moviesList.containsKey(mId)) {
                            if (oldNewMovieId.containsKey(mId)) {
                                psInsertStars.setString(1, sId);
                                psInsertStars.setString(2, oldNewMovieId.get(mId));
                                psInsertStars.addBatch();
                            }
                            else {
                                System.out.println("The movie Id " + mId + " is not existed in the movies table.");
                            }
                        }
                        if (!actorsList.containsKey(sId)) {
                            System.out.println("The star Id " + sId + " is not existed in the stars table.");
                        }
                        continue;
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            iNoRows = psInsertStars.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(psInsertStars != null) psInsertStars.close();
            if(conn != null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete updating the stars_in_movies table!");
    }   

    public static void main (String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        BatchInsert bi = new BatchInsert();

        bi.insertGenres();
        bi.insertStars();
        bi.insertMovies();
        bi.getMoviesList2();
        bi.getActorsList2();
        bi.getGenresList();
        bi.insertGenresMovies();
        bi.insertStarsMovies();

        System.out.println("Done!");
    }

}


