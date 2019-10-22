package deafult;

import controllers.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://195.150.230.210:5434///";
    static final String USER = "///";
    static final String PASS = "//";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainGui.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Mail Sender");
        primaryStage.getIcons().add(new Image("/icon/mailicon.png"));
        primaryStage.show();

        mainController mainController = (mainController) loader.getController();
        getUsers(mainController);

    }

    public static void main(String[] args) { launch(args); }

    private static void getUsers(mainController mainController){
        DatabaseManager databaseManager = new DatabaseManager();
        mainController.addNewUser(databaseManager.getUsers());
    }
}