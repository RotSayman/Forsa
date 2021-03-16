package sample.dao;

import sample.exception.DaoException;
import sample.models.Order;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {



    @Override
    public void createTable() throws DaoException {
        try(Connection conn = ConnectorBase.getConnection();
            Statement prep = conn.createStatement()) {
            prep.executeUpdate(SQLOrder.CREATE_TABLE);
        } catch (SQLException t) {
            throw new DaoException(t);
        }
    }

    @Override
    public void updateOrder(long id, Order order) throws DaoException {
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.UPDATE)) {
            prep.setDate(1 , order.getOrderDate());
            prep.setString(2 , order.getArticul());
            prep.setDouble(3, order.getPrice());
            prep.setInt(4 , order.getQuanty());
            prep.setString(5 , order.getPhone());
            prep.setString(6 , order.getStatus());
            prep.setString(7 , order.getForm());
            prep.setString(8 , order.getDescription());
            prep.setLong(9 , id);
            prep.executeUpdate();
        } catch (SQLException t) {
            throw new DaoException(t);
        }
    }

    @Override
    public void createOrder(Order order) throws DaoException {
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.CREATE_ORDER)) {
            prep.setDate(1 , order.getOrderDate());
            prep.setString(2 , order.getArticul());
            prep.setDouble(3, order.getPrice());
            prep.setInt(4 , order.getQuanty());
            prep.setString(5 , order.getPhone());
            prep.setString(6 , order.getStatus());
            prep.setString(7 , order.getForm());
            prep.setString(8 , order.getDescription());
            prep.executeUpdate();
        } catch (SQLException t) {
            throw new DaoException(t);
        }
    }

    @Override
    public List<Order> showOrders() throws DaoException {
        List<Order> orderList = new LinkedList<>();
        try(Connection conn = ConnectorBase.getConnection();
            Statement prep = conn.createStatement()) {
            ResultSet result = prep.executeQuery(SQLOrder.SELECT_ALL);
            while (result.next()){
                Order order = new Order(
                        result.getLong("order_list_id"),
                        result.getDate("order_date"),
                        result.getString("articul"),
                        result.getDouble("price"),
                        result.getInt("quanty"),
                        result.getString("phone"),
                        result.getString("status"),
                        result.getString("form"),
                        result.getString("description")
                );
                orderList.add(order);
            }
        } catch (SQLException t) {
            throw new DaoException(t);
        }
        return orderList;
    }

    @Override
    public List<Order> showOrdersByDate(Date date) throws DaoException {
        List<Order> orderList = new LinkedList<>();
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.SELECT_BY_DATE)) {
            prep.setDate(1, date);
            ResultSet result = prep.executeQuery();
            while (result.next()){
                Order order = new Order(
                        result.getLong("order_list_id"),
                        result.getDate("order_date"),
                        result.getString("articul"),
                        result.getDouble("price"),
                        result.getInt("quanty"),
                        result.getString("phone"),
                        result.getString("status"),
                        result.getString("form"),
                        result.getString("description")
                );
                orderList.add(order);
            }
        } catch (SQLException t) {
            throw new DaoException(t);
        }
        return orderList;
    }

    @Override
    public List<Order> searchByArticul(String art) throws DaoException {
        List<Order> orderList = new LinkedList<>();
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.SEARCH)) {
            prep.setString(1, art);
            ResultSet result = prep.executeQuery();
            while (result.next()){
                Order order = new Order();
                order.setOrderListId(result.getLong("order_list_id"));
                order.setOrderDate(result.getDate("order_date"));
                order.setPrice(result.getDouble("price"));
                order.setQuanty(result.getInt("quanty"));
                order.setPhone(result.getString("phone"));
                order.setStatus(result.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException t) {
            throw new DaoException(t);
        }

        return orderList;
    }

    @Override
    public void deleteOrder(long id) throws DaoException {
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.DELETE)) {
            prep.setLong(1 , id);
            prep.executeUpdate();
        } catch (SQLException t) {
            throw new DaoException(t);
        }
    }

    @Override
    public Order showOrder(long id) throws DaoException {
        Order resOrder = new Order();
        try(Connection conn = ConnectorBase.getConnection();
            PreparedStatement prep = conn.prepareStatement(SQLOrder.SELECT)) {
            prep.setLong(1 , id);
            ResultSet res = prep.executeQuery();
            while (res.next()){
                resOrder.setOrderListId(res.getLong("order_list_id"));
                resOrder.setArticul(res.getString("articul"));
                resOrder.setPrice(res.getDouble("price"));
                resOrder.setQuanty(res.getInt("quanty"));
                resOrder.setPhone(res.getString("phone"));
                resOrder.setStatus(res.getString("status"));
                resOrder.setForm(res.getString("form"));
                resOrder.setDescription(res.getString("description"));
            }
        } catch (SQLException t) {
            throw new DaoException(t);
        }
        return resOrder;
    }
}
