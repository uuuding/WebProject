package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {
    private static final String getLineItemsByOrderId = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String insertLineItem = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";


    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getLineItemsByOrderId);
            preparedStatement.setString(1,orderId + "");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        boolean flag = false;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertLineItem);
            preparedStatement.setString(1,lineItem.getOrderId() + "");
            preparedStatement.setString(2,lineItem.getLineNumber() + "");
            preparedStatement.setString(3,lineItem.getItemId());
            preparedStatement.setString(4,lineItem.getQuantity() + "");
            preparedStatement.setString(5,lineItem.getUnitPrice().toString());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }


            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return;

    }
}
