package sample;

import java.util.*;

public class Search {
    private static List<Player> playerList;

    public static void start() throws Exception {
        playerList = FileInfo.readFromFile();
    }

    public static List<Player> ByPlayerName(String name)  {
        List<Player> temp = new ArrayList<>();
        for(int i = 0;i < playerList.size();i++){
            Player p = playerList.get(i);
            if(p.getName().equalsIgnoreCase(name)){
                temp.add(p);
            }
        }
        return  temp;
    }

    public static List<Player> ByClubCountryName(String countryName,String clubName)  {
        List<Player> temp = new ArrayList<>();
        if(clubName.equalsIgnoreCase("any")){
            for(int i = 0;i < playerList.size() ;i++){
                Player p = playerList.get(i);
                if(p.getCountry().equalsIgnoreCase(countryName)){
                    temp.add(p);
                }
            }
        }
        else{
            for(int i = 0; i < playerList.size() ; i++){
                Player p = playerList.get(i);
                if(p.getCountry().equalsIgnoreCase(countryName) && p.getClub().equalsIgnoreCase(clubName)){
                    temp.add(p);
                }
            }
        }
        return temp;
    }

    public static List<Player> ByPosition(String positionName)  {
        List<Player> temp = new ArrayList<>();
        for(int i = 0;i < playerList.size() ; i++){
            Player p = playerList.get(i);
            if(p.getPosition().equalsIgnoreCase(positionName)){
                temp.add(p);
            }
        }
        return temp;
    }

    public static List<Player> BySalaryRange(double lo , double hi)  {
        List<Player> temp = new ArrayList<>();
        for(int i = 0; i < playerList.size() ; i++){
            Player p = playerList.get(i);
            if(p.getWeeklySalary() >=  lo && p.getWeeklySalary() <= hi){
                temp.add(p);
            }
        }
        return temp;
    }

    public static void ByCountry(){
        HashMap<String, Integer> cnt = new HashMap<String,Integer>();
        for(Player p: playerList){
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
        for(String s: cnt.keySet()){
            System.out.println(s + " " + cnt.get(s));
        }
    }

    public static List<Player> ByMaxSalary(String clubName){
        List<Player> temp = new ArrayList<>();
        double max = -9000000;
        for(Player p : playerList){
            if(p.getClub().equalsIgnoreCase(clubName)){
                if(p.getWeeklySalary() > max){
                    max = p.getWeeklySalary();
                }
            }
        }
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName) && p.getWeeklySalary() == max){
                temp.add(p);
            }
        }
        return temp;
    }

    public static List<Player> ByMaxAge(String clubName){
        List<Player> temp = new ArrayList<>();
        int max = -900000;
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName)){
                if(p.getAge() > max){
                    max = p.getAge();
                }
            }
        }
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName) && p.getAge() == max){
                temp.add(p);
            }
        }
        return temp;
    }

    public static List<Player> ByMaxHeight(String clubName){
        List<Player> temp = new ArrayList<>();
        double max = -900000;
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName)){
                if(p.getHeight() > max){
                    max = p.getHeight();
                }
            }
        }
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName) && p.getHeight() == max){
                temp.add(p);
            }
        }
        return temp;
    }

    public static double YearlySalary(String clubName){
        double cnt = 0.0;
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName)){
                cnt += p.getWeeklySalary()*(365.0/7.0);
            }
        }
        if(cnt > 0.0) return cnt;
        return -1;
    }

    public static boolean IfPlayerExists(String name){
        boolean found = false;
        for(Player p: playerList){
            if(p.getName().equalsIgnoreCase(name)){
                found = true;
            }
        }
        return found;
    }

    public static boolean ifClubIsFull(String clubName){
        int cnt = 0;
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName)){
                cnt++;
            }
        }
        if(cnt == 7) return true;
        return false;
    }

    public static void addPlayer(Player p) throws Exception {
        playerList.add(p);
        FileInfo.writeToFile(playerList);
    }

    public static boolean ifPlayerNumberExists(String clubName,int number){
        boolean found = false;
        for(Player p: playerList){
            if(p.getClub().equalsIgnoreCase(clubName) && p.getNumber() == number){
                found = true;
            }
        }
        return found;
    }

    public static boolean isPositionOk(String position){
        if(position.equalsIgnoreCase("forward") || position.equalsIgnoreCase("midfielder") || position.equalsIgnoreCase("defender") || position.equalsIgnoreCase("goalkeeper")){
            return true;
        }
        return false;
    }

    public static void sortByCategory(String Category)
    {
        boolean f = false;
        List<Player> temp= new ArrayList<Player>();
        for(Player P : playerList)
        {
            if(P.getCategory().equalsIgnoreCase(Category))
            {
                f = true;
                temp.add(P);
            }
        }

        if(f == false)
        {
            System.out.println("No Player found with this category");
            return;
        }

        int i,j;

        for(i=0; i<temp.size(); i++)
        {
            for(j=i+1; j<temp.size(); j++)
            {
                if(temp.get(i).getWeeklySalary()<temp.get(j).getWeeklySalary())
                {
                    Player P1 = temp.get(i);
                    Player P2 = temp.get(j);
                    temp.set(j, P1);
                    temp.set(i, P2);
                }
            }
        }

        Print.PlayerInfo(temp);
    }

}
