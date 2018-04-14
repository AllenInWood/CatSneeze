package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.service.IMovieService;
import com.cs122b.catsneeze.service.Impl.MovieServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.cs122b.catsneeze.vo.ShoppingItemVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDeleteServlet extends HttpServlet {

    private ShoppingItemVo shoppingItemVo = new ShoppingItemVo();

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
            CustomerVo customerVo = (CustomerVo) session.getAttribute(Const.CURRENT_CUSTOMER);
            if (customerVo == null) {
                String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {
                Map<String, ShoppingItemVo> shoppingCart = new HashMap<String, ShoppingItemVo>();
                shoppingCart = session.getAttribute(Const.SHOPPING_CART) == null ? shoppingCart : (HashMap<String, ShoppingItemVo>) session.getAttribute(Const.SHOPPING_CART);
                String movieId = request.getParameter("movieId");
                int number = Integer.parseInt(request.getParameter("number") == null ? "1" : request.getParameter("number"));
                if (!shoppingCart.isEmpty() && shoppingCart.containsKey(movieId)) {
                    ShoppingItemVo shoppingItemVo = shoppingCart.get(movieId);
                    number = shoppingItemVo.getQuantity() - number;
                    if (number <= 0) {
                        shoppingCart.remove(movieId);
                    } else {
                        shoppingItemVo.setQuantity(number);
                        shoppingCart.put(movieId, shoppingItemVo);
                    }
                }
                session.setAttribute(Const.SHOPPING_CART, shoppingCart);
                String json = new Gson().toJson(ServerResponse.createBySuccessMessage("delete item successfully"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        }
    }
}
