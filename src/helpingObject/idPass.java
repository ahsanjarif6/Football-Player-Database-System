package helpingObject;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class idPass implements Serializable {
    public String clubName;
    public String password;
    public boolean InAction;
    public List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public boolean isInAction() {
        return InAction;
    }

    public void setInAction(boolean inAction) {
        InAction = inAction;
    }

    public idPass(String username , String  password){
        this.clubName = username;
        this.password = password;
        this.setInAction(false);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
