package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.exception.DaoException;
import sample.program.UpdateTableView;
import sample.services.OrderService;
import sample.services.OrderServiceImpl;

public class Main extends Application{
    public static Image icons = new Image(String.valueOf(Main.class.getResource("car.png")));

    private OrderService orderService = OrderServiceImpl.getService();;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Forsa");
        primaryStage.getIcons().add(icons);
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try {
            orderService.createTable();
            UpdateTableView.updateData(orderService);
        } catch (DaoException e) {
            e.printStackTrace();
            System.err.println("Ошибка подключения при базе данных");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка подключения при базе данных!", ButtonType.CLOSE);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.CLOSE){
                System.exit(1);
            }
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


}
