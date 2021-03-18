package sample.program;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.exception.DaoException;
import sample.models.Order;
import sample.services.OrderService;
import sample.util.Date;

import java.util.List;

public class UpdateTableView
{
    public synchronized static void updateData(OrderService orderService){
        try {
            List<Order> orderList = orderService.showOrdersByDate(Date.newInstance().getDate());
            Data.getData().removeAll();
            Data.getData().getList().addAll(orderList);
        } catch (DaoException e) {
            e.printStackTrace();
            System.err.println("Ошибка добавления !");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка добавления данных!", ButtonType.CLOSE);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.CLOSE){
                System.exit(1);
            }
        }
    }
}
