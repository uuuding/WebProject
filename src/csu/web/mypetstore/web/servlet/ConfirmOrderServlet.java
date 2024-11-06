package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConfirmOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER_FORM = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/signon.jsp";

    private Account account;
    private String userName;
    private Cart cart;

    private String creditCard;
    private String expiryDate;
    private String cardType;

    private String billToFirstName;
    private String billToLastName;
    private String billAddress1;
    private String billAddress2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;


    private String shipToFirstName;
    private String shipToLastName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        this.cardType = request.getParameter("cardType");
        this.creditCard = request.getParameter("creditCard");

        this.expiryDate = request.getParameter("expiryDate");
        this.billToFirstName = request.getParameter("firstName");
        this.billToLastName = request.getParameter("lastName");
        this.billAddress1 = request.getParameter("address1");
        this.billAddress2 = request.getParameter("address2");
        this.billCity = request.getParameter("city");
        this.billState = request.getParameter("state");
        this.billZip = request.getParameter("zip");
        this.billCountry = request.getParameter("country");

        this.shipToFirstName = request.getParameter("shipToFirstName");
        this.shipToLastName = request.getParameter("shipToLastName");
        this.shipAddress1 = request.getParameter("shipAddress1");
        this.shipAddress2 = request.getParameter("shipAddress2");
        this.shipCity = request.getParameter("shipCity");
        this.shipState = request.getParameter("shipState");
        this.shipZip = request.getParameter("shipZip");
        this.shipCountry = request.getParameter("shipCountry");

        // 获得购物车
        cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        // 获得账户
        account = (Account) session.getAttribute("loginAccount");
        if(account == null){
            // 跳转到登录界面
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }else {
            Order order = (Order) session.getAttribute("order");

            order.setCardType(request.getParameter("cardType"));
            order.setCreditCard(request.getParameter("creditCard"));
            order.setExpiryDate(request.getParameter("expiryDate"));

            order.setBillToFirstName(request.getParameter("firstName"));
            order.setBillToLastName(request.getParameter("lastName"));
            order.setBillAddress1(request.getParameter("address1"));
            order.setBillAddress2(request.getParameter("address2"));
            order.setBillCity(request.getParameter("city"));
            order.setBillState(request.getParameter("state"));
            order.setBillZip(request.getParameter("zip"));
            order.setBillCountry(request.getParameter("country"));

            order.setShipToFirstName(request.getParameter("shipToFirstName"));
            order.setShipToLastName(request.getParameter("shipToLastName"));
            order.setShipAddress1(request.getParameter("shipAddress1"));
            order.setShipAddress2(request.getParameter("shipAddress2"));
            order.setShipCity(request.getParameter("shipCity"));
            order.setShipState(request.getParameter("shipState"));
            order.setShipZip(request.getParameter("shipZip"));
            order.setShipCountry(request.getParameter("shipCountry"));

            session.setAttribute("creditCardTypes",order.getCardType());
            session.setAttribute("order",order);

            // 跳转到 订单 页面
            request.getRequestDispatcher(VIEW_ORDER_FORM).forward(request,response);
        }

    }
}