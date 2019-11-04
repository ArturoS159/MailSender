package controllers;

import deafult.DatabaseManager;
import deafult.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class mainController {
    public TableView<Object> tab;
    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void selectTable() {
        if (!choiceBox.getSelectionModel().isEmpty()) {
            tab.getItems().clear();
            tab.getColumns().clear();
            tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            ObservableList rowsData = DatabaseManager.getRows(tab, choiceBox);
            for (Object rowsDatum : rowsData) {
                tab.getItems().add(rowsDatum);
            }
        }
    }

    @FXML
    private void loginDatabase() {
        if(DatabaseManager.checkConnectionDatabase(loginField.getText(),passwordField.getText())){
            Main.user = loginField.getText();
            Main.pass = passwordField.getText();
            choiceBox.getItems().clear();
            loginField.clear();
            passwordField.clear();
            ArrayList tableList = DatabaseManager.getTables();
            choiceBox.getItems().addAll(tableList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOGIN IN");
            alert.setHeaderText(null);
            alert.setContentText("CORRECT LOGIN AND PASSWORD");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LOGIN IN");
            alert.setHeaderText(null);
            alert.setContentText("INCORRECT LOGIN AND PASSWORD");
            alert.showAndWait();
        }
    }

    @FXML
    private void sendEmailGui() {
        if(!Main.user.equals("")&&!Main.pass.equals("")){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/sendMail.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Mail Sender");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image("/icon/mailicon.png"));
                stage.show();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please log in !");
            alert.showAndWait();
        }

    }
}
