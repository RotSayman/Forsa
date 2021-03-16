package sample.program;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.exception.DaoException;
import sample.services.OrderService;
import sample.util.Date;

public class UpdateTableView
{
    public static void updateData(OrderService orderService){
        Data.getData().removeAll();
        try {
            Data.getData().getList().addAll(orderService.showOrdersByDate(Date.newInstance().getDate()));
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
