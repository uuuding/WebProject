package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(){
        this.accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return this.accountDao.getAccountByUsernameAndPassword(account);
    }

    public boolean checkAccount(String username) {
        return this.accountDao.isUsernameExists(username);
    }

    public void registerAccount(String username, String password, String email, String firstName, String lastName, String status,
                                String address1, String address2, String city, String state, String zip,
                                String country, String phone, String languagePreference, String favouriteCategoryId,
                                boolean listOption, boolean bannerOption, String bannerName) {

        // 创建 Account 对象
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setStatus(status);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setAddr1(address1);
        account.setAddr2(address2);
        account.setCity(city);
        account.setState(state);
        account.setZip(zip);
        account.setCountry(country);
        account.setPhone(phone);
        account.setLanguagePreference(languagePreference);
        account.setFavouriteCategoryId(favouriteCategoryId);
        account.setListOption(listOption);
        account.setBannerOption(bannerOption);
        account.setBannerName(bannerName);

        // 使用 DAO 方法插入数据
        accountDao.insertAccount(account);
        accountDao.insertSignon(account);
        accountDao.insertProfile(account);
    }

    public void updateAccount(Account account) {
        this.accountDao.updateAccount(account);
        this.accountDao.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            this.accountDao.updateSignon(account);
        }
    }
}
