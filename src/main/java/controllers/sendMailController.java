package controllers;

import deafult.DatabaseManager;
import deafult.MailManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import java.util.ArrayList;


public class sendMailController {
    @FXML
    private VBox vBox;
    @FXML
    private CheckBox selectAllCheck;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField titleField;
    @FXML
    private HTMLEditor content;

    private ArrayList<CheckBox> listCheckBox;

    public void initialize(){
        listCheckBox = new ArrayList<>();
        addNewUser(DatabaseManager.getUsers());
    }

    private void addNewUser(ArrayList list){
        for (Object o : list) {
            CheckBox checkBox = new CheckBox(o.toString());
            listCheckBox.add(checkBox);
            vBox.getChildren().add(checkBox);
        }
    }

    @FXML
    public void selectAll() {
        if(selectAllCheck.isSelected()&&!vBox.getChildren().isEmpty()){
            for (CheckBox checkBox : listCheckBox) {
                checkBox.selectedProperty().setValue(true);
            }
        }else if(!selectAllCheck.isSelected()&&!vBox.getChildren().isEmpty()){
            for (CheckBox checkBox : listCheckBox) {
                checkBox.selectedProperty().setValue(false);
            }
        }
    }

    @FXML
    public void sendEmail() {
        if(listCheckBox.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("No emails selected!");
            alert.showAndWait();
        }else{
            int gl=0;
            for (CheckBox checkBox : listCheckBox) {
                if (checkBox.isSelected()) {
                    gl = 1;
                }
            }
            if(gl==1){
                for(int i=0;i<listCheckBox.size();i++){
                    if(listCheckBox.get(i).isSelected()){
                        if(MailManager.sendMail(titleField.getText(),content.getHtmlText(),listCheckBox.get(i).getText())){
                            titleField.clear();
                            content.setHtmlText("");
                            for (CheckBox checkBox : listCheckBox) {
                                checkBox.selectedProperty().setValue(false);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("INFORMATION");
                            alert.setHeaderText(null);
                            alert.setContentText("Mail sent!");
                            alert.showAndWait();
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("ERROR");
                            alert.showAndWait();
                        }
                    }
                }
            }
        }
    }
}
