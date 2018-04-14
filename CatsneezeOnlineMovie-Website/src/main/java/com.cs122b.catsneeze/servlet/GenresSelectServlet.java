package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.service.IGenresService;
import com.cs122b.catsneeze.service.Impl.GenresServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GenresSelectServlet extends HttpServlet {
    private IGenresService iGenresService = new GenresServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServerResponse<List<Genres>> serverResponse = iGenresService.selectAllGenres();
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
