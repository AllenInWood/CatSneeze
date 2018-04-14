package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IMovieSearchingService;
import com.cs122b.catsneeze.service.IMovieService;
import com.cs122b.catsneeze.service.Impl.MovieSearchingServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.cs122b.catsneeze.vo.MovieVo;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FullTextSearchServlet extends HttpServlet {
    private IMovieSearchingService iMovieSearchingService = new MovieSearchingServiceImpl();

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
            response.sendRedirect("./page/login.jsp");
        } else {
            CustomerVo customerVo = (CustomerVo) session.getAttribute(Const.CURRENT_CUSTOMER);
            if (customerVo == null) {
                String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                response.sendRedirect("./page/login.jsp");
            } else {


                String query, mobile;
                int limit, offset;
                query = request.getParameter("query") == null ? "" : request.getParameter("query");
                limit = Integer.valueOf(request.getParameter("limit") == null ? "20" : request.getParameter("limit"));
                offset = Integer.valueOf(request.getParameter("offset") == null ? "0" : request.getParameter("offset"));
                mobile = request.getParameter("mobile") == null ? "" : request.getParameter("mobile");
                ServerResponse<List<MovieVo>> serverResponse = iMovieSearchingService.listMovieFulltextSearchingResults(query, limit, offset);
                if ("mobile".equals(mobile)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(new Gson().toJson(serverResponse));
                } else {
                    request.setAttribute(Const.MOVIE_LIST, serverResponse.getData());
                    request.setAttribute("query", query);
                    request.setAttribute("limit", limit + "");
                    request.setAttribute("offset", offset + "");

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(new Gson().toJson(ServerResponse.createBySuccessMessage("OK")));
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/moviesFulltextSearchResultList.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
