package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductsServlet extends HttpServlet {

    private final CatalogService catalogService = new CatalogService();
    private static final String SEARCH_PRODUCTS_FORM = "/WEB-INF/jsp/catalog/searchProducts.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        HttpSession session = req.getSession();

        if (keyword != null && !keyword.isEmpty()) {
            // 将关键字转换为小写并执行搜索
            List<Product> productList = catalogService.searchProductList(keyword.toLowerCase());
            req.setAttribute("productList", productList);

            //设置product对应的category
            Category category = catalogService.getCategory(productList.get(0).getCategoryId());
            session.setAttribute("category", category);

            // 转发到搜索结果页面
            req.getRequestDispatcher(SEARCH_PRODUCTS_FORM).forward(req, resp);
        } else {
            // 如果关键字为空，则设置错误消息并转发到错误页面
            req.setAttribute("errorMsg", "Please enter a keyword to search for, then press the search button.");
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        }
    }
}
