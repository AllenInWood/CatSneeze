package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IGenresDao;
import com.cs122b.catsneeze.dao.IFuzzySearchDao;
import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.dao.Impl.GenresDaoImpl;
import com.cs122b.catsneeze.dao.Impl.MoviesDaoImpl;
import com.cs122b.catsneeze.dao.Impl.StarsDaoImpl;
import com.cs122b.catsneeze.dao.Impl.FuzzySearchDaoImpl;
import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IMovieSearchingService;
import com.cs122b.catsneeze.vo.MovieVo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MovieSearchingServiceImpl implements IMovieSearchingService {
    private IMoviesDao iMoviesDao = new MoviesDaoImpl();
    private IGenresDao iGenresDao = new GenresDaoImpl();
    private IStarsDao iStarsDao = new StarsDaoImpl();
    private IFuzzySearchDao iFuzzySearchDao = new FuzzySearchDaoImpl();

    public ServerResponse<List<MovieVo>> listMovieSearchingResults(String title, String year, String director, String star, String base, String ascOrDes, int limit, int offset) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        List<Movies> moviesList = iMoviesDao.searchingMovies(title, year, director, star, base, ascOrDes, limit, offset);
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

    public ServerResponse<List<MovieVo>> listMovieFulltextSearchingResults(String query, int limit, int offset) {
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();

        // TJ calculation
        // the average time spent on the parts that use JDBC, per query
        long startTime = System.nanoTime();

        List<Movies> moviesList = iMoviesDao.listMoviesByFullTextIndexing(query, limit, offset);
        if (moviesList.isEmpty()) {
            moviesList = iFuzzySearchDao.listMoviesByFuzzySearch(query.trim().toLowerCase(), limit, offset);
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        //can obtain current path, write the elapsedTime into a txt file in com.cs122b.catsneeze
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("./").getPath() + "../");
        //write the elapsedTime into the log file
        System.out.println("!!!!!!!!!!!InnerelapsedTime is : " + elapsedTime/1000000.0 + "!!!!!!!!!!!!!!!");

        FileWriter fw = null;
        try {
            File f=new File(Thread.currentThread().getContextClassLoader().getResource("./").getPath() + "../timeLog.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.print(elapsedTime);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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