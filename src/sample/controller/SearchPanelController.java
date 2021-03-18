package sample.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.exception.DaoException;
import sample.models.Order;
import sample.models.Sender;
import sample.program.DataSearch;
import sample.services.OrderService;
import sample.services.OrderServiceImpl;

public class SearchPanelController {

    private final DataSearch dataSearch = DataSearch.getData();
    private OrderService orderService;
    private final ObservableList<Order> observableList = dataSearch.getList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Order, String> statCol;

    @FXML
    private TableColumn<Order, String> phoneCol;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<Order, String> dateCol;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, Integer> quanCol;

    @FXML
    private TableColumn<Order, Double> priceCol;

    @FXML
    void initialize() {
        orderService = OrderServiceImpl.getService();
        configureTableView();

        searchButton.setOnAction(actionEvent -> {
            DataSearch.getData().removeAll();
            String art = searchField.getText();
            try {
                observableList.addAll(orderService.searchByArticul(art));
            } catch (DaoException e) {
                e.printStackTrace();
                System.err.println("Потеря связи с Базой данных");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Потеря связи с Базой данных", ButtonType.CLOSE);
                alert.showAndWait();

            }
        });

    }


    private void configureTableView() {
        dateCol.setCellValueFactory(orderStringCellDataFeatures -> {
            var date = orderStringCellDataFeatures.getValue().getOrderDate();
            var dateFormat = new SimpleDateFormat("dd.MM.yy");
            String s = dateFormat.format(date);
            return new SimpleObjectProperty<>(s);
        });
        statCol.setCellValueFactory(orderStringCellDataFeatures -> {
            String statCode = orderStringCellDataFeatures.getValue().getStatus();
            return new SimpleObjectProperty<>(Sender.getByCode(statCode).getText());
        });
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quanCol.setCellValueFactory(new PropertyValueFactory<>("quanty"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableView.setItems(observableList);
        tableView.setPlaceholder(new Label(""));
    }
}
