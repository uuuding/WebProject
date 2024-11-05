package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDaoImpl implements AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME AS bannerName " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private Account resultSetToAccount(ResultSet resultSet)throws Exception{
        Account account = new Account();
        account.setUsername(resultSet.getString("USERNAME"));
        account.setEmail(resultSet.getString("EMAIL"));
        account.setFirstName(resultSet.getString("FIRSTNAME"));
        account.setLastName(resultSet.getString("LASTNAME"));
        account.setStatus(resultSet.getString("STATUS"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("CITY"));
        account.setState(resultSet.getString("STATE"));
        account.setZip(resultSet.getString("ZIP"));
        account.setCountry(resultSet.getString("COUNTRY"));
        account.setPhone(resultSet.getString("PHONE"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setListOption(resultSet.getBoolean("listOption"));
        account.setBannerOption(resultSet.getBoolean("bannerOption"));
        account.setBannerName(resultSet.getString("bannerName"));
        System.out.println("success");
        return account;
    };

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    @Override
    public void insertAccount(Account account) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 插入账户信息
            String insertAccountSQL = "INSERT INTO ACCOUNT (USERID, EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertAccountSQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getFirstName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setString(5, account.getStatus());
            preparedStatement.setString(6, account.getAddress1());
            preparedStatement.setString(7, account.getAddress2());
            preparedStatement.setString(8, account.getCity());
            preparedStatement.setString(9, account.getState());
            preparedStatement.setString(10, account.getZip());
            preparedStatement.setString(11, account.getCountry());
            preparedStatement.setString(12, account.getPhone());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

    }

    @Override
    public void insertProfile(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String insertProfileSQL = "INSERT INTO PROFILE (USERID, LANGPREF, FAVCATEGORY, MYLISTOPT, BANNEROPT) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertProfileSQL);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getLanguagePreference());
            preparedStatement.setString(3, account.getFavouriteCategoryId());
            preparedStatement.setBoolean(4, account.getListOption());
            preparedStatement.setBoolean(5, account.getBannerOption());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertSignon(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String insertSignonSQL = "INSERT INTO SIGNON (USERNAME, PASSWORD) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertSignonSQL);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateAccount(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // SQL 更新语句
            String updateAccountSQL = "UPDATE ACCOUNT SET " +
                    "EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, " +
                    "ADDR1 = ?, ADDR2 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? " +
                    "WHERE USERID = ?";

            preparedStatement = connection.prepareStatement(updateAccountSQL);

            // 设置参数
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());

            // 执行更新
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateProfile(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // SQL 更新语句
            String updateProfileSQL = "UPDATE PROFILE SET " +
                    "LANGPREF = ?, FAVCATEGORY = ?, MYLISTOPT = ?, BANNEROPT = ? " +
                    "WHERE USERID = ?";

            preparedStatement = connection.prepareStatement(updateProfileSQL);

            // 设置参数
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setBoolean(3, account.getListOption());
            preparedStatement.setBoolean(4, account.getBannerOption());
            preparedStatement.setString(5, account.getUsername());

            // 执行更新
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateSignon(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // SQL 更新语句
            String updateSignonSQL = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

            preparedStatement = connection.prepareStatement(updateSignonSQL);

            // 设置参数
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());

            // 执行更新
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }


   /* public static void main(String[] args){

        AccountDao accountDao = new AccountDaoImpl();
        Account account = new Account();
        account.setUsername("j2ee");
        account.setPassword("j2ee");
        Account result = accountDao.getAccountByUsernameAndPassword(account);
        System.out.println("success");

    }*/
}
