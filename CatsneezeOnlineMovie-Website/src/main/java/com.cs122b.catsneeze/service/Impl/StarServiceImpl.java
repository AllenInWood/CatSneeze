package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IMoviesDao;
import com.cs122b.catsneeze.dao.IStarsDao;
import com.cs122b.catsneeze.dao.Impl.MoviesDaoImpl;
import com.cs122b.catsneeze.dao.Impl.StarsDaoImpl;
import com.cs122b.catsneeze.pojo.Movies;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IStarService;
import com.cs122b.catsneeze.vo.StarVo;

import java.util.ArrayList;
import java.util.List;

public class StarServiceImpl implements IStarService {

    private IMoviesDao iMoviesDao = new MoviesDaoImpl();

    private IStarsDao iStarsDao = new StarsDaoImpl();

    public ServerResponse<StarVo> listProfileOfStar(String starId) {
        StarVo starVo = new StarVo();
        List<Movies> moviesList = iMoviesDao.selectMoviesByStarId(starId);
        Stars stars = iStarsDao.selectStarById(starId);
        starVo.setId(stars.getId());
        starVo.setName(stars.getName());
        starVo.setBirthYear(stars.getBirthYear());
        starVo.setMoviesList(moviesList);
        return ServerResponse.createBySuccess(starVo);
    }

    public ServerResponse<List<Stars>> listTopNIdStars(Integer n) {
        return ServerResponse.createBySuccess(iStarsDao.listTopNIdStars(n));
    }

    public ServerResponse addStar(String name, int birthYear) {
        int resultValue = iStarsDao.checkStarName(name);
        if (resultValue > 0) {
            return ServerResponse.createByErrorMessage("name exists");
        } else {
            String maxStarId = iStarsDao.SelectStarMaxId();
            int nid = Integer.valueOf(maxStarId.substring(2)) + 1;
            String newId = "nm" + nid;
            System.out.println("newId:" + newId);
            System.out.println("newId:" + newId + " name:" + name + " birthYear:" + birthYear);

            iStarsDao.insertStar(newId, name, birthYear);
            return ServerResponse.createBySuccessMessage("insert new star successfully");
        }
    }
}