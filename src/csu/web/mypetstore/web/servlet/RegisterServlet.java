package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AccountService accountService = new AccountService();
        accountService.registerAccount(
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("email"),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("status"),
                req.getParameter("address1"),
                req.getParameter("address2"),
                req.getParameter("city"),
                req.getParameter("state"),
                req.getParameter("zip"),
                req.getParameter("country"),
                req.getParameter("phone"),
                req.getParameter("languagePreference"),
                req.getParameter("favouriteCategoryId"),
                Boolean.parseBoolean(req.getParameter("listOption")),
                Boolean.parseBoolean(req.getParameter("bannerOption")),
                req.getParameter("bannerName")
        );
        System.out.println(req.getParameter("username"));

        // 使用 JavaScript 在控制台打印注册成功信息
        resp.setContentType("text/html"); // 设置响应类型
        PrintWriter out = resp.getWriter();
        out.println("<html><head>");
        out.println("<script>console.log('注册成功！');</script>");
        out.println("</head><body></body></html>");


        // 成功注册后的重定向或响应
        resp.sendRedirect("mainForm");
    }

}

