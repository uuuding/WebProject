package csu.web.mypetstore.persistence.impl;


import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {
    private static final String getSequence = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String updateSequence = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence checkSequence = null;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSequence);
            preparedStatement.setString(1,sequence.getName());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                checkSequence = new Sequence();
                checkSequence.setName(resultSet.getString(1));
                checkSequence.setNextId(resultSet.getInt(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return checkSequence;
    }

    @Override
    public boolean updateSequence(Sequence sequence) {
        boolean flag = false;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSequence);
            preparedStatement.setString(2,sequence.getName());
            preparedStatement.setInt(1,sequence.getNextId());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }


            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}