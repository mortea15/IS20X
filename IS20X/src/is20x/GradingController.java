package is20x;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Morten
 */
public class GradingController implements Initializable, ControlledScreen {
    
    @FXML
    ComboBox studentPicker;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox modulePicker;
    @FXML
    ComboBox teacherPicker;
    @FXML
    Label errorLabel;

    private IS20X application;

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void goToMain(ActionEvent event){
        myController.setScreen(IS20X.mainID);
    }
    
    @FXML
    public void submitGrade(ActionEvent event) {
        String dbUsername = "root";
        String dbPassword = "0verwatch1.0";
        String dbURL = "jdbc:mysql://localhost:33306/uia";
        
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement stm = (Statement) conn.createStatement();
            String ssql = "SELECT userID FROM user WHERE name='" + teacherPicker.getValue() + "'";
            PreparedStatement pps = conn.prepareStatement(ssql);
            ResultSet rss = pps.executeQuery();
            String approverID = rss.getString("userID");
            Statement statement = (Statement) conn.createStatement();
            String sql = "SELECT userID FROM user WHERE username='" + studentPicker.getValue() + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                if (modulePicker.getValue().equals("Modul 1")) {
                    statement.execute("UPDATE approvals SET mod1='" + datePicker.getValue() + "', mod1Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 2")) {
                    statement.execute("UPDATE approvals SET mod2='" + datePicker.getValue() + "', mod2Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 3")) {
                    statement.execute("UPDATE approvals SET mod3='" + datePicker.getValue() + "', mod3Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 4")) {
                    statement.execute("UPDATE approvals SET mod4='" + datePicker.getValue() + "', mod4Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 5")) {
                    statement.execute("UPDATE approvals SET mod5='" + datePicker.getValue() + "', mod5Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 6")) {
                    statement.execute("UPDATE approvals SET mod6='" + datePicker.getValue() + "', mod6Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 7")) {
                    statement.execute("UPDATE approvals SET mod7='" + datePicker.getValue() + "', mod7Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 8")) {
                    statement.execute("UPDATE approvals SET mod8='" + datePicker.getValue() + "', mod8Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 9")) {
                    statement.execute("UPDATE approvals SET mod9='" + datePicker.getValue() + "', mod9Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 10")) {
                    statement.execute("UPDATE approvals SET mod10='" + datePicker.getValue() + "', mod10Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 11")) {
                    statement.execute("UPDATE approvals SET mod11='" + datePicker.getValue() + "', mod11Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 12")) {
                    statement.execute("UPDATE approvals SET mod12='" + datePicker.getValue() + "', mod12Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 13")) {
                    statement.execute("UPDATE approvals SET mod13='" + datePicker.getValue() + "', mod13Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                } else if (modulePicker.getValue().equals("Modul 14")) {
                    statement.execute("UPDATE approvals SET mod14='" + datePicker.getValue() + "', mod14Approver='" + approverID + "' WHERE studentID='" + userID + "';");
                }
                
            }
            
            errorLabel.setText(modulePicker.getValue() + " godkjent for " + studentPicker.getValue() + ".");
        } catch (SQLException e) {
            System.out.println(e);
        }
        myController.setScreen(IS20X.overlookID);
    }
}