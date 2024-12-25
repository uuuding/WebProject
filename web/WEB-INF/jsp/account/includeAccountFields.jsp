<h3>Account Information</h3>

<table>
    <tr>
        <td>First name:</td>
        <td><input type="text" name="firstName" value="${loginAccount.firstName}" /></td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td><input type="text" name="lastName" value="${loginAccount.lastName}" /></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="email" size="40" name="email" value="${loginAccount.email}" required /></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="${loginAccount.phone}" /></td>
    </tr>
    <tr>
        <td>Address 1:</td>
        <td><input type="text" size="40" name="address1" value="${loginAccount.address1}" /></td>
    </tr>
    <tr>
        <td>Address 2:</td>
        <td><input type="text" size="40" name="address2" value="${loginAccount.address2}" /></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text" name="city" value="${loginAccount.city}" /></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" size="4" maxlength="2" name="state" value="${loginAccount.state}" /></td>
    </tr>
    <tr>
        <td>Zip:</td>
        <td><input type="text" size="10" name="zip" value="${loginAccount.zip}" /></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" size="15" name="country" value="${loginAccount.country}" /></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td>
            <select name="languagePreference">
                <option value="english" ${loginAccount.languagePreference == 'english' ? 'selected="selected"' : ''}>english</option>
                <option value="japanese" ${loginAccount.languagePreference == 'japanese' ? 'selected="selected"' : ''}>japanese</option>
            </select>
        </td>
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
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td>
            <input name="listOption" type="checkbox" value="true" ${loginAccount.listOption ? 'checked="checked"' : ''} />
        </td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td>
            <input name="bannerOption" type="checkbox" value="true" ${loginAccount.bannerOption ? 'checked="checked"' : ''} />
        </td>
    </tr>
</table>
