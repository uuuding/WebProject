package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {
    public void updateInventoryQuantity(Map<String, Object> var1);

    public int getInventoryQuantity(String var1);

    public List<Item> getItemListByProduct(String var1);

    public Item getItem(String var1);
}
