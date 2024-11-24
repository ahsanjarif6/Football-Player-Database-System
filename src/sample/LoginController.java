package sample;

import helpingObject.idPass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event)  {

        String userName = userText.getText();
        String password = passwordText.getText();

        idPass obj = new idPass(userName,password);
        System.out.println(userName + " " + password);
        try {
            //main.getNetworkUtil().write(obj);
            Main.networkUtil.write(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            main.showHomePage(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("OK write korsi");


        /*if (userName.equals("liverpool") && password.equals("123")) {
            try {
                main.showHomePage(userName);
                userText.setText(null);
                passwordText.setText(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (userName.equals("manchester city") && password.equals("123")) {
            try {
                main.showHomePage(userName);
                userText.setText(null);
                passwordText.setText(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (userName.equals("manchester united") && password.equals("123")) {
            try {
                main.showHomePage(userName);
                userText.setText(null);
                passwordText.setText(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (userName.equals("arsenal") && password.equals("123")) {
            try {
                main.showHomePage(userName);
                userText.setText(null);
                passwordText.setText(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (userName.equals("chelsea") && password.equals("123")) {
            try {
                main.showHomePage(userName);
                userText.setText(null);
                passwordText.setText(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            main.showAlert();
        }*/
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

}
