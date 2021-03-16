package sample.controller;



import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.exception.DaoException;
import sample.models.Form;
import sample.models.Order;
import sample.models.Sender;
import sample.program.Data;
import sample.program.UpdateTableView;
import sample.services.OrderService;
import sample.services.OrderServiceImpl;
import sample.util.Date;

import java.io.IOException;

public class Controller {

    private final OrderService orderService = OrderServiceImpl.getService();
    private final ObservableList<Order> list = Data.getData().getList();

    private static Order selectedOrder;



    @FXML
    private TableColumn<Order, Integer> quantyCol;

    @FXML
    private TableColumn<Order, String> phoneCol;

    @FXML
    private TableColumn<Order, Sender> sendCol;

    @FXML TableColumn<Order, Form> formCol;

    @FXML
    private Button editButton;

    @FXML
    private Button delButton;

    @FXML
    private Button searchButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Order, String> articulCol;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Order> mainTableView;

    @FXML
    private TableColumn<Order, Double> priceCol;

    @FXML
    private TableColumn<Order, String> descriptionCol;

    @FXML
    void initialize() {
        // Устанавливаем дату
        datePicker.setValue(Date.newInstance().getDate().toLocalDate());
        // Отключаем сортировку
        articulCol.setSortable(false);
        priceCol.setSortable(false);
        quantyCol.setSortable(false);
        phoneCol.setSortable(false);
        descriptionCol.setSortable(false);
        sendCol.setSortable(false);
        formCol.setSortable(false);

        //Связываем таблицу с классом Order
        articulCol.setCellValueFactory(new PropertyValueFactory<>("articul"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantyCol.setCellValueFactory(new PropertyValueFactory<>("quanty"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        // Статус заказа
        ObservableList<Sender> senderList = FXCollections.observableArrayList(Sender.values());
        sendCol.setCellValueFactory(cellDataFeatures -> {
            Order order = cellDataFeatures.getValue();
            String senderCode = order.getStatus();
            Sender sender = Sender.getByCode(senderCode);
            return new SimpleObjectProperty<>(sender);
        });
        sendCol.setCellFactory(ComboBoxTableCell.forTableColumn(senderList));

        sendCol.setOnEditCommit(event -> {
            TablePosition<Order, Sender> pos = event.getTablePosition();
            Sender newSender = event.getNewValue();
            int row = pos.getRow();
            Order order = event.getTableView().getItems().get(row);
            order.setStatus(newSender.getCode());
            try {
                orderService.updateOrder(order.getOrderListId(), order);
                UpdateTableView.updateData(orderService);
            } catch (DaoException e) {
                e.printStackTrace();
                System.err.println("Потеря связи с Базой данных");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Потеря связи с Базой данных", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    UpdateTableView.updateData(orderService);
                }
            }
        });

        // Форма отправки
        ObservableList<Form> formList = FXCollections.observableArrayList(Form.values());
        formCol.setCellValueFactory(cellDataFeatures -> {
            Order order = cellDataFeatures.getValue();
            String formCode = order.getForm();
            Form form = Form.getByCode(formCode);
            return new SimpleObjectProperty<>(form);
        });
        formCol.setCellFactory(ComboBoxTableCell.forTableColumn(formList));

        formCol.setOnEditCommit(event -> {
            TablePosition<Order, Form> pos = event.getTablePosition();
            Form newForm = event.getNewValue();
            int row = pos.getRow();
            Order order = event.getTableView().getItems().get(row);
            order.setForm(newForm.getCode());
            try {
                orderService.updateOrder(order.getOrderListId(), order);
                UpdateTableView.updateData(orderService);
            } catch (DaoException e) {
                e.printStackTrace();
                System.err.println("Потеря связи с Базой данных");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Потеря связи с Базой данных", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    UpdateTableView.updateData(orderService);
                }
            }
        });


        mainTableView.setItems(list);

        //События кнопки Добавить
        addButton.setOnAction(actionEvent -> {
            Stage addStage = new Stage();

            try {
                Parent addParent = FXMLLoader.load(Main.class.getResource("addPanel.fxml"));
                addStage.setResizable(false);
                addStage.initOwner(this.mainTableView.getScene().getWindow());
                addStage.initModality(Modality.WINDOW_MODAL);
                addStage.getIcons().add(Main.icons);
                addStage.setTitle("Добавить заказ");
                addStage.setScene(new Scene(addParent, 424.0D, 400.0D));
                addStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Добавление не возможно не найден файл addPanel.fxml");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Добавление не возможно не найден файл addPanel.fxml", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    System.exit(1);
                }
            }

        });

        // События кнопки Изменить
        editButton.setOnAction(actionEvent -> {
            selectedOrder = mainTableView.getSelectionModel().getSelectedItem();
            Stage addStage = new Stage();

            try {
                Parent addParent = FXMLLoader.load(Main.class.getResource("editPanel.fxml"));
                addStage.setResizable(false);
                addStage.initOwner(this.mainTableView.getScene().getWindow());
                addStage.initModality(Modality.WINDOW_MODAL);
                addStage.getIcons().add(Main.icons);
                addStage.setTitle("Изменить заказ");
                addStage.setScene(new Scene(addParent, 424.0D, 400.0D));
                addStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Изменение не возможно не найден файл editPanel.fxml");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Добавление не возможно не найден файл editPanel.fxml", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    System.exit(1);
                }
            }
            selectedOrder = null;
        });

        // События кнопки удалить
        delButton.setOnAction(actionEvent -> {
            long id = mainTableView.getSelectionModel().getSelectedItem().getOrderListId();
            try {
                orderService.deleteOrder(id);
            } catch (DaoException e) {
                e.printStackTrace();
                System.err.println("Удаление не возможно!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Удаление не возможно!", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    UpdateTableView.updateData(orderService);
                }
            }


            UpdateTableView.updateData(orderService);
        });

        // События календаря
        datePicker.setOnAction(actionEvent -> {
            Date.newInstance().setDate(datePicker.getValue());
            UpdateTableView.updateData(orderService);
        });

        // Поиск
        searchButton.setGraphic(new ImageView(String.valueOf(Main.class.getResource("search.png"))));

        searchButton.setOnAction((actionEvent) -> {
            Stage addStage = new Stage();

            try {
                Parent addParent = FXMLLoader.load(Main.class.getResource("searchPanel.fxml"));
                addStage.setResizable(false);
                addStage.initOwner(this.mainTableView.getScene().getWindow());
                addStage.initModality(Modality.WINDOW_MODAL);
                addStage.getIcons().add(Main.icons);
                addStage.setTitle("Поиск");
                addStage.setScene(new Scene(addParent, 424.0D, 400.0D));
                addStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Не найден файл searchPanel.fxml");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Не найден файл searchPanel.fxml", ButtonType.CLOSE);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.CLOSE){
                    System.exit(1);
                }
            }
        });
    }

    public synchronized static Order getSelectedOrder(){
        return selectedOrder;
    }

}