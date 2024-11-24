package sample;

import java.io.Serializable;

public class Player implements Serializable {
    String name;
    String country;
    String club;
    String position;
    int age;
    int number;
    double height;
    double weeklySalary;
    String category;

    public String getName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

    public String getClub(){
        return club;
    }

    public int getAge(){
        return age;
    }

    public String getPosition(){
        return position;
    }

    public int getNumber(){
        return number;
    }

    public double getHeight(){
        return height;
    }

    public double getWeeklySalary(){
        return weeklySalary;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setClub(String club){
        this.club = club;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeeklySalary(double weeklySalary){
        this.weeklySalary = weeklySalary;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
