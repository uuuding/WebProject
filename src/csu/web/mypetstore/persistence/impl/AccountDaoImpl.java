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
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private Account resultSetToAccount(ResultSet resultSet)throws Exception{
        return null;
    };


    @Override
    public Account getAccountByUsername(String username) {
        return null;
    }

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

    }

    @Override
    public void insertProfile(Account account) {

    }

    @Override
    public void insertSignon(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void updateProfile(Account account) {

    }

    @Override
    public void updateSignon(Account account) {

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
