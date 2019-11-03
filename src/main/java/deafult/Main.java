package deafult;

import controllers.sendMailController;
import controllers.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://195.150.230.210:5434/2019_kowalski_artur";
    public static String user = "2019_kowalski_artur";
    public static String pass = "arczi30761";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainGui.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Mail Sender");
        primaryStage.getIcons().add(new Image("/icon/mailicon.png"));
        primaryStage.show();

        mainController mainController = loader.getController();
        //sendMailController.listCheckBox = new ArrayList<>();
       // sendMailController sendMailController = (sendMailController) loader.getController();

    }

    public static void main(String[] args) { launch(args); }

}