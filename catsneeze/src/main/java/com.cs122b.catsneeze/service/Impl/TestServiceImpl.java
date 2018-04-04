package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.ITestDao;
import com.cs122b.catsneeze.dao.Impl.TestDaoImpl;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.ITestService;
import com.cs122b.catsneeze.vo.TestVo;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImpl implements ITestService {

    private ITestDao iTestDao = new TestDaoImpl();

    public ServerResponse<List<TestVo>> listStarInfosByMovieId(String movieId) {
        List<TestVo> testVoList = new ArrayList<TestVo>();

        List<Stars> starsList = iTestDao.selectStarsByMovieId(movieId);
        for (Stars stars : starsList) {
            TestVo testVo = new TestVo();
            testVo.setName(stars.getName());
            testVo.setInfo("Id is" + stars.getId() + ", born in " + stars.getBirthYear());
            testVoList.add(testVo);
        }

        return ServerResponse.createBySuccess(testVoList);
    }
}
