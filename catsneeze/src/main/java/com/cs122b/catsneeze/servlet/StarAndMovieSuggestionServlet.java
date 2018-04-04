package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IStarAndMovieSuggestionService;
import com.cs122b.catsneeze.service.Impl.StarAndMovieSuggestionServiceImpl;
import com.cs122b.catsneeze.vo.SuggestionVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StarAndMovieSuggestionServlet extends HttpServlet {

    private IStarAndMovieSuggestionService iStarAndMovieSuggestionService = new StarAndMovieSuggestionServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        ServerResponse<List<SuggestionVo>> serverResponse = iStarAndMovieSuggestionService.getSuggestionsFromStarAndMovie(query);
        String json = new Gson().toJson(serverResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
