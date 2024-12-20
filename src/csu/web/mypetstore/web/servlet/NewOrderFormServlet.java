package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet{

    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    private Account account;
    private String userName;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 获得购物车
        cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if(loginAccount == null){
            resp.sendRedirect("signonForm");
        }else{
            Order order = new Order();

            // 生成订单
            order.initOrder(loginAccount, (Cart) cart);

            session.setAttribute("creditCardTypes",order.getCardType());
            session.setAttribute("order",order);

            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
        }

    }
}
