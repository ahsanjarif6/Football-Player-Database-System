package sample;

import java.util.List;
import java.util.Scanner;

public class Show {

    public static void PlayerName() throws Exception {
        System.out.println("Input Player Name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        List<Player> temp = Search.ByPlayerName(name);
        if(temp.isEmpty()){
            System.out.println("No such player with this name");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void CountryClub() throws Exception {
        System.out.println("Input Country Name:");
        Scanner sc = new Scanner(System.in);
        String countryName = sc.nextLine();
        System.out.println("Input Club Name:");
        String clubName = sc.nextLine();
        List<Player> temp = Search.ByClubCountryName(countryName,clubName);
        if(temp.isEmpty()){
            System.out.println("No such player with this country and club");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void Position() throws Exception {
        System.out.println("Position:");
        Scanner sc = new Scanner(System.in);
        String positionName = null;
        while (true){
            System.out.println("Position:");
            positionName = sc.nextLine();
            if(Search.isPositionOk(positionName)) break;
            else {
                System.out.println("Invalid input, position can only be forward,midfielder,defender or goalkeeper");
            }
        }
        List<Player> temp = Search.ByPosition(positionName);
        if(temp.isEmpty()){
            System.out.println("No such player with this position");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void SalaryRange()  {
        System.out.println("Input two numbers as range:");
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        double lo = -1,hi = -1;
        System.out.println("Input lower bound on salary:");
        while (true){
            boolean f = false;
            while (!f){
                try {
                    lo = sc.nextDouble();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input");
                    System.out.println("Input lower bound on salary:");
                }
            }
            if(f) break;
        }
        System.out.println("Input higher bound on salary:");
        while (true){
            boolean f = false;
            while (!f){
                try {
                    hi = sc.nextDouble();
                    f = true;
                }
                catch (Exception e){
                    String g = sc.nextLine();
                    System.out.println("Invalid input");
                    System.out.println("Input higher bound on salary:");
                }
            }
            if(f) break;
        }
        List<Player> temp = Search.BySalaryRange(lo,hi);
        if(temp.isEmpty()){
            System.out.println("No such player with this weekly salary range");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void MaxSalary(){
        System.out.println("Input club name:");
        Scanner sc = new Scanner(System.in);
        String clubName = sc.nextLine();
        List<Player> temp = Search.ByMaxSalary(clubName);
        if(temp.isEmpty()){
            System.out.println("No such club with this name");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void MaxAge(){
        System.out.println("Input club name:");
        Scanner sc = new Scanner(System.in);
        String clubName =  sc.nextLine();
        List<Player> temp = Search.ByMaxAge(clubName);
        if(temp.isEmpty()){
            System.out.println("No such club with this name");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void MaxHeight(){
        System.out.println("Input a club name:");
        Scanner sc = new Scanner(System.in);
        String clubName = sc.nextLine();
        List<Player> temp = Search.ByMaxHeight(clubName);
        if(temp.isEmpty()){
            System.out.println("No such club with this name");
        }
        else{
            Print.PlayerInfo(temp);
        }
    }

    public static void YearlySalary(){
        System.out.println("Input a club name:");
        Scanner sc = new Scanner(System.in);
        String clubName = sc.nextLine();
        double z = Search.YearlySalary(clubName);
        if(z == -1){
            System.out.println("No such club with this name");
        }
        else {
            System.out.printf("Total Yearly Salary of %s : %f\n",clubName,z ) ;
        }
    }
}
