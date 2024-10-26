package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {
    public void updateInventoryQuantity(Map<String, Object> param);

    public int getInventoryQuantity(String itemId);

    public List<Item> getItemListByProduct(String productId);

    public Item getItem(String itemId);
}
