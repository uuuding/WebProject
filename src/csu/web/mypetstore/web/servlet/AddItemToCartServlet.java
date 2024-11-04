package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private static final String VIEW_CART = "cartForm";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //获取用户名
        Account account = (Account) session.getAttribute("loginAccount");
        String username = null;
        if(account != null) {
            username = account.getUsername();
        }

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        CartService cartService = new CartService();
        CatalogService catalogService = new CatalogService();
        // 检查商品是否已经在购物车中
        if (cart.getItemMap().containsKey(workingItemId)) {
            CartItem cartItem = cart.getItemMap().get(workingItemId);
            cartService.updateItemQuantity(username, cart, workingItemId, cartItem.getQuantity() + 1);
        } else {
            Item item = catalogService.getItem(workingItemId);
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            cartService.addItemToCart(username, cart, item, isInStock);
        }


        session.setAttribute("cart", cart);
        // 避免重复提交表单
        resp.sendRedirect(VIEW_CART);
    }
}
