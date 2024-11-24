package server;

import helpingObject.idPass;
import helpingObject.marketRequest;
import helpingObject.playerBuy;
import sample.Main;
import util.NetworkUtil;
import sample.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import helpingObject.playerSell;
//import network.dto.*;

public class ReadThreadServer implements Runnable{
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThreadServer(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        System.out.println("Read thread server start hoitese");
        thr.start();
    }


    public void run()
    {
        try{
            while(true)
            {
                Object o = networkUtil.read();
                if(o instanceof  idPass){
                    List<Player> tempList = new ArrayList<>();
                    idPass serverObject = (idPass) o;
                    String tempString = serverObject.getClubName();
                    boolean tempBoolean = false;
                    for(Player p : Server.playerList){
                        if(p.getClub().equalsIgnoreCase(tempString)){
                            serverObject.setInAction(true);
                            tempBoolean = true;
                            break;
                        }
                    }

                    if(tempBoolean){
                        for(Player p : Server.playerList){
                            if(p.getClub().equalsIgnoreCase(tempString)){
                                tempList.add(p);
                            }
                        }
                        serverObject.setPlayers(tempList);
                    }
                    networkUtil.write(o);
                }
                else if(o instanceof playerSell){
                    playerSell playerSell = (playerSell) o;
                    List<Player> tempList = new ArrayList<Player>();
                    int  i  = 0 ;
                    for(Player p : Server.playerList){
                        if(p.getName().equalsIgnoreCase(playerSell.player.getName())){
                            Server.playersituation[i] = 0;
                        }
                        i ++ ;
                    }
                    Server.auctionList.add(playerSell.player);
                    System.out.println("Haga asche");
                    for(Player p : Server.auctionList){
                        System.out.println(p.getName());
                    }
                    i = 0;
                    for(Player p : Server.playerList){
                        if(Server.playersituation[i] == 1 && !(p.getName().equalsIgnoreCase(playerSell.player.getName())) && playerSell.player.getClub().equalsIgnoreCase(p.getClub())){
                            tempList.add(p);
                        }
                        i ++ ;
                    }
                    playerSell.playerList = tempList;
                    networkUtil.write(o);
                }
                else if(o instanceof marketRequest){
                    marketRequest object = (marketRequest) o;
                    object.playerList = Server.auctionList;
                    System.out.println(object.clubName + " wanted to see the marketplace" );
                    for(Player p : object.playerList){
                        System.out.println(p.getName());
                    }
                    networkUtil.write(o);
                }
                else if(o instanceof playerBuy){
                    playerBuy object = (playerBuy) o;
                    boolean found = false;
                    String playerName = object.player.getName();
                    String clubName = object.clubName;
                    List<Player> playerList = new ArrayList<Player>();
                    for(Player p : Server.auctionList){
                        if(p.getName().equalsIgnoreCase(playerName)){
                            found = true;
                            break;
                        }
                    }
                    if(found){
                        for(int i = Server.auctionList.size() - 1 ; i >= 0 ; i -- ){
                            Player p = Server.auctionList.get(i);
                            if(p.getName().equalsIgnoreCase(playerName)){
                                Server.auctionList.remove(i);
                                break;
                            }
                        }
                        System.out.println("Auction list theke remove korar por print kortsei");
                        for(Player p : Server.auctionList){
                            System.out.println(p.getName());
                        }
                        //server player situation
                        int  i  = 0 ;
                        for(Player p : Server.playerList){
                            if(p.getName().equalsIgnoreCase(playerName)){
                                Server.playersituation[i] = 1;
                                break;
                            }
                            i ++ ;
                        }
                        i = 0;
                        for(i = 0 ; i < Server.playerList.size() ; i ++ ){
                            if(Server.playerList.get(i).getName().equalsIgnoreCase(playerName)){
                                Server.playerList.get(i).setClub(clubName);
                            }
                        }
                        System.out.println("Server playerList list theke remove korar por print kortsei");
                        for(Player p : Server.playerList){
                            System.out.println(p.getName() + " " + p.getClub());
                        }
                    }
                    int i = 0 ;
                    for(Player p : Server.playerList){
                        if( (p.getClub().equalsIgnoreCase(clubName)) && Server.playersituation[i] == 1){
                            playerList.add(p);
                        }
                        i ++ ;
                    }
                    object.playerList = playerList;
                    networkUtil.write(o);
                }

                /*if(o instanceof idPass) {
                    List<Player> forSend = new ArrayList<Player>();
                    idPass serverobj = (idPass) o;
                    String temp = serverobj.getClubName();
                    boolean tempo = false;
                    System.out.println(serverobj.getClubName());
                    System.out.println(serverobj.getPassword());
                    System.out.println(Server.playerListServer.size());
                    for (Player P : Server.playerListServer) {
                        P.printPlayer();
                        if (P.getClub().equalsIgnoreCase(temp)) {
                            serverobj.setInAction(true);
                            tempo = true;
                            System.out.println("Paisi" + temp);
                            break;
                        }
                    }
                    if (tempo) {

                        for (Player P : Server.playerListServer) {
                            if (P.getClub().equalsIgnoreCase(temp)) {
                                forSend.add(P);
                            }
                        }
                        for(Player P:forSend)
                        {
                            P.printPlayer();
                        }
                        serverobj.setMiddleman(forSend);

                    }
                    networkUtil.write(o);
                }
                else if(o instanceof Player)
                {
                    System.out.println("Kisu ekta paisi");
                    Player temp =(Player)o;
                    Server.auctionPlayerList.add(temp);
                    for(Player P:Server.auctionPlayerList)
                    {
                        P.printPlayer();
                    }
                }*/

            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}