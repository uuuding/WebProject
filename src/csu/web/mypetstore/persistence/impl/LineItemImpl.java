package csu.web.mypetstore.persistence.impl;

import com.mysql.cj.jdbc.ha.ReplicationMySQLConnection;
import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LineItemImpl implements LineItemDao {
    private static String GET_LINE_ITEM_BY_ORDER_ID = "SELECT" +
            "      ORDERID," +
            "      LINENUM AS lineNumber," +
            "      ITEMID," +
            "      QUANTITY," +
            "      UNITPRICE" +
            "    FROM LINEITEM" +
            "    WHERE ORDERID = ?";

    private static String INSERT_LINE_ITEM = "INSERT INTO LINEITEM " +
            "(ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) " +
            "VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEM_BY_ORDER_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(orderId);
                lineItem.setLineNumber(resultSet.getInt("lineNumber"));
                lineItem.setItemId(resultSet.getString("ITEMID"));
                lineItem.setQuantity(resultSet.getInt("QUANTITY"));
                lineItem.setUnitPrice(resultSet.getBigDecimal("UNITPRICE"));
                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINE_ITEM);
            preparedStatement.setInt(1, lineItem.getOrderId());
            preparedStatement.setInt(2, lineItem.getLineNumber());
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setInt(4, lineItem.getQuantity());
            preparedStatement.setBigDecimal(5, lineItem.getUnitPrice());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
