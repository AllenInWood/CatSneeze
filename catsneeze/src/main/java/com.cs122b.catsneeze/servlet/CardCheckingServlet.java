package com.cs122b.catsneeze.servlet;

import com.cs122b.catsneeze.common.Const;
import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Creditcards;
import com.cs122b.catsneeze.service.ICardService;
import com.cs122b.catsneeze.service.ISalesService;
import com.cs122b.catsneeze.service.Impl.CardServiceImpl;
import com.cs122b.catsneeze.service.Impl.SalesServiceImpl;
import com.cs122b.catsneeze.vo.CustomerVo;
import com.cs122b.catsneeze.vo.ShoppingItemVo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CardCheckingServlet extends HttpServlet {
    private ICardService iCardService = new CardServiceImpl();

    private ISalesService iSalesService = new SalesServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String expiration = request.getParameter("expiration");

        String msg = "Fail";
        int status = 0;

        HttpSession session = request.getSession(false);
        if (session == null) {
            String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            CustomerVo customerVo = (CustomerVo) session.getAttribute(Const.CURRENT_CUSTOMER);
            if (customerVo == null) {
                String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {

                if (cardId == null || firstName == null || lastName == null || expiration == null ||
                        cardId == "" || firstName == "" || lastName == "" || expiration == "") {

                    msg = "Card Info not complete";
                    String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NOT_COMPLETE.getCode(), ResponseCode.NOT_COMPLETE.getDesc()));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } else {
                    ServerResponse<Creditcards> serverResponse = iCardService.selectCard(cardId);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String recordCardId = serverResponse.getData().getId();
                    String recordFirstName = serverResponse.getData().getFirstName();
                    String recordLastName = serverResponse.getData().getLastName();
                    Date expire = serverResponse.getData().getExpiration();
                    String recordExpiration = expire == null ? "" : formatter.format(expire);


                    recordCardId = (recordCardId == null) ? "" : recordCardId;
                    recordFirstName = (recordFirstName == null) ? "" : recordFirstName;
                    recordLastName = (recordLastName == null) ? "" : recordLastName;
                    recordExpiration = (recordExpiration == null) ? "" : recordExpiration;

                    Map<String, ShoppingItemVo> shoppingCart = (HashMap<String, ShoppingItemVo>) session.getAttribute(Const.SHOPPING_CART);
                    if (recordCardId.equals(cardId) && recordFirstName.equals(firstName)
                            && recordLastName.equals(lastName) && recordExpiration.equals(expiration)) {
                        for (String movieId : shoppingCart.keySet()) {
                            if (!iSalesService.updateSales(movieId, Integer.valueOf(customerVo.getId())).getData()) {
                                msg = "Insert into database falsely";
                            }
                        }
                        shoppingCart.clear();
                        session.setAttribute(Const.SHOPPING_CART, shoppingCart);
                        msg = "Checkout Successfully";
                        status = 1;
                    }

                    String json = new Gson().toJson(ServerResponse.createByErrorCodeMessage(ResponseCode.SUCCESS.getCode(), msg));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            }
        }
    }
}
