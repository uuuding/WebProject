package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CartService {
    private CartDao cartDao = new CartDaoImpl();

    public Cart getCartByUserId(String username) {
        Cart cart = new Cart();
        List<CartItem> cartItems = cartDao.getCartItemsByUserId(username);

        for (CartItem item : cartItems) {
            cart.getItemMap().put(item.getItem().getItemId(), item);
            cart.getItemList().add(item);
        }

        updateCartSubTotal(cart);
        return cart;
    }

    public void addItemToCart(String username, Cart cart, Item item, boolean isInStock) {
        String itemId = item.getItemId();
        Map<String, CartItem> itemMap = cart.getItemMap();
        List<CartItem> itemList = cart.getItemList();

        if (itemMap.containsKey(itemId)) {
            CartItem existingCartItem = itemMap.get(itemId);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartDao.updateCartItem(username, existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(1);
            cartItem.setInStock(isInStock);
            itemMap.put(itemId, cartItem);
            itemList.add(cartItem);
            cartDao.insertCartItem(username, cartItem);
        }

        updateCartSubTotal(cart);
    }

    public Item removeCartItem(String username, Cart cart, String itemId) {
        Map<String, CartItem> itemMap = cart.getItemMap();
        List<CartItem> itemList = cart.getItemList();
        CartItem removedItem = itemMap.remove(itemId);

        if (removedItem != null) {
            itemList.remove(removedItem);
            cartDao.deleteCartItem(username, itemId);
            updateCartSubTotal(cart);
            return removedItem.getItem();
        }
        else{
            return null;
        }
    }

    public void updateItemQuantity(String username, Cart cart, String itemId, int quantity) {
        CartItem cartItem = cart.getItemMap().get(itemId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartDao.updateCartItem(username, cartItem);
            updateCartSubTotal(cart);
        }
    }

    private void updateCartSubTotal(Cart cart) {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getItemMap().values()) {
            subTotal = subTotal.add(cartItem.getTotal());
        }
        cart.setSubTotal(subTotal);
    }

    public void clearCart(String username){
        cartDao.clearCart(username);
    }
}
