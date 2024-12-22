<%@ include file="../common/top.jsp" %>
<link rel="StyleSheet" href="css/cart.css" type="text/css" media="screen"/>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>

        <form action="updateCart" method="post">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox" id="select-all"> Select All</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="cart-items">
                <tr id="empty-cart-message" style="display:none;">
                    <td colspan="6"><b>Your cart is empty.</b></td>
                </tr>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                    <tr id="rank-${cartItem.item.itemId}" class="item-id">
                        <td>
                            <input type="checkbox" class="item-select" data-item-id="${cartItem.item.itemId}">
                        </td>
                        <td class="item-info">
                            <div class="item-details">
                                    ${cartItem.item.product.description}
                            </div>
                        </td>
                        <td id="single-price-${cartItem.item.itemId}">
                            <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/>
                        </td>
                        <td>
                            <button type="button" class="quantity-decrease" data-item-id="${cartItem.item.itemId}">-
                            </button>
                            <input type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}"
                                   class="item-quantity" id="item-quantity-${cartItem.item.itemId}"
                                   data-item-id="${cartItem.item.itemId}">
                            <button type="button" class="quantity-increase" data-item-id="${cartItem.item.itemId}">+
                            </button>
                        </td>
                        <td id="total-price-${cartItem.item.itemId}">
                            <fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00"/>
                        </td>
                        <td>
                            <a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button"
                               id="remove-item" data-item-id="${cartItem.item.itemId}">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

        <div id="cart-summary">
            <span>Total items: <span id="selected-count">${sessionScope.cart.numberOfItems}</span></span>
            <span>Subtotal: <span id="subTotal">
                    <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00"/>
                </span></span>
            <a href="newOrderForm" class="Button">Proceed to Checkout</a>
        </div>

    </div>

    <div id="MyList">
        <c:if test="${sessionScope.loginAccount != null}">
            <c:if test="${!empty sessionScope.loginAccount.listOption}">
                <%@ include file="includeMyList.jsp" %>
            </c:if>
        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>

    <div id="tooltip" class="tooltip"></div>
</div>

<script src="js/updateAuto.js"></script>
<%@ include file="../common/bottom.jsp" %>
