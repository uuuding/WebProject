<%@ include file="../common/top.jsp"%>

<!-- Message Section -->
<ul class="message" style="text-align: center; font-size: 1.2em; margin: 1.5em 0;">
    <li>Thank you, your order has been submitted.</li>
</ul>

<!-- Back Link -->
<div id="BackLink" style="text-align: center; margin: 1.5em 0;">
    <a href="mainForm" style="font-size: 1.2em; color: #1d4886; text-decoration: none; padding: 0.5em;">
        Return to Main Menu
    </a>
</div>

<!-- Order Details -->
<div id="Catalog" style="max-width: 1000px; margin: 0 auto; padding: 2em; background-color: #ffffff; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); border-radius: 8px;">
    <table style="width: 100%; border-collapse: collapse;">

        <!-- Order Header -->
        <tr>
            <th colspan="2" style="background-color: #1d4886; color: white; padding: 1em; text-align: center; font-size: 1.5em; border-top-left-radius: 8px; border-top-right-radius: 8px;">
                Order #${sessionScope.order.orderId}<br />
                <fmt:formatDate value="${sessionScope.order.timestamp}" pattern="yyyy/MM/dd hh:mm:ss" />
            </th>
        </tr>

        <!-- Payment Details -->
        <tr>
            <th colspan="2" style="background-color: #f4f4f4; color: #1d4886; padding: 1em; text-align: left; font-size: 1.1em; font-weight: bold;">
                Payment Details
            </th>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Card Type:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.cardType}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Card Number:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.creditCard}" /> * Fake number!</td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Expiry Date (MM/YYYY):</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.expiryDate}" /></td>
        </tr>

        <!-- Billing Address -->
        <tr>
            <th colspan="2" style="background-color: #f4f4f4; color: #1d4886; padding: 1em; text-align: left; font-size: 1.1em; font-weight: bold;">
                Billing Address
            </th>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">First name:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billToFirstName}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Last name:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billToLastName}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Address 1:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billAddress1}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Address 2:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billAddress2}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">City:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billCity}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">State:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billState}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Zip:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billZip}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Country:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.billCountry}" /></td>
        </tr>

        <!-- Shipping Address -->
        <tr>
            <th colspan="2" style="background-color: #f4f4f4; color: #1d4886; padding: 1em; text-align: left; font-size: 1.1em; font-weight: bold;">
                Shipping Address
            </th>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">First name:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipToFirstName}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Last name:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipToLastName}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Address 1:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipAddress1}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Address 2:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipAddress2}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">City:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipCity}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">State:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipState}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Zip:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipZip}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Country:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.shipCountry}" /></td>
        </tr>
        <tr>
            <td style="padding: 0.75em 0; font-weight: bold;">Courier:</td>
            <td style="padding: 0.75em 0;"> <c:out value="${sessionScope.order.courier}" /></td>
        </tr>
        <tr>
            <td colspan="2" style="font-weight: bold; padding: 1.25em 0; text-align: center;">Status: <c:out value="${sessionScope.order.status}" /></td>
        </tr>

        <!-- Line Items Table -->
        <tr>
            <td colspan="2">
                <table style="width: 100%; border-collapse: collapse; margin-top: 1.5em;">
                    <thead>
                    <tr style="background-color: #f4f4f4; color: #1d4886;">
                        <th style="padding: 0.75em 1em;">Item ID</th>
                        <th style="padding: 0.75em 1em;">Description</th>
                        <th style="padding: 0.75em 1em;">Quantity</th>
                        <th style="padding: 0.75em 1em;">Price</th>
                        <th style="padding: 0.75em 1em;">Total Cost</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lineItem" items="${sessionScope.order.lineItems}">
                        <tr>
                            <td style="padding: 0.75em 1em;">
                                <a href="viewItem?itemId=${lineItem.item.itemId}" style="color: #1d4886; text-decoration: none;">
                                        ${lineItem.item.itemId}
                                </a>
                            </td>
                            <td style="padding: 0.75em 1em;">
                                <c:if test="${lineItem.item != null}">
                                    ${lineItem.item.attribute1} ${lineItem.item.attribute2}
                                    ${lineItem.item.attribute3} ${lineItem.item.attribute4}
                                    ${lineItem.item.attribute5} ${lineItem.item.product.name}
                                </c:if>
                                <c:if test="${lineItem.item == null}">
                                    <i>{description unavailable}</i>
                                </c:if>
                            </td>
                            <td style="padding: 0.75em 1em;">${lineItem.quantity}</td>
                            <td style="padding: 0.75em 1em;">
                                <fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00" />
                            </td>
                            <td style="padding: 0.75em 1em;">
                                <fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="font-size: 1.2em; font-weight: bold; padding: 1.25em 1em; text-align: right;">
                            Total:
                            <fmt:formatNumber value="${sessionScope.order.totalPrice}" pattern="$#,##0.00" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>

    </table>
</div>

<%@ include file="../common/bottom.jsp"%>
