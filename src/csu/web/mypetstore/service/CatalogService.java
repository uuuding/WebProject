package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.CategoryDao;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.ProductDao;
import csu.web.mypetstore.persistence.impl.CategoryDaoImpl;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;
import csu.web.mypetstore.persistence.impl.ProductDaoImpl;

import java.util.List;

public class CatalogService {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private ItemDao itemDao;

    public CatalogService() {
        this.categoryDao = new CategoryDaoImpl();
        this.productDao = new ProductDaoImpl();
        this.itemDao = new ItemDaoImpl();
    }

    public List<Category> getCategoryList() {
        return this.categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return this.categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return this.productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return this.productDao.getProductListByCategory(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return this.productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return this.itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return this.itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return this.itemDao.getInventoryQuantity(itemId) > 0;
    }


}
