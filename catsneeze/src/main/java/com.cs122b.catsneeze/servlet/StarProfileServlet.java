package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IStarService;
import com.cs122b.catsneeze.service.Impl.StarServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.cs122b.catsneeze.vo.StarVo;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StarProfileServlet extends HttpServlet {

    private IStarService iStarService = new StarServiceImpl();

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

                String starId = request.getParameter("id");
                ServerResponse<StarVo> serverResponse = iStarService.listProfileOfStar(starId);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                request.setAttribute(Const.CURRENT_STAR, serverResponse.getData());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/starProfile.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
