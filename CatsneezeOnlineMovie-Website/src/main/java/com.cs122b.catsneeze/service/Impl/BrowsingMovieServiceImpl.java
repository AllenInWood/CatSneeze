package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IGenresDao;
import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.dao.Impl.GenresDaoImpl;
import com.cs122b.catsneeze.dao.Impl.MoviesDaoImpl;
import com.cs122b.catsneeze.dao.Impl.StarsDaoImpl;
import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IBrowsingMovieService;
import com.cs122b.catsneeze.vo.MovieVo;

import java.util.ArrayList;
import java.util.List;

public class BrowsingMovieServiceImpl implements IBrowsingMovieService {

    private IMoviesDao iMoviesDao = new MoviesDaoImpl();

    private IGenresDao iGenresDao = new GenresDaoImpl();

    private IStarsDao iStarsDao = new StarsDaoImpl();

    public ServerResponse<List<MovieVo>> listMoviesByGenre(String genre, String base, String ascOrDes, int limit, int offset) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        List<Movies> moviesList = iMoviesDao.browsingMoviesByGenres(genre, base, ascOrDes, limit, offset);
        for (Movies movies : moviesList) {
            MovieVo movieVo = new MovieVo();
            List<Genres> genresList = iGenresDao.selectGenresByMovieId(movies.getId());
            List<Stars> starsList = iStarsDao.selectStarsByMovieId(movies.getId());

            //assemble
            movieAssemble(movieVo, movies, genresList, starsList);
            movieVoList.add(movieVo);
        }
        return ServerResponse.createBySuccess(movieVoList);
    }

    public ServerResponse<List<MovieVo>> listMoviesByTitle(String headLetter, String base, String ascOrDes, int limit, int offset) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        List<Movies> moviesList = iMoviesDao.browsingMoviesByTitle(headLetter, base, ascOrDes, limit, offset);
        for (Movies movies : moviesList) {
            MovieVo movieVo = new MovieVo();
            List<Genres> genresList = iGenresDao.selectGenresByMovieId(movies.getId());
            List<Stars> starsList = iStarsDao.selectStarsByMovieId(movies.getId());

            //assemble
            movieAssemble(movieVo, movies, genresList, starsList);
            movieVoList.add(movieVo);
        }
        return ServerResponse.createBySuccess(movieVoList);
    }

    private void movieAssemble(MovieVo movieVo, Movies movies, List<Genres> genresList, List<Stars> starsList) {
        movieVo.setId(movies.getId());
        movieVo.setTitle(movies.getTitle());
        movieVo.setYear(movies.getYear());
        movieVo.setDirector(movies.getDirector());
        movieVo.setGenresList(genresList);
        movieVo.setStarsList(starsList);
    }
}