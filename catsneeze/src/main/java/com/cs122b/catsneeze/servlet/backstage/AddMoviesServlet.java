package com.cs122b.catsneeze.servlet.backstage;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Employees;
import com.cs122b.catsneeze.service.IMovieService;
import com.cs122b.catsneeze.service.Impl.MovieServiceImpl;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddMoviesServlet extends HttpServlet {

    private IMovieService iMovieService = new MovieServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            Employees employees = (Employees) session.getAttribute(Const.CURRENT_EMPLOYEE);
            if (employees == null) {
                String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {
                String title = request.getParameter("title");
                int year = Integer.valueOf(request.getParameter("year"));
                String director = request.getParameter("director");
                String genreName = request.getParameter("genreName");
                String starName = request.getParameter("starName");
                int birthYear = request.getParameter("birthYear") == null ? -1 : Integer.valueOf(request.getParameter("birthYear"));

                ServerResponse<String> serverResponse = iMovieService.InsertMovieWithGenreAndStar(1, title, year, director, genreName, starName, birthYear);
                String json = new Gson().toJson(serverResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        }
    }
}