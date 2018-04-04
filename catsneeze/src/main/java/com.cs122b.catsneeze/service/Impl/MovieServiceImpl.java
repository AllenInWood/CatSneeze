package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IGenresDao;
import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.dao.IRatingsDao;
import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.dao.Impl.GenresDaoImpl;
import com.cs122b.catsneeze.dao.Impl.MoviesDaoImpl;
import com.cs122b.catsneeze.dao.Impl.RatingsDaoImpl;
import com.cs122b.catsneeze.dao.Impl.StarsDaoImpl;
import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Ratings;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IMovieService;
import com.cs122b.catsneeze.vo.MovieVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieServiceImpl implements IMovieService {

    private IRatingsDao iRatingsDao = new RatingsDaoImpl();

    private IMoviesDao iMoviesDao = new MoviesDaoImpl();

    private IGenresDao iGenresDao = new GenresDaoImpl();

    private IStarsDao iStarsDao = new StarsDaoImpl();

    public ServerResponse<List<MovieVo>> listTopRatingNMovie(Integer n) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        List<Ratings> ratingsList = iRatingsDao.selectTopNRatings(n);
        for (Ratings ratings : ratingsList) {
            MovieVo movieVo = new MovieVo();
            Movies movies = iMoviesDao.selectMovieById(ratings.getMovieId());
            List<Genres> genresList = iGenresDao.selectGenresByMovieId(ratings.getMovieId());
            List<Stars> starsList = iStarsDao.selectStarsByMovieId(ratings.getMovieId());

            //assemble
            movieAssemble(movieVo, movies, genresList, starsList, ratings);
            movieVoList.add(movieVo);
        }
        //sort movie list
        Collections.sort(movieVoList, new Comparator<MovieVo>() {
            public int compare(MovieVo o1, MovieVo o2) {
                float flag = o1.getRatings().getRating() - o2.getRatings().getRating();
                if (flag > 0) {
                    return -1;
                } else if (flag == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return ServerResponse.createBySuccess(movieVoList);
    }

    public ServerResponse<List<MovieVo>> listTopIdNMovie(Integer n) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        List<Movies> moviesList = iMoviesDao.listTopNIdMoviesInfo(n);
        for (Movies movies : moviesList) {
            MovieVo movieVo = new MovieVo();
            Ratings ratings = iRatingsDao.selectRatingsByMovieId(movies.getId());
            List<Genres> genresList = iGenresDao.selectGenresByMovieId(movies.getId());
            List<Stars> starsList = iStarsDao.selectStarsByMovieId(movies.getId());

            //assemble
            movieAssemble(movieVo, movies, genresList, starsList, ratings);
            movieVoList.add(movieVo);
        }
        return ServerResponse.createBySuccess(movieVoList);
    }

    public ServerResponse<MovieVo> listProfileOfMovie(String movieId) {
        MovieVo movieVo = new MovieVo();
        Movies movies = iMoviesDao.selectMovieById(movieId);
        List<Genres> genresList = iGenresDao.selectGenresByMovieId(movieId);
        List<Stars> starsList = iStarsDao.selectStarsByMovieId(movieId);
        movieAssemble(movieVo, movies, genresList, starsList, null);
        return ServerResponse.createBySuccess(movieVo);
    }

    private void movieAssemble(MovieVo movieVo, Movies movies, List<Genres> genresList, List<Stars> starsList, Ratings ratings) {
        movieVo.setId(movies.getId());
        movieVo.setTitle(movies.getTitle());
        movieVo.setYear(movies.getYear());
        movieVo.setDirector(movies.getDirector());
        movieVo.setGenresList(genresList);
        movieVo.setStarsList(starsList);
        movieVo.setRatings(ratings);
    }

    public ServerResponse<String> InsertMovieWithGenreAndStar(int flag, String title, int year, String director, String genreName, String starName, int birthYear) {
        String maxStarId = iStarsDao.SelectStarMaxId();
        int nStarId = Integer.valueOf(maxStarId.substring(2)) + 1;
        String nextStarId = "nm" + nStarId;
        String maxMovieId = iMoviesDao.SelectMovieMaxId();
        int nMovieId = Integer.valueOf(maxMovieId.substring(2)) + 1;
        String nextMovieId = "tt0" + nMovieId;

        System.out.println("nextStarId:" + nextStarId + " nextMovieId:" + nextMovieId);
        System.out.println("title:" + title + " year:" + year + " director:" + director + " genreName:" + genreName + " starName:" + starName + " birthYear:" + birthYear);
        String result = iMoviesDao.addMovieByProcedure(flag, nextMovieId, title, year, director, genreName, nextStarId, starName, birthYear);
        return ServerResponse.createBySuccessMessage(result);
    }

    public ServerResponse<String> InsertExistMovieInfo(int flag, String title, String genreName, String starName, int birthYear) {
        String maxStarId = iStarsDao.SelectStarMaxId();
        int nStarId = Integer.valueOf(maxStarId.substring(2)) + 1;
        String nextStarId = "nm" + nStarId;
        String curMovieId = iMoviesDao.selectMovieIdByTitle(title);

        System.out.println("nextStarId:" + nextStarId + " curMovieId:" + curMovieId);
        System.out.println("title:" + title + " genreName:" + genreName + " starName:" + starName + " birthYear:" + birthYear);
        String result = iMoviesDao.addMovieByProcedure(flag, curMovieId, title, -1, "", genreName, nextStarId, starName, birthYear);
        return ServerResponse.createBySuccessMessage(result);
    }
}
