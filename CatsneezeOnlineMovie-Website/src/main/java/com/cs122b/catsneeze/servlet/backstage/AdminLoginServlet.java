package com.cs122b.catsneeze.servlet.backstage;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Employees;
import com.cs122b.catsneeze.service.IEmployeesService;
import com.cs122b.catsneeze.service.Impl.EmployeesServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginServlet extends HttpServlet {

    private IEmployeesService iEmployeesService = new EmployeesServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServerResponse<Employees> serverResponse = iEmployeesService.login(request.getParameter("email"), request.getParameter("password"));
//        System.out.println("status:" + serverResponse.getStatus() + " msg:" + serverResponse.getMsg());
        if (serverResponse.isSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute(Const.CURRENT_EMPLOYEE, serverResponse.getData());
        }
//        System.out.println(serverResponse.getStatus() + " " + serverResponse.getMsg() + " " + serverResponse.getData().toString());
        String json = new Gson().toJson(serverResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
