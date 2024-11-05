package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

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
        CatalogService catalogService = new CatalogService();
        CartService cartService = new CartService();
        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();

        String workingItemId = req.getParameter("workingItemId");
        Item item = cartService.removeCartItem(username,cart,workingItemId);
        if (item == null) {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        } else {
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
