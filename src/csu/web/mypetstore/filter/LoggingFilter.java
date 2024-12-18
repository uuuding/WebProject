package csu.web.mypetstore.filter;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.DBUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.logging.Logger;

@WebFilter("/*") // 过滤所有请求
public class LoggingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    private static final String INSERT_LOG =
            "INSERT INTO user_logs (user_name, action_type, action_details) VALUES (?, ?, ?)";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        Account account = (Account)session.getAttribute("loginAccount");
        String userName = "";
        if(account != null) {
            userName = account.getUsername();
        }
        String requestURI = httpRequest.getRequestURI();
        String actionType = "";
        String actionDetails = "";

        String categoryId = request.getParameter("categoryId");
        String productId = request.getParameter("productId");
        String itemId = request.getParameter("itemId");

        // 检查URI来确定用户行为类型
        if (requestURI.contains("/categoryForm")) {
            // 浏览某一类商品
            actionType = "Browse category";
            actionDetails = "category ID: " + categoryId;
        } else if(requestURI.contains("/productForm")) {
            actionType = "Browse product";
            actionDetails = "product ID: " + productId;
        } else if(requestURI.contains("/itemForm")) {
            actionType = "Browse item";
            actionDetails = "item ID: " + itemId;
        } else if (requestURI.contains("/cartForm")) {
            // 添加至购物车
            categoryId = (String)session.getAttribute("categoryId");
            productId = (String)session.getAttribute("productId");
            itemId = (String)session.getAttribute("itemId");
            actionType = "Add to Cart";
            actionDetails = "category - product - item: " + categoryId + " - " + productId + " - " + itemId;
          }

        if (!actionType.isEmpty()) {
            saveLogToSql(userName, actionType, actionDetails);
        }

        // 继续处理请求
        chain.doFilter(request, response);
    }

    private void saveLogToSql(String userName, String actionType, String actionDetails) {

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, actionType);
            preparedStatement.setString(3, actionDetails);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        logger.info("LoggingFilter destroyed");
    }
}
