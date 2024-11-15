package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartItemServlet extends HttpServlet {

    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // 如果会话中没有购物车对象，创建新的购物车并设置到会话中
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        CartService cartService = new CartService();
        Account account = (Account) session.getAttribute("loginAccount");
        String workingItemId = req.getParameter("workingItemId");

        // 检查用户是否已登录
        if (account != null) {
            // 已登录情况下使用 CartService 处理
            String username = account.getUsername();
            Item item = cartService.removeCartItem(username, cart, workingItemId);
            if (item == null) {
                session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
                req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
                return;
            }
        } else {
            // 未登录情况下手动从购物车中删除项目
            CartItem cartItem = cart.getItemMap().remove(workingItemId);
            if (cartItem != null) {
                cart.getItemList().remove(cartItem);
            } else {
                session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
                req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
                return;
            }
        }

        // 更新会话中的购物车对象，并转发到购物车页面
        session.setAttribute("cart", cart);
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
