<%@ include file="../common/top.jsp"%>


<div id="Catalog">

   <form action="signOn" method="post">
      <p>Please enter your username and password.</p>
      <c:if test="${requestScope.signOnMsg != null}">
          <p> <font color="red">${requestScope.signOnMsg} </font></p>
      </c:if>

      <p>
         <label for="username">Username:</label>
         <input type="text" name="username" id="username" required> <br />

         <label for="password">Password:</label>
         <input type="password" name="password" id="password" required><br />

         <label for="captcha">Captcha:</label>
         <input type="text" name="captcha" id="captcha" required>
         <img src="captcha" alt="Captcha Image" id="captchaImage" style="cursor: pointer; margin-top: 20px" onclick="refreshCaptcha()" />
      </p>

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