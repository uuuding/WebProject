package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class UpdateCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // 获取用户名
        CartService cartService = new CartService();
        Account account = (Account) session.getAttribute("loginAccount");
        String username = (account != null) ? account.getUsername() : null;

        if (cart == null) {
            cart = new Cart();
        }

        // 遍历购物车中的所有商品并更新数量
        for (String itemId : cart.getItemMap().keySet()) {

            String quantityStr = req.getParameter(itemId);
            int quantity = Integer.parseInt(quantityStr);

            if (quantity > 0) {
                cartService.updateItemQuantity(username, cart, itemId, quantity);
            } else {
                cartService.removeCartItem(username, cart, itemId);
            }
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
            return;
        }
        // 更新 session 中的 cart
        session.setAttribute("cart", cart);

        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
