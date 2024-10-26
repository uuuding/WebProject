package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.web.servlet.ProductFormServlet;
import org.ietf.jgss.GSSContext;

import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    private static String UPDATE_INVENTORY_QUANTITY =
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    private static String GET_INVENTORY_QUANTITY =
            "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";

    private static String GET_ITEM_LIST_BY_PRODUCT =
            "SELECT" +
                    "      I.ITEMID," +
                    "      LISTPRICE," +
                    "      UNITCOST," +
                    "      SUPPLIER AS supplierId," +
                    "      I.PRODUCTID AS \"product.productId\"," +
                    "      NAME AS \"product.name\"," +
                    "      DESCN AS \"product.description\"," +
                    "      CATEGORY AS \"product.categoryId\"," +
                    "      STATUS," +
                    "      ATTR1 AS attribute1," +
                    "      ATTR2 AS attribute2," +
                    "      ATTR3 AS attribute3," +
                    "      ATTR4 AS attribute4," +
                    "      ATTR5 AS attribute5" +
                    "    FROM ITEM I, PRODUCT P" +
                    "    WHERE P.PRODUCTID = I.PRODUCTID" +
                    "    AND I.PRODUCTID = ?";

    private static String GET_ITEM =
            "SELECT" +
                    "      I.ITEMID," +
                    "      LISTPRICE," +
                    "      UNITCOST," +
                    "      SUPPLIER AS supplierId," +
                    "      I.PRODUCTID AS \"product.productId\"," +
                    "      NAME AS \"product.name\"," +
                    "      DESCN AS \"product.description\"," +
                    "      CATEGORY AS \"product.categoryId\"," +
                    "      STATUS," +
                    "      ATTR1 AS attribute1," +
                    "      ATTR2 AS attribute2," +
                    "      ATTR3 AS attribute3," +
                    "      ATTR4 AS attribute4," +
                    "      ATTR5 AS attribute5," +
                    "      QTY AS quantity" +
                    "    from ITEM I, INVENTORY V, PRODUCT P" +
                    "    where P.PRODUCTID = I.PRODUCTID" +
                    "      and I.ITEMID = V.ITEMID" +
                    "      and I.ITEMID = ?";


    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer) param.get(itemId);
            preparedStatement.setInt(1, increment.intValue());
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getInt("value");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                Product product = new Product();
                product.setProductId(resultSet.getString("product.productId"));
                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));
                item.setProduct(product);
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = new Item();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                item.setItemId(resultSet.getString("ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                item.setQuantity(resultSet.getInt("quantity"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
