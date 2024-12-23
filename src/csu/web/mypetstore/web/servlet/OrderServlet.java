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
import java.math.BigDecimal;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 获取当前订单对象
        Order order = (Order) session.getAttribute("order");
        session.setAttribute("lineItems", order.getLineItems());

        // 将订单保存到数据库
        OrderService orderService = new OrderService();
        orderService.insertOrder(order);

        // 获取购物车和选中的商品
        Cart cart = (Cart) session.getAttribute("cart");
        Cart selectedCart = (Cart) session.getAttribute("selectedCart"); // 修改这里

        if (selectedCart != null) {
            List<CartItem> selectedItems = selectedCart.getItemList(); // 获取选中的商品

            // 获取 CartService 实例
            CartService cartService = new CartService();
            Account account = (Account) session.getAttribute("loginAccount");

            if (account != null) {
                // 遍历已选商品并从购物车中移除
                for (CartItem selectedItem : selectedItems) {
                    // 从数据库中删除商品
                    cartService.removeCartItem(account.getUsername(), cart, selectedItem.getItem().getItemId());
                }
            }

            // 从购物车中移除已购买的商品
            for (CartItem selectedItem : selectedItems) {
                cart.getItemMap().remove(selectedItem.getItem().getItemId());
                cart.getItemList().remove(selectedItem);
            }

            // 重新计算购物车的小计
            BigDecimal subTotal = BigDecimal.ZERO;
            for (CartItem cartItem : cart.getItemMap().values()) {
                subTotal = subTotal.add(cartItem.getTotal());
            }
            cart.setSubTotal(subTotal);
        }

        // 清空已购买的商品记录
        session.removeAttribute("selectedCart");

        // 更新 session 中的购物车
        session.setAttribute("cart", cart);

        // 转发到查看订单页面
        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}

//package csu.web.mypetstore.web.servlet;
//
//import csu.web.mypetstore.domain.Account;
//import csu.web.mypetstore.domain.Cart;
//import csu.web.mypetstore.domain.CartItem;
//import csu.web.mypetstore.domain.Order;
//import csu.web.mypetstore.service.CartService;
//import csu.web.mypetstore.service.OrderService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//public class OrderServlet extends HttpServlet {
//    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//
//        Order order = (Order) session.getAttribute("order");
//        session.setAttribute("lineItems", order.getLineItems());
//
//        OrderService orderService = new OrderService();
//        orderService.insertOrder(order);
//
//        // 重置购物车
//        Cart cart = new Cart();
//        session.setAttribute("cart", cart);
//        CartService cartService = new CartService();
//        Account account = (Account) session.getAttribute("loginAccount");
//        cartService.clearCart(account.getUsername());
//
//        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
//    }
//}