package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IGenresDao;
import com.cs122b.catsneeze.dao.Impl.GenresDaoImpl;
import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.service.IGenresService;

import java.util.List;

public class GenresServiceImpl implements IGenresService {

    private IGenresDao iGenresDao = new GenresDaoImpl();

    public ServerResponse<List<Genres>> selectAllGenres() {
        return ServerResponse.createBySuccess(iGenresDao.selectAllGenres());
    }
}
