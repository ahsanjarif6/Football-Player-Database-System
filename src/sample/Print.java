package sample;

import java.util.List;

public class Print {

    public static void PlayerInfo(List<Player> temp){
        System.out.println("Player Info:");
        for(int i = 0; i < temp.size(); i++){
            Player p = temp.get(i);
            System.out.println("");
            System.out.println("Name:" + p.getName());
            System.out.println("Country:" + p.getCountry());
            System.out.println("Age:" + p.getAge());
            System.out.println("Height:" + p.getHeight());
            System.out.println("Club:" + p.getClub());
            System.out.println("Position:" + p.getPosition());
            System.out.println("Number" + p.getNumber());
            System.out.println("Weekly Salary:" + p.getWeeklySalary());
            System.out.println("Category:" + p.getCategory());
        }
        System.out.println("");
    }

    public static void MainMenu(){
        System.out.println("Main menu");
        System.out.println("(1)Search Players");
        System.out.println("(2)Search Clubs");
        System.out.println("(3)Add Player");
        System.out.println("(4)By Category");
        System.out.println("(5)Exit");
    }

    public static void SearchPlayersMenu(){
        System.out.println("Player Searching Options");
        System.out.println("(1)By Player Name");
        System.out.println("(2)By Club and Country");
        System.out.println("(3)By Position");
        System.out.println("(4)By Salary Range");
        System.out.println("(5)Country-wise player count");
        System.out.println("(6)Back to Main Menu");
    }


    public static void SearchClubMenu(){
        System.out.println("Club Searching Options");
        System.out.println("(1)Player(s) with the maximum salary of a club");
        System.out.println("(2)Player(s) with the maximum age of a club");
        System.out.println("(3)Player(s) with the maximum height of a club");
        System.out.println("(4)Total yearly salary of a club");
        System.out.println("(5)Back to Main Menu");
    }

}

