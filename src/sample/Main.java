package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import server.Server;
import util.NetworkUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;

    public static NetworkUtil networkUtil;

    public static List<Player> players = new ArrayList<Player>();

    public static List<Player> auctionList = new ArrayList<Player>();

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    @Override
    public void start(Stage primaryStage)  {
        stage = primaryStage;

        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        try {
            networkUtil = new NetworkUtil(serverAddress, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }



        new readThread(this);
        try {
            showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(){
        try {
            players = FileInfo.readFromFile();
            Server.playerList = players; // maybe have to remove
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showLoginPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Football Manager");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();

        TableViewController controller = loader.getController();
        controller.load(userName );
        controller.setMain(this);
        controller.setClubName(userName);

        stage.setTitle(userName);
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void showMarketPage(String userName) throws Exception {

        System.out.println("showMarketPage e ashchi");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("market.fxml"));
        Parent root = loader.load();

        Market controller = loader.getController();
        controller.load(userName  );
        controller.setMain(this);

        stage.setTitle(userName);
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }


    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }

}
