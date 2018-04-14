package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IMovieService;
import com.cs122b.catsneeze.service.Impl.MovieServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.cs122b.catsneeze.vo.MovieVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    private IMovieService iMovieService = new MovieServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = Integer.valueOf(request.getParameter("NumofMovie"));

        ServerResponse<List<MovieVo>> serverResponse = iMovieService.listTopRatingNMovie(num);
        HttpSession session = request.getSession(false);
        if (session == null) {
            serverResponse.setStatus(ResponseCode.NEED_LOGIN.getCode());
            serverResponse.setMsg(ResponseCode.NEED_LOGIN.getDesc());
        } else {
            CustomerVo customerVo = (CustomerVo) session.getAttribute(Const.CURRENT_CUSTOMER);
            if (customerVo == null) {
                serverResponse.setStatus(ResponseCode.NEED_LOGIN.getCode());
                serverResponse.setMsg(ResponseCode.NEED_LOGIN.getDesc());
            }
        }
        String json = new Gson().toJson(serverResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
