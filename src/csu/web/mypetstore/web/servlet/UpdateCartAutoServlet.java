package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.*;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;

public class UpdateCartAutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // 获取用户名
        Account account = (Account) session.getAttribute("loginAccount");
        String username = (account != null) ? account.getUsername() : null;

        if (cart == null) {
            cart = new Cart();
        }

        // 获取请求中的商品ID和数量
        String itemId = req.getParameter("itemId");
        String quantityString = req.getParameter("quantity");

        if (itemId == null || quantityString == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item ID and quantity are required.");
            return;
        }


        int quantity = Integer.parseInt(quantityString);
        CatalogService catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);

        if (item == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found.");
            return;
        }

        // 查找购物车中的商品并更新数量
        CartItem cartItem = null;
        Iterator<CartItem> cartItems = cart.getCartItems();
        while (cartItems.hasNext()) {
            CartItem tempCartItem = cartItems.next();
            if (tempCartItem.getItem().getItemId().equals(itemId)) {
                cartItem = tempCartItem;
                break;
            }
        }

        if (cartItem != null) {
            // 更新商品数量
            cartItem.setQuantity(quantity);

            // 如果数量小于 1，则从购物车中移除该商品
            if (quantity < 1) {
                cartItems.remove();
                CartService cartService = new CartService();
                cartService.removeCartItem(username, cart, itemId);
            } else {
                // 更新商品数量
                CartService cartService = new CartService();
                cartService.updateItemQuantity(username, cart, itemId, quantity);
            }
        }

        // 计算更新后的总价和小计
        BigDecimal totalPrice = item.getListPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal subTotal = cart.getSubTotal();

        // 返回更新后的数据
        ItemDTO response = new ItemDTO(totalPrice, subTotal);
        String result = JSON.toJSONString(response);
        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.println(result);

    }

}
