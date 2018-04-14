package com.cs122b.catsneeze.dao;

import com.cs122b.catsneeze.vo.MetadataVo;

import java.util.List;

public interface IMetaDao {

    List<MetadataVo> selectMetadataByDBName(String DBName);
}
