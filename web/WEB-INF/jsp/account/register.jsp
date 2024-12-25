<%@ include file="../common/top.jsp"%>

<script src="${pageContext.request.contextPath}/js/register.js"></script>
<link rel="StyleSheet" href="css/account.css" type="text/css" media="screen"/>

<div  class="registration-form" id="registration-form">

    <form action="register" method="post">

        <p id="m">Init your account.</p>

        <c:if test="${requestScope.signOnMsg != null}">
            <span> <font color="red">${requestScope.signOnMsg} </font></span>
        </c:if>

        <table>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" id="username" name="username" required></td>
                <td id="msg"><label id="msg1">Init ?</label></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="email" id="email" name="email" required></td>
                <td></td>
            </tr>


            <tr>
                <td><label for="firstName">First Name:</label></td>
                <td><input type="text" id="firstName" name="firstName" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="lastName">Last Name:</label></td>
                <td><input type="text" id="lastName" name="lastName" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="status">Status:</label></td>
                <td><input type="text" id="status" name="status" maxlength="2"></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="address1">Address Line 1:</label></td>
                <td><input type="text" id="address1" name="address1"></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="address2">Address Line 2:</label></td>
                <td><input type="text" id="address2" name="address2"></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="city">City:</label></td>
                <td><input type="text" id="city" name="city" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="state">State:</label></td>
                <td><input type="text" id="state" name="state" required ></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="zip">ZIP Code:</label></td>
                <td><input type="text" id="zip" name="zip" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="country">Country:</label></td>
                <td><input type="text" id="country" name="country" required></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="phone">Phone:</label></td>
                <td><input type="tel" id="phone" name="phone"></td>
                <td></td>
            </tr>


            <tr>
                    <td>Language Preference:</td>
                    <td>
                        <select name="languagePreference">
                            <option value="english" ${loginAccount.languagePreference == 'english' ? 'selected="selected"' : ''}>english</option>
                            <option value="japanese" ${loginAccount.languagePreference == 'japanese' ? 'selected="selected"' : ''}>japanese</option>
                        </select>
                    </td>
                <td></td>
                </tr>
                <tr>
                    <td>Favourite Category:</td>
                    <td>
                        <select name="favouriteCategoryId">
                            <option value="FISH" ${loginAccount.favouriteCategoryId == 'FISH' ? 'selected="selected"' : ''}>FISH</option>
                            <option value="DOGS" ${loginAccount.favouriteCategoryId == 'DOGS' ? 'selected="selected"' : ''}>DOGS</option>
                            <option value="REPTILES" ${loginAccount.favouriteCategoryId == 'REPTILES' ? 'selected="selected"' : ''}>REPTILES</option>
                            <option value="CATS" ${loginAccount.favouriteCategoryId == 'CATS' ? 'selected="selected"' : ''}>CATS</option>
                            <option value="BIRDS" ${loginAccount.favouriteCategoryId == 'BIRDS' ? 'selected="selected"' : ''}>BIRDS</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
            <tr>
                <td><label for="listOption">Mailing List Option:</label></td>
                <td><input type="checkbox" id="listOption" name="listOption"></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="bannerOption">Banner Option:</label></td>
                <td><input type="checkbox" id="bannerOption" name="bannerOption"></td>
                <td></td>
            </tr>
            <tr>
                <td><label for="bannerName">Banner Name:</label></td>
                <td><input type="text" id="bannerName" name="bannerName"></td>
                <td></td>
            </tr>

        </table>

        <input type="submit" value="ok" id="submit">


    </form>


</div>





<%@ include file="../common/bottom.jsp"%>
