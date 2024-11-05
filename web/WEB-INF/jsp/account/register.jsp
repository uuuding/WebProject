<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="register" method="post">
        <p>Init your account.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p> <font color="red">${requestScope.signOnMsg} </font></p>
        </c:if>

        <table>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" id="username" name="username" required></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password" required></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="email" id="email" name="email" required></td>
            </tr>
            <tr>
                <td><label for="firstName">First Name:</label></td>
                <td><input type="text" id="firstName" name="firstName" required></td>
            </tr>
            <tr>
                <td><label for="lastName">Last Name:</label></td>
                <td><input type="text" id="lastName" name="lastName" required></td>
            </tr>
            <tr>
                <td><label for="status">Status:</label></td>
                <td><input type="text" id="status" name="status"></td>
            </tr>
            <tr>
                <td><label for="address1">Address Line 1:</label></td>
                <td><input type="text" id="address1" name="address1"></td>
            </tr>
            <tr>
                <td><label for="address2">Address Line 2:</label></td>
                <td><input type="text" id="address2" name="address2"></td>
            </tr>
            <tr>
                <td><label for="city">City:</label></td>
                <td><input type="text" id="city" name="city" required></td>
            </tr>
            <tr>
                <td><label for="state">State:</label></td>
                <td><input type="text" id="state" name="state" required></td>
            </tr>
            <tr>
                <td><label for="zip">ZIP Code:</label></td>
                <td><input type="text" id="zip" name="zip" required></td>
            </tr>
            <tr>
                <td><label for="country">Country:</label></td>
                <td><input type="text" id="country" name="country" required></td>
            </tr>
            <tr>
                <td><label for="phone">Phone:</label></td>
                <td><input type="tel" id="phone" name="phone"></td>
            </tr>
            <tr>
                <td><label for="favouriteCategoryId">Favourite Category ID:</label></td>
                <td><input type="text" id="favouriteCategoryId" name="favouriteCategoryId"></td>
            </tr>
            <tr>
                <td><label for="languagePreference">Language Preference:</label></td>
                <td><input type="text" id="languagePreference" name="languagePreference"></td>
            </tr>
            <tr>
                <td><label for="listOption">Mailing List Option:</label></td>
                <td><input type="checkbox" id="listOption" name="listOption"></td>
            </tr>
            <tr>
                <td><label for="bannerOption">Banner Option:</label></td>
                <td><input type="checkbox" id="bannerOption" name="bannerOption"></td>
            </tr>
            <tr>
                <td><label for="bannerName">Banner Name:</label></td>
                <td><input type="text" id="bannerName" name="bannerName"></td>
            </tr>

        </table>

        <input type="submit" value="ok">


    </form>


</div>





<%@ include file="../common/bottom.jsp"%>
