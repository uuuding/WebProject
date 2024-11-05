package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDaoImpl implements SequenceDao {

    private static String GET_SEQUENCE = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";

    private static String UPDATE_SEQUENCE = "UPDATE SEQUENCE" +
            "    SET NEXTID = ?" +
            "    WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence res = new Sequence();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                res.setName(resultSet.getString("name"));
                res.setNextId(resultSet.getInt("nextid"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
