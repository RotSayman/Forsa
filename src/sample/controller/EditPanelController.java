package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.exception.DaoException;
import sample.models.Order;
import sample.program.UpdateTableView;
import sample.services.OrderService;
import sample.services.OrderServiceImpl;
import sample.util.Date;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPanelController {

    private OrderService orderService = OrderServiceImpl.getService();
    private Order order;

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
        order = Controller.getSelectedOrder();
        if(order != null){
            articleField.setText(order.getArticul());
            priceField.setText(Double.toString(order.getPrice()));
            quantyField.setText(Integer.toString(order.getQuanty()));
            phoneField.setText(order.getPhone());
            descripField.setText(order.getDescription());
        }


        addButton.setOnAction(actionEvent -> {
            Order newOrder = new Order();
            newOrder.setOrderDate(Date.newInstance().getDate());
            newOrder.setArticul(articleField.getText());
            newOrder.setPrice(Double.parseDouble(priceField.getText()));
            newOrder.setQuanty(Integer.parseInt(quantyField.getText()));
            newOrder.setPhone(phoneField.getText());
            newOrder.setStatus(order.getStatus());
            newOrder.setForm(order.getForm());
            newOrder.setDescription(descripField.getText());
            try {
                orderService.updateOrder(order.getOrderListId(), newOrder);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            UpdateTableView.updateData(orderService);
            ((Stage) addButton.getScene().getWindow()).close();

        });

        cancelButton.setOnAction(actionEvent -> {
            ((Stage)cancelButton.getScene().getWindow()).close();
        });

    }




}

