package com.cs122b.catsneeze.servlet;


import com.cs122b.catsneeze.service.ITestService;
import com.cs122b.catsneeze.service.Impl.TestServiceImpl;
import com.google.gson.Gson;

import java.io.IOException;

public class TestServlet extends javax.servlet.http.HttpServlet {

    private ITestService iTestService = new TestServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //根据movieId查找这个movie有关的stars的信息
        String json = new Gson().toJson(iTestService.listStarInfosByMovieId("tt0094859"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
