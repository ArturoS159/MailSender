package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;


public class mainController {
    @FXML
    private VBox vBox;
    @FXML
    private CheckBox selectAllCheck;
    @FXML
    private AnchorPane pane;

    private ArrayList<CheckBox> listCheckBox;

    public void addNewUser(ArrayList list){
        listCheckBox = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CheckBox checkBox = new CheckBox(list.get(i).toString());
            listCheckBox.add(checkBox);
            vBox.getChildren().add(checkBox);
        }
    }

    @FXML
    public void selectAll(ActionEvent actionEvent) {
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
    public void sendEmail(MouseEvent mouseEvent) {
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
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No emails selected!");
            alert.showAndWait();
        }


    }

    @FXML
    public void connectDatabase(MouseEvent mouseEvent) {

    }
}
