package gui.Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Data.ModuleDeliveryDataModel;
import Framework.Managers.ModuleDeliveryManager;
import Framework.Managers.ModuleManager;
import Framework.Managers.UserManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import gui.Screens.ControlledScreen;
import gui.Screens.Main;
import gui.Screens.ScreensController;

/**
 * FXML Controller class
 *
 * @author Morten
 */
public class DeliveryController implements Initializable, ControlledScreen {
    
    @FXML
    ComboBox modulePicker;
    @FXML
    TextArea commentField;
    @FXML
    TextField fileName;
    @FXML
    ToggleButton fileButton;
    @FXML
    Label errorLabel;
    private Desktop desktop = Desktop.getDesktop();
    private int moduleID;

    private ModuleDeliveryManager moduleDeliveryManager = new ModuleDeliveryManager(); 
    private UserManager userManager = new UserManager();
    private ModuleManager moduleManager = new ModuleManager();

    private Main application;

    ScreensController myController;
    @FXML
    private Button cancel;
    @FXML
    private Button confirm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO-DO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void goToMain(ActionEvent event){
        commentField.setText("");
        fileName.setText("");
        modulePicker.setValue("");
        myController.setScreen(Main.studentMainID);
    }
    
    @FXML
    public void deliverModule(ActionEvent event) {   
        System.out.println(modulePicker.getValue() + " " + commentField.getText() + " " + fileName.getText() + " av " + UserType.username + " AKA UserID " + UserType.userID);
        try {
            String module = (String) modulePicker.getValue();
            /*if (modulePicker.getValue().equals("Modul 1")) {
                
            }*/
            String intString = module.replaceAll("[^0-9]", ""); // returns 123
            int moduleID = Integer.parseInt(String.valueOf(intString));
            System.out.println(moduleID);
            
            ModuleDeliveryDataModel moduleDeliveryModel = new ModuleDeliveryDataModel(this.userManager.getUserFromID(UserType.userID), this.moduleManager.getModule(moduleID), commentField.getText(), fileName.getText());

            this.moduleDeliveryManager.storeModuleDelivery(moduleDeliveryModel);
            this.errorLabel.setTextFill(Color.web("#13c113"));
            errorLabel.setText("Oppgavebesvarelse for " + modulePicker.getValue() + " levert");
        } catch (Exception e) {
            e.printStackTrace();
        }
        modulePicker.setValue("");
        commentField.setText("");
        fileName.setText("");
        
        myController.setScreen(Main.studentMainID);
        //myController.setScreen(Main.overlookID);
    }
    
    public void getPickerData() {
        
    }
    
    @FXML
    public void chooseFile() throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg en modulvideo (zippet)");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("RAR", "*.rar"),
                new FileChooser.ExtensionFilter("MP4", "*.mp4")
            );
        
        File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        String filepath = file.toString();
                        fileName.setText(filepath);
                    }
    }
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}