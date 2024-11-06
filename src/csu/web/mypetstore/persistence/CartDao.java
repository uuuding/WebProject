package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.CartItem;

import java.util.List;

public interface CartDao {
    void insertCartItem(String username, CartItem cartItem);
    void updateCartItem(String username, CartItem cartItem);
    void deleteCartItem(String username, String itemId);
    List<CartItem> getCartItemsByUserId(String username);

    void clearCart(String username);
}
