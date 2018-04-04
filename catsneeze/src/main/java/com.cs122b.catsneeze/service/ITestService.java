package com.cs122b.catsneeze.service;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.TestVo;

import java.util.List;

public interface ITestService {

    ServerResponse<List<TestVo>> listStarInfosByMovieId(String movieId);
}
