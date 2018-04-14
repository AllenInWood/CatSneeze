package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IFuzzySearchDao;
import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.dao.Impl.MoviesDaoImpl;
import com.cs122b.catsneeze.dao.Impl.StarsDaoImpl;
import com.cs122b.catsneeze.dao.Impl.FuzzySearchDaoImpl;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IStarAndMovieSuggestionService;
import com.cs122b.catsneeze.vo.SuggestionMeta;
import com.cs122b.catsneeze.vo.SuggestionVo;

import java.util.ArrayList;
import java.util.List;

public class StarAndMovieSuggestionServiceImpl implements IStarAndMovieSuggestionService {

    private IStarsDao iStarsDao = new StarsDaoImpl();

    private IMoviesDao iMoviesDao = new MoviesDaoImpl();

    private IFuzzySearchDao iFuzzySearchDao = new FuzzySearchDaoImpl();

    public ServerResponse<List<SuggestionVo>> getSuggestionsFromStarAndMovie(String query) {
        List<SuggestionVo> suggestionVoList = new ArrayList<SuggestionVo>();
        if (query == null || query.trim().equals("")) {
            return ServerResponse.createBySuccess(suggestionVoList);
        }
        List<Stars> starsList = iStarsDao.listStarsByMatchingTitle(query.trim().toLowerCase());
        List<Movies> moviesList = iMoviesDao.listMoviesByMatchingTitle(query.trim().toLowerCase());

        if (moviesList.isEmpty()) {
            moviesList = iFuzzySearchDao.listMoviesByFuzzySearch(query.trim().toLowerCase(), 5, 0);
        }
        for (Movies movies : moviesList) {
            SuggestionMeta suggestionMeta = new SuggestionMeta("movie", movies.getId());
            SuggestionVo suggestionVo = new SuggestionVo(suggestionMeta, movies.getTitle());
            suggestionVoList.add(suggestionVo);
        }

        if (starsList.isEmpty()) {
            starsList = iFuzzySearchDao.listStarsByFuzzySearch(query.trim().toLowerCase(), 5, 0);
        }
        for (Stars stars : starsList) {
            SuggestionMeta suggestionMeta = new SuggestionMeta("star", stars.getId());
            SuggestionVo suggestionVo = new SuggestionVo(suggestionMeta, stars.getName());
            suggestionVoList.add(suggestionVo);
        }

        return ServerResponse.createBySuccess(suggestionVoList);
    }
}
