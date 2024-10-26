package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductListByCategory(String categoryId);

    public Product getProduct(String productId);

    public List<Product> searchProduct(String keywords);
}
