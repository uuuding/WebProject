<%@ include file="../common/top.jsp"%>

<div id="BackLink" style="text-align: center; margin: 1em 0;">
    <a href="mainForm" style="font-size: 1.2em; color: #1d4886; text-decoration: none;">
        Return to Main Menu
    </a>
</div>

<div id="Catalog" style="max-width: 900px; margin: 0 auto; padding: 2em; background-color: #ffffff; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); border-radius: 8px;">
    <p style="font-size: 1.2em; text-align: center; color: #333;">Please confirm the information below and then press continue...</p>

    <table style="width: 100%; border-collapse: collapse; margin-top: 1.5em;">
        <!-- Order Information -->
        <tr>
            <th colspan="2" style="background-color: #1d4886; color: white; padding: 1em; text-align: center; border-top-left-radius: 8px; border-top-right-radius: 8px;">
                <font size="4"><b>Order</b></font><br />
                <font size="3">
                    <b>
                        <fmt:formatDate value="${sessionScope.order.timestamp}" pattern="yyyy/MM/dd hh:mm:ss" />
                    </b>
                </font>
            </th>
        </tr>

        <!-- Billing Address Section -->
        <tr>
            <th colspan="2" style="background-color: #f4f4f4; padding: 1em; color: #1d4886; text-align: left; font-size: 1.1em; font-weight: bold;">
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

        <!-- Shipping Address Section -->
        <tr>
            <th colspan="2" style="background-color: #f4f4f4; padding: 1em; color: #1d4886; text-align: left; font-size: 1.1em; font-weight: bold;">
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
    </table>

    <!-- Confirm Button -->
    <div style="text-align: center; margin-top: 2em;">
        <a href="order" style="text-decoration: none;">
            <input type="submit" name="confirmed" value="Confirmed"
                   style="background-color: #1d4886; color: white; padding: 1em 2em; border: none; border-radius: 4px; font-size: 1.1em; cursor: pointer; transition: background-color 0.3s;">
        </a>
    </div>
</div>

<%@ include file="../common/bottom.jsp"%>
