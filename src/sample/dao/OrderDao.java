package sample.dao;

import sample.exception.DaoException;
import sample.models.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao
{
    void createTable() throws DaoException;
    void updateOrder(long id, Order order) throws DaoException;
    void createOrder(Order order) throws DaoException;
    List<Order> showOrders() throws DaoException;
    List<Order> showOrdersByDate(Date date) throws DaoException;
    List<Order> searchByArticul(String art) throws DaoException;
    void deleteOrder(long id) throws DaoException;
    Order showOrder(long id) throws DaoException;
}
