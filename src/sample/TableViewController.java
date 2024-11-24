package sample;

import helpingObject.marketRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Server;
import helpingObject.playerSell;

import java.io.IOException;
import java.util.*;

public class TableViewController {

    private Main main;

    @FXML
    public Button viewButton;

    private Stage stage;

    @FXML
    public  TableView tableView;

    public ObservableList<Player> data;
    TableColumn<Player, String> firstNameCol;
    public String clubName;

    @FXML
    private Button button;

    private boolean init = true;

    public void setClubName(String clubName){
        this.clubName = clubName;
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
        firstNameCol = new TableColumn<>("Name");
        firstNameCol.setMinWidth(300);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));


        /*TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(80);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Person, String> buttonCol = new TableColumn<>("View");
        buttonCol.setMinWidth(80);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));*/

        tableView.getColumns().addAll(firstNameCol);
    }

    public void load(String username) throws Exception {
        //if (init) {
            initializeColumns();
            //init = false;
        //}

        List<Player> playerList = new ArrayList<>();
        //playerList = FileInfo.readFromFile();
        playerList = Main.players;

        data = FXCollections.observableArrayList();
        //data.clear();
        for(Player p : playerList){
            if(p.getClub().equalsIgnoreCase(username)){
                data.add(p);
            }
        }

        tableView.setEditable(true);
        tableView.setItems(data);
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
        Label label3 = new Label();
        label3.setText("Club: " + p.getClub());
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
        layout.getChildren().addAll( label1,label2,label3,label4,label5,label6,label7,label8);
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

    public void searchPlayer(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        Label label = new Label("Player name:");
        TextField textField = new TextField();
        textField.setPromptText("Player name");
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            String s = textField.getText();
            stage.close();
            if(s.isEmpty()){
                noSuchPlayer();
            }
            else{
                showPlayer(s);
            }
        });
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label , textField , button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showPlayer(String s){
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getName().equalsIgnoreCase(s)){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void searchCountry(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        Label label = new Label("Country name:");
        TextField textField = new TextField();
        textField.setPromptText("Country name");
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            String s = textField.getText();
            stage.close();
            if(s.isEmpty()){
                noSuchPlayer();
            }
            else{
                showCountry(s);
            }
        });
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label , textField , button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showCountry(String s){
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getCountry().equalsIgnoreCase(s)){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void searchPosition(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        Label label = new Label("Position:");
        TextField textField = new TextField();
        textField.setPromptText("Position");
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            String s = textField.getText();
            stage.close();
            if(s.isEmpty()){
                noSuchPlayer();
            }
            else{
                showPosition(s);
            }
        });
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label , textField , button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showPosition(String s){
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getPosition().equalsIgnoreCase(s)){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void searchRange(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        Label label1 = new Label("Low range:");
        TextField textField1 = new TextField();
        textField1.setPromptText("Low range");
        Label label2 = new Label("High range:");
        TextField textField2 = new TextField();
        textField2.setPromptText("High range");
        Button button = new Button("Submit");
        button.setOnAction(e -> {
            String s1 = textField1.getText();
            String s2 = textField2.getText();
            if(s1.isEmpty() || s2.isEmpty()){
                noSuchPlayer();
            }
            else{
                showRange(s1 , s2);
            }
        });
        HBox hBox1 = new HBox(10);
        hBox1.getChildren().addAll(label1,textField1);
        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(label2,textField2);
        VBox layout = new VBox(15);
        layout.getChildren().addAll(hBox1,hBox2,button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showRange(String s1,String s2){
        double low,high;
        try {
            low = Double.parseDouble(s1);
            high = Double.parseDouble(s2);
            ArrayList<Player> players = new ArrayList<>();
            for(Player p : data){
                if(p.getWeeklySalary() >= low && p.getWeeklySalary() <= high ){
                    players.add(p);
                }
            }
            if(players.isEmpty()){
                noSuchPlayer();
            }
            else{
                playerInfo(players);
            }
        }
        catch (Exception e){
            noSuchPlayer();
            System.out.println(e);
        }
    }

    public void searchCountryCount(ActionEvent actionEvent) {
        HashMap<String, Integer> cnt = new HashMap<>();
        for(Player p: data){
            boolean f = false;
            for(String s: cnt.keySet()){
                if(s.equalsIgnoreCase(p.getCountry())){
                    f = true;
                    break;
                }
            }
            if(f){
                int c = cnt.get(p.getCountry().toLowerCase(Locale.ROOT));
                cnt.put(p.getCountry().toLowerCase(Locale.ROOT) , c + 1 );
            }
            else cnt.put(p.getCountry().toLowerCase(Locale.ROOT) , 1);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        HBox layout = new HBox(10);
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        vBox.getChildren().add(new Label("Country"));
        vBox1.getChildren().add(new Label("Count"));
        for(String s : cnt.keySet()){
            Label label = new Label(s);
            Label label1 = new Label(cnt.get(s).toString());
            vBox.getChildren().addAll(label);
            vBox1.getChildren().addAll(label1);
        }
        layout.getChildren().addAll(vBox,vBox1);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void searchMaxSalary(ActionEvent actionEvent) {
        double maxSalary = -1000000;
        for(Player p : data){
            maxSalary = Math.max(maxSalary , p.getWeeklySalary());
        }
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getWeeklySalary() == maxSalary){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void searchMaxAge(ActionEvent actionEvent) {
        int maxAge = -100000;
        for(Player p : data){
            maxAge = Math.max(maxAge , p.getAge());
        }
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getAge() == maxAge){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void searchMaxHeight(ActionEvent actionEvent) {
        double maxHeight = -100000;
        for(Player p : data){
            maxHeight = Math.max(maxHeight , p.getHeight());
        }
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : data){
            if(p.getHeight() == maxHeight){
                players.add(p);
            }
        }
        if(players.isEmpty()){
            noSuchPlayer();
        }
        else{
            playerInfo(players);
        }
    }

    public void showYearlySalary(ActionEvent actionEvent) {
        double yearlySalary = 0.0;
        for(Player p :data){
            yearlySalary += (p.getWeeklySalary()*365.0) / 7.0;
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        Label label = new Label();
        String s = "The yearly salary of this club is : "  + yearlySalary;
        label.setText(s);
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void noSuchPlayer(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        Label label = new Label("No such player found!");
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void playerInfo(ArrayList<Player> players){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Information");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        VBox layout = new VBox(15);
        for(Player p : players){
            Label label = new Label(p.getName());
            Button button = new Button("View");
            button.setOnAction(e -> showAlert(p));
            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(label , button);
            layout.getChildren().addAll(hBox);
        }
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void sellPlayer(ActionEvent actionEvent) throws Exception {
        ObservableList<Player>  selectedPlayer ;
        selectedPlayer = tableView.getSelectionModel().getSelectedItems();
        //Player p = selectedPlayer.get(0);
        //System.out.println(p.getName());
        playerSell playerSell = new playerSell(selectedPlayer.get(0));
        //playerSell.player = selectedPlayer.get(0);
        Main.networkUtil.write(playerSell);
    }

    public void showMarket(ActionEvent actionEvent) throws Exception {
        ///System.out.println("Ami sakib");
        marketRequest marketRequest = new marketRequest(clubName);
        Main.networkUtil.write(marketRequest);
    }
}
