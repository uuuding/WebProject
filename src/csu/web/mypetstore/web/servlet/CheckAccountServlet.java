package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Check")
public class CheckAccountServlet extends HttpServlet {

    private String username;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        AccountService accountService = new AccountService();
        System.out.println("456");

        if(accountService.checkAccount(username)) {
            resp.getWriter().write("true");
        }
        else{
            resp.getWriter().write("false");
        }
    }
}
