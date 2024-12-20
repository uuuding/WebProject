<%@ include file="../common/top.jsp"%>

<div id="Catalog">

   <form action="confirmOrder" method="post">

      <table>
         <tr>
            <th colspan=2>Payment Details</th>
         </tr>
         <tr>
            <td>Card Type:</td>
            <td>
               <select name="cardType" id="cardType">
                  <!-- 手动添加的选项 -->
                  <option value="Visa">Visa</option>
                  <option value="MasterCard">MasterCard</option>
                  <option value="American Express">American Express</option>
                  <!-- 动态生成的选项 -->
                  <c:forEach items="${sessionScope.creditCardTypes}" var="cardType">
                     <option value="${cardType}">${cardType}</option>
                  </c:forEach>
               </select>
            </td>
         </tr>
         <tr>
            <td>Card Number:</td>
            <td>
               <input type="text" name="creditCard" id="creditCard" value="${sessionScope.order.creditCard}">
               * Use a fake number!
            </td>
         </tr>
         <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td>
               <input type="text" name="expiryDate" id="expiryDate" value="${sessionScope.order.expiryDate}">
            </td>
         </tr>
         <tr>
            <th colspan=2>Billing Address</th>
         </tr>

         <tr>
            <td>First name:</td>
            <td>
               <input type="text" name="firstName" id="firstName" value="${sessionScope.order.billToFirstName}">
            </td>
         </tr>
         <tr>
            <td>Last name:</td>
            <td>
               <input type="text" name="lastName" id="lastName" value="${sessionScope.order.billToLastName}">
            </td>
         </tr>
         <tr>
            <td>Address 1:</td>
            <td>
               <input type="text" name="address1" id="address1" value="${sessionScope.order.billAddress1}">
            </td>
         </tr>
         <tr>
            <td>Address 2:</td>
            <td>
               <input type="text" name="address2" id="address2" value="${sessionScope.order.billAddress2}">
            </td>
         </tr>
         <tr>
            <td>City:</td>
            <td>
               <input type="text" name="city" id="city" value="${sessionScope.order.billCity}">
            </td>
         </tr>
         <tr>
            <td>State:</td>
            <td>
               <input type="text" name="state" id="state" value="${sessionScope.order.billState}">
            </td>
         </tr>
         <tr>
            <td>Zip:</td>
            <td>
               <input type="text" name="zip" id="zip" value="${sessionScope.order.billZip}">
            </td>
         </tr>
         <tr>
            <td>Country:</td>
            <td>
               <input type="text" name="country" id="country" value="${sessionScope.order.billCountry}">
            </td>
         </tr>


         <tr>
            <th colspan=2>Shipping Address</th>
         </tr>
         <tr>
            <td>First name:</td>
            <td><input type="text" name="shipToFirstName" id="shipToFirstName" value="${sessionScope.order.shipToFirstName}"/></td>
         </tr>
         <tr>
            <td>Last name:</td>
            <td><input type="text" name="shipToLastName" id="shipToLastName" value="${sessionScope.order.shipToLastName}"/></td>
         </tr>
         <tr>
            <td>Address 1:</td>
            <td><input type="text" size="40" name="shipAddress1" id="shipAddress1" value="${sessionScope.order.shipAddress1}"/></td>
         </tr>
         <tr>
            <td>Address 2:</td>
            <td><input type="text" size="40" name="shipAddress2" id="shipAddress2" value="${sessionScope.order.shipAddress2}"/></td>
         </tr>
         <tr>
            <td>City:</td>
            <td><input type="text" name="shipCity" id="shipCity" value="${sessionScope.order.shipCity}"/></td>
         </tr>
         <tr>
            <td>State:</td>
            <td><input type="text" size="4" name="shipState" id="shipState" value="${sessionScope.order.shipState}"/></td>
         </tr>
         <tr>
            <td>Zip:</td>
            <td><input type="text" size="10" name="shipZip" id="shipZip" value="${sessionScope.order.shipZip}"/></td>
         </tr>
         <tr>
            <td>Country:</td>
            <td><input type="text" size="15" name="shipCountry" id="shipCountry" value="${sessionScope.order.shipCountry}"/></td>
         </tr>


         <tr>
            <td colspan=2>
               <input type="checkbox" name="shippingAddressRequired" id="shippingAddressRequired" value="shippingAddressRequired">
               Ship to different address...
            </td>
         </tr>

      </table>

      <input type="submit" value="Continue">

   </form>
</div>

<%@ include file="../common/bottom.jsp"%>