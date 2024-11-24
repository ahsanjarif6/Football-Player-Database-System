package sample;

import helpingObject.marketRequest;
import helpingObject.playerBuy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Server;

import java.io.IOException;
import java.util.*;

public class Market {

    private Main main;

    @FXML
    public Button viewButton;

    private Stage stage;

    @FXML
    public TableView tableView;

    public String clubName;

    public ObservableList<Player> data;
    TableColumn<Player, String> firstNameCol;

    @FXML
    private Button button;

    private boolean init = true;


    public void setClubName(String clubName){
        this.clubName = clubName;
        System.out.println("club er name "  + this.clubName + " keno print holo na?");
    }

    public void setMain(Main main){
        this.main = main;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    //public TableView getTableView() {
    //    return tableView;
    //}

    private void initializeColumns() {
        System.out.println("column e jhamela");
        this.firstNameCol = new TableColumn<>("Name");
        this.firstNameCol.setMinWidth(300);
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));


        /*TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(80);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Person, String> buttonCol = new TableColumn<>("View");
        buttonCol.setMinWidth(80);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));*/
        System.out.println("Column er majhe jhamela");
        this.tableView.getColumns().addAll(this.firstNameCol);
        System.out.println("Column er shesh e jhamela");
    }

    public void load(String username ) throws Exception {
        //if (init) {
            System.out.println("Initialize hoitese");
            System.out.println(clubName);
            initializeColumns();
            //init = false;
        //}
        setClubName(username);
        System.out.println("Tahole somossa koi ?");
        List<Player> playerList = new ArrayList<>();
        if(Main.auctionList == null){
            System.out.println("Null");
        }
        if(Main.auctionList.isEmpty()){
            System.out.println("Empty eita");
        }
        for(Player p: Main.auctionList){
            if( !(p.getClub().equalsIgnoreCase(clubName)) ){
                System.out.println("Loop er vitore");
                System.out.println(p.getName());
                playerList.add(p);
            }
        }

        System.out.println("Print hoy nai ken?");
        data = FXCollections.observableArrayList();
        //data.clear();
        for(Player p : playerList){
            //if(p.getClub().equalsIgnoreCase(username)){
            System.out.println(p.getName());
            data.add(p);
            //}
        }

        tableView.setEditable(true);
        tableView.setItems(data);
        System.out.println("sob e hoise");
        System.out.println("Ei club er nam " + clubName);
    }



    public void logOut(ActionEvent actionEvent ) {
        //this.stage.close();
        try {
            main.showLoginPage();
        } catch (Exception e) {
            System.out.println("Hoga");
            //e.printStackTrace();
        }
    }

    public void showAlert(Player p) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(300);
        stage.setMinWidth(300);

        Label label1 = new Label();
        label1.setText("Name: " + p.getName());
        Label label2 = new Label();
        label2.setText("Country: " + p.getCountry());
        //Label label3 = new Label();
        ///label3.setText("Club: " + p.getClub());
        Label label4 = new Label();
        label4.setText("Position: " + p.getPosition());
        Label label5 = new Label();
        label5.setText("Age: " + p.getAge());
        Label label6 = new Label();
        label6.setText("Number: " + p.getNumber());
        Label label7 = new Label();
        label7.setText("Height: " + p.getHeight());
        Label label8 = new Label();
        label8.setText("Weekly Salary: " + p.getWeeklySalary());

        VBox layout = new VBox(15);
        layout.getChildren().addAll( label1,label2,label4,label5,label6,label7,label8);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void viewInfo(ActionEvent actionEvent) {
        ObservableList<Player>  selectedPlayer ;
        selectedPlayer = tableView.getSelectionModel().getSelectedItems();
        showAlert(selectedPlayer.get(0));
    }


    public void goBack(ActionEvent actionEvent) throws Exception {
        main.showHomePage(clubName);
    }

    public void buyPlayer(ActionEvent actionEvent) throws IOException {
        ObservableList<Player>  selectedPlayer ;
        selectedPlayer = tableView.getSelectionModel().getSelectedItems();
        Player p = selectedPlayer.get(0);
        playerBuy object = new playerBuy();
        object.player = p;
        object.clubName = this.clubName;
        Main.networkUtil.write(object);
    }

    public void refresh(ActionEvent actionEvent) throws Exception {
        //main.showHomePage(clubName);
        marketRequest marketRequest = new marketRequest(clubName);
        Main.networkUtil.write(marketRequest);
    }
}
