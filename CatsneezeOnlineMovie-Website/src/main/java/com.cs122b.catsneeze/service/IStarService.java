package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.vo.StarVo;

import java.util.List;

public interface IStarService {

    ServerResponse<StarVo> listProfileOfStar(String starId);

    ServerResponse<List<Stars>> listTopNIdStars(Integer n);

    ServerResponse addStar(String name, int birthYear);
}
