<%@ include file="../common/top.jsp"%>

<div id="Catalog">
   <form action="your-server-side-script" method="post">

      <table>
         <tr>
            <th colspan="2">Payment Details</th>
         </tr>
         <tr>
            <td>Card Type:</td>
            <td>
               <label for="cardType">Card Type:</label>
               <select name="order.cardType" id="cardType">
                  <!-- Options will be populated by server-side code -->
                  <option value="">Select a Card Type</option>
                  <!-- Add options here -->
               </select>
            </td>
         </tr>
         <tr>
            <td>Card Number:</td>
            <td>
               <label for="creditCard">Card Number:</label>
               <input type="text" name="order.creditCard" id="creditCard" />
               * Use a fake number!
            </td>
         </tr>
         <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td>
               <label for="expiryDate">Expiry Date (MM/YYYY):</label>
               <input type="text" name="order.expiryDate" id="expiryDate" />
            </td>
         </tr>
         <tr>
            <th colspan="2">Billing Address</th>
         </tr>
         <tr>
            <td>First name:</td>
            <td>
               <label for="billToFirstName">First name:</label>
               <input type="text" name="order.billToFirstName" id="billToFirstName" />
            </td>
         </tr>
         <tr>
            <td>Last name:</td>
            <td>
               <label for="billToLastName">Last name:</label>
               <input type="text" name="order.billToLastName" id="billToLastName" />
            </td>
         </tr>
         <tr>
            <td>Address 1:</td>
            <td>
               <label for="billAddress1">Address 1:</label>
               <input type="text" size="40" name="order.billAddress1" id="billAddress1" />
            </td>
         </tr>
         <tr>
            <td>Address 2:</td>
            <td>
               <label for="billAddress2">Address 2:</label>
               <input type="text" size="40" name="order.billAddress2" id="billAddress2" />
            </td>
         </tr>
         <tr>
            <td>City:</td>
            <td>
               <label for="billCity">City:</label>
               <input type="text" name="order.billCity" id="billCity" />
            </td>
         </tr>
         <tr>
            <td>State:</td>
            <td>
               <label for="billState">State:</label>
               <input type="text" size="4" name="order.billState" id="billState" />
            </td>
         </tr>
         <tr>
            <td>Zip:</td>
            <td>
               <label for="billZip">Zip:</label>
               <input type="text" size="10" name="order.billZip" id="billZip" />
            </td>
         </tr>
         <tr>
            <td>Country:</td>
            <td>
               <label for="billCountry">Country:</label>
               <input type="text" size="15" name="order.billCountry" id="billCountry" />
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <label for="shippingAddressRequired">
                  <input type="checkbox" name="shippingAddressRequired" id="shippingAddressRequired" />
                  Ship to different address...
               </label>
            </td>
         </tr>
      </table>

      <input type="submit" name="newOrder" value="Continue" />

   </form>
</div>

<%@ include file="../common/bottom.jsp"%>