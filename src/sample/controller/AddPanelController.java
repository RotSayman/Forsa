package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.exception.DaoException;
import sample.models.Order;
import sample.program.UpdateTableView;
import sample.services.OrderService;
import sample.services.OrderServiceImpl;
import sample.util.Date;

public class AddPanelController {

    private final OrderService orderService = OrderServiceImpl.getService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea descripField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField priceField;

    @FXML
    private Button addButton;

    @FXML
    private TextField articleField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField quantyField;

    @FXML
    void initialize() {
        // Добавить заказ
        addButton.setOnAction(actionEvent -> {
            try{
                Order order = new Order();
                order.setOrderDate(Date.newInstance().getDate());
                order.setArticul(articleField.getText());
                order.setPrice(Double.parseDouble(priceField.getText()));
                order.setQuanty(Integer.parseInt(quantyField.getText()));


                order.setPhone(phoneField.getText());
                order.setStatus("D");
                order.setForm("C");
                order.setDescription(descripField.getText());
                try {
                    orderService.createOrder(order);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                UpdateTableView.updateData(orderService);
                ((Stage) addButton.getScene().getWindow()).close();
            }catch (NumberFormatException e){
                e.printStackTrace();
                System.err.println("Не верные данные!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введите корректные данные!", ButtonType.CLOSE);
                alert.showAndWait();
            }



        });

        cancelButton.setOnAction(actionEvent -> {
            ((Stage)cancelButton.getScene().getWindow()).close();
        });
    }
}

