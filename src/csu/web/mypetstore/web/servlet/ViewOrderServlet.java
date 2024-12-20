package csu.web.mypetstore.web.servlet;


import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderId");

        try {
            int orderId = Integer.parseInt(orderIdParam);
            Order order = orderService.getOrder(orderId);
            HttpSession session = request.getSession();
            session.setAttribute("order", order);
            request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }
}
