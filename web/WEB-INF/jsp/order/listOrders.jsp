<%@ include file="../common/top.jsp"%>

<h2 class="order-title">My Orders</h2>

<table class="order-table">
  <tr>
    <th>Order ID</th>
    <th>Date</th>
    <th>Total Price</th>
  </tr>

  <c:forEach var="order" items="${orderList}">
    <tr>
      <td><a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a></td>
      <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd" /></td>
      <td><fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" /></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../common/bottom.jsp"%>


