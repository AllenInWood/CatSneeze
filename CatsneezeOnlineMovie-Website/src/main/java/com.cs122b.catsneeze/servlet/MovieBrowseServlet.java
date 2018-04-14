package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IBrowsingMovieService;
import com.cs122b.catsneeze.service.Impl.BrowsingMovieServiceImpl;
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

public class MovieBrowseServlet extends HttpServlet {

    private IBrowsingMovieService iBrowsingMovieService = new BrowsingMovieServiceImpl();

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
            CustomerVo customerVo = (CustomerVo) session.getAttribute(Const.CURRENT_CUSTOMER);
            if (customerVo == null) {
                String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {
                ServerResponse<List<MovieVo>> serverResponse;
                String genreBase = request.getParameter("genreBase");
                String base = request.getParameter("base") == null ? "title" : request.getParameter("base");
                String ascOrDes = request.getParameter("ascOrDes") == null ? "" : request.getParameter("ascOrDes");
                int limit = Integer.parseInt(request.getParameter("limit") == null ? "20" : request.getParameter("limit"));
                int offset = Integer.parseInt(request.getParameter("offset") == null ? "0" : request.getParameter("offset"));

                if (genreBase.equals("genre")) {
                    String genre = request.getParameter("genre");
                    serverResponse = iBrowsingMovieService.listMoviesByGenre(genre, base, ascOrDes, limit, offset);
                    request.setAttribute("genre", genre);
                } else {
                    String headLetter = request.getParameter("headLetter");
                    serverResponse = iBrowsingMovieService.listMoviesByTitle(headLetter, base, ascOrDes, limit, offset);
                    request.setAttribute("headLetter", headLetter);
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                request.setAttribute(Const.MOVIE_LIST, serverResponse.getData());
                request.setAttribute("genreBase", genreBase);
                request.setAttribute("base", base);
                request.setAttribute("ascOrDes", ascOrDes);
                request.setAttribute("offset", offset + "");
                request.setAttribute("limit", limit + "");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/moviesResultList.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
