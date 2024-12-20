package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListOrderServlet extends HttpServlet {

    private static final String LIST_ORDERS_FORM = "/WEB-INF/jsp/order/listOrders.jsp";

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        // 检查用户是否已登录
        if (account != null) {
            // 获取用户订单列表
            List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
            request.setAttribute("orderList", orderList);
            // 转发请求到订单列表页面
            request.getRequestDispatcher(LIST_ORDERS_FORM).forward(request, response);
        } else {
            // 如果用户未登录，重定向到登录页面
            response.sendRedirect("signOnForm");
        }
    }
}
