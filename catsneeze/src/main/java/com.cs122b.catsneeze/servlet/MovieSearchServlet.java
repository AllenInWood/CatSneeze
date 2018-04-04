package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IMovieSearchingService;
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

public class MovieSearchServlet extends HttpServlet {

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
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(ServerResponse.createBySuccessMessage("OK")));

                String title, year, director, star, base, ascOrDes;
                int limit, offset;
                title = request.getParameter("title") == null ? "" : request.getParameter("title");
                year = request.getParameter("year") == null ? "" : request.getParameter("year");
                director = request.getParameter("director") == null ? "" : request.getParameter("director");
                star = request.getParameter("star") == null ? "" : request.getParameter("star");
                base = request.getParameter("base") == null ? "title" : request.getParameter("base");
                ascOrDes = request.getParameter("ascOrDes") == null ? "" : request.getParameter("ascOrDes");
                limit = Integer.valueOf(request.getParameter("limit") == null ? "20" : request.getParameter("limit"));
                offset = Integer.valueOf(request.getParameter("offset") == null ? "0" : request.getParameter("offset"));
                ServerResponse<List<MovieVo>> serverResponse = iMovieSearchingService.listMovieSearchingResults(title, year, director, star, base, ascOrDes, limit, offset);
                request.setAttribute(Const.MOVIE_LIST, serverResponse.getData());
                request.setAttribute("title", title);
                request.setAttribute("year", year);
                request.setAttribute("director", director);
                request.setAttribute("star", star);
                request.setAttribute("base", base);
                request.setAttribute("ascOrDes", ascOrDes);
                request.setAttribute("limit", limit + "");
                request.setAttribute("offset", offset + "");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/moviesSearchResultList.jsp");
                // request.getContextPath() + "/moviesResultList.jsp"
                dispatcher.forward(request, response);
            }
        }
    }
}
