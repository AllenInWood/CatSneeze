package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.ICustomerService;
import com.cs122b.catsneeze.service.Impl.CustomerServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerLoginServlet extends HttpServlet {

    private ICustomerService iCustomerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobile = request.getParameter("mobile");
        if ("mobile".equals(mobile)) {
            ServerResponse<CustomerVo> serverResponse = iCustomerService.mobileLogin(request.getParameter("email"), request.getParameter("password"));
            if (serverResponse.isSuccess()) {
                HttpSession session = request.getSession();
                session.setAttribute(Const.CURRENT_CUSTOMER, serverResponse.getData());
            }
            String json = new Gson().toJson(serverResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            ServerResponse<CustomerVo> serverResponse = iCustomerService.login(request.getParameter("email"), request.getParameter("password"), request.getParameter("captcha"));
            if (serverResponse.isSuccess()) {
                HttpSession session = request.getSession();
                session.setAttribute(Const.CURRENT_CUSTOMER, serverResponse.getData());
            }
            String json = new Gson().toJson(serverResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
