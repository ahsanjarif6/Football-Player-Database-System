package sample;

import java.util.List;
import java.util.Scanner;

public class Input {

    public static int MainMenu(){
        Scanner sc = new Scanner(System.in);
        int x = -1;
        while (true){
            Print.MainMenu();
            boolean f = false;
            while (!f){
                try {
                    x = sc.nextInt();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, choose a number between 1 to 5");
                    Print.MainMenu();
                }
            }

            if(x == 1 || x == 2 || x == 3 || x == 4 || x == 5){
                break;
            }
            else System.out.println("Invalid input, choose a number between 1 to 5");
        }
        return x;
    }

    public static int SearchPlayers(){
        Scanner sc = new Scanner(System.in);
        int x = -1;
        while (true){
            Print.SearchPlayersMenu();
            boolean f = false;
            while (!f){
                try {
                    x = sc.nextInt();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, choose a number between 1 to 6");
                    Print.SearchPlayersMenu();
                }
            }

            if(x == 1 || x == 2 || x == 3 || x == 4 || x == 5 || x == 6){
                break;
            }
            else System.out.println("Invalid input, choose a number between 1 to 6");
        }
        return x;
    }

    public static int SearchClub(){
        Scanner sc = new Scanner(System.in);
        int x = -1;
        while (true){
            Print.SearchClubMenu();
            boolean f = false;
            while (!f){
                try {
                    x = sc.nextInt();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input,choose a number between 1 to 5");
                }
            }
            if(x == 1 || x == 2 || x == 3 || x == 4 || x == 5){
                break;
            }
            else System.out.println("Invalid input,choose a number between 1 to 5");
        }
        return x;
    }

    public static void addPlayer() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Information of Player:");
        System.out.println("Name:");             String playerName = sc.nextLine();
        System.out.println("Country:");          String countryName = sc.nextLine();
        System.out.println("Club:");             String clubName = sc.nextLine();
        int age = -1;
        while (true){
            System.out.println("Age:");
            boolean f = false;
            while (!f){
                try {
                    age = sc.nextInt();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, age should be an integer");
                }
            }
            if(age > 0){
                break;
            }
            else System.out.println("Invalid input, age should be positive");
        }
        int number = -1;
        while (true){
            System.out.println("Number:");
            boolean f = false;
            while (!f){
                try {
                    number = sc.nextInt();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, number should be an integer");
                }
            }
            if(number > 0){
                break;
            }
            else System.out.println("Invalid input, number should be positive");
        }
        sc.skip("\n");
        String  position = null;
        while (true){
            System.out.println("Position:");
            position = sc.nextLine();
            if(Search.isPositionOk(position)) break;
            else {
                System.out.println("Invalid input, position can only be forward,midfielder,defender or goalkeeper");
            }
        }
        double height = -1.0;
        while (true){
            System.out.println("Height:");
            boolean f = false;
            while (!f){
                try {
                    height = sc.nextDouble();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, height should be a floating point number");
                }
            }
            if(height > 0.0){
                break;
            }
            else System.out.println("Invalid input, height should be positive");
        }
        double WeeklySalary = -1.0;
        while (true){
            System.out.println("Weekly Salary:");
            boolean f = false;
            while (!f){
                try {
                    WeeklySalary = sc.nextDouble();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input, weekly salary should be a floating point number");
                }
            }
            if(WeeklySalary > 0.0){
                break;
            }
            else System.out.println("Invalid input, weekly salary should be positive");
        }
        sc.skip("\n");
        String category = null;
        while (true){
            System.out.println("Category:");
            category = sc.nextLine();
            if(category.equalsIgnoreCase("a") || category.equalsIgnoreCase("b") || category.equalsIgnoreCase("c") || category.equalsIgnoreCase("d") || category.equalsIgnoreCase("e")){
                break;
            }
            else{
                System.out.println("Invalid Input, category can be A,B,C,D,E");
            }
        }
        Player p = new Player();
        p.setName(playerName);
        p.setCountry(countryName);
        p.setClub(clubName);
        p.setAge(age);
        p.setNumber(number);
        p.setPosition(position);
        p.setHeight(height);
        p.setWeeklySalary(WeeklySalary);
        p.setCategory(category);
        if(Search.IfPlayerExists(playerName)){
            System.out.println("Cannot add player, player already exists!");
            return;
        }
        if(Search.ifClubIsFull(clubName)){
            System.out.println("Cannot add player, club is full!");
            return;
        }
        if(Search.ifPlayerNumberExists(clubName , number)){
            System.out.println("Cannot add player, player with this number already exists in  this club");
            return;
        }
        Search.addPlayer(p);
        Search.start();
    }

    public static void ByCategory(){
        Scanner sc = new Scanner(System.in);
        String category = null;

        while (true){
            System.out.println("Category:");
            category = sc.nextLine();
            if(category.equalsIgnoreCase("a") || category.equalsIgnoreCase("b") || category.equalsIgnoreCase("c") || category.equalsIgnoreCase("d") || category.equalsIgnoreCase("e")){
                break;
            }
            else{
                System.out.println("Invalid Input, category can be A,B,C,D,E");
            }
        }
        Search.sortByCategory(category);
    }
}
