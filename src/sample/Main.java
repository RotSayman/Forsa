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
    private static final int WIN_WIDTH = 850;
    private static final int WIN_HEIGHT = 650;
    private static final String TITLE = "Forsa";
    public static boolean isRun = false;

    private OrderService orderService = OrderServiceImpl.getService();;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(icons);
        primaryStage.setScene(new Scene(root, WIN_WIDTH, WIN_HEIGHT));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try {
            isRun = true;
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

    @Override
    public void stop() throws Exception {
        isRun = false;
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
