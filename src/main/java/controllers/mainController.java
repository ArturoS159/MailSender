package controllers;

import deafult.DatabaseManager;
import deafult.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;


public class mainController {
    @FXML
    private VBox vBox;
    @FXML
    private CheckBox selectAllCheck;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public static ArrayList<CheckBox> listCheckBox;

    public void addNewUser(ArrayList list){
        for(int i=0;i<list.size();i++){
            CheckBox checkBox = new CheckBox(list.get(i).toString());
            listCheckBox.add(checkBox);
            vBox.getChildren().add(checkBox);
        }
    }

    @FXML
    public void selectAll() {
        if(selectAllCheck.isSelected()&&!vBox.getChildren().isEmpty()){
            for(int i=0;i<listCheckBox.size();i++) {
                listCheckBox.get(i).selectedProperty().setValue(true);
            }
        }else if(!selectAllCheck.isSelected()&&!vBox.getChildren().isEmpty()){
            for(int i=0;i<listCheckBox.size();i++){
                listCheckBox.get(i).selectedProperty().setValue(false);
            }
        }
    }

    @FXML
    public void sendEmail() {
        if(listCheckBox.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No emails selected!");
            alert.showAndWait();
        }else{
            int gl=0;
            for(int i=0;i<listCheckBox.size();i++){
                if(listCheckBox.get(i).isSelected()){
                    gl=1;
                }
            }
            if(gl==1){
                for(int i=0;i<listCheckBox.size();i++){
                    if(listCheckBox.get(i).isSelected()){
                        System.out.println(listCheckBox.get(i).getText());
                    }
                }
            }
        }
    }

    @FXML
    public void connectDatabase() {
        if(DatabaseManager.checkConnectionDatabase(loginField.getText(),passwordField.getText())){
            listCheckBox.clear();
            vBox.getChildren().clear();
            Main.user = loginField.getText();
            Main.pass = passwordField.getText();
            loginField.clear();
            passwordField.clear();
            addNewUser(DatabaseManager.getUsers());
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
}
