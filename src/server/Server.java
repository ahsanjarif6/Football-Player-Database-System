package server;

import sample.FileInfo;
import sample.Player;
import sample.Print;
import util.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, ClientInfo> clientMap;
    public static List<Player> playerList = new ArrayList<Player>();
    public static List<Player> auctionList = new ArrayList<Player>();
    public static int[] playersituation = new int[25];

    public List<Player> getPlayerList() {
        return playerList;
    }


    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("server.Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(networkUtil);
    }

    public static void readFromFile() throws Exception {
        playerList = FileInfo.readFromFile();
        for(int i = 0 ; i < 25 ; i ++ ){
            playersituation[i] = 1; // not sold yet
        }
    }


    public static void main(String args[]) throws Exception {

        readFromFile();
        System.out.println(playerList.size());
        for(Player p: playerList){
            System.out.println(p.getName());
        }
        Server server = new Server();
    }
}
