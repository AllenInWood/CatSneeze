package com.cs122b.catsneeze.servlet.backstage;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Employees;
import com.cs122b.catsneeze.pojo.Stars;
import com.cs122b.catsneeze.service.IStarService;
import com.cs122b.catsneeze.service.Impl.StarServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StarsListServlet extends HttpServlet {

    private IStarService iStarService = new StarServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = request.getParameter("NumofStars") == null ? 10 : Integer.valueOf(request.getParameter("NumofStars"));
        ServerResponse<List<Stars>> serverResponse = iStarService.listTopNIdStars(num);
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
                String json = new Gson().toJson(serverResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        }
    }
}
