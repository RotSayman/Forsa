package sample.services;

import sample.dao.OrderDao;
import sample.dao.OrderDaoImpl;
import sample.exception.DaoException;
import sample.models.Order;

import java.sql.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl orderService;

    private OrderDao dao;


    private OrderServiceImpl(){
        dao = new OrderDaoImpl();
    }

    @Override
    public void createTable() throws DaoException {
        dao.createTable();
    }

    @Override
    public void updateOrder(long id, Order order) throws DaoException {
        dao.updateOrder(id, order);
    }

    @Override
    public void createOrder(Order order) throws DaoException {
        dao.createOrder(order);
    }

    @Override
    public List<Order> showOrders() throws DaoException {
        return dao.showOrders();
    }

    @Override
    public List<Order> showOrdersByDate(Date date) throws DaoException {
        return dao.showOrdersByDate(date);
    }

    @Override
    public List<Order> searchByArticul(String art) throws DaoException {
        return dao.searchByArticul(art);
    }

    @Override
    public void deleteOrder(long id) throws DaoException {
        dao.deleteOrder(id);
    }

    @Override
    public Order showOrder(long id) throws DaoException {
        return dao.showOrder(id);
    }

    public static OrderServiceImpl getService(){
        if(orderService == null){
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }
}
