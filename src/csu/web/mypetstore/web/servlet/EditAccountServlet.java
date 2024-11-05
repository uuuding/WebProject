package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccountForm.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");

        //登录 且 两次输入密码一致
        if (account != null && password.equals(repeatedPassword)) {
            // 从表单获取参数并设置到 Account 对象中
            account.setPassword(req.getParameter("password"));
            account.setEmail(req.getParameter("email"));
            account.setFirstName(req.getParameter("firstName"));
            account.setLastName(req.getParameter("lastName"));
            account.setAddress1(req.getParameter("address1"));
            account.setAddress2(req.getParameter("address2"));
            account.setCity(req.getParameter("city"));
            account.setState(req.getParameter("state"));
            account.setZip(req.getParameter("zip"));
            account.setCountry(req.getParameter("country"));
            account.setPhone(req.getParameter("phone"));
            account.setLanguagePreference(req.getParameter("languagePreference"));
            account.setFavouriteCategoryId(req.getParameter("favouriteCategoryId"));
            account.setListOption(req.getParameter("listOption") != null);
            account.setBannerOption(req.getParameter("bannerOption") != null);

            // 更新账户信息
            accountService.updateAccount(account);

            //未更改密码
            if (password == "" && repeatedPassword == "") {
                // 设置信息并返回编辑页面
                req.setAttribute("editMsg", "Successfully saved");
                req.getRequestDispatcher("editAccountForm").forward(req, resp);
            } else {
                // 更新 session 中的 account 对象 用户需重新登录
                session.setAttribute("loginAccount", null);
                session.setAttribute("cart", null);
                //重定向到登录页面
                resp.sendRedirect("signonForm");
            }
        } else if (account != null && !password.equals(repeatedPassword)) {//两次输入密码不一致
            // 设置错误信息并返回编辑页面
            req.setAttribute("editMsg", "Passwords do not match. Please re-enter.");
            req.getRequestDispatcher("editAccountForm").forward(req, resp);
        } else {
            resp.sendRedirect("signonForm");
        }
    }
}
