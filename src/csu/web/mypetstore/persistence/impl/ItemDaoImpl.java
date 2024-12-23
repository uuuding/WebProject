package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ItemDao;

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

            // 获取第一个 itemId 作为查询条件
            String itemId = param.keySet().iterator().next();
            Object incrementObj = param.get(itemId);

            // 检查增量值是否是 Integer 类型
            if (incrementObj instanceof Integer) {
                Integer increment = (Integer) incrementObj;

                // 设置增量和 itemId 到 SQL 语句
                preparedStatement.setInt(1, increment.intValue());
                preparedStatement.setString(2, itemId);

                // 执行更新
                preparedStatement.executeUpdate();
            } else {
                // 如果增量值不是 Integer 类型，尝试将其转换为整数
                try {
                    // 如果增量值是字符串并且可以转换为整数
                    Integer increment = Integer.parseInt(incrementObj.toString());

                    // 设置增量和 itemId 到 SQL 语句
                    preparedStatement.setInt(1, increment.intValue());
                    preparedStatement.setString(2, itemId);

                    // 执行更新
                    preparedStatement.executeUpdate();
                } catch (NumberFormatException e) {
                    // 如果不能转换为整数，打印错误信息
                    System.err.println("Invalid increment value: " + incrementObj);
                    // 可以根据需求抛出异常，或者记录日志
                }
            }

            // 关闭资源
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
            if (resultSet.next()) {
                result = resultSet.getInt("value");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
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
            while (resultSet.next()) {
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
            if (resultSet.next()) {
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

                Product product = new Product();
                product.setProductId(resultSet.getString("product.productId"));
                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));
                item.setProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
