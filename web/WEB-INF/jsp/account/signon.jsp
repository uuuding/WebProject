<%@ include file="../common/top.jsp"%>


<div id="Catalog">

   <form action="signOn" method="post">
      <c:if test="${requestScope.signOnMsg != null}">
         <p> <font color="red">${requestScope.signOnMsg} </font></p>
      </c:if>

      <p>Please enter your username and password.</p>
      <table>
         <tr>
            <td><label for="username">Username:</label></td>
            <td><input type="text" name="username" id="username" required></td>
         </tr>
         <tr>
            <td><label for="password">Password:</label></td>
            <td><input type="password" name="password" id="password" required></td>
         </tr>
         <tr>
            <td><label for="captcha">Captcha:</label></td>
            <td>
               <input type="text" name="captcha" id="captcha" required style="width: 100px;">
               <img src="captcha" alt="Captcha Image" id="captchaImage" style="cursor: pointer; margin-left: 10px; vertical-align: middle;" onclick="refreshCaptcha()" />
            </td>
         </tr>
      </table>


      <input type="submit" value="Login">


   </form>
   Need a user name and password?
   <a href="registerForm">Register Now</a>

</div>

<script>
   function refreshCaptcha() {
      const captchaImage = document.getElementById('captchaImage');
      // 更新图片的src值，添加一个时间戳以避免缓存
      captchaImage.src = 'captcha?' + new Date().getTime();
   }
</script>

<%@ include file="../common/bottom.jsp"%>