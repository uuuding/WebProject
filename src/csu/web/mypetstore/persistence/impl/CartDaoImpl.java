package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    private static final String INSERT_CART_ITEM =
            "INSERT INTO cartitem (username, itemid, quantity, in_stock) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CART_ITEM =
            "UPDATE cartitem SET quantity = ? WHERE username = ? AND itemid = ?";
    private static final String DELETE_CART_ITEM =
            "DELETE FROM cartitem WHERE username = ? AND itemid = ?";
    private static final String GET_CART_ITEMS_BY_USER_ID =
            "SELECT itemid, quantity, in_stock FROM cartitem WHERE username = ?";
    private final static String UPDATE_ITEM_ByY_ITEMID_AND_PAYSQL = "update cart set pay = ? where username = ? and itemId = ?";

    @Override
    public void insertCartItem(String username, CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CART_ITEM)) {
            statement.setString(1, username);
            statement.setString(2, cartItem.getItem().getItemId());
            statement.setInt(3, cartItem.getQuantity());
            statement.setBoolean(4, cartItem.isInStock());
            statement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartItem(String username, CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CART_ITEM)) {
            statement.setInt(1, cartItem.getQuantity());
            statement.setString(2, username);
            statement.setString(3, cartItem.getItem().getItemId());
            statement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItem(String username, String itemId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CART_ITEM)) {
            statement.setString(1, username);
            statement.setString(2, itemId);
            statement.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(String username) {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CART_ITEMS_BY_USER_ID)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                CatalogService catalogService = new CatalogService();
                // 从数据库中获取商品数据并设置到 CartItem 对象
                Item item = catalogService.getItem(rs.getString("itemid"));
                cartItem.setItem(item);
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setInStock(rs.getBoolean("in_stock"));
                cartItems.add(cartItem);
            }
            DBUtil.closeConnection(connection);
            DBUtil.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void updateItemByItemIdAndPay(String username, String itemId, boolean pay){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_ByY_ITEMID_AND_PAYSQL);
            preparedStatement.setBoolean(1,pay);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,itemId);
            int result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
