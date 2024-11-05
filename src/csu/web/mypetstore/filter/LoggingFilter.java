//package csu.web.mypetstore.filter;
//
//import csu.web.mypetstore.persistence.DBUtil;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.Date;
//import java.util.logging.Logger;
//
//@WebFilter("/*") // 过滤所有请求
//public class LoggingFilter implements Filter {
//
//    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());
//
//    private static final String INSERT_LOG = "INSERT INTO userlog (username, actiontype, categoryid, productid, itemid) VALUES (?, ?, ?, ?, ?)";
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("LoggingFilter initialized");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String userName = (String) httpRequest.getSession().getAttribute("loginAccount");
//        String requestURI = httpRequest.getRequestURI();
//        String actionType = "";
//        String categoryId = httpRequest.getParameter("categoryId");
//        String productId = httpRequest.getParameter("productId");
//        String itemId = httpRequest.getParameter("itemId");
//        // 检查URI来确定用户行为类型
//        if (requestURI.contains("/categoryForm")) {
//            // 浏览某一类商品
//            actionType = "Browse category - category ID: " + categoryId;
//        } else if(requestURI.contains("/productForm")) {
//            actionType = "Browse product - product ID: " + productId;
//        }
//
//        else if (requestURI.contains("/cartForm")) {
//            // 添加至购物车
//            actionType = "Add to Cart - item ID: " + itemId;
//          }
////        else if (requestURI.contains("/order/create")) {
////            // 生成订单
////            action = "Create Order";
////        }
//
//        if (!actionType.isEmpty()) {
//            saveLogToSql(userName, actionType, categoryId, productId, itemId);
//        }
//
//        // 继续处理请求
//        chain.doFilter(request, response);
//    }
//
//    private void saveLogToSql(String userName, String actionType, String categoryId, String productId, String itemId) {
//
//        try {
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
//            preparedStatement.setString(1, userName);
//            preparedStatement.setString(2, actionType);
//            preparedStatement.setString(3, categoryId);
//            preparedStatement.setString(4, productId);
//            preparedStatement.setString(5, itemId);
//            preparedStatement.executeUpdate();
//            DBUtil.closePreparedStatement(preparedStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("LoggingFilter destroyed");
//    }
//}
