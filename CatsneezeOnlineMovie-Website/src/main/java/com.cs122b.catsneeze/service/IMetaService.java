package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.MetadataVo;

import java.util.List;

public interface IMetaService {
    ServerResponse<List<List<MetadataVo>>> getMetadataByDBName();
}
