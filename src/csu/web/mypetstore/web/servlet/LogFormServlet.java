package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LogFormServlet extends HttpServlet {

    private static final String LOG_FORM = "/WEB-INF/jsp/account/log.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogDao logDao = new LogDaoImpl();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        List<Log> logList = logDao.getLogListByUserName(account.getUsername());
        session.setAttribute("logList", logList);
        req.getRequestDispatcher(LOG_FORM).forward(req, resp);
    }
}
