package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("order");
        session.setAttribute("lineItems", order.getLineItems());

        OrderService orderService = new OrderService();
        orderService.insertOrder(order);

        // 重置购物车
        CartService cartService = new CartService();
        Account user = (Account) session.getAttribute("loginAccount");
        String username = user.getUsername();
        Cart cart = (Cart) session.getAttribute("cart"); // 获取Cart对象
        List<CartItem> cartItems = cart.getItemList(); // 获取Cart中的itemList

        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                cartService.updateItemByItemIdAndPay(username, cartItem.getItem().getItemId(), true);
            }
        }

        session.removeAttribute("cart");

        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}