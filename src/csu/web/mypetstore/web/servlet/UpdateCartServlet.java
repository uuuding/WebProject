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
        // 遍历购物车中的商品并更新数量
        Iterator<CartItem> cartItems = cart.getCartItems();

        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                // 获取请求中每个商品的数量参数
                String quantityString = req.getParameter(itemId);

                if (quantityString != null) {
                    int quantity = Integer.parseInt(quantityString);

                    // 更新数量或移除商品
                    cartItem.setQuantity(quantity);
                    cartService.updateItemQuantity(username, cart, itemId, quantity);
                    if (quantity < 1) {
                        cartItems.remove();
                        cartService.removeCartItem(username, cart, itemId);
                    }
                }
            } catch (NumberFormatException e) {
                // 捕获异常并忽略无效数量
                System.err.println("Invalid quantity for item " + itemId + ": " + e.getMessage());
            }
        }

        // 页面转发
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
