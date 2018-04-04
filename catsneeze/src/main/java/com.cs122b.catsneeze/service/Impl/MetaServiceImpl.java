package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.IMetaDao;
import com.cs122b.catsneeze.dao.Impl.MetaDaoImpl;
import com.cs122b.catsneeze.service.IMetaService;
import com.cs122b.catsneeze.vo.MetadataVo;

import java.util.ArrayList;
import java.util.List;

public class MetaServiceImpl implements IMetaService{

    private IMetaDao iMetaDao = new MetaDaoImpl();

    public ServerResponse<List<List<MetadataVo>>> getMetadataByDBName() {
        List<List<MetadataVo>> result = new ArrayList<List<MetadataVo>>();

        List<MetadataVo> creditcardsList = iMetaDao.selectMetadataByDBName("creditcards");
        result.add(creditcardsList);
        List<MetadataVo> customersList = iMetaDao.selectMetadataByDBName("customers");
        result.add(customersList);
        List<MetadataVo> employeesList = iMetaDao.selectMetadataByDBName("employees");
        result.add(employeesList);
        List<MetadataVo> genresList = iMetaDao.selectMetadataByDBName("genres");
        result.add(genresList);
        List<MetadataVo> genresInMoviesList = iMetaDao.selectMetadataByDBName("genres_in_movies");
        result.add(genresInMoviesList);
        List<MetadataVo> moviesList = iMetaDao.selectMetadataByDBName("movies");
        result.add(moviesList);
        List<MetadataVo> ratingsList = iMetaDao.selectMetadataByDBName("ratings");
        result.add(ratingsList);
        List<MetadataVo> salesList = iMetaDao.selectMetadataByDBName("sales");
        result.add(salesList);
        List<MetadataVo> starsList = iMetaDao.selectMetadataByDBName("stars");
        result.add(starsList);
        List<MetadataVo> starsInMoviesList = iMetaDao.selectMetadataByDBName("stars_in_movies");
        result.add(starsInMoviesList);
        return ServerResponse.createBySuccess(result);
    }
}
