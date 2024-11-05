<%@ include file="../common/top.jsp" %>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <c:if test="${not empty editMsg}">
        <p style="color:red;">${editMsg}</p>
    </c:if>

    <h3>User Information</h3>

    <form action="editAccount" method="post">
        <table>
            <tr>
                <td>User ID:</td>
                <td>${loginAccount.username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatedPassword"/></td>
            </tr>
        </table>
        <%@ include file="includeAccountFields.jsp" %>

        <input type="submit" value="Save Account Information"/>

    </form>
    <a href="listOrders">My Orders</a>
</div>

<%@ include file="../common/bottom.jsp" %>
