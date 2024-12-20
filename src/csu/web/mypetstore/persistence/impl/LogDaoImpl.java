package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Log;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogDaoImpl implements LogDao {

    private static String GET_LOG_LIST_BY_USER_NAME = "SELECT * FROM user_logs WHERE user_name = ?";
    @Override
    public List<Log> getLogListByUserName(String userName) {
        List<Log> logList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LOG_LIST_BY_USER_NAME);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Log log = new Log();
                log.setId(resultSet.getInt("id"));
                log.setUserName(resultSet.getString("user_name"));
                log.setActionType(resultSet.getString("action_type"));
                log.setActionDetails(resultSet.getString("action_details"));
                log.setRequestTime(resultSet.getTimestamp("request_time"));
                logList.add(log);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return logList;
    }
}
