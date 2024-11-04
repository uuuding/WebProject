package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 8329559983943337176L;

    private Map<String, CartItem> itemMap = new HashMap<>();
    private final List<CartItem> itemList = new ArrayList();
    private BigDecimal subTotal = BigDecimal.ZERO;

    public List<CartItem> getItemList() {
        return itemList;
    }

    public Map<String, CartItem> getItemMap() {
        return itemMap;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public Iterator<CartItem> getCartItems() {
        return this.itemList.iterator();
    }

    public int getNumberOfItems() {
        return this.itemList.size();
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
