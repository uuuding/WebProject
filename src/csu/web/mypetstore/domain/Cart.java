package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap());
    private final List<CartItem> itemList = new ArrayList();
    private BigDecimal subTotal = BigDecimal.ZERO;

    public Cart() {
    }

    public Iterator<CartItem> getCartItems() {
        return this.itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return this.itemList;
    }

    public int getNumberOfItems() {
        return this.itemList.size();
    }

    public Iterator<CartItem> getAllCartItems() {
        return this.itemList.iterator();
    }

    public boolean containsItemId(String itemId) {
        return this.itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = (CartItem) this.itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            this.itemMap.put(item.getItemId(), cartItem);
            this.itemList.add(cartItem);
        }

        cartItem.incrementQuantity();
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = (CartItem) this.itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            this.itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) this.itemMap.get(itemId);
        cartItem.incrementQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = (CartItem) this.itemMap.get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");

        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice=item.getListPrice();
            BigDecimal quantity=new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal=subTotal.add(quantity.multiply(listPrice));
        }

        return subTotal;
    }
}
