package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    private Account account;
    private String userName;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 获取购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 检查用户是否登录
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if (loginAccount == null) {
            resp.sendRedirect("signonForm");
            return;
        }

        // 获取选中的商品 ID
        String selectedItemsParam = req.getParameter("selectedItems");
        if (selectedItemsParam == null || selectedItemsParam.isEmpty()) {
            resp.sendRedirect("cartView.jsp?error=NoItemsSelected");
            return;
        }

        String[] selectedItemIds = selectedItemsParam.split(",");
        List<CartItem> selectedItems = new ArrayList<>();

        // 创建一个新的购物车
        Cart newCart = new Cart();

        // 根据选中的 ID 筛选商品，并添加到新的购物车
        BigDecimal newSubTotal = BigDecimal.ZERO;
        for (String itemId : selectedItemIds) {
            CartItem cartItem = cart.getItemMap().get(itemId);
            if (cartItem != null) {
                selectedItems.add(cartItem);
                newCart.getItemList().add(cartItem); // 添加商品到新的购物车
                newSubTotal = newSubTotal.add(cartItem.getTotal()); // 计算新的小计
            }
        }

        // 更新新的购物车小计
        newCart.setSubTotal(newSubTotal);

        // 设置新的购物车到 session 中
        session.setAttribute("selectedCart", newCart);


        Order order = new Order();

        // 生成订单
        order.initOrder(loginAccount, newCart);

        session.setAttribute("creditCardTypes", order.getCardType());
        session.setAttribute("order", order);

        req.getRequestDispatcher(NEW_ORDER_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
//package csu.web.mypetstore.web.servlet;
//
//import csu.web.mypetstore.domain.Account;
//import csu.web.mypetstore.domain.Cart;
//import csu.web.mypetstore.domain.Order;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class NewOrderFormServlet extends HttpServlet{
//
//    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
//
//    private Account account;
//    private String userName;
//    private Cart cart;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        // 获得购物车
//        cart = (Cart) session.getAttribute("cart");
//        if (cart == null){
//            cart = new Cart();
//            session.setAttribute("cart",cart);
//        }
//
//        Account loginAccount = (Account) session.getAttribute("loginAccount");
//        if(loginAccount == null){
//            resp.sendRedirect("signonForm");
//        }else{
//            Order order = new Order();
//
//            // 生成订单
//            order.initOrder(loginAccount, (Cart) cart);
//
//            session.setAttribute("creditCardTypes",order.getCardType());
//            session.setAttribute("order",order);
//
//            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req, resp);
//    }
//}
