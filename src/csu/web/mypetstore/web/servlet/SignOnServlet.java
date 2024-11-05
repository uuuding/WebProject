package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;
    private String captcha;
    private String gotCaptcha;


    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.captcha = (String) session.getAttribute("captcha");
        this.gotCaptcha = req.getParameter("captcha");


        //检验用户输入
        if (!validate()) {
            //失败回跳
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        } else {
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if (loginAccount == null) {
                this.msg = "用户名或密码错误";
                System.out.println("11111");
                req.setAttribute("signOnMsg", this.msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else {
                //loginAccount.setPassword(null);

                session.setAttribute("loginAccount", loginAccount);
                session.setAttribute("userName", username);

                if (loginAccount.isListOption()) {
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList", myList);
                }
                //加载用户购物车信息
                CartService cartService = new CartService();
                Cart cart = cartService.getCartByUserId(username);
                if (cart != null) {
                    session.setAttribute("cart", cart);
                }

                resp.sendRedirect("mainForm");

            }


        }

    }

    private boolean validate(){
        System.out.println(captcha);
        System.out.println(gotCaptcha);
        if(this.username == null || this.username.equals("")){
            this.msg = "用户名不能空";
            return false;
        }
        if(this.password == null || this.password.equals("")){
            this.msg = "密码不能空";
            return false;
        }
        if(this.gotCaptcha == null || !(this.gotCaptcha.equalsIgnoreCase(this.captcha))){
            this.msg = "验证码错误";
            return false;
        }
        return true;
    }
}
