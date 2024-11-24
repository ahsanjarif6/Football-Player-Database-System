package sample;

import helpingObject.idPass;
import helpingObject.marketRequest;
import helpingObject.playerBuy;
import helpingObject.playerSell;
import javafx.application.Platform;

import java.io.IOException;

class readThread implements Runnable{
    public Thread thread;
    public Main main;

    public readThread(Main main) {
        this.main = main;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true){
            Object o = null;
            try {
                //o = main.getNetworkUtil().read();
                o = Main.networkUtil.read();
                System.out.println("Reading from server");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (o != null) {
                if (o instanceof idPass) {
                    System.out.println("Madfaka");
                    idPass loginDTO = (idPass) o;
                    System.out.println(loginDTO.getClubName());
                    System.out.println(loginDTO.isInAction());
                    // the following is necessary to update JavaFX UI components from user created threads
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (loginDTO.isInAction()) {
                                try {
                                    //Main.players=loginDTO.getClubSpecified();
                                    Main.players = loginDTO.getPlayers();
                                    System.out.println("Hoga " + Main.players.size());
                                    System.out.println("ses korsi");
                                    main.showHomePage(loginDTO.getClubName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                main.showAlert();
                            }

                        }
                    });
                }
                else if (o instanceof playerSell) {
                    //System.out.println("Madfaka");
                    playerSell login = (playerSell) o;
                    //System.out.println(loginDTO.getClubName());
                    //System.out.println(loginDTO.isInAction());
                    // the following is necessary to update JavaFX UI components from user created threads
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //if (loginDTO.isInAction()) {
                                try {
                                    //Main.players=loginDTO.getClubSpecified();
                                    Main.players = login.playerList;
                                    //System.out.println("Hoga " + Main.players.size());
                                    //System.out.println("ses korsi");
                                    System.out.println("sell er por Print ditesi");
                                    for(Player p : Main.players){
                                        System.out.println(p.getName());
                                    }
                                    main.showHomePage(login.player.getClub());
                                    //TableViewController.refresh(Main.players);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            //} else {
                                //main.showAlert();
                            //}

                        }
                    });
                }
                else if (o instanceof marketRequest) {
                    //System.out.println("Madfaka");
                    marketRequest object = (marketRequest) o;
                    //System.out.println(loginDTO.getClubName());
                    //System.out.println(loginDTO.isInAction());
                    // the following is necessary to update JavaFX UI components from user created threads
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //if (loginDTO.isInAction()) {
                                try {
                                    //Main.players=loginDTO.getClubSpecified();
                                    Main.auctionList = object.playerList;
                                    System.out.println("Auction List print korchi");
                                    for(Player p : Main.auctionList){
                                        System.out.println(p.getName());
                                    }
                                    //System.out.println("Hoga " + Main.players.size());
                                    //System.out.println("ses korsi");
                                    //main.showHomePage(loginDTO.getClubName());
                                    main.showMarketPage(object.clubName);
                                    //Market.refresh(Main.auctionList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            //} else {
                            //    main.showAlert();
                            //}

                        }
                    });
                }
                else if (o instanceof playerBuy) {
                    //System.out.println("Madfaka");
                    //idPass loginDTO = (idPass) o;
                    playerBuy object = (playerBuy) o;
                    //System.out.println(loginDTO.getClubName());
                    //System.out.println(loginDTO.isInAction());
                    // the following is necessary to update JavaFX UI components from user created threads
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //if (loginDTO.isInAction()) {
                                try {
                                    Main.players = object.playerList;
                                    main.showHomePage(object.clubName);
                                    //Main.players=loginDTO.getClubSpecified();
                                    //Main.players = loginDTO.getPlayers();
                                    //System.out.println("Hoga " + Main.players.size());
                                    //System.out.println("ses korsi");
                                    //main.showHomePage(loginDTO.getClubName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            //} else {
                            //    main.showAlert();
                            //}

                        }
                    });
                }
            }
        }
    }


}