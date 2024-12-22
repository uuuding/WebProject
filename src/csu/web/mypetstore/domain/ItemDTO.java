package csu.web.mypetstore.domain;

import java.math.BigDecimal;

public class ItemDTO {

    private BigDecimal totalPrice;
    private BigDecimal subTotal;

    public ItemDTO(BigDecimal totalPrice, BigDecimal subTotal) {
        this.totalPrice = totalPrice;
        this.subTotal = subTotal;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

}
