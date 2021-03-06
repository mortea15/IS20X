/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Controllers;

import gui.Screens.ScreensController;
import gui.Screens.Main;
import gui.Screens.ControlledScreen;
import Data.UsersDataModel;
import Framework.Managers.UserManager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author Morten
 */
public class LoginController implements Initializable, ControlledScreen {
    
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label errorMessage;
    @FXML
    Button login;
    @FXML
    Label forgottenPassword;
    
    UserManager user = new UserManager();
    
    private Main application;

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    @FXML
    public void goToMain() {
        if(!this.password.getText().isEmpty() && !this.username.getText().isEmpty())
        {            
            UsersDataModel userModule = user.loginUser(this.username.getText(), this.password.getText());
            if(userModule.getUsername() != null)
            {
                this.errorMessage.setTextFill(Color.web("#13c113"));
                this.errorMessage.setText("Velkommen, " + username.getText());
                
                try 
                {
                    UserType currUser = new UserType(userModule.getUserrole(), userModule.getUsername(), userModule.getUserID());
                    System.out.println(currUser.getUserrole());
                    
                    if (!username.getText().equals(password.getText())) {
                        if (currUser.getUserrole() == 1 || currUser.getUserrole() == 2 || currUser.getUserrole() == 3) {
                            myController.setScreen(Main.teacherMainID);
                            username.setText("");
                            password.setText("");
                            //errorMessage.setText("");
                        
                        } else if (currUser.getUserrole() == 4) {
                            myController.setScreen(Main.studentMainID);
                            username.setText("");
                            password.setText("");
                            //errorMessage.setText("");
                        }
                    } else {
                        myController.setScreen(Main.firstLoginID);
                        username.setText("");
                        password.setText("");
                        //errorMessage.setText("");
                    }
                                        
                }
                catch(Exception e) 
                {
                    this.errorMessage.setTextFill(Color.web("#da0d0d"));
                    this.errorMessage.setText(e.getMessage()); 
                }
            }
            else 
            {
                this.errorMessage.setTextFill(Color.web("#da0d0d"));
                this.errorMessage.setText("Feil brukernavn/passord.");
            }
            
        }
        else 
        {
            this.errorMessage.setTextFill(Color.web("#da0d0d"));
            this.errorMessage.setText("Begge feltene m� fylles ut.");
        }
    }
    
    public void openResetPasswordDialog() {
        resetPasswordDialog();
    }
    
    protected Dialog resetPasswordDialog() {
        TextInputDialog emailDialog = new TextInputDialog("");
        emailDialog.setTitle("Tilbakestill passord");
        emailDialog.getDialogPane().setContentText("Epost:");
        emailDialog.setHeaderText("Vennligst fyll inn din epost (@student.uia.no)");
        emailDialog.showAndWait()
                .ifPresent(new Consumer<String>() {
            @Override
            public void accept(String response) {
                if (response.isEmpty()) {
                    System.out.println("Link ble ikke sendt");
                } else {
                    System.out.println("Epost med nytt passord sendt til " + response);
                }
            }
        });
        return emailDialog;
    }
}
