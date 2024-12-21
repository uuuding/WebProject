<%@ include file="../common/top.jsp"%>

<div id="Catalog" style="max-width: 900px; margin: 0 auto; padding: 2em; background-color: #ffffff; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); border-radius: 8px;">
   <form action="confirmOrder" method="post">

      <table style="width: 100%; border-collapse: collapse;">
         <!-- Payment Details Section -->
         <tr>
            <th colspan="2" style="background-color: #1d4886; color: white; padding: 1em; text-align: left; border-top-left-radius: 8px; border-top-right-radius: 8px;">Payment Details</th>
         </tr>
         <tr>
            <td style="padding: 0.75em 0; width: 40%; vertical-align: middle;">
               <label for="cardType" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Card Type:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <select name="cardType" id="cardType" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
                  <option value="Visa">Visa</option>
                  <option value="MasterCard">MasterCard</option>
                  <option value="American Express">American Express</option>
                  <c:forEach items="${sessionScope.creditCardTypes}" var="cardType">
                     <option value="${cardType}">${cardType}</option>
                  </c:forEach>
               </select>
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="creditCard" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Card Number:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="creditCard" id="creditCard" value="${sessionScope.order.creditCard}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
               <span style="font-size: 0.85em; color: #777;">* Use a fake number!</span>
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="expiryDate" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Expiry Date (MM/YYYY):</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="expiryDate" id="expiryDate" value="${sessionScope.order.expiryDate}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>

         <!-- Billing Address Section -->
         <tr>
            <th colspan="2" style="background-color: #1d4886; color: white; padding: 1em; text-align: left;">Billing Address</th>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="firstName" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">First Name:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="firstName" id="firstName" value="${sessionScope.order.billToFirstName}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="lastName" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Last Name:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="lastName" id="lastName" value="${sessionScope.order.billToLastName}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="address1" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Address 1:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="address1" id="address1" value="${sessionScope.order.billAddress1}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="address2" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Address 2:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="address2" id="address2" value="${sessionScope.order.billAddress2}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="city" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">City:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="city" id="city" value="${sessionScope.order.billCity}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="state" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">State:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="state" id="state" value="${sessionScope.order.billState}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="zip" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Zip:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="zip" id="zip" value="${sessionScope.order.billZip}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="country" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Country:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="country" id="country" value="${sessionScope.order.billCountry}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>

         <!-- Shipping Address Section - Initially Hidden -->
         <tr>
            <th colspan="2" style="background-color: #1d4886; color: white; padding: 1em; text-align: left;">Shipping Address</th>
         </tr>

         <tbody id="shippingAddressSection" style="display: none;">
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipToFirstName" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">First Name:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipToFirstName" id="shipToFirstName" value="${sessionScope.order.shipToFirstName}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipToLastName" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Last Name:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipToLastName" id="shipToLastName" value="${sessionScope.order.shipToLastName}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipAddress1" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Address 1:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipAddress1" id="shipAddress1" value="${sessionScope.order.shipAddress1}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipAddress2" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Address 2:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipAddress2" id="shipAddress2" value="${sessionScope.order.shipAddress2}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipCity" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">City:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipCity" id="shipCity" value="${sessionScope.order.shipCity}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipState" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">State:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipState" id="shipState" value="${sessionScope.order.shipState}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipZip" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Zip:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipZip" id="shipZip" value="${sessionScope.order.shipZip}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         <tr>
            <td style="padding: 0.75em 0;">
               <label for="shipCountry" style="font-weight: bold; color: #1d4886; display: block; margin-bottom: 5px;">Country:</label>
            </td>
            <td style="padding: 0.75em 0;">
               <input type="text" name="shipCountry" id="shipCountry" value="${sessionScope.order.shipCountry}" style="width: 100%; max-width: 350px; padding: 0.5em; border: 1px solid #ccc; border-radius: 4px; font-size: 1em; color: #333;">
            </td>
         </tr>
         </tbody>

         <!-- Checkbox for Shipping Address -->
         <tr>
            <td colspan="2" style="padding: 1em 0;">
               <label>
                  <input type="checkbox" name="shippingAddressRequired" id="shippingAddressRequired" value="shippingAddressRequired">
                  Ship to different address...
               </label>
            </td>
         </tr>

      </table>

      <div style="text-align: right; margin-top: 2em;">
         <input type="submit" value="Continue" style="background-color: #1d4886; color: white; padding: 1em 2em; border: none; border-radius: 4px; cursor: pointer;">
      </div>

   </form>
</div>

<script>
   // Function to show or hide the shipping address section
   function toggleShippingAddress() {
      var shippingSection = document.getElementById('shippingAddressSection');
      var checkbox = document.getElementById('shippingAddressRequired');

      if (checkbox.checked) {
         shippingSection.style.display = 'table-row-group'; // Show the shipping address section
      } else {
         shippingSection.style.display = 'none'; // Hide the shipping address section
      }
   }

   // Event listener to call the toggle function when the checkbox state changes
   document.getElementById('shippingAddressRequired').addEventListener('change', toggleShippingAddress);

   // Initialize the form by checking the checkbox status
   window.onload = function() {
      toggleShippingAddress();
   };
</script>

<%@ include file="../common/bottom.jsp"%>
